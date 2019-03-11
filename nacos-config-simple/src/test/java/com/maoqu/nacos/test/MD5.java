package com.maoqu.nacos.test;

import org.springframework.util.DigestUtils;

/**
 * MD5通用类
 * 
 * @author 浩令天下
 * @since 2017.04.15
 * @version 1.0.0_1
 * 
 */
public class MD5 {
	/**
	 * MD5方法
	 * 
	 * @param text
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文
	 * @throws Exception
	 */
	public static String md5(String text, String key) throws Exception {
		// 加密后的字符串
//		String encodeStr = DigestUtils.md5Hex(text + key);
		String encodeStr =DigestUtils.md5DigestAsHex(text.getBytes());
		System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
		return encodeStr;
	}

	/**
	 * MD5验证方法
	 * 
	 * @param text
	 *            明文
	 * @param key
	 *            密钥
	 * @param md5
	 *            密文
	 * @return true/false
	 * @throws Exception
	 */
	public static boolean verify(String text, String key, String md5) throws Exception {
		// 根据传入的密钥进行验证
		String md5Text = md5(text, key);
		if (md5Text.equalsIgnoreCase(md5)) {
			System.out.println("MD5验证通过");
			return true;
		}

		return false;
	}
	
	public static void main(String[] args) throws Exception {
		MD5.md5("ipyxrrn5b1e2jifcauser20190122", "");
	}
}
