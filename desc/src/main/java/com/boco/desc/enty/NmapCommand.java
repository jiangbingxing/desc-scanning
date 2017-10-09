package com.boco.desc.enty;

public class NmapCommand {
	private String ip;
	private String Command;
	private String path;
	
	
	public NmapCommand() {
		this("127.0.0.1","nmap -p 0-65535","C:\\Program Files (x86)\\Nmap");
	}
	
	public NmapCommand(String ip, String path) {
		super();
		this.ip = ip;
		this.path = path;
	}

	public NmapCommand(String ip, String commond, String path) {
		super();
		this.ip = ip;
		Command = commond;
		this.path = path;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCommond() {
		return Command;
	}
	public void setCommond(String commond) {
		Command = commond;
	}
	
}
