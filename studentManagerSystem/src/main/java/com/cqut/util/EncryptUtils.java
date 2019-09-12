package com.cqut.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class EncryptUtils {
	public static String md5Encrypt(String pwd, String salt) {
		Md5Hash mh = new Md5Hash(pwd,salt);
		return mh.toString();
	}
}
