package ${objs.pagckdir};


import java.util.*;
import javax.servlet.http.*; 
import java.sql.*;

public class ${objs.className} extends ${objs.parentClassName}{
       /**
	    * Constructer of this class ;
	    * 作者：zyf
	    * 编写时间：2010.1.16
	    * 模板类型：VO模板，1.0版
	    */
       <#--
       public List fieldName = new ArrayList();
	   public List columnName = new ArrayList();
	   public List sqlDataType = new ArrayList();
	   public String tableName = "${objs.tableName}";

       
       public ${objs.className}(){
	    <#list polist as o>
		  fieldName.add("${o.fname}");
		</#list>
		//----------------------------init fieldName end----------------------
		<#list polist as o>
		  sqlDataType.add("${o.sql_type}");
		</#list>
		//----------------------------init datatype end -----------------------
		<#list polist as o>
		  columnName.add("${o.columnName}");
		</#list>
		//----------------------------init datatype end -----------------------
       }
       -->
<#list polist as o>
    <#if  o.ftype = 'Integer'>
       private ${o.ftype}  ${o.fname} = null;
    <#else>
       private ${o.ftype}  ${o.fname} = null;
    </#if>
</#list>
//------------------------------------------------------------
<#list polist as o>
        public void set${o.fieldSet}(${o.ftype} ${o.fname}){
            this.${o.fname} = ${o.fname};
        }
        public ${o.ftype} get${o.fieldSet}(){
            return this.${o.fname};
        }   
</#list>
//------------------------------------------------------------
    public static ${objs.className} getInstanceFrom(HttpServletRequest request) throws Exception  {
       ${objs.className} vo = new ${objs.className}();
   <#list polist as o>
       String _${o.fname} = request.getParameter("${o.fname}");
       
     <#if o.ftype == 'java.sql.Timestamp'>
       if(_${o.fname} != null && _${o.fname}.length() > 0){
           vo.set${o.fieldSet}(${o.ftype}.valueOf(_${o.fname}));
       }
     <#else>
       if(_${o.fname} != null && _${o.fname}.length() > 0){
           vo.set${o.fieldSet}(new ${o.ftype}(_${o.fname}));
       }
     </#if>
   </#list>
       return vo;
    }
    //-----------------------------------------------------------         

}
