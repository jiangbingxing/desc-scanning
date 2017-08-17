package com.boco.desc;


import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boco.desc.analysis.OSCheck;
import com.boco.desc.dto.LoginScanning;
import com.boco.desc.dto.NmapScanning;
import com.boco.desc.enty.IpAndCommod;
import com.boco.desc.enty.Login;
import com.boco.desc.liunx.LinuxAnalysiser;
import com.boco.desc.util.LoadUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RestController
public class ScanningBoot {
       
	private LinuxAnalysiser linuxAnalysiser=null;
	private OSCheck OSCheck=null;

	@RequestMapping("/getBaseAst")
	String getBaseAst(String ip,String username,String password)
	{
		IpAndCommod ipAndCommod=new IpAndCommod();
		ipAndCommod.setIp(ip);
		
		Login login=new Login(ip,username, password);
		
		OSCheck=new OSCheck();
		LoginScanning baseScanning=OSCheck.OSAnalysiser(ipAndCommod, login);
		
		//把对象解析成字符串
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	    String single=gson.toJson(baseScanning);
	
		return single.toString();
		
	}
	
	
	//Nmap扫描ip
	@RequestMapping("/getNmapScanning")
	String getNmapScanning(String ip)
	{
		linuxAnalysiser=new LinuxAnalysiser();
	  NmapScanning baseScanning=linuxAnalysiser.getNmapScanning(ip);
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
	     
	}
