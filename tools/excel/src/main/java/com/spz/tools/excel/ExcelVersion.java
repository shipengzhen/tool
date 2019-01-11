/**
 * @文件名称： ExcelVersion.java
 * @文件路径： com.spz.excel.util
 * @功能描述： TODO
 * @作者： shipengzhen
 * @创建时间：2019年1月8日 下午6:31:16
 */
package com.spz.tools.excel;


/**
 * @功能描述：
 * @创建人： shipengzhen
 * @创建时间： 2019年1月8日 下午6:31:16
 */
public enum ExcelVersion {

    /**
     * 虽然V2007版本支持最大支持1048575 * 16383 ， 
     * V2003版支持65535*255
     * 但是在实际应用中如果使用如此庞大的对象集合会导致内存溢出，
     * 因此这里限制最大为10000*100，如果还要加大建议先通过单元测试进行性能测试。
     * 1000*100 全部导出预计时间为27s左右
     */
    V2003(".xls", 10000, 100), V2007(".xlsx", 100, 100);

    private String suffix;

    private int maxRow;

    private int maxColumn;

    ExcelVersion(String suffix, int maxRow, int maxColumn) {
        this.suffix = suffix;
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getMaxColumn() {
        return maxColumn;
    }

    public void setMaxColumn(int maxColumn) {
        this.maxColumn = maxColumn;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
