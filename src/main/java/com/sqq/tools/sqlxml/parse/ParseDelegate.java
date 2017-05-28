package com.sqq.tools.sqlxml.parse;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sqq.tools.sqlxml.enums.ElementType;
import com.sqq.tools.sqlxml.exception.ParseDocumentException;

/**
 * @author Jasun 2017年5月26日 下午11:34:29
 */
public class ParseDelegate {
	private static final Logger logger = LoggerFactory.getLogger(ParseDelegate.class);
	
	/**
	 * 读取xml文档
	 * 
	 * @author Jasun 2017年5月27日 上午12:09:20
	 * @param xmlFiles
	 */
	public void readXml(Collection<File> xmlFiles) {
		for (File file : xmlFiles) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(file);
				parseDocumentXml(document);
			} catch (ParserConfigurationException | SAXException | IOException e) {
				logger.info("parse：" + file.getName() + " exception, please check file is correct!", e);
			}
		}
	}

	public void parseDocumentXml(Node node) {
		switch (node.getNodeType()) {
		case Node.DOCUMENT_NODE:
			break;
		case Node.ELEMENT_NODE:
			if(StringUtils.equals(ElementType.mapper.name(), node.getNodeName())){
				if(node.hasAttributes()){
					Node item = node.getAttributes().item(0);
					System.out.println(item.getFirstChild().getNodeValue());
				}else
					throw new ParseDocumentException("parse：the namespace attribute is required, please check file is correct!");
			}
			break;
		default:
			break;
		}
		
		if (node.hasChildNodes()) {
			NodeList childNodes = node.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				parseDocumentXml(childNodes.item(i));
			}
		} else {
			return;
		}
		
		//ScriptEngineManager engineManager = new ScriptEngineManager();
	}
}
