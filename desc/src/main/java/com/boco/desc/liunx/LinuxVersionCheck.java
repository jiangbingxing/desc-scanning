package com.boco.desc.liunx;



import com.boco.desc.enty.Login;
import com.boco.desc.util.CommandUtils;
import com.boco.sces.command.Command;
import com.boco.sces.command.CommandImpl;
import com.boco.sces.login.UserInfo;
import com.boco.sces.result.Result;

public class LinuxVersionCheck {
	
	public  static int VersionCheck(Login login) {
		
		try {
			UserInfo userInfo = new UserInfo(login.getIp(),login.getPort(),
					login.getUsername(), login.getPassword());
			
			//获得版本名
			Command command = new CommandImpl(LinuxCommand.VERSION);//指令
			Result result1 = CommandUtils.execute(userInfo, command);
			if(result1.getCode()==0 &&result1.getMessage()!=null)
			{
				String ver=result1.getMessage().split("release ")[1];
				String version=ver.split("\\.")[0].trim();
				if(version!=null)
				{
					int num=Integer.parseInt(version);
					if(num==6)
					{
						return 6;
					}
					if (num==7) {
						return 7;
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
	
			e.printStackTrace();
			return 6;
			
		}
		
		return 6;
		
		
	}
    

}
