/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.loginsight.inbound;

import java.util.concurrent.ExecutionException;

import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.integration.loginsight.core.LogInsightTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.ListenableFuture;

import com.vmware.loginsightapi.core.IngestionRequest;
import com.vmware.loginsightapi.core.IngestionResponse;

/**
 * Handle message and write data into LogInsight
 *
 * @author Kalyan Venu Gopal Abbaraju
 * @since 1.0
 *
 */
public class LogInsightInboundChannelAdapter extends AbstractReplyProducingMessageHandler {

	private final LogInsightTemplate logInsightTemplate;
//	private String sourceType;
//	private String source;
//	private String agentId;
//	private String port;
	private boolean producesReply = true;   // false for outbound-channel-adapter,
											// true for outbound-gateway

	/**
	 * Constructor taking an {@link LogInsightTemplate} that wraps common LogInsight
	 * Operations.
	 *
	 * @param logInsightTemplate
	 *            Must not be null
	 *
	 */
	public LogInsightInboundChannelAdapter(LogInsightTemplate logInsightTemplate) {
		Assert.notNull(logInsightTemplate, "LogInsightTemplate must not be null.");
		this.logInsightTemplate = logInsightTemplate;
	}

	@Override
	protected Object handleRequestMessage(Message<?> requestMessage) {
		final IngestionResponse result;
		final ListenableFuture<IngestionResponse> future;
		future = this.logInsightTemplate.send((IngestionRequest) requestMessage.getPayload());
		try {
			result = future.get();
			if (result == null || !producesReply) {
				return null;
			}
			return MessageBuilder.withPayload(future).copyHeaders(requestMessage.getHeaders()).build();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new MessageHandlingException(requestMessage, e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new MessageHandlingException(requestMessage, e);
		}

	}

	/**
	 * If set to 'false', this component will act as an Outbound Channel
	 * Adapter. If not explicitly set this property will default to 'true'.
	 *
	 * @param producesReply
	 *            Defaults to 'true'.
	 *
	 */
	public void setProducesReply(boolean producesReply) {
		this.producesReply = producesReply;
	}
	
	public LogInsightTemplate getLogInsightTemplate() {
		return this.logInsightTemplate;
	}

}
