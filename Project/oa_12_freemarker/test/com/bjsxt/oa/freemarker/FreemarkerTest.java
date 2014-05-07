package com.bjsxt.oa.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bjsxt.oa.SystemContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerTest {

	private String dir = "D:\\javaproject\\oa_12_freemarker\\test\\com\\bjsxt\\oa\\freemarker";
	
	@Test
	public void testFreemarker() {
		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(dir));
			
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("abc", "你好");
			
			Template template = cfg.getTemplate("test.ftl");
			
			PrintWriter out = new PrintWriter(
					new BufferedWriter(
							new FileWriter(dir + "//out.txt")
					)
			);
			
			template.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
