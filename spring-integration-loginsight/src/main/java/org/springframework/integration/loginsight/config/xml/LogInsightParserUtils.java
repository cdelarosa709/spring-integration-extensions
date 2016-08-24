package org.springframework.integration.loginsight.config.xml;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class LogInsightParserUtils {
	
	public static void processOutboundTypeAttributes(Element element, ParserContext parserContext,
			 BeanDefinitionBuilder builder) {
		String agetId = element.getAttribute("agent-id");
		String ingestionPort = element.getAttribute("port");
		
	}

}
