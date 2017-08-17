package com.boco.desc.liunx;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import org.jsoup.Jsoup;
import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import com.boco.desc.analysis.Analysiser;
import com.boco.desc.analysis.BaseAsset;
import com.boco.desc.dto.LoginScanning;
import com.boco.desc.dto.NmapScanning;
import com.boco.desc.enty.AstDB;
import com.boco.desc.enty.AstHost;
import com.boco.desc.enty.AstMiddleWare;
import com.boco.desc.enty.AstSoftWare;
import com.boco.desc.enty.BaseNmap;

import com.boco.desc.enty.IpAndCommod;
import com.boco.desc.enty.Login;
import com.boco.desc.result.ResultCode;
import com.boco.desc.util.LoadUtil;


public class LinuxAnalysiser implements Analysiser{
	

	private LoginScanning loginScanning=new LoginScanning();
	private BaseAsset baseAssetLinux=null;


	public  LoginScanning getLoginScanning(String output,Login login) {
		/*
		 * 1.使用远程登录，先从回显中获得系统版本名
		 * 2.通过得到的不同的OS，进行不同的命令调度
		 */
			
		int version=LinuxVersionCheck.VersionCheck(login);
	      switch (version) {
		   case 6:
			   baseAssetLinux=new BaseAssetLinuxImpl();
			 
			break;
           case 7:
			      baseAssetLinux=new BaseAssetLinuxImpl2();
			break;

		default:
			baseAssetLinux=new BaseAssetLinuxImpl();
			break;
		}
           
		
		//把得到的output对象封装成baseScanning类
		org.jsoup.nodes.Document doc = Jsoup.parse(output); 
		org.jsoup.select.Elements links = doc.getElementsByTag("port");
		
		List<AstSoftWare> astSoftWares=new ArrayList<AstSoftWare>();
		for(int i=0;i<links.size();i++)
		{
			AstSoftWare astSoftWare=new AstSoftWare();
			astSoftWare.setAstName((links.get(i).getElementsByTag("service").attr("name")));
			astSoftWare.setServerPort(Integer.parseInt(links.get(i).attr("portid")));
			astSoftWare.setAstStatus(links.get(i).getElementsByTag("state").attr("state"));
			astSoftWare.setSystemVersion((links.get(i).getElementsByTag("service").attr("product")+links.get(i).getElementsByTag("service").attr("version")));
			if(astSoftWare.getAstName()!=null &&!astSoftWare.getAstName().equals(""))
			{
				
				String string=baseAssetLinux.getPathAndUser(login,astSoftWare.getAstName());
				if(string.equals("102"))
				{
					loginScanning.setResultCode(ResultCode.LOGIN_FAIL);
				}
				else
				{
				String[]strings=string.split("星星");
				astSoftWare.setInstallPath((strings[0]));
				astSoftWare.setInstallUser((strings[1]));	
				loginScanning.setResultCode(ResultCode.NMAP_LOGIN_SUCCESS);
				}
							
			}
			
			astSoftWares.add(astSoftWare);
			loginScanning.setAstSoftWares(astSoftWares);
			
		}
		
		
		//数据库应用资产发现
		AstDBAnalysiser astDBAnalysiser=new AstDBAnalysiser();
		try {
			List<AstDB> astDBs=astDBAnalysiser.DBFind(login);
			if(astDBs!=null&& astDBs.size()>0)
		     {
				for (int i = 0; i < astDBs.size(); i++) {
					astDBs.get(i).setManageIp(login.getIp());
				}
				loginScanning.setAstDBs(astDBs);
		     }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//中间件应用资产发现
		AstMiddleWareAnalysiser astMiddleWareAnalysiser=new AstMiddleWareAnalysiser();
		try {
			List<AstMiddleWare> astMiddleWares=astMiddleWareAnalysiser.middleWareFind(login);
			if(astMiddleWares!=null&& astMiddleWares.size()>0)
		     {
				for (int i = 0; i < astMiddleWares.size(); i++) {
					astMiddleWares.get(i).setManageIp(login.getIp());
				}
				loginScanning.setAstMiddleWares(astMiddleWares);
		     }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//设置主机资产信息
	    AstHost astHost=baseAssetLinux.getBaseAsset(login);
	    if(astHost!=null)
	    {
	    
		loginScanning.setAstHosts(astHost);
		
		loginScanning.setResultCode(ResultCode.NMAP_LOGIN_SUCCESS);
		
	    }
	    else {
			loginScanning.setResultCode(ResultCode.LOGIN_FAIL);
		}
		
		
		return loginScanning;	

	}

	
	public NmapScanning getNmapScanning(String ip) {
		NmapScanning nmapScanning=new NmapScanning();
	
		String path=LoadUtil.getNmapPath();
		IpAndCommod ic=new IpAndCommod();
		Nmap4j nmap4j = new Nmap4j(path);
		nmap4j.addFlags(ic.getCommond());
		nmap4j.includeHosts(ip);
		try {
			nmap4j.execute();
			if( !nmap4j.hasError() ) {

				String output = nmap4j.getOutput() ;

				if( output == null ) {
					System.out.println("没有开启任何端口"); 

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
					baseNmap.setVersion(links.get(i).getElementsByTag("service").attr("product")+links.get(i).getElementsByTag("service").attr("version"));
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
