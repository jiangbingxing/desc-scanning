package com.boco.desc.util;

import java.io.IOException;

import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.executor.CommandExecutor;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;
import com.boco.sces.result.ResultInfo;
import com.boco.sces.util.ErrorMessageUtil;

public class CommandUtils{
	public static Result execute(UserInfo info, Command command){
		Result ri;
		try {
			ri = new CommandExecutor(info, command).execute();
		} catch (IOException e) {
			ri = new ResultInfo(false, 126, ErrorMessageUtil.getErrorMessageByErrorCode(126));
		} finally {
			try {
				info.logout();
			} catch (IOException e) {
			}
		}
		return ri;
	}
	public static void main(String[] args) {
		UserInfo userInfo = new UserInfo("10.101.167.188", 22, "root", "rootroot");// 主机的IP,端口，登录账号，密码
		Command command = new CommandImpl("ifconfig");//指令
		Result result = execute(userInfo, command);
		String [] netCard=result.getMessage().split("inet addr:");
		String[] netcard2=new String[netCard.length];
		
		for(int i=0;i<netCard.length-1;i++)
		{
			
			String[] string1=netCard[i+1].split(" ");
			String ip1=string1[0].trim();
		    netcard2[i]=ip1;
		    System.out.println(netcard2[i]);
			
		}
	}
}