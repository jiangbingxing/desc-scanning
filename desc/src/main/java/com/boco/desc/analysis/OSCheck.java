package com.boco.desc.analysis;


import org.jsoup.Jsoup;
import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;

import com.asiainfo.uap.util.des.EncryptInterface;
import com.boco.desc.dto.LoginScanning;
import com.boco.desc.enty.IpAndCommod;
import com.boco.desc.enty.Login;
import com.boco.desc.liunx.LinuxAnalysiser;
import com.boco.desc.result.ResultCode;
import com.boco.desc.util.LoadUtil;
import com.boco.desc.version.AssectOS;


public class OSCheck  {

	private String output;
	private String OS="Linux";
	private LoginScanning loginScanning=null;
	
	public LoginScanning OSAnalysiser(IpAndCommod ic, Login login) {

		/*
		 * 1.使用Nmap进行扫描，先从<osclass>标签中的osfamily中获得系统名
		 * 2.通过得到的不同的OS，进行不同的系统解析API的调度
		 */
	
	
	  /*   if(login.getPassword()!=null)
		{
	    	try {
	         password=EncryptInterface.desUnEncryptData(login.getPassword());
			} catch (Exception e) {
				
				loginScanning=new LoginScanning();
				loginScanning.setResultCode(ResultCode.LOGIN_FAIL);
				return loginScanning;
			}
		  login.setPassword(password);
		}*/
		
			
		try {
			//获得配置文件中nmap的路径
			String path=LoadUtil.getNmapPath();
			
			Nmap4j nmap4j = new Nmap4j(path);
			nmap4j.addFlags(ic.getCommond());
			nmap4j.includeHosts(ic.getIp()) ;
			
			
			nmap4j.execute();
			
			if( !nmap4j.hasError() ) {
				
				output = nmap4j.getOutput() ;
				
				if( output == null ) {
					System.out.println("没有开启任何端口"); 
					
				}
			}
			
		}
			
		catch (NMapInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//失败直接返回状态码
			loginScanning=new LoginScanning();
			loginScanning.setResultCode(ResultCode.NMAP_FAIL);
			return loginScanning;
			
		} catch (NMapExecutionException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			loginScanning=new LoginScanning();
			loginScanning.setResultCode(ResultCode.NMAP_FAIL);
			return loginScanning;
			
		}
		
		org.jsoup.nodes.Document doc = Jsoup.parse(output); 
		org.jsoup.select.Elements links = doc.getElementsByTag("osclass");
		if(links.attr("osfamily")!=null&&links.attr("osfamily").equals(""))
		{
		   OS=links.attr("osfamily");
		
		}
		
		if (OS.equals(AssectOS.LINUX)) {
			
			LinuxAnalysiser linuxAnalysiser=new LinuxAnalysiser();
			loginScanning=linuxAnalysiser.getLoginScanning(output, login);
		}
		return loginScanning;
		
		
		
	}


}
