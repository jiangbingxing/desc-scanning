package com.boco.desc;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boco.desc.analysis.FindExec;

import com.boco.desc.dto.NmapScanning;

import com.boco.desc.enty.Login;

import com.boco.desc.nmap.NmapAnalysiser;
import com.boco.desc.util.CommandUtils;
import com.boco.desc.util.LoadUtil;
import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;






@RestController
public class ScanningBoot {
	private static Logger logger = Logger.getLogger(ScanningBoot.class);
	@RequestMapping(method=RequestMethod.POST,value="/getBaseAst")
	String  getBaseAst(String ip,String username,String password) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException
	{
		//选择4A算法进行解密服务
		Login login=new Login(ip, username, password);
		login.Decrypt();
		logger.error(login);
		UserInfo userInfo =new UserInfo(login.getIp(),login.getPort(),login.getUsername(),login.getPassword());
	   
		FindExec findExec=FindExec.getInstance(userInfo);
		JsonObject jsonObject=findExec.
				OSChecked().  //检测操作系统
				ReadXML().    //读取相应系统的XML文件
				AllExec();    //用反射调用所有的发现服务
		if (jsonObject==null) {
			return null;
		}
		return jsonObject.toString();
		
	}
	
	
	//Nmap扫描ip
	@RequestMapping("/getNmapScanning")
	String getNmapScanning(String ip)
	{
		NmapAnalysiser nmapAnalysiser =new NmapAnalysiser();
	  NmapScanning baseScanning=nmapAnalysiser.getNmapSingleScanning(ip);
	  Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	    String base=gson.toJson(baseScanning);
		return base;
		
	}
	
	@RequestMapping("/getNmapTest")
	public String getNmapTest(String ip,String commond)
	{
		String output=null;
		String path=LoadUtil.getNmapPath();
		Nmap4j nmap4j = new Nmap4j(path);
		nmap4j.addFlags(commond);
		nmap4j.includeHosts(ip);
	
			try {
				nmap4j.execute();
			} catch (NMapInitializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NMapExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( !nmap4j.hasError() ) {

			output = nmap4j.getOutput();
		
	}
			  return output;
	}
	
	//Linux系统命名调试
	@RequestMapping("/commondTest")
	public String comondTest(String commond)
	{
		
		UserInfo userInfo = new UserInfo("10.109.209.73",22,"smp","#w$84K74");
			
			//获得资产名
			Command command = new CommandImpl(commond);//指令
			Result result1 = CommandUtils.execute(userInfo, command);
			String commondResult=result1.getMessage();
			try {
				userInfo.logout();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return commondResult;
	}
	     
	}
