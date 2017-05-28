package com.sqq.tools.sqlxml.enums;

public enum DynamicType {
	IF("if"), WHERE("where");

	public String attr;

	private DynamicType(String attr) {
		this.attr = attr;
	}

	public String getAttr() {
		return attr;
	}
}
