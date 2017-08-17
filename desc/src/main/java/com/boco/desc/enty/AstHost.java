package com.boco.desc.enty;

import java.util.List;

public class AstHost extends Asset{

	private String mangagePort;
	private String mac;
	private String bcast;
	private String mask;
	private String gateway;
	private Integer protocolId;
	private List<String> ipCard; //一台主机的所以从IP
	
	
	public List<String> getIpCard() {
		return ipCard;
	}

	public void setIpCard(List<String> ipCard) {
		this.ipCard = ipCard;
	}

	public String getMangagePort() {
		return mangagePort;
	}

	public void setMangagePort(String mangagePort) {
		this.mangagePort = mangagePort;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getBcast() {
		return bcast;
	}

	public void setBcast(String bcast) {
		this.bcast = bcast;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public Integer getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(Integer protocolId) {
		this.protocolId = protocolId;
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5197551642542822249L;



	@Override
	public String toString() {
		return "AstHost [mangagePort=" + mangagePort + ", mac=" + mac + ", bcast=" + bcast + ", mask=" + mask
				+ ", gateway=" + gateway + ", protocolId=" + protocolId + ", ipCard=" + ipCard + "]";
	}

	
	

}
