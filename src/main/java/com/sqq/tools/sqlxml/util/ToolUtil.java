package com.sqq.tools.sqlxml.util;

public class ToolUtil {
	public static String instandAndOr(String oldInstand) {
		return oldInstand.replaceAll(" and ", " && ").replaceAll(" or ", " || ");
	}
}
