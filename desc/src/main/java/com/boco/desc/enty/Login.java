package com.boco.desc.enty;

import com.asiainfo.uap.util.des.EncryptInterface;

public class Login {
	private String ip;
	private String username;
	private int port;
	private String password;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Login(String ip, String username, int port, String password) {
		super();
		this.ip = ip;
		this.username = username;
		this.port = port;
		this.password = password;
	}
	public Login(String ip, String username, String password) {
		
		this(ip, username, 22,password);
	}
 
	public void Decrypt()
	{
		setPassword(EncryptInterface.desUnEncryptData(password));
	}
	@Override
	public String toString() {
		return "Login [ip=" + ip + ", username=" + username + ", port=" + port + ", password=" + password + "]";
	}
	

}
