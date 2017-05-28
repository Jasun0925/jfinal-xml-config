package com.sqq.tools.sqlxml.bean;

import java.io.Serializable;
import java.util.List;

public class GenerateXmlResult implements Serializable {
	private static final long serialVersionUID = -6275222241776768096L;
	/**
	 * sql语句
	 */
	private String sql;
	/**
	 * sql语句值集合
	 */
	private List<Object> sqlList;

	public GenerateXmlResult(String sql, List<Object> sqlList) {
		super();
		this.sql = sql;
		this.sqlList = sqlList;
	}

	public String getSql() {
		return sql;
	}

	public List<Object> getSqlList() {
		return sqlList;
	}

	@Override
	public String toString() {
		return "GenerateXmlResult [sql=" + sql + ", sqlList=" + sqlList + "]";
	}
}
