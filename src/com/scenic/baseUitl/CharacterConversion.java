package com.scenic.baseUitl;

import java.io.UnsupportedEncodingException;

/**
 * 字符转换
 * 
 * @author Lr
 * 
 */
public class CharacterConversion {

	public static String toUTF8(String param) {
		if (param == null) {
			return null;
		} else {
			try {
				param = new String(param.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return param;
			}
		}
		return param;
	}
}
