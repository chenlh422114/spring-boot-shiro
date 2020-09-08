package com.leigq.www.shiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
	
	public static void main(String[] args) {
		String password  = "123456";
		String salt = "admin8d78869f470951332959580424d4bf4f";
		password = md5Pwd(password, salt);
        // 结果 ： d3c59d25033dbf980d29554025c23a75
        System.out.println(password);
	}
	
	//加盐生成密码
	public static String md5Pwd(String password, String salt) {
        int hashIterations = 2;
        SimpleHash simpleHash = new SimpleHash("md5", password, ByteSource.Util.bytes(salt), hashIterations);

        return simpleHash.toHex();
    }
}
