package com.sqq.tools.sqlxml.parse;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

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

import com.google.common.collect.ImmutableList;
import com.sqq.tools.sqlxml.enums.ElementType;
import com.sqq.tools.sqlxml.exception.ParseDocumentException;

/**
 * @author Jasun 2017年5月26日 下午11:34:29
 */
public class ParseDelegate {
	private static final Logger logger = LoggerFactory.getLogger(ParseDelegate.class);
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
				if (parentNode.hasAttributes()) {
					Node item = parentNode.getAttributes().item(0);
					System.out.println(item.getFirstChild().getNodeValue());
				} else
					throw new ParseDocumentException(
							"parse：the namespace attribute is required, please check file is correct!");

				if (node.hasAttributes()) {
					Node item = node.getAttributes().item(0);
					System.out.println(item.getFirstChild().getNodeValue());
				} else
					throw new ParseDocumentException(
							"parse：the id attribute is required, please check file is correct!");
				Node firstChild = node.getFirstChild();
				System.out.println(firstChild.getNodeValue().trim());
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
}
