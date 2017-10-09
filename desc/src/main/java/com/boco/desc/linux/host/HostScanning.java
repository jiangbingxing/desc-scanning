package com.boco.desc.linux.host;





import org.jsoup.select.Elements;


import com.boco.desc.analysis.FindExec;

import com.boco.desc.util.PatternUtil;

import com.google.gson.JsonObject;



public class HostScanning {

	public JsonObject json=new JsonObject();
	

	public JsonObject Find(Elements elements) {
		
	
		//获得资产名
		json.addProperty("astType","主机");
		json.addProperty("typeName","Linux");
		//获得厂商
	    json.addProperty(elements.get(1).attr("name"),"RedHat");
				
		String astName = FindExec.commandExec(elements.get(0).attr("command"));//指令
		if (astName!=null) {
			
			json.addProperty(elements.get(0).attr("name"),astName.trim());
		}
              
		
		
		//获得版本号
		String version =FindExec.commandExec(elements.get(3).attr("command1"));//指令
		
		if(version!=null && PatternUtil.getMatch(version,"-bash:").size()==0)
		{
			json.addProperty(elements.get(3).attr("name"),version.trim());
		}
		else  {
			String version2 =FindExec.commandExec(elements.get(3).attr("command2"));//指令
			if(version2!=null&&PatternUtil.getMatch(version2,"-bash:").size()==0)
			{
				json.addProperty(elements.get(3).attr("name"),version2.trim());
			}
			else {
				String version3 =FindExec.commandExec(elements.get(3).attr("command3"));//指令
				if(version2!=null&&PatternUtil.getMatch(version2,"-bash:").size()==0)
				{
					json.addProperty(elements.get(3).attr("name"),version3.trim());
				}
			}
		}
		
		
	 //获得资产类型
		if (PatternUtil.getMatch(version,"CentOS").size()>0 ){
			
		
		json.addProperty(elements.get(2).attr("name"),"CentOS");
		}
		if (PatternUtil.getMatch(version,"RedHat").size()>0 ){
			
			
			json.addProperty(elements.get(2).attr("name"),"RedHat");
			}
		
		//获得子网掩码
		String mask= FindExec.commandExec(elements.get(4).attr("command"));//指令
		
		
	
		if(mask!=null){
			if(PatternUtil.getMatch(mask,"Mask:").size()>0)
			{
				json.addProperty(elements.get(4).attr("name"),mask.split("Mask:")[1].split("\n")[0].trim());		
			}
			
		}
	
		
		
		//获得MAC地址
		String mac= FindExec.commandExec(elements.get(5).attr("command"));//指令
		
	
		//解析mac地址
		if(mac!=null)
		{
			
			if(PatternUtil.getMatch(mac,"HWaddr").size()>0)
			{
			 json.addProperty(elements.get(5).attr("name"),mac.split("HWaddr ")[1].split(" ")[0].trim());
			
			}
	
		}
		
		
		//获得不同的网卡地址
		String ipCard= FindExec.commandExec(elements.get(8).attr("command"));//指令
		if(ipCard!=null)
		{
			if(PatternUtil.getMatch(ipCard,"inet addr:").size()>0)
			{
			String [] netCard=ipCard.split("inet addr:");
			
			
			for(int i=0;i<netCard.length-2;i++)
			{
				
				String[] string1=netCard[i+2].split(" ");
				String ip1=string1[0].trim();
				if (!ip1.equals("127.0.0.1")) {
					json.addProperty(elements.get(8).attr("name"),ip1);
				}
			  
				
			}
			
			}	
		}
		
		
		//获得主机的默认网关
		String gateway= FindExec.commandExec(elements.get(7).attr("command"));//指令
		if(gateway!=null)
		{
			if (PatternUtil.getMatch(gateway,"\n").size()>4) {
				
			
			
		      gateway=gateway.split("\n")[4];
		     json.addProperty(elements.get(7).attr("name"),gateway.trim());
			}
			else if (PatternUtil.getMatch(gateway,"\n").size()>3) {

			    gateway=gateway.split("\n")[3];
			     json.addProperty(elements.get(7).attr("name"),gateway.trim());
			}
		}
		//获得主机的广播地址
		String bcast= FindExec.commandExec(elements.get(6).attr("command"));//指令
		if(bcast!=null)
		{
			if (PatternUtil.getMatch(bcast,"Bcast:\\d+\\.\\d+\\.\\d+\\.\\d+").size()>0) {
			  bcast=bcast.split("Bcast:")[1].split(" ")[0];	 
			 json.addProperty(elements.get(6).attr("name"),bcast.trim());
			}
		}

	return json;
	}

	


}
