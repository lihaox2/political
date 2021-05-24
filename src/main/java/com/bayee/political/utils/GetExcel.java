package com.bayee.political.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.date.DateUtil;

public class GetExcel {

    public static List<List<String>> ReadExcel(MultipartFile file) throws Exception {
        return ReadExcel(file, 0);//默认读取第一个sheet
    }

    public static List<List<String>> ReadExcel(MultipartFile file, int index) throws Exception {
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        return ReadXlsx(file, index, extension);

    }

    public static List<List<String>> ReadXlsx(MultipartFile file, int index, String extension) throws Exception {
        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new Exception("不支持的文件格式");
        }

        Sheet sheet = workbook.getSheetAt(index);
        List<List<String>> result = new ArrayList<List<String>>();
        
        //所有行
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
        	
        	//每一行
            Row row = sheet.getRow(i);
            
            List<String> list = new ArrayList<String>();
            
            if (row == null || null == row.getCell(0) || "".equals(GetString(row.getCell(0)))) {
            	break;
            }
            
            if (row != null) {
            	//每一行每一列
                for (int j = 0; j <= row.getPhysicalNumberOfCells(); j++) {
                	//获取列
                    Cell cell = row.getCell(j);
                    
                    String val = null;
                    
                    if (null == cell) {
                    	
                    	val = null;
                    	
                    } else {
                        	 CellType type = cell.getCellTypeEnum();
                             if (type == CellType.NUMERIC) {
                            	//判断是否是时间格式
                                 if(HSSFDateUtil.isCellDateFormatted(cell)){
                                 	
                                      //return date
                                      val = DateUtil.format(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss");
                                      
                                  } else {
                                	  val = GetString(cell);
                                  }
                             } else if (type == CellType.FORMULA) {
                                 val = GetString(cell);
                             } else {
                                 val = cell.getStringCellValue();
                             }
                                                 	
                    }
                    
                    //添加到集合
                    list.add(val);
                }
            }
            //添加到集合
            result.add(list);
        }
        workbook.close();
        return result;
    }

    private static String GetString(Cell cell) {
        String val = null;
        try {
            val = String.valueOf(cell.getStringCellValue());
        } catch (IllegalStateException e) {
        	//val = String.valueOf(cell.getNumericCellValue());
        	DecimalFormat df = new DecimalFormat("0");
            val = df.format(cell.getNumericCellValue());
        }

        if (val.indexOf("E") > -1) {//防止科学计数法  防止数字变成科学记数法
            DecimalFormat df = new DecimalFormat("0");
            val = df.format(cell.getNumericCellValue());
        }

        return val;
    }
}
