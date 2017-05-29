package com.sqq.tools.sqlxml.parse;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.sqq.tools.sqlxml.enums.DynamicType;
import com.sqq.tools.sqlxml.enums.ElementType;
import com.sqq.tools.sqlxml.exception.ParseDocumentException;

/**
 * @author Jasun 2017年5月26日 下午11:34:29
 */
public class ParseDelegate {
	private static final Logger logger = LoggerFactory.getLogger(ParseDelegate.class);

	private Map<String, Object> cacheSqlMap = Maps.newConcurrentMap();
	/**
	 * 待过滤解析的元素
	 */
	private List<String> waitFilterNodeTypeList = ImmutableList.of(ElementType.mapper.name(), ElementType.select.name(),
			ElementType.insert.name(), ElementType.update.name(), ElementType.delete.name(), ElementType.sql.name(),
			"#document");

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
		if (!waitFilterNodeTypeList.contains(node.getNodeName()))
			return;
		switch (node.getNodeType()) {
		case Node.DOCUMENT_NODE:
			break;
		case Node.ELEMENT_NODE:
			if (StringUtils.equals(ElementType.mapper.name(), node.getNodeName()))
				break;
			else if (StringUtils.equals(ElementType.select.name(), node.getNodeName())) {
				Node parentNode = node.getParentNode();
				if (!parentNode.hasAttributes())
					throw new ParseDocumentException(
							"parse：the namespace attribute is required, please check file is correct!");
				if (!node.hasAttributes())
					throw new ParseDocumentException(
							"parse：the id attribute is required, please check file is correct!");

				String namespace = parentNode.getAttributes().item(0).getNodeValue();
				String method = node.getAttributes().item(0).getNodeValue();
				String namespaceMethod = namespace + "." + method;
				System.out.println(namespaceMethod);

				NodeList childNodes = node.getChildNodes();
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node nodeCondition = childNodes.item(i);
					String nodeName = nodeCondition.getNodeName();
					if (StringUtils.equals(DynamicType.WHERE.getAttr(), nodeName)) {

					}
					if (StringUtils.equals(DynamicType.IF.getAttr(), nodeName)) {
						if (!nodeCondition.hasAttributes())
							throw new ParseDocumentException(
									"parse：the id attribute is required, please check file is correct!");
						// condition
						String nodeValue = nodeCondition.getAttributes().item(0).getNodeValue();
						System.out.println("condition:" + nodeValue);

						String nodeValue2 = nodeCondition.getFirstChild().getNodeValue();
						System.out.println("value:" + nodeValue2.trim());
					}

					if (StringUtils.isNotBlank(nodeCondition.getNodeValue())) {
						System.out.println(nodeCondition.getNodeValue().trim());
					}
				}

				cacheSqlMap.put(namespaceMethod, namespaceMethod);
				System.out.println("--------------------------------------------");
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
		}
	}
	
	public static void main(String[] args) {
		String replace = StringUtils.replace("and username = ${username}", "$\\w+}^", "?");
		System.out.println(replace);
		
		System.out.println("and username = ${username}".replaceAll("^$\\w+}$", "?"));
		
	}
}
