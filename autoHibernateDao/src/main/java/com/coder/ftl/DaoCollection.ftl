package ${packagepath};

import ${packagepath}.*;
public class DaoCollection{
<#list xmlfile as o>
  public static ${o.className}Dao get${o.className}Dao() {return (${o.className}Dao)CommonContextUtil.getApplicationContext().getBean("${o.className}Dao");}
</#list>
}