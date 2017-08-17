package com.boco.desc.enty;

import java.io.Serializable;

public class BaseNmap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1562628676397215575L;
  
	private Integer port;
	private String serviceName;
	private String version;
	private String portProtocol;
	private String state;
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPortProtocol() {
		return portProtocol;
	}
	public void setPortProtocol(String portProtocol) {
		this.portProtocol = portProtocol;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BaseNmap [port=" + port + ", serviceName=" + serviceName + ", version=" + version + ", portProtocol="
				+ portProtocol + ", state=" + state + "]";
	}
	
	
	
	
	
}
