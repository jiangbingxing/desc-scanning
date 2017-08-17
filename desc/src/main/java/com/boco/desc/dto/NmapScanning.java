package com.boco.desc.dto;

import java.util.List;

import com.boco.desc.enty.BaseNmap;

public class NmapScanning extends BaseNmap{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974781628039703367L;
	
	
	private List<BaseNmap> baseNmaps;
	private String OS;
	private Integer resultCode;
	

	public List<BaseNmap> getBaseNmaps() {
		return baseNmaps;
	}


	public void setBaseNmaps(List<BaseNmap> baseNmaps) {
		this.baseNmaps = baseNmaps;
	}


	public Integer getResultCode() {
		return resultCode;
	}


	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}


	public String getOS() {
		return OS;
	}


	public void setOS(String oS) {
		OS = oS;
	}


	public NmapScanning() {
		super();
	}


	@Override
	public String toString() {
		return "NmapScanning [baseNmaps=" + baseNmaps + ", OS=" + OS + ", resultCode=" + resultCode + "]";
	}


}
