package com.boco.desc.enty;

public class AstDB extends Asset {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7576338631654818653L;
	private String SID;
	private Integer serverPort;
	private String installPath;
	

	public String getSID() {
		return SID;
	}


	public void setSID(String sID) {
		SID = sID;
	}


	

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





	@Override
	public String toString() {
		return "AstDB [SID=" + SID + ", serverPort=" + serverPort + ", installPath=" + installPath + "]";
	}


	

}
