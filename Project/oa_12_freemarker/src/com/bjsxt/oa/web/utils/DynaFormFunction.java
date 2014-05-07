package com.bjsxt.oa.web.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.bjsxt.oa.freemarker.FreemarkerManager;
import com.bjsxt.oa.manager.FormManager;
import com.bjsxt.oa.model.FlowForm;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DynaFormFunction {

	private static FormManager formManager;
	private static String defaultTemplate = "document_form.ftl";
	
	public static String form(int workflowId) {
		
		try {
			// 查找表单定义
			FlowForm flowForm = formManager.findForm(workflowId);
			
			if (flowForm == null) {
				return null;
			}
			
			Configuration cfg = FreemarkerManager.getConfiguration();
			
			Template template = null;
			if (flowForm.getTemplate() == null || "".equals(flowForm.getTemplate().trim())) {
				flowForm.setTemplate(defaultTemplate);
			} else {
				template = cfg.getTemplate(flowForm.getTemplate());
			}
			
			// 最终输出位置
			Writer out = new StringWriter();
			
			// 数据模型
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("form", flowForm);
			
			template.process(root, out);
			
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (TemplateException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public void setFormManager(FormManager formManager) {
		DynaFormFunction.formManager = formManager;
	}
	public void setDefaultTemplate(String defaultTemplate) {
		DynaFormFunction.defaultTemplate = defaultTemplate;
	}
}
