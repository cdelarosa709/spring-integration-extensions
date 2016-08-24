/*
 * Copyright 2011-2012 the original author or authors.
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
package org.springframework.integration.loginsight.config.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.loginsight.core.LogInsightTemplate;
import org.springframework.integration.loginsight.outbound.LogInsightOutboundChannelAdapter;
import org.springframework.integration.loginsight.support.LogInsightServer;
import org.springframework.integration.test.util.TestUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmware.loginsightapi.IngestionRequestBuilder;
import com.vmware.loginsightapi.ConstraintBuilder;
import com.vmware.loginsightapi.MessageQuery;
import com.vmware.loginsightapi.core.FieldConstraint;
import com.vmware.loginsightapi.core.IngestionRequest;
import com.vmware.loginsightapi.core.Message;
import com.vmware.loginsightapi.core.MessageQueryResponse;

/**
 * @author Kalyan Venu Gopal Abbaraju
 * @since 1.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LogInsightOutboundChannelAdapterParserTests {

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private MessageChannel outputToLogInsight;

	/**
	 * Test method for
	 * {@link org.springframework.integration.loginsight.config.xml.LogInsightOutboundChannelAdapterParser#parseConsumer(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)}
	 * .
	 */
	@Test
	public void testParseConsumerElementParserContext() {
		LogInsightOutboundChannelAdapter adapter = TestUtils.getPropertyValue(
				appContext.getBean("logInsightOutboundChannelAdapter"), "handler",
				LogInsightOutboundChannelAdapter.class);
		assertNotNull(adapter);
		assertEquals("logInsightOutboundChannelAdapter", TestUtils.getPropertyValue(adapter, "componentName"));
		assertEquals(appContext.getBean("logInsightTemplate"),
				TestUtils.getPropertyValue(adapter, "logInsightTemplate"));
		LogInsightTemplate template = adapter.getLogInsightTemplate();
		LogInsightServer server = template.getServer();
		assertEquals("admin", server.getUsername());
		List<Message> messages = new ArrayList<Message>();
		Message msg1 = new Message("First message");
		msg1.addField("test_field_1", "test_content_11");
		Message msg2 = new Message("First message");
		msg2.addField("test_field_1", "test_content_12");
		messages.add(msg1);
		messages.add(msg1);
		IngestionRequest ingestionRequest = new IngestionRequestBuilder().messages(messages).build();
		System.out.println("sending ingestionRequest json " + ingestionRequest.toJson());
		org.springframework.messaging.Message<IngestionRequest> message = MessageBuilder.withPayload(ingestionRequest).build();
		outputToLogInsight.send(message);
		
		List<FieldConstraint> constraints = new ConstraintBuilder().contains("test_field_1", "test_content_11").gt("timestamp", "0").build();
		MessageQuery mqb = (MessageQuery) new MessageQuery().limit(100).setConstraints(constraints);
		System.out.println("MQB " + mqb.toUrlString());
		org.springframework.messaging.Message<MessageQuery> mqbMessage = MessageBuilder.withPayload(mqb).build();
		outputToLogInsight.send(mqbMessage);
//		Assert.assertTrue("Invalid number of messages", messages.getEvents().size() <= 100);
		
	}

}
