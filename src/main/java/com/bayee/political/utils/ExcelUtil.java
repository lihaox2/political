package com.bayee.political.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * excel数据导入工具类
 *
 * @author xxl
 * @date 2021/2/26
 */
public class ExcelUtil {

    /**
     * 数据导入
     *
     * @param file        文件信息
     * @param sheetIndex  工作簿下标索引 从0开始
     * @param headerIndex 表头下标索引 从0开始
     * @return
     */
    public static List<JSONObject> dataImport(MultipartFile file, Integer sheetIndex, Integer headerIndex) {
        if (file == null || sheetIndex == null || headerIndex == null) {
            throw new NullPointerException();
        }
        List<JSONObject> result = new ArrayList<>();

        try {
            Workbook workbook = getWorkbook(file);
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // 获取行数
            int rowCount = getExcelRealRow(sheet);
            if (rowCount == -1 || rowCount == headerIndex) {
                return null;
            }
            // 获取列数
            int columnNum = sheet.getRow(headerIndex).getPhysicalNumberOfCells();
            // 从表头下一行开始取数据
            for (int i = headerIndex + 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                JSONObject jsonObject = new JSONObject();
                if (row != null) {
                    for (int j = 0; j < columnNum; j++) {
                        jsonObject.put(j+"", getCellValue(row.getCell(j)));
                    }
                    result.add(jsonObject);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取真实数据行数
     *
     * @param sheet
     * @return
     */
    private static int getExcelRealRow(Sheet sheet) {
        boolean flag = false;
        for (int i = 1; i <= sheet.getLastRowNum(); ) {
            Row r = sheet.getRow(i);
            if (r == null) {
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                continue;
            }
            flag = false;
            for (Cell c : r) {
                if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                i++;
                continue;
            } else {
                // 如果是空白行（即可能没有数据，但是有一定格式）
                // 如果到了最后一行，直接将那一行remove掉
                if (i == sheet.getLastRowNum()){
                    sheet.removeRow(r);
                } else{
                    //如果还没到最后一行，则数据往上移一行
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                }
            }
        }
        return sheet.getLastRowNum();
    }

    private static Object getCellValue(Cell cell){
        Object cellValue = null;
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
            case Cell.CELL_TYPE_FORMULA: {
                if (DateUtil.isCellDateFormatted(cell)) {
                    //日期类型
                    cellValue = cell.getDateCellValue();
                }else {
                    //数字类型
                    String value = String.valueOf(cell.getNumericCellValue()).trim();

                    //  判断是否为科学计数法（包含E、e、+等符号）
                    if (value.contains("E") || value.contains("e") || value.contains("+")) {
                        BigDecimal bd = new BigDecimal(Double.valueOf(value));
                        cellValue = bd.toString();
                    }else{
                        //处理数据后面拼接 .0 和 科学计数法 123,456
                        NumberFormat nf = NumberFormat.getInstance();
                        value = nf.format(Double.valueOf(value));
                        if (value.contains(",")) {
                            value = value.replace(",", "");
                        }

                        cellValue = value;
                    }
                }
                break;
            }
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    private static Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        String fileName = file.getOriginalFilename();

        if (fileName.endsWith("xls") || fileName.endsWith("XLS")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else if (fileName.endsWith("xlsx") || fileName.endsWith("XLSX")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            throw new RuntimeException("文件格式错误！");
        }
        return workbook;
    }

    /**
     * 数据导出
     *
     * @param response   导出流
     * @param fileName   文件名
     * @param columnKeys 列名
     * @param dataArray  数据集
     */
    public static void exportData(HttpServletResponse response, String fileName,
                                  LinkedHashMap<String, String> columnKeys, JSONArray dataArray) {
        try{
            createFile(response, fileName+".xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }

        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("sheet1");
        //创建表头
        Row row = sheet.createRow(0);
        int i = 0;
        LinkedList<String> list = new LinkedList();

        for (String key : columnKeys.keySet()) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnKeys.get(key));
            list.add(key);
            i++;
        }

        //写入数据
        for (int j = 0; j < dataArray.size(); j++) {
            JSONObject jsonObject = dataArray.getJSONObject(j);
            Row dataRow = sheet.createRow(j + 1);

            for (int k = 0; k < list.size(); k++) {
                dataRow.createCell(k).setCellValue(String.valueOf(jsonObject.get(list.get(k))));
            }
        }

        try {
            book.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        //设置文件头编码格式
        response.setHeader("Content-disposition", "attachment;filename=" +
                new String(fileName.getBytes("gb2312"), "ISO8859-1"));

        //设置类型
        response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");

        //设置头
        response.setHeader("Cache-Control", "no-cache");

        //设置日期头
        response.setDateHeader("Expires", 0);
    }

    /**
     *
     */
    /*public static void generateReport(){

    }
*/

    public static void main(String[] args) {
        String str = "1.831058856E+10";
        NumberFormat nf = NumberFormat.getInstance();
        if (str.indexOf(",") >= 0) {
            str = str.replace(",", "");
        }
        String s = nf.format(Double.valueOf(str));

        System.out.println(s);
    }

}
