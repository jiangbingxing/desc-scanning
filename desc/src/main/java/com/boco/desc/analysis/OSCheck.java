package com.boco.desc.analysis;







public class OSCheck  {


	public static String OScommand="uname";
	
	public static String getOS () {
		String OS=FindExec.commandExec(OScommand);
		return OS;
		
	}
	        
	     
		
		
	



}
