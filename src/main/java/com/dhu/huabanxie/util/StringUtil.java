package com.dhu.huabanxie.util;

public class StringUtil {
	public static String getVal(Object o) {
		if (null == o)
			return "";
		// if (o instanceof String)
		return String.valueOf(o);
		// return o.toString();
	}
	/**
	 * 检查str是否为空或者空串
	 * @param str
	 * @return
	 */
	public static boolean isNullString(String str) {
		return (str == null) || (str.equals(""));
	}
}
