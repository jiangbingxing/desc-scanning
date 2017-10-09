package com.boco.desc.linux.db;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import com.boco.desc.analysis.Analysiser;
import com.boco.desc.analysis.FindExec;

import com.boco.desc.util.PatternUtil;

import com.google.gson.JsonObject;

public class  mysql extends Analysiser {
	private static Logger logger = Logger.getLogger(mysql.class);
	
	
	@Override
	public List<JsonObject> Find(Elements elements) {
		// TODO Auto-generated method stub
		
		List<JsonObject> jsonObjects=new ArrayList<JsonObject>();
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
				JsonObject jsonObject=new JsonObject();
				String lie[]=hangs[i].split("\\s+");
				String username=lie[0];
				String procesId=lie[1];
				String path=null;
				String sid=null;
				
				 if (lie[7].equals("grep")) {
					continue;
				}else if(PatternUtil.getMatch(hangs[i],"datadir=").size()>0&&
						PatternUtil.getMatch(hangs[i],"pid=").size()>0){
					String temp=hangs[i].split("datadir=")[1];
					path=temp.split(" ")[0];
					temp=hangs[i].split("pid=")[1];
					sid=temp.split(" ")[0];
				
					
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
					 jsonObject.addProperty(elements.attr("property1"),username);
					   jsonObject.addProperty(elements.attr("property3"),path);
					   jsonObject.addProperty("name",elements2.get(0).attr("name"));
					   jsonObject.addProperty(elements.attr("property1"),sid);
					   jsonObject.addProperty("astType",elements.attr("astType"));
					   jsonObjects.add(jsonObject);
					 
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