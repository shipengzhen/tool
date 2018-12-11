package com.coder.vo;

public class Po {
	 private String fname = "";//属性名如 pageSize
	 private String ftype = "";//字段数据类型
     //
     private String sql_type = "";//数据库类型 orcle mysql
     private String columnName = ""; //数据库的列名
     private String fieldSet = "";   //JAVA  set后面的属性名格式如：PageSize
     private String isPk = "false";  //是否是主键
     private String columnLength = ""; //字段长度
     private String columnLengthIn = "";//字段长度进位 ,例如decimal(10,3) 那么进位就为3,
     private String importDataType = ""; //要导入的数据类型
     private String key_set = ""; //主键产生规则
     
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public String getSql_type() {
		return sql_type;
	}
	public void setSql_type(String sql_type) {
		this.sql_type = sql_type;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getFieldSet() {
		return fieldSet;
	}
	public void setFieldSet(String fieldSet) {
		this.fieldSet = fieldSet;
	}
	public String getIsPk() {
		return isPk;
	}
	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}
	public String getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
	public String getColumnLengthIn() {
		return columnLengthIn;
	}
	public void setColumnLengthIn(String columnLengthIn) {
		this.columnLengthIn = columnLengthIn;
	}
	public String getImportDataType() {
		return importDataType;
	}
	public void setImportDataType(String importDataType) {
		this.importDataType = importDataType;
	}
	public String getKey_set() {
		return key_set;
	}
	public void setKey_set(String key_set) {
		this.key_set = key_set;
	}
	
	
}
