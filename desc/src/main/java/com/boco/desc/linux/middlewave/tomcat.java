package com.boco.desc.linux.middlewave;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import com.boco.desc.analysis.Analysiser;
import com.boco.desc.analysis.FindExec;

import com.boco.desc.util.PatternUtil;

import com.google.gson.JsonObject;

public class  tomcat extends Analysiser {
	private static Logger logger = Logger.getLogger(tomcat.class);
	
	
	@Override
	public List<JsonObject> Find(Elements elements) {
		// TODO Auto-generated method stub
		List<JsonObject> jsonObjects =new ArrayList<JsonObject>();
		
		Elements elements2=elements.select("component");
		String[] hangs=null;
		totalString=FindExec.commandExec(elements2.get(0).attr("command1"));
		if (totalString==null) {
			logger.error("the password is erro or the network no connection");
			return null;
		}
		 hangs=totalString.split("\n");
		if(totalString!=null &&hangs.length>1)
		{
			for (int i = 0; i < hangs.length; i++) {
				
				String lie[]=hangs[i].split("\\s+");
				
				String username=lie[0];
				String procesId=lie[1];
				String path=null;
				 if (lie[7].equals("grep")) {
					continue;
				}else if(PatternUtil.getMatch(hangs[i],"-Dcatalina.home=").size()>0){
					String temp=hangs[i].split("-Dcatalina.home=")[1];
					path=temp.split(" ")[0];
					
					
				}else {
					path="unkown";
				}
				
				String portString=FindExec.commandExec(elements.attr("command")+" "+procesId+"|awk '{print $4}'");
				logger.debug(portString);
				if(portString!=null)
				{
					 
					 if(portString!=null&&!portString.equals(""))
					 {
						 String[] pStrings=null;
						 if (PatternUtil.getMatch(portString,"\n").size()>0) {
							
						
					        pStrings=portString.split("\n");
						
					
					 for (int j = 0; j < pStrings.length; j++) {
						 if (PatternUtil.getMatch(pStrings[j],":\\d+").size()>0) {
							 JsonObject jsonObject=new JsonObject();
						 String[] nativePorts=pStrings[j].split(":");
					     String nativePort=nativePorts[nativePorts.length-1];
					     jsonObject.addProperty(elements.attr("property2"),nativePort);
					     jsonObject.addProperty("astType","中间件");
						   jsonObject.addProperty("typeName","tomcat");
						   jsonObject.addProperty(elements.attr("property1"),username);
						   jsonObject.addProperty(elements.attr("property3"),path);
						   jsonObject.addProperty(elements.attr("property4"),procesId);
						   
						   logger.debug(jsonObject);
						   jsonObjects.add(jsonObject);
						 }
					 }
					 }
					
					
		}
					
				}
				
				
			}
			return jsonObjects;
		}
		   else
		     {
		    	 return null;
		     }
	}
}
