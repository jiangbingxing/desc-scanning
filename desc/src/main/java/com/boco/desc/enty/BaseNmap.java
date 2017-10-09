package com.boco.desc.enty;

public class BaseNmap {
	private String ServiceName;
	private Integer Port;
	private String State;
	private String PortProtocol;
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public Integer getPort() {
		return Port;
	}
	public void setPort(Integer port) {
		Port = port;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPortProtocol() {
		return PortProtocol;
	}
	public void setPortProtocol(String portProtocol) {
		PortProtocol = portProtocol;
	}
	
}

