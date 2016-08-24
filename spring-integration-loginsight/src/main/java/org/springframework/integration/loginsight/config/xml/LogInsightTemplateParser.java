/*
 * Copyright 2011-2014 the original author or authors.
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

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.loginsight.core.LogInsightTemplate;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * LogInsigth template element parser.
 *
 * The XML element is like this:
 * 
 * <pre class="code">
 * {@code
 * <loginsight:template id="loginsightTemplate" loginsight-server-ref="logInsightServerBeanName"/>
 * }
 * </pre>
 *
 * @author Kalyan Venu Gopal Abbaraju
 * @since 1.0
 *
 */
public class LogInsightTemplateParser extends AbstractSimpleBeanDefinitionParser {

	private static final String SERVER_ATTRIBUTE = "server";
	
	@Override
	public Class<?> getBeanClass(Element element) {
		return LogInsightTemplate.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
//		super.doParse(element, parserContext, builder);

//		BeanDefinitionBuilder logInsightTemplateBuilder = BeanDefinitionBuilder
//				.genericBeanDefinition(LogInsightTemplate.class);
		String server = element.getAttribute(SERVER_ATTRIBUTE);

		if (!StringUtils.hasText(server)) {
			parserContext.getReaderContext().error("A '" + SERVER_ATTRIBUTE + "' attribute must be set.",
					element);
		}

		if (StringUtils.hasText(server)) {
			// Use constructor with connectionFactory parameter
			System.out.println("serverRef " + server);
			builder.addConstructorArgReference(server);
		}

		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element,
		// BeanDefinitionParserDelegate.SCOPE_ATTRIBUTE);
//		String logInsightServerBeanName = element.getAttribute("loginsight-server-ref");
//		RuntimeBeanReference logInsightServer = new RuntimeBeanReference(logInsightServerBeanName);
//		LogInsightTemplateBuilder.addConstructorArgReference(logInsightServerBeanName);
		// BeanDefinition logInsightTemplateBuilderBeanDefinition =
		// logInsightServerBuilder.getBeanDefinition();
		// parserContext.registerBeanComponent(new
		// BeanComponentDefinition(LogInsightTemplateBuilder.getBeanDefinition(),));
		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element, "host");
		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element, "port");
		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element, "ingestionPort");
		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element, "scheme");
		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element, "owner");
		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element, "username");
		// IntegrationNamespaceUtils.setValueIfAttributeDefined(builder,
		// element, "password");

	}

//	private BeanDefinition parseListener(Element childElement, Element element, ParserContext parserContext) {
////		BeanDefinition replyContainer = RabbitNamespaceUtils.parseContainer(childElement, parserContext);
//		BeanDefinition replyContainer;
//		String connectionFactoryBeanName = "rabbitConnectionFactory";
//		if (childElement.hasAttribute(SERVER_REF_ATTRIBUTE)) {
//			connectionFactoryBeanName = childElement.getAttribute(SERVER_REF_ATTRIBUTE);
//			if (!StringUtils.hasText(connectionFactoryBeanName)) {
//				parserContext.getReaderContext().error(
//						"Listener container 'connection-factory' attribute contains empty value.", childElement);
//			}
//		}
//		if (StringUtils.hasText(connectionFactoryBeanName)) {
//			childElement.getPropertyValues().add("connectionFactory",
//					new RuntimeBeanReference(connectionFactoryBeanName));
//		}
//		if (replyContainer != null) {
//			replyContainer.getPropertyValues().add("connectionFactory",
//					new RuntimeBeanReference(element.getAttribute(CONNECTION_FACTORY_ATTRIBUTE)));
//		}
////		if (element.hasAttribute(REPLY_QUEUE_ATTRIBUTE)) {
////			replyContainer.getPropertyValues().add("queues",
////					new RuntimeBeanReference(element.getAttribute(REPLY_QUEUE_ATTRIBUTE)));
////		}
//		return replyContainer;
//	}

}
