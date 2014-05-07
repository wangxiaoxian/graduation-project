package com.bjsxt.oa.model;

import java.beans.PropertyDescriptor;

import com.sun.org.apache.commons.beanutils.PropertyUtils;

public class DocumentProperty {

	private String java_lang_String;
	private Integer java_lang_Integer;
	private byte[] java_io_File;
	
	private static String[] supportedTypeNames;
	static{
		PropertyDescriptor[] ps = PropertyUtils.getPropertyDescriptors(DocumentProperty.class);
		supportedTypeNames = new String[ps.length];
		for(int i=0; i<ps.length; i++){
			PropertyDescriptor pd = ps[i];
			supportedTypeNames[i] = pd.getName(); //获得属性的名称
		}
	}
	
	/**
	 * 判断是否支持此类型
	 * @param type 类型名称
	 * @return
	 */
	public static boolean support(String type){
		for(int i=0; i<supportedTypeNames.length; i++){
			if(type.equals(supportedTypeNames[i])){
				return true;
			}
		}
		return false;
	}
	
	public String getJava_lang_String() {
		return java_lang_String;
	}
	public void setJava_lang_String(String java_lang_String) {
		this.java_lang_String = java_lang_String;
	}
	public Integer getJava_lang_Integer() {
		return java_lang_Integer;
	}
	public void setJava_lang_Integer(Integer java_lang_Integer) {
		this.java_lang_Integer = java_lang_Integer;
	}
	public byte[] getJava_io_File() {
		return java_io_File;
	}
	public void setJava_io_File(byte[] java_io_File) {
		this.java_io_File = java_io_File;
	}
}
