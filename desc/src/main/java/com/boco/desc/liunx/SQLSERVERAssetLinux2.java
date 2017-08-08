package com.boco.desc.liunx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.boco.desc.enty.Component;
import com.boco.desc.enty.Login;
import com.boco.desc.util.CommandUtils;
import com.boco.desc.util.LoadUtil;
import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class SQLSERVERAssetLinux2 {
	
	List<Component> com=new ArrayList<Component>();
	Component coms=new Component("123","123","123",1,"123", null, null,1);
	
	
	/*未安装应用的查找
	 * *
	 */
	 
	public List<Component> tomcatCompoentFind(Login login) throws IOException
	 {
		List<Component> com=new ArrayList<Component>();
		Component coms=new Component("123","123","123",1,"123", null, null, 1);
		com.add(coms);
		 List<Component> components=new ArrayList<Component>();
		 //设置登录信息
		 UserInfo userInfo = new UserInfo(login.getIp(),login.getPort(),
				 login.getUsername(), login.getPassword());
		 
		 //从配置文件取回所有的未安装应用的 命令
		 List<String> propertiesList=LoadUtil.LoadPreperties("speApplication.properties");
		 if(propertiesList!=null&&propertiesList.size()%2==0)
			 
			 for (int m = 0; m < propertiesList.size()/2; m++) {
				 	
				 Result result1=null;
				 Result result2=null;
				 Result result3=null;				 
				 //取回所有使用未安装应用应用的端口号
				 try {
					 					 
					 Command command1= new CommandImpl(propertiesList.get(2*m)+"|awk '{print $2}'");//指令
					 result1= CommandUtils.execute(userInfo, command1);
					 if(result1.getCode()==0)
					 {
						 String address=result1.getMessage();
						 
							 String[] strings=address.split("\r\n");
							 if(strings.length>=2){
							 for (int i = 0; i < strings.length; i++) {
								 
								 String process=strings[i];
								 try {
									 Command command2= new CommandImpl(propertiesList.get(2*m+1)+" "+process+"|awk '{print $4}'");//指令
									 result2= CommandUtils.execute(userInfo, command2);
									 if(result2.getCode()==0)
									 {
										 String portString=result2.getMessage();
										 if(portString!=null&&!portString.equals(""))
										 {
										 String[] pStrings=portString.split("\r\n");
										 
										 for (int j = 0; j < pStrings.length; j++) {
											 Component component=new Component();
											 String[] nativePorts=pStrings[j].split(":");
											 String nativePort=nativePorts[nativePorts.length-1];
											 if(nativePort!=null &&!nativePort.equals(" "));
											 {
											 component.setPort(Integer.parseInt(nativePort.trim()));
											 components.add(component);
											 }
										 }
										 }
									 }
									 
								 } catch (Exception e) {
									 // TODO: handle exception
									 e.printStackTrace();
									
								 }
							 }
						 }
					 }
				 }
				 
				  catch (Exception e) {
					 e.printStackTrace();
				 }
				 
				 //取得所有未安装程序的路径
				 try {
					 
					 Command command3= new CommandImpl(propertiesList.get(2*m));//指令
					 result3= CommandUtils.execute(userInfo, command3);
					 if(result3.getCode()==0)
					 {
						 String[] pathStrings=result3.getMessage().split(".home=");
						 if(pathStrings!=null&&pathStrings.length>1)
						 {
					     String username=pathStrings[0].split(" ")[0];
						 String path=pathStrings[1].split(" ")[0];
						 for (int i = 0; i < components.size(); i++) {
							components.get(i).setPath(path);
							components.get(i).setUsername(username);
							components.get(i).setStatus("open");
							components.get(i).setName("tomcat");
						}
						 }
						 else {
							return com;
						}
					 }
					    return components;                
					 
				 } catch (Exception e) {
					 e.printStackTrace();
					 System.out.println("登录异常");
				 }
				
			}
		return com;	 
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<Component> aComponents=new ArrayList<Component>();
	     SQLSERVERAssetLinux2 speAssetLinux=new SQLSERVERAssetLinux2();
	     Login login=new Login("10.101.167.174", "root","rootroot");
	      List<Component> components= speAssetLinux.tomcatCompoentFind((login));
	      aComponents.addAll(components);
	      System.out.println(aComponents);
	}
	
	 
}
