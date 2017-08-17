package com.lay.shop.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.FileSystemResourceLoader;

public class PoiUtil {
    
    private static FileSystemResourceLoader fis = new FileSystemResourceLoader();
    
    /**
     * 创建后缀名 .xlsx 的Excel对象
     * @param path  模板路径
     * */
    public static Workbook createXSSFWorkbook(String path) throws IOException{
        if(path == null){
            return new XSSFWorkbook();
        }
        return new XSSFWorkbook(fis.getResource(path).getInputStream());
    }

    /**
     * 创建后缀名 .xls 的Excel对象
     * @param path  模板路径
     * */
    public static Workbook createHSSFWorkbook(String path) throws IOException{    
        if(path == null){
            return new HSSFWorkbook();
        }
        return new HSSFWorkbook(fis.getResource(path).getInputStream());
    }
    
    public static Workbook createWorkbook(InputStream inp) throws EncryptedDocumentException, InvalidFormatException, IOException{        
        return WorkbookFactory.create(inp);
    }
    
    public static Workbook createWorkbook(String path) throws EncryptedDocumentException, InvalidFormatException, IOException{
        if(path == null){
            return null;
        }
        return createWorkbook(fis.getResource(path).getInputStream());
    }
    
    /***
     * 创建  处理大批量数据的 导出的EXCEL对象 针对.xlsx
     * @author Lay
     * @param path
     * @param count 内存中保留的行数
     * @return
     * @throws IOException
     * @since
     */
    public static SXSSFWorkbook createSXSSFWorkbook(String path,int count) throws IOException{
    	return new SXSSFWorkbook(new XSSFWorkbook(fis.getResource(path).getInputStream()), count);
    }
    
    public static Object getCellData(Cell cell) {        
        return getCellData(cell, null);
    }
    
    public static Object getCellData(Cell cell, FormulaEvaluator formula) {        
        if(cell == null) {
            return null;
        }        
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:            
            return cell.getRichStringCellValue().getString();
        case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            } else {
                return cell.getNumericCellValue();
            }
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue();
        case Cell.CELL_TYPE_FORMULA:
            if(formula == null){
                return null;
            }
            switch (formula.evaluate(cell).getCellType()) {
                case Cell.CELL_TYPE_STRING:                    
                    return formula.evaluate(cell).getStringValue();
                case Cell.CELL_TYPE_NUMERIC:                     
                     return formula.evaluate(cell).getNumberValue();                    
                case Cell.CELL_TYPE_BOOLEAN:                    
                    return formula.evaluate(cell);                    
            }
        default:
            return null;
        }
    }    
}
