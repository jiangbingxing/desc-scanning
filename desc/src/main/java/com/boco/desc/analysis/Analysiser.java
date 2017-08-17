package com.boco.desc.analysis;



import com.boco.desc.dto.LoginScanning;
import com.boco.desc.dto.NmapScanning;
import com.boco.desc.enty.Login;



	
public interface Analysiser {

	LoginScanning getLoginScanning(String output,Login login);
	NmapScanning getNmapScanning(String ip);
	
	
	
	
}
