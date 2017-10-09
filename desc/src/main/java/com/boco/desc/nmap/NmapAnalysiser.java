package com.boco.desc.nmap;





import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;

import com.boco.desc.dto.NmapScanning;
import com.boco.desc.enty.BaseNmap;
import com.boco.desc.enty.NmapCommand;
import com.boco.desc.result.ResultCode;
import com.boco.desc.util.LoadUtil;

public class NmapAnalysiser {
	
	public NmapScanning getNmapSingleScanning(String ip) {
		NmapScanning nmapScanning=new NmapScanning();
	
		String path=LoadUtil.getNmapPath();
		NmapCommand ic=new NmapCommand();
		Nmap4j nmap4j = new Nmap4j(path);
		nmap4j.addFlags(ic.getCommond());
		nmap4j.includeHosts(ip);
		try {
			nmap4j.execute();
			if( !nmap4j.hasError() ) {

				String output = nmap4j.getOutput() ;

				if( output == null ) {
					System.out.println("没有开启任何端口"); 
				
					 nmapScanning.setResultCode(ResultCode.NMAP_FAIL);
						return nmapScanning;

				}
				String errors = nmap4j.getExecutionResults().getErrors() ;
				if (errors == null ) {
				
					 nmapScanning.setResultCode(ResultCode.NMAP_FAIL);
						return nmapScanning;
				}
				//把得到的output对象封装成baseScanning类
				org.jsoup.nodes.Document doc = Jsoup.parse(output); 
				org.jsoup.select.Elements links = doc.getElementsByTag("port");
             if(!doc.getElementsByTag("port").attr("portid").equals(""))
             {
				List<BaseNmap> baseNmaps=new ArrayList<BaseNmap>();
				for(int i=0;i<links.size();i++)
				{
					BaseNmap baseNmap=new BaseNmap();
					
					baseNmap.setServiceName((links.get(i).getElementsByTag("service").attr("name")));
					baseNmap.setPort(Integer.parseInt(links.get(i).attr("portid")));
					baseNmap.setState(links.get(i).getElementsByTag("state").attr("state"));
					baseNmap.setPortProtocol(links.get(i).attr("protocol"));
					baseNmaps.add(baseNmap);
				}
				nmapScanning.setBaseNmaps(baseNmaps);
				nmapScanning.setOS(doc.getElementsByTag("osclass").attr("osfamily"));
				if(nmapScanning!=null &&baseNmaps.size()>0)
				nmapScanning.setResultCode(ResultCode.NMAP_SUCCESS);
				return nmapScanning;
			}
             else {
            	 nmapScanning.setResultCode(ResultCode.NMAP_FAIL);
				return  nmapScanning;
			}
			}
		
			
				} catch (NMapInitializationException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				
					 nmapScanning.setResultCode(ResultCode.NMAP_FAIL);
					
					return nmapScanning;
				 
				} catch (NMapExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			
					 nmapScanning.setResultCode(ResultCode.NMAP_FAIL);
					
						return nmapScanning;
				}
				return nmapScanning;
			}

	
}
