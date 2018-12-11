package com.coder.template;

import java.io.File;

public class FileTool {
    
	public static File CreateDirectory(String as_Path) throws Exception
    {
        File lo_File = new File(as_Path);
        if (lo_File.exists())
        {
            if (!lo_File.isDirectory()) throw new Exception("路径存在但并非目录。");
        }
        lo_File.mkdirs();
        return lo_File;
    }
}