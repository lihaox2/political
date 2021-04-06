package com.bayee.political.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoExcel {

	private static final Logger LOG = LoggerFactory.getLogger(ExportExcelUtils.class);

    /**
     * 创建一个excel文件
     * */
    private static Workbook createWorkBoot(
    		String title,
            String[] excelHeader, 
            List<Map<String, Object>> list, 
            String[] keys) {
        Workbook workbook = new HSSFWorkbook();
        //设置sheet的名字
        Sheet sheet = workbook.createSheet(list.get(0).get("sheetName").toString());
        /*设置表格宽度*/
        for(int i = 0; i < keys.length; i++){
            sheet.setColumnWidth(i, 35*150);
        }

        /*font样式设置字体大小,是否加粗*/
 //       Font titleFont = createFont(workbook, (short)20, true);
        Font headerFont = createFont(workbook, (short)12, true);
        Font bodyFont = createFont(workbook, (short)12, false);
        /*cell通用样式*/
//        CellStyle titleStyle = createStyle(workbook, titleFont);
        CellStyle headerStyle = createStyle(workbook, headerFont);
        CellStyle bodyStyle = createStyle(workbook, bodyFont);
        
        // excel中当前行索引
        int index = 4;
        
        
        /*
         * 创建表头
         */
        createHeader(headerFont, workbook, sheet, headerStyle);
        
        
        
        /*设置每行每 列的值及样式
         *Row为行,cell为方格
         *创建i*j个方格并设置对应的属性值*/
        for(int i = 1; i < list.size(); i++) {
            Row bodyRow = sheet.createRow(index++);
            for (int j = 0; j < keys.length; j++) {
                Cell bodyCell = bodyRow.createCell(j);
                bodyCell.setCellValue(list.get(i).get(keys[j]) == null ? 
                        " " : list.get(i).get(keys[j]).toString());
                bodyCell.setCellStyle(bodyStyle);
            }
        }
        return workbook;
    }
    
    /**
     * 创建表头
     */
    private static void createHeader(
    		Font headerFont,
    		Workbook workbook,
    		Sheet sheet, 
    		CellStyle headerStyle) {
    	
        sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 3, 1, 1));
        //----------------------------------------------------------------------
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 15));
        
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 12));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 15));
        
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 3, 3));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 4, 4));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 5, 5));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 6, 6));
        
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 9));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 10, 12));
        
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 13, 13));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 14, 14));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 15, 15));
        //----------------------------------------------------------------------负面
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 16, 22));
        
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 18));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 22));
        
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 16, 16));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 17, 17));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 18, 18));
        
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 19, 20));
        
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 21, 21));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 22, 22));
        
        //----------------------------------------------------------------------正面
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 23, 25));
        
        sheet.addMergedRegion(new CellRangeAddress(1, 3, 23, 23));
        sheet.addMergedRegion(new CellRangeAddress(1, 3, 24, 24));
        sheet.addMergedRegion(new CellRangeAddress(1, 3, 25, 25));
        //----------------------------------------------------------------------中性
        
        sheet.addMergedRegion(new CellRangeAddress(0, 3, 26, 26));
        sheet.addMergedRegion(new CellRangeAddress(0, 3, 27, 27));
        
        
        /*设置表格头信息及样式*/
        /*
         * 第一行
         */
        Row headerRow0 = sheet.createRow(0);
        
        Cell headerRow0Cell0 = headerRow0.createCell(0);
        headerRow0Cell0.setCellValue("被记分人");
        headerRow0Cell0.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow0Cell1 = headerRow0.createCell(1);
        headerRow0Cell1.setCellValue("警号");
        headerRow0Cell1.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow0Cell2 = headerRow0.createCell(2);
        headerRow0Cell2.setCellValue("负面考评");
        headerRow0Cell2.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow0Cell3 = headerRow0.createCell(16);
        headerRow0Cell3.setCellValue("正面考评");
        headerRow0Cell3.setCellStyle(createStyle(workbook, headerFont));

        Cell headerRow0Cell4 = headerRow0.createCell(23);
        headerRow0Cell4.setCellValue("中性考评");
        headerRow0Cell4.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow0Cell5 = headerRow0.createCell(26);
        headerRow0Cell5.setCellValue("总分(负面+中性)");
        headerRow0Cell5.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow0Cell6 = headerRow0.createCell(27);
        headerRow0Cell6.setCellValue("总分(正面+中性)");
        headerRow0Cell6.setCellStyle(createStyle(workbook, headerFont));

        /*
         * 第二行
         */
        Row headerRow1 = sheet.createRow(1);
        
        Cell headerRow1Cell2 = headerRow1.createCell(2);
        headerRow1Cell2.setCellValue("局规记分");
        headerRow1Cell2.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow1Cell3 = headerRow1.createCell(4);
        headerRow1Cell3.setCellValue("业务工作");
        headerRow1Cell3.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow1Cell4 = headerRow1.createCell(7);
        headerRow1Cell4.setCellValue("队所管理");
        headerRow1Cell4.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow1Cell5 = headerRow1.createCell(13);
        headerRow1Cell5.setCellValue("纪检监督");
        headerRow1Cell5.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow1Cell6 = headerRow1.createCell(16);
        headerRow1Cell6.setCellValue("记功表扬");
        headerRow1Cell6.setCellStyle(createStyle(workbook, headerFont));
    	
        Cell headerRow1Cell7 = headerRow1.createCell(19);
        headerRow1Cell7.setCellValue("队所管理");
        headerRow1Cell7.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow1Cell8 = headerRow1.createCell(23);
        headerRow1Cell8.setCellValue("家庭变动");
        headerRow1Cell8.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow1Cell9 = headerRow1.createCell(24);
        headerRow1Cell9.setCellValue("岗位调动");
        headerRow1Cell9.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow1Cell10 = headerRow1.createCell(25);
        headerRow1Cell10.setCellValue("其他情况");
        headerRow1Cell10.setCellStyle(createStyle(workbook, headerFont));
        
        /*
         * 第三行
         */
        Row headerRow2 = sheet.createRow(2);
        
        Cell headerRow2Cell2 = headerRow2.createCell(2);
        headerRow2Cell2.setCellValue("分局记分");
        headerRow2Cell2.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell3 = headerRow2.createCell(3);
        headerRow2Cell3.setCellValue("自主记分");
        headerRow2Cell3.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell4 = headerRow2.createCell(4);
        headerRow2Cell4.setCellValue("督查提醒");
        headerRow2Cell4.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell5 = headerRow2.createCell(5);
        headerRow2Cell5.setCellValue("法制点评");
        headerRow2Cell5.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell6 = headerRow2.createCell(6);
        headerRow2Cell6.setCellValue("其他业务");
        headerRow2Cell6.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell7 = headerRow2.createCell(7);
        headerRow2Cell7.setCellValue("队伍管理");
        headerRow2Cell7.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell8 = headerRow2.createCell(10);
        headerRow2Cell8.setCellValue("业务管理");
        headerRow2Cell8.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell9 = headerRow2.createCell(13);
        headerRow2Cell9.setCellValue("群众反应");
        headerRow2Cell9.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell10 = headerRow2.createCell(14);
        headerRow2Cell10.setCellValue("媒体监督");
        headerRow2Cell10.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell11 = headerRow2.createCell(15);
        headerRow2Cell11.setCellValue("信访投诉");
        headerRow2Cell11.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell12 = headerRow2.createCell(16);
        headerRow2Cell12.setCellValue("立功嘉奖");
        headerRow2Cell12.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell13 = headerRow2.createCell(17);
        headerRow2Cell13.setCellValue("通报表扬");
        headerRow2Cell13.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell14 = headerRow2.createCell(18);
        headerRow2Cell14.setCellValue("其他表扬");
        headerRow2Cell14.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell15 = headerRow2.createCell(19);
        headerRow2Cell15.setCellValue("工作质能");
        headerRow2Cell15.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell16 = headerRow2.createCell(21);
        headerRow2Cell16.setCellValue("工作效能");
        headerRow2Cell16.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow2Cell17 = headerRow2.createCell(22);
        headerRow2Cell17.setCellValue("其他管理");
        headerRow2Cell17.setCellStyle(createStyle(workbook, headerFont));
        
        /*
         * 第四行
         */
        Row headerRow3 = sheet.createRow(3);
        
        Cell headerRow3Cell7 = headerRow3.createCell(7);
        headerRow3Cell7.setCellValue("生活纪律");
        headerRow3Cell7.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow3Cell8 = headerRow3.createCell(8);
        headerRow3Cell8.setCellValue("工作纪律");
        headerRow3Cell8.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow3Cell9 = headerRow3.createCell(9);
        headerRow3Cell9.setCellValue("其他管理");
        headerRow3Cell9.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow3Cell10 = headerRow3.createCell(10);
        headerRow3Cell10.setCellValue("质能管理");
        headerRow3Cell10.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow3Cell11 = headerRow3.createCell(11);
        headerRow3Cell11.setCellValue("效能管理");
        headerRow3Cell11.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow3Cell12 = headerRow3.createCell(12);
        headerRow3Cell12.setCellValue("其他管理");
        headerRow3Cell12.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow3Cell19 = headerRow3.createCell(19);
        headerRow3Cell19.setCellValue("优质案件");
        headerRow3Cell19.setCellStyle(createStyle(workbook, headerFont));
        
        Cell headerRow3Cell20 = headerRow3.createCell(20);
        headerRow3Cell20.setCellValue("最佳案件");
        headerRow3Cell20.setCellStyle(createStyle(workbook, headerFont));
        
        
        
    }
    
    
    /**设置字体大小，颜色，样式，是否加粗*/
    private static Font createFont(Workbook workbook,
            short fontHeightInPoints, boolean isBlod) {
        Font font = workbook.createFont();
        //字体大小
        font.setFontHeightInPoints(fontHeightInPoints);
        //字体颜色
        font.setColor(IndexedColors.BLACK.getIndex());
        //字体样式
        font.setFontName("宋体");
        //是否加粗
        font.setBold(isBlod);
        return font;
    }
    
    /**设置字体居中显示，背景色，边框*/
    private static CellStyle createStyle(Workbook workbook, Font font) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        
        //背景颜色
        /*cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        cellStyle.setFillBackgroundColor(IndexedColors.WHITE.index);
        cellStyle.setFillPattern(FillPatternType.FINE_DOTS);
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("25%"));
        */
        //边框
        /*cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        */
        return cellStyle;
    }

    public static boolean exportExcel(HttpServletResponse response,List<Map<String, Object>> list) throws IOException {
        String fileName = list.get(0).get("fileName").toString();
        String[] excelHeader = (String [])list.get(0).get("excelHeader");
        String[] keys = (String [])list.get(0).get("keys");
//        String title = list.get(0).get("title").toString();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            createWorkBoot(null, excelHeader, list, keys).write(baos);
        } catch (IOException e) {
            LOG.error("将workbook中信息写入输出流时失败");
            return false;
        }
        byte[] content = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(content);

        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream sos = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(sos);
            byte[] buff = new byte[2048];
            int byteRead = 0;
            while (-1 != (byteRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, byteRead);
            }
        } catch (IOException e) {
            LOG.error("创建excel文件时失败");
            return false;
        } finally { 
            if (bos != null)
                bos.close();
            if (bis != null)
                bis.close();
            if(is != null)
                is.close();
            if(baos != null)
                baos.close();
        }
        return true;
    }
}
