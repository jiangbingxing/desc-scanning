package com.boco.desc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
	public static List<String> getMatch(String source, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(source);
		List<String> result = new ArrayList<String>();
		
		while(m.find()){
			result.add(m.group());
		}
		return result;
	}
	
	public static void main(String[] args) {
		String string="sadsadsa ddinet 10.108.226.3  netmask 255.255.255.0  broadcast 10.108.226.255";
		System.out.println(getMatch(string,"inet\\s\\d+\\.\\d+\\.\\d+\\.\\d+").get(0));
		
	}
}
