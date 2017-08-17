package com.boco.desc.dto;

import java.util.List;

import com.boco.desc.enty.BDComponent;


public class BDComponentScanning {

	private List<BDComponent> bdComponents;
	private Integer resultCode;
	public List<BDComponent> getBdComponents() {
		return bdComponents;
	}
	public void setBdComponents(List<BDComponent> bdComponents) {
		this.bdComponents = bdComponents;
	}
	
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	@Override
	public String toString() {
		return "BDComponentScanning [bdComponents=" + bdComponents + ", resultCode=" + resultCode + "]";
	}
	
     

}
