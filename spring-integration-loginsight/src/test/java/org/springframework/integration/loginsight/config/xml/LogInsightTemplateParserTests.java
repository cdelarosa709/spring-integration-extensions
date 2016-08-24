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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.loginsight.core.LogInsightTemplate;
import org.springframework.integration.loginsight.support.LogInsightServer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jarred Li
 * @since 1.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LogInsightTemplateParserTests {

	@Autowired
	private ApplicationContext appContext;

	/**
	 * Test method for {@link org.springframework.integration.splunk.config.xml.SplunkServerParser#doParse(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext, org.springframework.beans.factory.support.BeanDefinitionBuilder)}.
	 */
	@Test
	public void testDoParseElementParserContextBeanDefinitionBuilder() {
		LogInsightTemplate template = appContext.getBean("logInsightTemplate", LogInsightTemplate.class);
		LogInsightServer server = template.getServer();
		LogInsightServer server2 = appContext.getBean("loginsightServer", LogInsightServer.class);
		System.out.println("Server2 " + server2.getHost());
		System.out.println("Server " + server.getHost());
		Assert.assertEquals("10.152.215.3", server.getHost());
		
//		LogInsightTemplate template2 = appContext.getBean("logInsightTemplateMock", LogInsightTemplate.class);
//		LogInsightServer server3 = template2.getServer();
//		LogInsightServer server4 = appContext.getBean("loginsightServer", LogInsightServer.class);
//		System.out.println("server3 " + server3.getHost());
//		System.out.println("server4 " + server4.getHost());
//		Assert.assertEquals("10.152.215.3", server3.getHost());
	}

}
