package com.sqq.tools.sqlxml.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.sqq.tools.sqlxml.exception.DynamicSqlScriptException;

public class EngineUtil {
	private static final ScriptEngineManager manager = new ScriptEngineManager();
	// 默认引擎
	private static final ScriptEngine engine = getEngine();

	public static ScriptEngineManager getEngineManager() {
		return manager;
	}

	public static ScriptEngine getEngine() {
		return manager.getEngineByName("js");
	}

	public static boolean excuteScriptCondition(String dynamicSql) {
		try {
			return (boolean) engine.eval(dynamicSql);
		} catch (ScriptException e) {
			throw new DynamicSqlScriptException(e);
		}
	}
}
