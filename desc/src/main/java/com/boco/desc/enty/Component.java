package com.boco.desc.enty;

import java.io.Serializable;

/**
 * 组件
 * @author someone
 *
 */
public class Component implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;//应用的名称
	private String path;//应用的安装路径
	private String version;//应用的版本号
	private int port;//端口号
	private String status;//开启状态
	private String username;//正在使用该应用的账户          
	private String appType; //应用的类型
	private int result;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Component() {
		super();
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int success) {
		this.result = success;
	}

	public Component(String name, String path, String version, int port, String status, String username, String appType,
			int result) {
		super();
		this.name = name;
		this.path = path;
		this.version = version;
		this.port = port;
		this.status = status;
		this.username = username;
		this.appType = appType;
		this.result = result;
	}
	@Override
	public String toString() {
		return "Component [name=" + name + ", path=" + path + ", version=" + version + ", port=" + port + ", status="
				+ status + ", username=" + username + ", appType=" + appType + ", result=" + result + "]";
	}

	
}
