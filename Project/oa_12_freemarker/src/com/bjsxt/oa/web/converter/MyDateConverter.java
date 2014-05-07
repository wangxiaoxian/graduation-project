package com.bjsxt.oa.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.bjsxt.oa.web.SystemException;

public class MyDateConverter extends StrutsTypeConverter {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String[] strs = (String[])values;
		Date date = null;
		try {
			date = format.parse(strs[0]);
		} catch (ParseException e) {
			throw new SystemException("解析日期类型的字符串出现错误！");
		}
		return date;
	}

	@Override
	public String convertToString(Map arg0, Object object) {
		
		if (object instanceof Date) {
			String dateStr = format.format((Date)object);
			return dateStr;
		}
		
		return null;
	}

}
