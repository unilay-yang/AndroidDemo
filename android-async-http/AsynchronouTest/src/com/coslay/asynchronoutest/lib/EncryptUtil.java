package com.coslay.asynchronoutest.lib;

import android.content.Context;

public class EncryptUtil {
	// ?????? ????????????key
	public  final static String KEY = "Laotong83!15";
	private String password;
	private Context context;

	public EncryptUtil(Context context, String password) {
		this.context = context;
		this.password = password;
	}

	public String getMd5(String value) {
		PersistenceUtil pu = new PersistenceUtil(context);
//		if (pu.getPersistenceString("zcxc", "password") == null) {
//			password = pu.getPersistenceString("zcxc", "password");
//		}
		String result = MD5Encoding.getMD5((value + KEY + MD5Encoding
				.getMD5(password.getBytes())).getBytes());
 
 return result;
	}
}
