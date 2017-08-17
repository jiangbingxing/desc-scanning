package com.boco.desc.enty;

public class AstSoftWare extends Asset{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5301465978659516389L;
	private Integer serverPort;
	private String installPath;
	private String installUser;
	

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
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
		return "AstMiddleWare [serverPort=" + serverPort + ", installPath=" + installPath + ", installUser="
				+ installUser + "]";
	}


	

}
