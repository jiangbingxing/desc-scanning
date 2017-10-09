package com.boco.desc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;


public class LoadUtil {
	
	public static List<String> LoadPreperties(String path)
	 {	 
		List<String> strings=new ArrayList<String>();
		OrderedProperties pro = new OrderedProperties();

		try {
		    InputStream inStr = LoadUtil.class.getClassLoader().getResourceAsStream(path);
		    pro.load(inStr);
		   LinkedHashSet<String> key=(LinkedHashSet<String>) pro.stringPropertyNames();
		   Iterator<String> iterator=key.iterator();
		  while (iterator.hasNext()) {
			String value=pro.getProperty(iterator.next());
			strings.add(value);	
		}
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
				return strings;    
		    }    
		 
	
	public static String getNmapPath()
	{
		Properties pro=new Properties();
		 InputStream inStr = LoadUtil.class.getClassLoader().getResourceAsStream("path.properties");
		    try {
				pro.load(inStr);
				String path=pro.getProperty("NMAP_PATH");
				
				return path;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}

    }
      
