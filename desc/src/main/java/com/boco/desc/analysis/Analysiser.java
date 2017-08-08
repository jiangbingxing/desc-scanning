package com.boco.desc.analysis;



import java.util.List;

import com.boco.desc.enty.BaseScanning;
import com.boco.desc.enty.Component;
import com.boco.desc.enty.IpAndCommod;
import com.boco.desc.enty.Login;



	
public interface Analysiser {

	BaseScanning SingleScanning(String output,Login login);
	List<Component>NmapScanning(String ip);
	
	
}
