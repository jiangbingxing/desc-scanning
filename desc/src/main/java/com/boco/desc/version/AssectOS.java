package com.boco.desc.version;

import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;

import com.boco.desc.enty.IpAndCommod;

public class AssectOS {
	public final static String LINUX="Linux";
	public final static String IBM="AIX";
	public final static String WINDOWS="Windows";
	  
	public static String OSCheck(String ip){
		
		
		return ip;
		
	}
	public static void main(String[] args) {
		
		IpAndCommod ic=new IpAndCommod();
		
		Nmap4j nmap4j = new Nmap4j(ic.getPath());
		nmap4j.addFlags(ic.getCommond());
		nmap4j.includeHosts("10.101.167.174");
		try {
			nmap4j.execute();
			System.out.println(nmap4j.getOutput());
		} catch (NMapInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NMapExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
