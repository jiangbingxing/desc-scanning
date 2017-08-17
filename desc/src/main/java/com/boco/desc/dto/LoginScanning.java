package com.boco.desc.dto;

import java.io.Serializable;

import java.util.List;

import com.boco.desc.enty.Asset;
import com.boco.desc.enty.AstDB;
import com.boco.desc.enty.AstHost;
import com.boco.desc.enty.AstMiddleWare;
import com.boco.desc.enty.AstSoftWare;



public class LoginScanning extends Asset implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 5733995910796120235L;
	
	private List<AstDB> astDBs;
	private AstHost astHosts;
	private List<AstMiddleWare> astMiddleWares;
	private List<AstSoftWare> astSoftWares;
	private Integer resultCode;
	public List<AstDB> getAstDBs() {
		return astDBs;
	}
	public void setAstDBs(List<AstDB> astDBs) {
		this.astDBs = astDBs;
	}

	public List<AstMiddleWare> getAstMiddleWares() {
		return astMiddleWares;
	}
	public void setAstMiddleWares(List<AstMiddleWare> astMiddleWares) {
		this.astMiddleWares = astMiddleWares;
	}
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	
	
	public List<AstSoftWare> getAstSoftWares() {
		return astSoftWares;
	}
	public void setAstSoftWares(List<AstSoftWare> astSoftWares) {
		this.astSoftWares = astSoftWares;
	}
	
	public AstHost getAstHosts() {
		return astHosts;
	}
	public void setAstHosts(AstHost astHosts) {
		this.astHosts = astHosts;
	}
	@Override
	public String toString() {
		return "LoginScanning [astDBs=" + astDBs + ", astHosts=" + astHosts + ", astMiddleWares=" + astMiddleWares
				+ ", astSoftWares=" + astSoftWares + ", resultCode=" + resultCode + "]";
	}

	
	
	

	

}
