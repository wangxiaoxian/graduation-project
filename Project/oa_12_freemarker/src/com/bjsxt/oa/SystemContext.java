package com.bjsxt.oa;

public class SystemContext {
	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pagesize = new ThreadLocal<Integer>();
	
	public static int getOffset() {
		Integer os = offset.get();
		if (os == null) {
			return 0;
		}
		return os;
	}
	
	public static void setOffset(Integer offsetValue) {
		offset.set(offsetValue);
	}
	
	public static void removeOffset() {
		offset.remove();
	}
	
	
	public static int getPagesize() {
		Integer ps = pagesize.get();
		// 没有设置分页的话，将取Integer的最大值
		if (ps == null) {
			return Integer.MAX_VALUE;
		}
		return ps;
	}
	
	public static void setPagesize(Integer pagesizeValue) {
		pagesize.set(pagesizeValue);
	}
	
	public static void removePagesize() {
		pagesize.remove();
	}
}
