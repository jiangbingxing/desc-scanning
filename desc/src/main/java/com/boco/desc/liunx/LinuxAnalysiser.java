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
import com.boco.desc.enty.Asset;
import com.boco.desc.enty.BaseScanning;
import com.boco.desc.enty.Component;
import com.boco.desc.enty.IpAndCommod;
import com.boco.desc.enty.Login;
import com.boco.desc.result.Result;
import com.boco.desc.util.LoadUtil;


public class LinuxAnalysiser implements Analysiser{
	

	private BaseScanning single=new BaseScanning();
	private BaseAsset baseAssetLinux=null;

	


	public  BaseScanning SingleScanning(String output,Login login) {
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
		
		List<Component> components=new ArrayList<Component>();
		for(int i=0;i<links.size();i++)
		{
			Component component=new Component();
			component.setName(links.get(i).getElementsByTag("service").attr("name"));
			component.setPort(Integer.parseInt(links.get(i).attr("portid")));
			component.setStatus(links.get(i).getElementsByTag("state").attr("state"));
			if(component.getName()!=null &&!component.getName().equals(""))
			{
				
				String string=baseAssetLinux.getPathAndUser(login, component.getName());
				String[]strings=string.split("星星");
				component.setPath(strings[0]);
				component.setUsername(strings[1]);
				component.setResult(Result.SUCCESS);
				
			}
			
			components.add(component);
			
		}
		
		
		//未安装应用资产发现
		TomcatAssetLinux speAssetLinux=new TomcatAssetLinux();
		try {
			List<Component> speComponents=speAssetLinux.tomcatCompoentFind(login);
			
			components.addAll(speComponents);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//设置组件信息
		single.setActiveIp(login.getIp());
		
		single.setComponent(components);
		
		//设置资产信息
		Asset asset=baseAssetLinux.getBaseAsset(login);
		
		single.setCardIp(asset.getIps());
		
		single.setAssetName(asset.getAssetName());
		single.setAssetType(asset.getAssetType());
		single.setDefaultGateway(asset.getDefaultGateway());
		single.setMacAddres(asset.getMacAddres());
		single.setManuFacturer(asset.getManuFacturer());
		single.setVersion(asset.getVersion());
		
		
		
		
		return single;	

	}




	@Override
	public List<Component> NmapScanning(String ip) {
		// TODO Auto-generated method stub
		String path=LoadUtil.getNmapPath();
		IpAndCommod ic=new IpAndCommod();
		Nmap4j nmap4j = new Nmap4j(path);
		nmap4j.addFlags(ic.getCommond());
		nmap4j.includeHosts(ip) ;
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

				List<Component> components=new ArrayList<Component>();
				for(int i=0;i<links.size();i++)
				{
					Component component=new Component();
					component.setName(links.get(i).getElementsByTag("service").attr("name"));
					component.setPort(Integer.parseInt(links.get(i).attr("portid")));
					component.setStatus(links.get(i).getElementsByTag("state").attr("state"));
					components.add(component);
				}
				return components;
			}
				} catch (NMapInitializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NMapExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

   }
