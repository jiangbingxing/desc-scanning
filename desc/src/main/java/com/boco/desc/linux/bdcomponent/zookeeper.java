package com.boco.desc.linux.bdcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import com.boco.desc.analysis.Analysiser;
import com.boco.desc.analysis.FindExec;
import com.boco.desc.util.BDid;
import com.boco.desc.util.PatternUtil;

import com.google.gson.JsonObject;

public class zookeeper extends Analysiser {
	private static Logger logger = Logger.getLogger(spark.class);
	
	
	@Override
	public List<JsonObject> Find(Elements elements) {
		// TODO Auto-generated method stub
		
		List<JsonObject> jsonObjects=new ArrayList<JsonObject>();
		Elements elements2=elements.select("component");
		String[] hangs=null;
		totalString=FindExec.commandExec(elements2.get(10).attr("command1"));
		if (totalString==null) {
			logger.error("the password is erro or the network no connection");
			return null;
		}
		 hangs=totalString.split("\n");
		if(totalString!=null &&hangs.length>1)
		{
			for (int i = 0; i < hangs.length; i++) {
				JsonObject jsonObject=new JsonObject();
				String lie[]=hangs[i].split("\\s+");
				String username=lie[0];
				String procesId=lie[1];
				String path=null;
				 if (lie[7].equals("grep")) {
					continue;
				}else if(PatternUtil.getMatch(hangs[i],"/zookeeper").size()>0){
					String temp=hangs[i].split("/zookeeper")[1];
					path=temp.split(" ")[0];
					logger.error(path);
					
				}else {
					path="unkown";
				}
				
				String portString=FindExec.commandExec(elements.attr("command")+procesId+"|awk '{print $4}'");
				if(portString!=null)
				{
					 
					 if(portString!=null&&!portString.equals(""))
					 {
						 String[] pStrings=null;
						 if (PatternUtil.getMatch(portString,"\n").size()>0) {
							
						
					        pStrings=portString.split("\n");
						 }
					
					 for (int j = 0; j < pStrings.length; j++) {
						 if (PatternUtil.getMatch(pStrings[j],":\\d+").size()>0) {
							
						 String[] nativePorts=pStrings[j].split(":");
					     String nativePort=nativePorts[nativePorts.length-1];
					     jsonObject.addProperty(elements.attr("property2"),nativePort);
						 }	   
				}
					 
					
		} 
				
					  
				}
				 jsonObject.addProperty("id",BDid.Zookeeper);
				 jsonObject.addProperty(elements.attr("property1"),username);
				   jsonObject.addProperty(elements.attr("property3"),path);
				   jsonObject.addProperty("typeName",elements2.get(10).attr("name"));
				   jsonObject.addProperty("dataType",elements2.get(10).attr("dataType1"));
				   jsonObject.addProperty("astType",elements.attr("astType"));
				   jsonObjects.add(jsonObject);
			}
		}
		
		return jsonObjects;
	}
}
