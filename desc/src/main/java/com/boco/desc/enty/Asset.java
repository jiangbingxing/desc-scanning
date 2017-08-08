package com.boco.desc.enty;

import java.util.Arrays;

public class Asset {
	private String assetName; //资产名
	private String manuFacturer;// 厂商
	private String version;// 版本
	private String assetType;// 资产类型
	private String DefaultGateway; // 默认网关
	private String MacAddres; //物理地址
	private String ips[];//网卡地址
	
	public String[] getIps() {
		return ips;
	}
	public void setIps(String[] ips) {
		this.ips = ips;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getManuFacturer() {
		return manuFacturer;
	}
	public void setManuFacturer(String manuFacturer) {
		this.manuFacturer = manuFacturer;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getDefaultGateway() {
		return DefaultGateway;
	}
	public void setDefaultGateway(String defaultGateway) {
		DefaultGateway = defaultGateway;
	}
	public String getMacAddres() {
		return MacAddres;
	}
	public void setMacAddres(String macAddres) {
		MacAddres = macAddres;
	}
	
	public Asset() {
		super();
	}
	@Override
	public String toString() {
		return "Asset [assetName=" + assetName + ", manuFacturer=" + manuFacturer + ", version=" + version
				+ ", assetType=" + assetType + ", DefaultGateway=" + DefaultGateway + ", MacAddres=" + MacAddres
				+ ", ips=" + Arrays.toString(ips) + "]";
	}
	public Asset(String assetName, String manuFacturer, String version, String assetType, String defaultGateway,
			String macAddres, String[] ips) {
		super();
		this.assetName = assetName;
		this.manuFacturer = manuFacturer;
		this.version = version;
		this.assetType = assetType;
		DefaultGateway = defaultGateway;
		MacAddres = macAddres;
		this.ips = ips;
	}
	
	
}
