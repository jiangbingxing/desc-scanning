package com.boco.desc.result;

import com.google.gson.JsonObject;

/*
 * 定义返回值
 */
public class ResultCode {

	public final static int UNKNOW_ERRO=0;
	public final static int NMAP_SUCCESS=200;
	public final static int NMAP_LOGIN_SUCCESS=201;
	public final static int NMAP_FAIL=101;
	public final static int LOGIN_FAIL=102;
	public static void main(String[] args) {
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("dasdas", "hello");
		System.out.println(jsonObject);
	}
}
