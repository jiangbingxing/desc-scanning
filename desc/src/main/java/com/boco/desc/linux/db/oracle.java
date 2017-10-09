package com.boco.desc.linux.db;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import com.boco.desc.analysis.Analysiser;
import com.boco.desc.analysis.FindExec;

import com.boco.desc.util.PatternUtil;

import com.google.gson.JsonObject;

public class oracle extends Analysiser {
	private static Logger logger = Logger.getLogger(oracle.class);
	
	
	@Override
	public List<JsonObject> Find(Elements elements) {
		// TODO Auto-generated method stub
		
		List<JsonObject> jsonObjects=new ArrayList<JsonObject>();
	
		Elements elements2=elements.select("component");
		String[] hangs=null;
		totalString=FindExec.commandExec(elements2.get(1).attr("command1"));
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
				String oracle_home=null;
				 if(PatternUtil.getMatch(hangs[i],"/oracle").size()>0){
					
					path=lie[7];
					logger.error(path);
					
				}
				 //获得oracle的SID和home
				String vi= FindExec.commandExec(elements2.get(1).attr("command2"));
				logger.error("vi");
				if (vi!=null && vi.equals("")) {
					if (PatternUtil.getMatch(vi,"ORACLE_HOME=").size()>0) {
					oracle_home=vi.split("ORACLE_HOME=")[1].split(")")[0];
					
					}
					if (PatternUtil.getMatch(vi,"SID_NAME =").size()>0) {
						sid=vi.split("SID_NAME =")[1].split(")")[0];
						
						}
				}
				
				 //获得oracle的端口号
				String portString=FindExec.commandExec(elements.attr("command")+" "+procesId+"|awk '{print $4}'");
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
				  jsonObject.addProperty("astType","数据库");
				   jsonObject.addProperty("typeName","oracle");
				   jsonObject.addProperty(elements.attr("property1"),username);
				   jsonObject.addProperty(elements.attr("property3"),path);
				   jsonObject.addProperty(elements.attr("property4"),procesId);
				   jsonObject.addProperty(elements.attr("property5"),sid);
				   jsonObject.addProperty(elements.attr("property6"),oracle_home);
				   jsonObject.addProperty("astType",elements.attr("astType"));
				   jsonObjects.add(jsonObject);
			}
			return jsonObjects;
		}
		
     else
     {
    	 return null;
     }
	}
}