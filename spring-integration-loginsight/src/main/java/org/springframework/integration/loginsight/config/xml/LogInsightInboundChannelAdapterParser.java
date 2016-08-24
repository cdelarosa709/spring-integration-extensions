/*
 * Copyright 2002-2014 the original author or authors.
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

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.AbstractPollingInboundChannelAdapterParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * The LogInsight Inbound Channel adapter parser
 *
 * @author Jarred Li
 * @author Olivier Lamy
 * @since 1.0
 *
 */
public class LogInsightInboundChannelAdapterParser extends AbstractPollingInboundChannelAdapterParser {

	private static final String TEMPLATE_REF_ATTRIBUTE = "template-ref";

	protected BeanMetadataElement parseSource(Element element, ParserContext parserContext) {

		BeanDefinitionBuilder LogInsightInboundChannelAdapterParser = BeanDefinitionBuilder
				.genericBeanDefinition(LogInsightInboundChannelAdapterParser.class);
		String templateName = element.getAttribute(TEMPLATE_REF_ATTRIBUTE);
		
		if (!StringUtils.hasText(templateName)) {
			parserContext.getReaderContext().error("A '" + TEMPLATE_REF_ATTRIBUTE + "' attribute must be set.",
					element);
		}
		if (StringUtils.hasText(templateName)) {
			System.out.println("template-ref=" + TEMPLATE_REF_ATTRIBUTE);
			LogInsightInboundChannelAdapterParser.addConstructorArgReference(templateName);
		}

		LogInsightInboundChannelAdapterParser.addPropertyValue("producesReply", Boolean.FALSE);

		return LogInsightInboundChannelAdapterParser.getBeanDefinition();
	}

}
