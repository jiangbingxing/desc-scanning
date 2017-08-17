package com.boco.desc.liunx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.boco.desc.enty.AstDB;
import com.boco.desc.enty.Login;

import com.boco.desc.util.CommandUtils;
import com.boco.desc.util.LoadUtil;
import com.boco.desc.version.AppType;
import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;



public class AstDBAnalysiser {
	

	/*数据库应用的查找
	 * *
	 */
	 
	public List<AstDB> DBFind(Login login) throws IOException
	 {
		List<AstDB> com=new ArrayList<AstDB>();	
		 List<AstDB> astDBs=new ArrayList<AstDB>();
		 
		 //设置登录信息
		 UserInfo userInfo = new UserInfo(login.getIp(),login.getPort(),
				 login.getUsername(), login.getPassword());
		 
		 //从配置文件取回所有的未安装应用的 命令
		 List<String> propertiesList=LoadUtil.LoadPreperties("ast_db.properties");
		 
		 if(propertiesList!=null&&propertiesList.size()%2==0)
			 
			 for (int m = 0; m < propertiesList.size()/2; m++) {
				 
			//获得未安装应用的名字
				 String appName=propertiesList.get(2*m).split(" ")[2];
				 
				 
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
											 AstDB astDB=new AstDB();
											 String[] nativePorts=pStrings[j].split(":");
											 String nativePort=nativePorts[nativePorts.length-1];
											 if(nativePort!=null &&!nativePort.equals(" "));
											 {
											 astDB.setServerPort((Integer.parseInt(nativePort.trim())));
											 astDBs.add(astDB);
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
					     //String username=pathStrings[0].split(" ")[0];
						 String path=pathStrings[1].split(" ")[0];
						 for (int i = 0; i < astDBs.size(); i++) {
								astDBs.get(i).setInstallPath(path);;
								astDBs.get(i).setAstStatus("open");
								astDBs.get(i).setAstName(appName);
								astDBs.get(i).setManageIp(login.getIp());
								astDBs.get(i).setAstTypeId1(AppType.MINDDLEWARE);
								
						}
						 }
						 else {
							return com;
						}
					 }
					    return astDBs;                
					 
				 } catch (Exception e) {
					 e.printStackTrace();
					 System.out.println("登录异常");
				 }
				
			}
		return com;	 
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		  List<AstDB> aastDBs=new ArrayList<AstDB>();
	      AstDBAnalysiser speAssetLinux=new AstDBAnalysiser();
	      Login login=new Login("10.108.226.63", "root","rootroot");
	      List<AstDB> astDBs= speAssetLinux.DBFind((login));
	      aastDBs.addAll(astDBs);
	      System.out.println(aastDBs);
	}
	
	 
}
