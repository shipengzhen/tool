package com.coder.db;

public class DataColunm {
    private String columnName = "";
    private String colunmType = "";//column data type
    private String columnLength = "";
    private String col_javaType = "";//java data type;
    private boolean isPrimaryKey = false;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColunmType() {
		return colunmType;
	}
	public void setColunmType(String colunmType) {
		this.colunmType = colunmType;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
	public String getCol_javaType() {
		return col_javaType;
	}
	public void setCol_javaType(String col_javaType) {
		this.col_javaType = col_javaType;
	}
    
    
}
