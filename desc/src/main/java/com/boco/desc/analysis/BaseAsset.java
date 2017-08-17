package com.boco.desc.analysis;

import com.boco.desc.enty.Asset;
import com.boco.desc.enty.AstHost;
import com.boco.desc.enty.Login;

public interface BaseAsset {

	
	public AstHost getBaseAsset(Login login);
    public String getPathAndUser(Login login,String Service);
}
