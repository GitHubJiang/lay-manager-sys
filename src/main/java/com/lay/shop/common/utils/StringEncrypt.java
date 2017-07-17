package com.lay.shop.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
 * 
 * @author 郑川旸
 * @date 2016年2月25日 下午8:25:39
 * @since
 */
public class StringEncrypt {
	public static String Encrypt(String strSrc, String encName) {
	    if(Validator.isNullOrEmpty(strSrc)){
	        return null;
	    }
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			if (encName == null || encName.equals("")) {
				encName = "SHA-256";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static void main(String args[]) {
		String s = StringEncrypt.Encrypt("111", "");
		System.out.println(s);
	}
}
