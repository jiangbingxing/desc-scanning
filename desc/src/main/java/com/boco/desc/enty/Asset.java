package com.boco.desc.enty;

import java.io.Serializable;




public class Asset implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6758442773698512020L;
	private String astName; //资产名
	private String manageIp;//管理ip
	private String astStatus;//资产状态
	private Integer astTypeId1;
	private Integer astTypeId2;
	private String vendor;
	private String astModel;
	private String systemVersion;
	public String getAstName() {
		return astName;
	}
	public void setAstName(String astName) {
		this.astName = astName;
	}
	public String getManageIp() {
		return manageIp;
	}
	public void setManageIp(String manageIp) {
		this.manageIp = manageIp;
	}
	public String getAstStatus() {
		return astStatus;
	}
	public void setAstStatus(String astStatus) {
		this.astStatus = astStatus;
	}
	public Integer getAstTypeId1() {
		return astTypeId1;
	}
	public void setAstTypeId1(Integer astTypeId1) {
		this.astTypeId1 = astTypeId1;
	}
	public Integer getAstTypeId2() {
		return astTypeId2;
	}
	public void setAstTypeId2(Integer astTypeId2) {
		this.astTypeId2 = astTypeId2;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getAstModel() {
		return astModel;
	}
	public void setAstModel(String astModel) {
		this.astModel = astModel;
	}
	public String getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	@Override
	public String toString() {
		return "Asset [astName=" + astName + ", manageIp=" + manageIp + ", astStatus=" + astStatus + ", astTypeId1="
				+ astTypeId1 + ", astTypeId2=" + astTypeId2 + ", vendor=" + vendor + ", astModel=" + astModel
				+ ", systemVersion=" + systemVersion + "]";
	}
	
	
	
	
	
	
}
