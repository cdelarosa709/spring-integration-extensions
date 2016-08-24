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

import org.w3c.dom.Element;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.IntegrationNamespaceUtils;
import org.springframework.integration.loginsight.support.LogInsightServer;

/**
 * LogInsight server element parser.
 *
 * The XML element is like this:
 * 
 * <pre class="code">
 * {@code
 * <loginsight:server id="loginsightServer" host="host" port="8089" username="admin" password="password"
 *                scheme="https" ingestionPort=""/>
 * }
 * </pre>
 *
 * @author Kalyan Venu Gopal Abbaraju
 * @since 1.0
 *
 */
public class LogInsightServerParser extends AbstractSimpleBeanDefinitionParser {

	private static final String HOST_ATTRIBUTE = "host";
	private static final String USER_NAME_ATTRIBUTE = "username";
	private static final String PASSWORD_ATTRIBUTE = "password";
	private static final String PORT_ATTRIBUTE = "port";
	private static final String INGESTION_PORT_ATTRIBUTE = "ingestionPort";
	private static final String SCHEME_ATTRIBUTE = "scheme";

	@Override
	public Class<?> getBeanClass(Element element) {
		return LogInsightServer.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		super.doParse(element, parserContext, builder);
		if (!element.hasAttribute(HOST_ATTRIBUTE) || !element.hasAttribute(USER_NAME_ATTRIBUTE)
				|| !element.hasAttribute(PASSWORD_ATTRIBUTE)) {
			parserContext.getReaderContext().error("Missing mandatory fields: host, username and passwore", element);
		}

		IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, element,
				BeanDefinitionParserDelegate.SCOPE_ATTRIBUTE);
		IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, element, HOST_ATTRIBUTE);
		IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, element, PORT_ATTRIBUTE);
		IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, element, INGESTION_PORT_ATTRIBUTE);
		IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, element, SCHEME_ATTRIBUTE);
		IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, element, USER_NAME_ATTRIBUTE);
		IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, element, PASSWORD_ATTRIBUTE);

	}

	protected void postProcess(BeanDefinitionBuilder beanDefinition, Element element) {
		System.out.println("Bean processing complete");
	}

}
