package com.boco.desc;



import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boco.desc.dto.BDComponentScanning;
import com.boco.desc.enty.Login;
import com.boco.desc.liunx.BDComponentAnalysiser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RestController
public class BDCompentBoot {
       
	
	
	private BDComponentScanning bdComponentScanning=null;
	private BDComponentAnalysiser bdComponentAnalysiser=null;
	
	
	@RequestMapping("/getBDComponent")
	String getBaseAst(String ip,String username,String password) throws IOException
	{
		bdComponentAnalysiser=new BDComponentAnalysiser();
		Login login=new Login(ip, username, password);
		bdComponentScanning=bdComponentAnalysiser.Find(login);
	
		
		//把对象解析成字符串
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	    String single=gson.toJson(bdComponentScanning);
	
		return single.toString();
		
	}
	

	     
	}
