package com.boco.desc.enty;

import java.io.Serializable;

public class BDComponent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4304184243599717627L;
	private Integer serverPort;
	private String componentName;
	private String installPath;
	private String installUser;
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getInstallPath() {
		return installPath;
	}
	public void setInstallPath(String installPath) {
		this.installPath = installPath;
	}
	public String getInstallUser() {
		return installUser;
	}
	public void setInstallUser(String installUser) {
		this.installUser = installUser;
	}
	@Override
	public String toString() {
		return "BDComponent [serverPort=" + serverPort + ", componentName=" + componentName + ", installPath="
				+ installPath + ", installUser=" + installUser + "]";
	}
	
	

}
