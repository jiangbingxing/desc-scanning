package com.boco.desc.liunx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import com.boco.desc.dto.BDComponentScanning;

import com.boco.desc.enty.BDComponent;

import com.boco.desc.enty.Login;
import com.boco.desc.result.ResultCode;
import com.boco.desc.util.CommandUtils;
import com.boco.desc.util.LoadUtil;

import com.boco.desc.version.BGComponentType;
import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;



public class BDComponentAnalysiser {
	
	
	/*大数据的查找
	 * 
	 * *
	 */
	
	 
	public BDComponentScanning Find(Login login) throws IOException
	 {
		 BDComponentScanning bdComponentScanning=new BDComponentScanning();
		 List<BDComponent> bdComponents=new ArrayList<BDComponent>();
		 
		 //设置登录信息
		 UserInfo userInfo = new UserInfo(login.getIp(),login.getPort(),
				 login.getUsername(), login.getPassword());
		 
		 //从配置文件取回所有的未安装应用的 命令
		 List<String> propertiesList=LoadUtil.LoadPreperties("bd_component.properties");
		 
		 if(propertiesList!=null&&propertiesList.size()%2==0)
			 
			 for (int m = 0; m < propertiesList.size()/2; m++) {
		
				 
				 
				 //指令结果
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
											 BDComponent bdComponent=new BDComponent();
											 String[] nativePorts=pStrings[j].split(":");
											 String nativePort=nativePorts[nativePorts.length-1];
											 if(nativePort!=null &&!nativePort.equals(" "));
											 {
											 bdComponent.setServerPort(((Integer.parseInt(nativePort.trim()))));
											 bdComponents.add(bdComponent);
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
						 for (int i = 0; i < bdComponents.size(); i++) {
							bdComponents.get(i).setInstallPath(path);;
							bdComponents.get(i).setInstallUser(username);;
							bdComponents.get(i).setComponentName(Arrays.asList(BGComponentType.bgComponent).get(i));
							
						}
						
						 bdComponentScanning.setBdComponents(bdComponents);
						 bdComponentScanning.setResultCode(ResultCode.NMAP_LOGIN_SUCCESS);
						 return bdComponentScanning;
						 }
						 else {
							return null;
						}
					 }
					              
					 
				 } catch (Exception e) {
					 e.printStackTrace();
					 System.out.println("登录异常");
				 }
				
			}
		 bdComponentScanning.setResultCode(ResultCode.LOGIN_FAIL);
		return bdComponentScanning ;	 
	}

	 
}
