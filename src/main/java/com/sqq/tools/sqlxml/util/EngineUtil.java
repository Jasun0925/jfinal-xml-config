package com.sqq.tools.sqlxml.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class EngineUtil {
	private static final ScriptEngineManager manager = new ScriptEngineManager();

	public static ScriptEngineManager getEngineManager() {
		return manager;
	}

	public static ScriptEngine getEngine() {
		return manager.getEngineByName("js");
	}
}
