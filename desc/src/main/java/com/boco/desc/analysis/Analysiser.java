package com.boco.desc.analysis;


import java.util.List;

import org.jsoup.select.Elements;



import com.google.gson.JsonObject;

public abstract class Analysiser {
	public String totalString;
	public String portString;
	
	public abstract List<JsonObject> Find(Elements elements);


}
