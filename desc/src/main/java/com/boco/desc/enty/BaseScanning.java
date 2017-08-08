package com.boco.desc.enty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.boco.desc.result.Result;


public class BaseScanning implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assetName; //资产名
	private String manuFacturer;// 厂商
	private String version;// 版本
	private String assetType;// 资产类型
	private String DefaultGateway; // 默认网关
	private String MacAddres; //物理地址ַ
	private List<Component> component;//安装的组件
	private String activeIp; //扫描的ip
	private String cardIp[];//网卡的ip

	

	public BaseScanning(String assetName, String manuFacturer, String version, String assetType, String defaultGateway,
			String macAddres, List<Component> component, String activeIp, String[] cardIp) {
		super();
		this.assetName = assetName;
		this.manuFacturer = manuFacturer;
		this.version = version;
		this.assetType = assetType;
		DefaultGateway = defaultGateway;
		MacAddres = macAddres;
		this.component = component;
		this.activeIp = activeIp;
		this.cardIp = cardIp;
	}


	@Override
	public String toString() {
		return "BaseScanning [assetName=" + assetName + ", manuFacturer=" + manuFacturer + ", version=" + version
				+ ", assetType=" + assetType + ", DefaultGateway=" + DefaultGateway + ", MacAddres=" + MacAddres
				+ ", component=" + component + ", activeIp=" + activeIp + ", cardIp=" + Arrays.toString(cardIp) + "]";
	}


	public String getActiveIp() {
		return activeIp;
	}


	public void setActiveIp(String activeIp) {
		this.activeIp = activeIp;
	}


	public String[] getCardIp() {
		return cardIp;
	}


	public void setCardIp(String[] cardIp) {
		this.cardIp = cardIp;
	}


	public BaseScanning() {
		super();
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
	public List<Component> getComponent() {
		return component;
	}
	public void setComponent(List<Component> component) {
		this.component = component;
	}


	
	
	


}
