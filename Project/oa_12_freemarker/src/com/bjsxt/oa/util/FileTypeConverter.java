package com.bjsxt.oa.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.bjsxt.oa.web.SystemException;

public class FileTypeConverter {

	public static byte[] getBytesFromFile(File file) {
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		
		byte[] ret = null;  
        try {  
            if (file == null) {  
                return null;  
            }  
            in = new FileInputStream(file);  
            out = new ByteArrayOutputStream(10240);  
            byte[] b = new byte[10240];  
            int n;  
            while ((n = in.read(b)) != -1) {  
                out.write(b, 0, n);  
            }  
            ret = out.toByteArray();  
        } catch (IOException e) {  
            e.printStackTrace();
            throw new SystemException("File类型转换为byte[]类型发生异常。请检查是否有以下错误：1、文件内容为空；2、文件大小是0");
        } finally {
        	if (in != null) {
        		try {
        			in.close();  
        		} catch (IOException e) {
        			e.printStackTrace();
        		}  
			}
        	if (out != null) {
        		try {
        			out.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}  
        	}
        }
        return ret; 
	}
	
	 public static File getFileFromBytes(byte[] b, String outputFile) {  
	        File ret = null;  
	        BufferedOutputStream stream = null;  
	        try {  
	            ret = new File(outputFile);  
	            FileOutputStream fstream = new FileOutputStream(ret);  
	            stream = new BufferedOutputStream(fstream);  
	            stream.write(b);  
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	            throw new SystemException("byte[]类型转换为File类型发生错误");
	        } finally {  
	            if (stream != null) {  
	                try {  
	                    stream.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return ret;  
	    }  
}
