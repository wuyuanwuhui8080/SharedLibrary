package com.share.util;

import com.share.pojo.SharedEmail;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 用于shiro加密类
 *
 * @author 博博
 * @Title: ShiroMd5
 * @ProjectName InvoicingSystem
 * @time 2018/12/3 21:23
 */
public class ShiroMd5 {

	/**
	 * 进行shiromd5加密
	 *
	 * @param salt
	 *            唯一加密的盐值 ，我们这里统一用用户名
	 * @param password
	 *            密码
	 * @return String
	 */
	public static final String hashMd5(String salt, String password) {
		Object sourcs = ByteSource.Util.bytes(salt);
		String pwd = new SimpleHash("MD5", password, sourcs, 1024).toHex();
		return pwd;
	}
	public static void main(String[] args){
	 System.out.println(ShiroMd5.hashMd5("admin","mhb123.."));
	}


}
