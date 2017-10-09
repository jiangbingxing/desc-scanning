package com.boco.desc.util;

import java.io.IOException;

import com.boco.sces.command.Command;

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
			
		} 
	
		
		return ri;
	}
	public static void main(String[] args) {
	System.out.println(System.getProperties());
	}
}