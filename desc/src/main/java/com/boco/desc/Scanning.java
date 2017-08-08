package com.boco.desc;




import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boco.desc.analysis.OSCheck;
import com.boco.desc.enty.BaseScanning;
import com.boco.desc.enty.Component;
import com.boco.desc.enty.IpAndCommod;
import com.boco.desc.enty.Login;
import com.boco.desc.liunx.LinuxAnalysiser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@RestController
public class Scanning {
       
	private LinuxAnalysiser linuxAnalysiser=null;
	private OSCheck OSCheck=null;
	
	
	
	
	@RequestMapping("/getBaseAst")
	String getBaseAst(String ip,String username,String password)
	{
		IpAndCommod ipAndCommod=new IpAndCommod();
		ipAndCommod.setIp(ip);
		
		Login login=new Login(ip,username, password);
		
		OSCheck=new OSCheck();
		BaseScanning baseScanning=OSCheck.OSAnalysiser(ipAndCommod, login);
		
		//把对象解析成字符串
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	    String single=gson.toJson(baseScanning);
		System.out.println(single);		
	
		return single.toString();
		
	}
	
	
	//Nmap扫描ip
	@RequestMapping("/getNmapScanning")
	String getNmapScanning(String ip)
	{
		linuxAnalysiser=new LinuxAnalysiser();
	  List<Component> components=linuxAnalysiser.NmapScanning(ip);
	  Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	    String component=gson.toJson(components);
		return component;
		
	}

}
