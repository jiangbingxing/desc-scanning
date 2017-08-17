package com.boco.desc.enty;

public class IpAndCommod {
	private String ip;
	private String Commond;
	private String path;
	
	
	public IpAndCommod() {
		this("127.0.0.1","nmap -A","C:\\Program Files (x86)\\Nmap");
	}
	
	public IpAndCommod(String ip, String path) {
		super();
		this.ip = ip;
		this.path = path;
	}

	public IpAndCommod(String ip, String commond, String path) {
		super();
		this.ip = ip;
		Commond = commond;
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
		return Commond;
	}
	public void setCommond(String commond) {
		Commond = commond;
	}
	
}
