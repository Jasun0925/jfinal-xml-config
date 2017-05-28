package com.sqq.tools.sqlxml;

import java.util.Map;

import com.google.common.collect.Maps;
import com.sqq.tools.sqlxml.bean.GenerateXmlResult;

public class GenerateSqlUtil {
	/**
	 * 生成sql语句，适用于${}方式
	 * 
	 * @param sqlId
	 *            xml语句id
	 * @param map
	 *            值
	 * @return 生成sql语句
	 */
	public static String generateSql(String sqlId, Map<String, Object> map) {
		return null;
	}

	/**
	 * 生成sql语句，适用于${}或#{}方式
	 * 
	 * @param sqlId
	 *            xml语句id
	 * @param map
	 *            值
	 * @return 生成sql语句和对应查询条件值
	 */
	public static GenerateXmlResult generateSqlResult(String sqlId, Map<String, Object> map) {
		return null;
	}

	public static void main(String[] args) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", "1234567890");
		map.put("username", "jasun");
		map.put("password", "12345");
		map.put("age", 18);
		map.put("price", 17.48);
		GenerateSqlUtil.generateSql("findById", map);
	}
}
