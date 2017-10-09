package com.boco.desc.analysis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.boco.desc.util.CommandUtils;

import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FindExec {
	public static UserInfo info;
	private static FindExec findExec;
	private String OS = null;
	private Elements hostXMl;
	private Elements MiddleWaveXMl;
	private Elements DBXMl;
	private Elements BDComponentXML;
	private Elements hostXMlHead;

	private FindExec() {

	}

	public synchronized static FindExec getInstance(UserInfo userInfo) {
		if (findExec == null) {
			info = userInfo;
			return new FindExec();

		}
		info = userInfo;
		return findExec;

	}

	// 所有的命名的执行都在这里
	public static String commandExec(String attr) {
		Command command = new CommandImpl(attr);// 指令
		Result result = CommandUtils.execute(info, command);
		if (result.getCode() == 0) {
			return result.getMessage();

		}
		return null;
	}

	// 用于操作系统的检测
	public FindExec OSChecked() {
		String os = OSCheck.getOS();
		if (os != null) {
			OS = os;
		}
		return this;
	}

	public JsonObject AllExec() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		if (OS == null) {
			return null;
		}
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		Class<?> cls1 = Class.forName(hostXMlHead.attr("class"));
		Method method1 = cls1.getDeclaredMethod("Find", Elements.class);
		JsonObject host = (JsonObject) method1.invoke(cls1.newInstance(), hostXMl);
		if (host.size() < 4) {
			return null;
		}

		jsonArray.add(host);
		// 获取中间件
		JsonArray middleware=XmlToCls(MiddleWaveXMl);
		if (middleware!=null) {
			jsonArray.addAll(middleware);
		}
			
	

		// 获取数据库
		JsonArray db=XmlToCls(DBXMl);
			if (db!=null) {
				jsonArray.addAll(db);
			}
			
		

		// 获取大数据组件

			JsonArray bdComponment=XmlToCls(BDComponentXML);
			if (bdComponment!=null) {
				jsonArray.addAll(bdComponment);
			}
	

		// 封装
		jsonObject.add("ast", jsonArray);

		return jsonObject;

	}

	// 反射xml集合类
	public JsonArray XmlToCls(Elements elements)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {
		JsonArray jsonArray = null;
		int size = elements.select("component").size();
		for (int i = 0; i < size; i++) {
			// 获取中间件的类名
			String clsName = elements.attr("class") + "." + elements.select("component").get(i).attr("name");
			Class<?> cls2 = Class.forName(clsName);
			Method method2 = cls2.getDeclaredMethod("Find", Elements.class);
			@SuppressWarnings("unchecked")
			List<JsonObject> obj = (List<JsonObject>) method2.invoke(cls2.newInstance(), elements);

			if (obj != null && obj.size() > 0) {

				jsonArray = new JsonArray();
				for (int j = 0; j < obj.size(); j++) {
					if (obj.get(j).size() > 0) {

						jsonArray.add(obj.get(j));
					}

				}
			}
		}
		return jsonArray;

	}

	// 读取xml中的信息
	public FindExec ReadXML() throws IOException {

		InputStream inputStream = FindExec.class.getClassLoader().getResourceAsStream("LinuxFind.xml");

		org.jsoup.nodes.Document doc;
		try {
			doc = Jsoup.parse(inputStream, "utf-8", "");

			hostXMl = doc.getElementsByTag("host").select("property");
			hostXMlHead = doc.getElementsByTag("host");
			MiddleWaveXMl = doc.getElementsByTag("middlewave");
			DBXMl = doc.getElementsByTag("db");
			BDComponentXML = doc.getElementsByTag("bdComponent");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return this;

	}

	public UserInfo getInfo() {
		return info;
	}

	public static void main(String[] args)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		UserInfo userInfo = new UserInfo("192.168.1.106", 22, "root", "rootroot");
		FindExec findExec = FindExec.getInstance(userInfo);
		JsonObject jsonObject = findExec.OSChecked(). // 检测操作系统
				ReadXML(). // 读取相应系统的XML文件
				AllExec(); // 用反射调用所有的发现服务
		System.out.println(jsonObject);

	}
}
