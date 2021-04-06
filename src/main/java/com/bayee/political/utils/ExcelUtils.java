/**
 * 
 */
package com.bayee.political.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.bayee.political.domain.User;
import com.bayee.political.service.UserService;

/**
 * @author seanguo
 *
 */
public class ExcelUtils {
	
	@Autowired
	private UserService userService;
	
	public static void main(String [] args) throws Exception {
		ExcelUtils utils = new ExcelUtils();
		utils.go();
	}
	
	public void go() throws Exception {
		//创建输入流
	    FileInputStream fis = new FileInputStream(new File("/Users/seanguo/Downloads/20200106.xls"));
	    //通过构造函数传参
	    HSSFWorkbook workbook = new HSSFWorkbook(fis);
	    //获取工作表
	    HSSFSheet sheet = workbook.getSheetAt(0);
	    //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
	    int rowCount = sheet.getLastRowNum();
	    int count = 0;
	    for(int i=0; i<rowCount; i++) {
	    	HSSFRow row = sheet.getRow(i);
		    //获取单元格,row已经确定了行号,列号作为参数传递给getCell,第一列从0开始计算
		    HSSFCell cell = row.getCell(0);
		    //获取单元格的值,即C1的值(第一行,第三列)
		    String userId = cell.getStringCellValue();
		    
		    cell = row.getCell(7);
		    String phone = cell.getStringCellValue();
		    
		    User user = userService.findByDDUserId(userId);
		    if(user != null) {
		    	user.setPhone(phone);
		    	userService.updatePhone(user);
		    	count++;
		    } 
	    }
	    fis.close();
	    System.out.println("updated :" + count);
	}
}
