/**
 * @文件名称： StringUtil.java
 * @文件路径： com.bdqn.spz.tools
 * @功能描述： TODO
 * @作者： shipengzhen
 * @创建时间：2018年6月5日 下午5:43:45
 */
package com.spz.tools.tool.util;

import java.lang.reflect.Field;

/**
 * @功能描述：
 * @创建人： shipengzhen
 * @创建时间： 2018年6月5日 下午5:43:45
 */
public class StringUtil {

    public String getClassString(Class<?> clazz) {

        StringBuilder builder = new StringBuilder("\""+clazz.getSimpleName() + " [");
        Field[] fields=clazz.getDeclaredFields();
        
        for (int i = 0; i < fields.length; i++) {
            String fieldName=fields[i].getName();
            if (!"serialVersionUID".equals(fieldName)) {
                if(i==1){
                    builder.append(fieldName+"=\"+"+fieldName+"+");
                }else if(i==fields.length-1){
                    builder.append("\","+fieldName+"=\"+"+fieldName);
                }else{
                    builder.append("\","+fieldName+"=\"+"+fieldName+"+");
                }
            }
        }
        builder.append("+\"]\"");
        String classString=builder.toString();
        return classString;
    }
}
