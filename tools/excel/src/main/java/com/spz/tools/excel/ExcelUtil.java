package com.spz.tools.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spz.tools.excel.ExcelSheetPO.ExcelSheetPOBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class ExcelUtil {

    /**
     * @功能描述：创建Workbook
     * @参数说明：@param file
     * @参数说明：@return
     * @参数说明：@throws FileNotFoundException
     * @参数说明：@throws IOException
     * @作者： shipengzhen
     * @创建时间：2019年1月9日 上午10:10:20
     */
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

    /**
     * @功能描述：读取Excel
     * @参数说明：@param workbook
     * @参数说明：@param file
     * @参数说明：@return
     * @参数说明：@throws FileNotFoundException
     * @参数说明：@throws IOException
     * @作者： shipengzhen
     * @创建时间：2019年1月9日 上午10:10:32
     */
    public static List<ExcelSheetPO> readExcel(Workbook workbook, File file) throws FileNotFoundException, IOException {

        List<ExcelSheetPO> sheetPOs = new ArrayList<ExcelSheetPO>();

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
                CreationHelper creationHelper = workbook.getCreationHelper();
                FormulaEvaluator evaluator = creationHelper.createFormulaEvaluator();
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

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\xiaomi\\Desktop\\customer.xlsx");
        Workbook workbook = createWorkbook(file);
        List<ExcelSheetPO> readExcel = ExcelUtil.readExcel(workbook, file);
        for (ExcelSheetPO excelSheetPO : readExcel) {
            System.out.println(excelSheetPO.getSheetName());
            List<Map<Row, List<Cell>>> rowList = excelSheetPO.getRowList();
            for (Map<Row, List<Cell>> rowMap : rowList) {
                Set<Entry<Row, List<Cell>>> entrySet = rowMap.entrySet();
                for (Entry<Row, List<Cell>> entry : entrySet) {
                    //Row row = entry.getKey();
                    List<Cell> cellList = entry.getValue();
                    for (Cell cell : cellList) {
                        System.out.println(getCellValue(workbook, cell));
                    }
                }
            }
        }
    }

}