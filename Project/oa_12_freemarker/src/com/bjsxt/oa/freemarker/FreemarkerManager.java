package com.bjsxt.oa.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerManager {

	private static Configuration cfg = new Configuration();
	
	static {
		// 定义模板的位置，从类路径中，相对于FreemarkerManager所在的路劲加载模板
		cfg.setTemplateLoader(new ClassTemplateLoader(FreemarkerManager.class, "templates"));
		
		// 设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		// 设置异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
	}
	
	public static Configuration getConfiguration() {
		return cfg;
	}
}
