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

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaveTrainExcel {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExportExcelUtils.class);

	/**
	 * 创建一个excel文件
	 */
	private static Workbook createWorkBoot(String title, String[] excelHeader, List<Map<String, Object>> list,
			String[] keys) {
		Workbook workbook = new HSSFWorkbook();
		// 设置sheet的名字
		Sheet sheet = workbook.createSheet(list.get(0).get("sheetName").toString());
		/* 设置表格宽度 */
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth(i, 35 * 150);
		}

		/* font样式设置字体大小,是否加粗 */
		Font headerFont = createFont(workbook, (short) 12, true);
		Font bodyFont = createFont(workbook, (short) 12, false);
		/* cell通用样式 */
		CellStyle headerStyle = createStyle(workbook, headerFont);
		CellStyle bodyStyle = createStyle(workbook, bodyFont);

		// excel中当前行索引
		int index = 0;

		/* 设置表格头信息及样式 */
		Row headerRow = sheet.createRow(index++);
		for (int i = 0; i < excelHeader.length; i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(excelHeader[i]);
			headerCell.setCellStyle(headerStyle);
		}

		/**
		 * 设置样式及下拉选择
		 */
		for (int i = 1; i < 1000; i++) {
			Row bodyRow = sheet.createRow(index++);
			for (int j = 0; j < keys.length; j++) {
				Cell bodyCell = bodyRow.createCell(j);
				if (j == keys.length - 1) {
					setSelect(bodyCell, sheet, i, j);
				}
				bodyCell.setCellStyle(bodyStyle);
				if (j == 1) {
					CellStyle style = workbook.createCellStyle();
					style.setDataFormat(workbook.createDataFormat().getFormat("@"));
					bodyCell.setCellStyle(style);
					bodyCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
			}
		}
		return workbook;
	}

	/**
	 * 下拉选项
	 * @param cell
	 * @param sheet
	 * @param rowCount
	 * @param cellCount
	 */
	private static void setSelect(Cell cell, Sheet sheet, int rowCount, int cellCount) {
    	
		setSelect(sheet, rowCount, cellCount, new String[] {"连续三年未休养", "立功受奖优先安排人数"});
    	
    }
	
	/**
	 * 设置下拉选项
	 */
	private static void setSelect(Sheet sheet, int rowCount, int cellCount, String[] str) {
		
		//设置为下拉
		CellRangeAddressList regions = new CellRangeAddressList(rowCount, rowCount, cellCount, cellCount);
		//创建下拉列表
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(str);
		//绑定
		sheet.addValidationData(new HSSFDataValidation(regions, constraint));
		
	}

	/** 设置字体大小，颜色，样式，是否加粗 */
	private static Font createFont(Workbook workbook, short fontHeightInPoints, boolean isBlod) {
		Font font = workbook.createFont();
		// 字体大小
		font.setFontHeightInPoints(fontHeightInPoints);
		// 字体颜色
		font.setColor(IndexedColors.BLACK.getIndex());
		// 字体样式
		font.setFontName("宋体");
		// 是否加粗
		font.setBold(isBlod);
		return font;
	}

	/** 设置字体居中显示，背景色，边框 */
	private static CellStyle createStyle(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		// 居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		// 背景颜色
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
		cellStyle.setFillBackgroundColor(IndexedColors.WHITE.index);
		cellStyle.setFillPattern(FillPatternType.FINE_DOTS);
		// 边框
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		return cellStyle;
	}

	public static boolean exportExcel(HttpServletResponse response, List<Map<String, Object>> list) throws IOException {
		String fileName = list.get(0).get("fileName").toString();
		String[] excelHeader = (String[]) list.get(0).get("excelHeader");
		String[] keys = (String[]) list.get(0).get("keys");
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
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
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
			if (is != null)
				is.close();
			if (baos != null)
				baos.close();
		}
		return true;
	}
}
