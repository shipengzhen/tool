package com.coder.config;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Config {
    
	public static String ReaderConfigInfo(String f)throws Exception{
		String v = null;
		if(f.indexOf(".")==-1)throw new Exception("读取参数错误");
		String frist = f.substring(0,f.indexOf("."));
		String last  = f.substring(f.indexOf(".")+1,f.length());
		String path = System.getProperty("user.dir")  + "\\src\\main\\resources\\config.xml";
		File file = new File(path);
	    SAXReader reader = new SAXReader();
	    Document doc = reader.read(file);
	    System.out.println(doc);
	    Element root = doc.getRootElement();
	    System.out.println(root);
	    
	    for ( Iterator<?> i = root.elementIterator(); i.hasNext(); ) { 
	    	Element element = (Element)i.next();
	    	if(frist.equals(element.getName())){
	    		for(Iterator<?> s = element.elementIterator(); s.hasNext();){
	    			Element cel = (Element)s.next();
	    			if(last.equals(cel.getName()))v = cel.getTextTrim();
	    		}
	    	}
	    	
	    }
		return v;
	}
}
