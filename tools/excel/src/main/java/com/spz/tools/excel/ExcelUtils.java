package com.spz.tools.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spz.tools.excel.ExcelSheetPO.ExcelSheetPOBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class ExcelUtils {

    //标题样式
    private final static String STYLE_HEADER = "header";

    //表头样式
    private final static String STYLE_TITLE = "title";

    //数据样式
    private final static String STYLE_DATA = "data";

    //存储样式
    private static final HashMap<String, CellStyle> cellStyleMap = new HashMap<String, CellStyle>();

    private static Workbook createWorkbook(File file) throws FileNotFoundException, IOException {
        // 根据后缀名称判断excel的版本
        String fileSuffixName = FileUtil.getFileSuffixName(file);
        Workbook workbook = null;
        if (ExcelVersion.V2003.getSuffix().equals(fileSuffixName)) {
            workbook = new HSSFWorkbook(new FileInputStream(file));

        } else if (ExcelVersion.V2007.getSuffix().equals(fileSuffixName)) {
            workbook = new XSSFWorkbook(new FileInputStream(file));

        } else {
            // 无效后缀名称，这里之能保证excel的后缀名称，不能保证文件类型正确，不过没关系，在创建Workbook的时候会校验文件格式
            throw new IllegalArgumentException("Invalid excel version");
        }
        return workbook;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\xiaomi\\Desktop\\customer.xlsx");
        List<ExcelSheetPO> readExcel = ExcelUtils.readExcel(file);
        for (ExcelSheetPO excelSheetPO : readExcel) {
            System.out.println(excelSheetPO.getSheetName());
            List<Map<Row, List<Cell>>> rowList = excelSheetPO.getRowList();
            for (Map<Row, List<Cell>> rowMap : rowList) {
                Set<Entry<Row, List<Cell>>> entrySet = rowMap.entrySet();
                for (Entry<Row, List<Cell>> entry : entrySet) {
                    //Row row = entry.getKey();
                    List<Cell> cellList = entry.getValue();
                    for (Cell cell : cellList) {
                        System.out.println(getCellValue(null, cell));
                    }
                }
            }
        }
    }

    public static List<ExcelSheetPO> readExcel(File file) throws FileNotFoundException, IOException {

        List<ExcelSheetPO> sheetPOs = new ArrayList<ExcelSheetPO>();
        Workbook workbook = createWorkbook(file);

        // 开始读取数据
        if (null != workbook) {
            //获取sheet数量
            int sheetNum = workbook.getNumberOfSheets();
            log.info("sheet数量----->" + sheetNum);
            for (int i = 0; i < sheetNum; i++) {
                //获取sheet
                Sheet sheet = workbook.getSheetAt(i);
                //创建Sheet数据模型
                ExcelSheetPOBuilder excelSheetPOBuilder = new ExcelSheetPOBuilder();
                ExcelSheetPO sheetPO = excelSheetPOBuilder.build();
                sheetPO.setSheetName(sheet.getSheetName());
                //行集合
                List<Map<Row, List<Cell>>> rowList = new ArrayList<Map<Row, List<Cell>>>();
                //获取sheet行数
                int rowNum = sheet.getPhysicalNumberOfRows();
                log.info("第" + (i + 1) + "个sheet的行数----->" + rowNum);
                for (int j = 0; j < rowNum; j++) {
                    //获取行
                    Row row = sheet.getRow(j);
                    Map<Row, List<Cell>> rowMap = new HashMap<Row, List<Cell>>();
                    //获取行的单元格的数量
                    int cellNum = row.getPhysicalNumberOfCells();
                    log.info("第" + (i + 1) + "个sheet的第" + (j + 1) + "row的单元格的数量----->" + cellNum);
                    List<Cell> cellList = new ArrayList<Cell>();
                    for (int k = 0; k < cellNum; k++) {
                        //获取Row的单元格
                        Cell cell = row.getCell(0);
                        cellList.add(cell);
                        rowMap.put(row, cellList);
                        log.info("第" + (i + 1) + "个sheet的第" + (j + 1) + "row的第" + (k + 1) + "个cell的值----->"
                                + getCellValue(workbook, cell));
                    }
                    rowList.add(rowMap);
                }
                sheetPO.setRowList(rowList);
                sheetPOs.add(sheetPO);
            }
        }
        return sheetPOs;
    }

    /**
     * @功能描述：获取单元格中的值
     * @参数说明：@param workbook
     * @参数说明：@param cell
     * @参数说明：@return
     * @作者： shipengzhen
     * @创建时间：2019年1月9日 上午9:18:20
     */
    private static Object getCellValue(Workbook workbook, Cell cell) {
        Object columnValue = null;
        if (cell != null) {
            switch (cell.getCellType()) {
            //字符串
            case STRING:
                columnValue = cell.getStringCellValue();
                break;
            //数字
            case NUMERIC:
                if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                    DecimalFormat df = new DecimalFormat("0");// 格式化 number
                    columnValue = df.format(cell.getNumericCellValue());
                } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                    columnValue = nf.format(cell.getNumericCellValue());
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                    columnValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                break;
            //布尔
            case BOOLEAN:
                columnValue = cell.getBooleanCellValue();
                break;
            //空白
            case BLANK:
                columnValue = "";
                break;
            //公式
            case FORMULA:
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                evaluator.evaluateFormulaCell(cell);
                CellValue cellValue = evaluator.evaluate(cell);
                columnValue = cellValue.getNumberValue();
                break;
            //其他
            default:
                columnValue = cell.toString();
            }
        }
        return columnValue;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static Workbook createWorkbook(ExcelVersion version) {
        switch (version) {
        case V2003:
            return new HSSFWorkbook();
        case V2007:
            return new XSSFWorkbook();
        }
        return null;
    }

    private static Workbook createWorkBook(ExcelVersion version, List<ExcelSheetPO> excelSheets) {
        Workbook wb = createWorkbook(version);
        for (int i = 0; i < excelSheets.size(); i++) {
            ExcelSheetPO excelSheetPO = excelSheets.get(i);
            if (excelSheetPO.getSheetName() == null) {
                excelSheetPO.setSheetName("sheet" + i);
            }
            // 过滤特殊字符
            Sheet tempSheet = wb.createSheet(WorkbookUtil.createSafeSheetName(excelSheetPO.getSheetName()));
            buildSheetData(wb, tempSheet, excelSheetPO, version);
        }
        return wb;
    }

    private static void buildSheetData(Workbook wb, Sheet sheet, ExcelSheetPO excelSheetPO, ExcelVersion version) {
        sheet.setDefaultRowHeight((short) 400);
        sheet.setDefaultColumnWidth((short) 10);
        createTitle(sheet, excelSheetPO, wb, version);
        createHeader(sheet, excelSheetPO, wb, version);
        createBody(sheet, excelSheetPO, wb, version);
    }

    private static void createBody(Sheet sheet, ExcelSheetPO excelSheetPO, Workbook wb, ExcelVersion version) {
        List<List<Object>> dataList = excelSheetPO.getDataList();
        for (int i = 0; i < dataList.size() && i < version.getMaxRow(); i++) {
            List<Object> values = dataList.get(i);
            Row row = sheet.createRow(2 + i);
            for (int j = 0; j < values.size() && j < version.getMaxColumn(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(getStyle(STYLE_DATA, wb));
                cell.setCellValue(values.get(j).toString());
            }
        }

    }

    private static void createHeader(Sheet sheet, ExcelSheetPO excelSheetPO, Workbook wb, ExcelVersion version) {
        String[] headers = excelSheetPO.getHeaders();
        Row row = sheet.createRow(1);
        for (int i = 0; i < headers.length && i < version.getMaxColumn(); i++) {
            Cell cellHeader = row.createCell(i);
            cellHeader.setCellStyle(getStyle(STYLE_HEADER, wb));
            cellHeader.setCellValue(headers[i]);
        }

    }

    private static void createTitle(Sheet sheet, ExcelSheetPO excelSheetPO, Workbook wb, ExcelVersion version) {
        Row titleRow = sheet.createRow(0);
        Cell titleCel = titleRow.createCell(0);
        titleCel.setCellValue(excelSheetPO.getTitle());
        titleCel.setCellStyle(getStyle(STYLE_TITLE, wb));
        // 限制最大列数
        int column = excelSheetPO.getDataList().size() > version.getMaxColumn() ? version.getMaxColumn()
                : excelSheetPO.getDataList().size();
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, column - 1));
    }

    private static CellStyle getStyle(String type, Workbook workbook) {

        if (cellStyleMap.containsKey(type)) {
            return cellStyleMap.get(type);
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setWrapText(true);

        if (STYLE_HEADER == type) {
            style.setAlignment(HorizontalAlignment.GENERAL);
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 16);
            style.setFont(font);
        } else if (STYLE_TITLE == type) {
            style.setAlignment(HorizontalAlignment.CENTER);
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 18);
            style.setFont(font);
        } else if (STYLE_DATA == type) {
            style.setAlignment(HorizontalAlignment.LEFT);
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
        }
        cellStyleMap.put(type, style);
        return style;
    }

    /**
     * 在硬盘上写入excel文件
     * 
     * @author JIANGYOUYAO
     * @email 935090232@qq.com
     * @date 2017年12月20日
     * @param version
     * @param excelSheets
     * @param filePath
     * @throws IOException
     */
    public static void createWorkbookAtDisk(ExcelVersion version, List<ExcelSheetPO> excelSheets, String filePath)
            throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filePath);
        createWorkbookAtOutStream(version, excelSheets, fileOut, true);
    }

    /**
     * 把excel表格写入输出流中，输出流会被关闭
     * 
     * @author JIANGYOUYAO
     * @email 935090232@qq.com
     * @date 2017年12月20日
     * @param version
     * @param excelSheets
     * @param outStream
     * @param closeStream
     *            是否关闭输出流
     * @throws IOException
     */
    public static void createWorkbookAtOutStream(ExcelVersion version, List<ExcelSheetPO> excelSheets,
            OutputStream outStream, boolean closeStream) throws IOException {
        if (CollectionUtils.isNotEmpty(excelSheets)) {
            Workbook wb = createWorkBook(version, excelSheets);
            wb.write(outStream);
            if (closeStream) {
                outStream.close();
            }
        }
    }

}