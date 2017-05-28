package com.sqq.tools.sqlxml.util;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.sqq.tools.sqlxml.parse.ParseDelegate;

public class ResourceUtil {
	/**
	 * 从根目录加载资源文件
	 * 
	 * @author Jasun 2017年5月26日 上午10:57:01
	 */
	public static void loadSqlXmlResource() {
		String path = ResourceUtil.class.getClassLoader().getResource("").getPath();
		loadSqlXmlResource(path, new String[] { "sqq.xml" }, true);
	}

	/**
	 * 从指定目录加载资源文件
	 * 
	 * @author Jasun 2017年5月26日 上午10:57:23
	 * @param resource
	 */
	public static void loadSqlXmlResource(String directory, String[] extensions, boolean recursive) {
		Collection<File> listFiles = FileUtils.listFiles(new File(directory), extensions, recursive);
		if (listFiles.isEmpty())
			return;
		new ParseDelegate().readXml(listFiles);
	}

	public static void main(String[] args) {
		loadSqlXmlResource();
	}
}
