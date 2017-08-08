package com.boco.desc.liunx;

import com.boco.desc.analysis.BaseAsset;
import com.boco.desc.enty.Asset;

import com.boco.desc.enty.Login;
import com.boco.desc.util.CommandUtils;
import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;


public class BaseAssetLinuxImpl2 implements BaseAsset{

     Asset resouce=new Asset();
	public Asset getBaseAsset(Login login) {
		// TODO Auto-generated method stub
		try {
			// 主机的IP,端口，登录账号，密码
			UserInfo userInfo = new UserInfo(login.getIp(),login.getPort(),
					login.getUsername(), login.getPassword());
			
			
			
			//获得资产名
			Command command = new CommandImpl(LinuxCommand2.ASSET_NAME);//指令
			Result result1 = CommandUtils.execute(userInfo, command);
			
			if(result1.getCode()==0)
			{
	                resouce.setAssetName(result1.getMessage().trim());
			}
			
			
			//获得资产类型
			Command command2 = new CommandImpl(LinuxCommand2.ASSET_TYPE);//指令
			
				Result result2= CommandUtils.execute(userInfo, command2);
				
				if(result2.getCode()==0 &&result2.getMessage()!=null)
				{
					resouce.setAssetType(result2.getMessage().trim());
				}
			
			//获得厂商
			Command command3 = new CommandImpl(LinuxCommand2.MANU_FACTURER);//指令
			Result result3= CommandUtils.execute(userInfo, command3);
	
			if(result3.getCode()==0)
			{
			resouce.setManuFacturer(result3.getMessage().trim());
			}
			
			//获得版本号
			Command command4 = new CommandImpl(LinuxCommand2.VERSION);//指令
			Result result4= CommandUtils.execute(userInfo, command4);
			
			if(result4.getCode()==0)
			{
		    resouce.setVersion(result4.getMessage().trim());
			}
			
			//获得默认网关
			Command command5= new CommandImpl(LinuxCommand2.DEFAULT_GATEWAY);//指令
			Result result5= CommandUtils.execute(userInfo, command5);
		
			if(result5.getCode()==0){
				
		            resouce.setDefaultGateway( result5.getMessage().trim());
			}
			
			
			//获得MAC地址
			Command command6= new CommandImpl(LinuxCommand2.MAC_ADDRESS);//指令
			Result result6= CommandUtils.execute(userInfo, command6);
			
			//解析mac地址
			if(result6.getCode()==0)
			{
			String[] string3=result6.getMessage().split("ether ");
			String[] string4=string3[1].split(" ");
			resouce.setMacAddres(string4[0].trim());
			}
			
			//获得不同的网卡地址
			Command command7= new CommandImpl(LinuxCommand2.NET_CARD_ADDRESS);
			Result result7= CommandUtils.execute(userInfo, command7);
			if(result7.getCode()==0)
			{
			String [] netCard=result7.getMessage().split("inet ");
			String[] netcard2=new String[netCard.length-1];
			
			for(int i=0;i<netCard.length-1;i++)
			{
				
				String[] string1=netCard[i+1].split(" ");
				String ip1=string1[0].trim();
			    netcard2[i]=ip1;
				
			}
			resouce.setIps(netcard2);
			
			}	
			userInfo.logout();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("账号或密码不对");
		}
		
		return resouce;
	}
	
	
	public  String getPathAndUser(Login login,String Service)
	{
		Result result1=null;
		Result result2=null;
		String pathString=null;
		String userString=null;
		try {
			UserInfo userInfo = new UserInfo(login.getIp(),login.getPort(),
					login.getUsername(), login.getPassword());
			//得到安装路径
			StringBuilder stringBuilder=new StringBuilder(LinuxCommand2.COMPONENT_PATH);
			stringBuilder.append(" ");
			stringBuilder.append(Service);
			
			Command command1= new CommandImpl(stringBuilder.toString());//指令
			result1 = CommandUtils.execute(userInfo, command1);
			
			if(result1.getCode()==0&&result1.getMessage()!=null)
			{
			
			//解析安装路径
			
			 pathString=result1.getMessage().trim();
			}
			
			//得到使用应用的用户账号
			StringBuilder stringBuilder2=new StringBuilder(LinuxCommand2.COMPONENT_USER);
			stringBuilder2.append(" ");
			stringBuilder2.append(Service);
			Command command2= new CommandImpl(stringBuilder2.toString());//指令
			result2 = CommandUtils.execute(userInfo, command2);
			if(result2.getCode()==0)
			{
			
			//解析用户账号
			String[] userStrings=result2.getMessage().split(" ");
			 userString=userStrings[0].trim();
			}
			
		userInfo.logout();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("登录超时或无数据");
			return "登录超时或无数据@[登录超时或无数据";
		}
		String resString=pathString+" 星星"+userString;
		
		return resString;
		
	}
	

	public static void main(String[] args) {
		String cString="CentOS";
		System.out.println(cString.equals("CentOS"));
	}
      
}