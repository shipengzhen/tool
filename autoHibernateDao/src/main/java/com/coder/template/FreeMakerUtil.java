package com.coder.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;



import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreeMakerUtil {
         
	
	  private static String templatePath = System.getProperty("user.dir")  + "\\src\\main\\java\\com\\coder\\ftl\\";
	  //private static String templatePath = "c:\\zyf_config\\";
	  //private static String templatePath = ClassLoader.getSystemResource("").getPath() + "com/coder/ftl";
	  //if(templatePath != null && templatePath.length() > 0)templatePath = templatePath.substring(6);
	   /***
	    * 
	    * @param templateFileName 模板文件名称	(包括文件名和扩展名)
	    * @param outToPath 输出文件路径
	    * @param outToPathFileName 输出文件全路径(包括文件名和扩展名)
	    * @param root Map 对象
	    */
	   public static void WriteFileToPath(String templateFileName,String outToPath,String outToPathFileName,Map<String, String> root){
		   try{
			   
			   //System.out.println("模板路径:  "+templatePath);
			   Configuration cfg = new Configuration();
			   //从什么地方加载freemarker模板文件   
	           cfg.setDirectoryForTemplateLoading(new File(templatePath)); 
	           //设置对象包装器   
	            cfg.setObjectWrapper(new DefaultObjectWrapper());    
	            //设置异常处理器
	           cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);   
	            //定义Template对象
	            Template template = cfg.getTemplate(templateFileName);   
	         //创建目录
	            FileTool.CreateDirectory(outToPath);
	          //定义输出   
	            PrintWriter out    
	                = new PrintWriter(   
	                    new BufferedWriter(   
	                        new FileWriter(outToPathFileName)
	                    )   
	                );
	          template.process(root, out);   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	   
	   public static void main(String args[]){
		   String temp = "vo.ftl";
		   
		   Map<String, String> root = new HashMap<String, String>();
		   root.put("package", "com.mm.vo"); 
		   root.put("filename", "Test");
		   String outp = "d:/aa/cc/";
		   String fname = "d:/aa/cc/text.java";
		   
		   
		   WriteFileToPath(temp,outp,fname,root);
		   
	   }
}
