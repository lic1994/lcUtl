import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	@SuppressWarnings({ "resource", "unused" })
	public ArrayList<ArrayList<String>> xlsx_reader(String excel_url, int... args) throws IOException { 
		// 读取xlsx文件
	// XSSFWorkbook xssfWorkbook= null;//寻找目录读取文件																									 																									
		File excelFile = new File(excel_url);
		InputStream is = new FileInputStream(excelFile);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		if (xssfWorkbook == null) {
			System.out.println("未读取到内容,请检查路径是否正确！");
			return null;
		}
		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>(); // 遍历xlsx中的sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			} // 对于每个sheet，读取其中的每一行
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null)
					continue;
				ArrayList<String> curarr = new ArrayList<String>();
				for (int columnNum = 0; columnNum < args.length; columnNum++) {
					XSSFCell cell = xssfRow.getCell(args[columnNum]);
					curarr.add(Trim_str(getValue(cell)));
				}
				ans.add(curarr);
			}
		}
		return ans;
	} 
	// 判断后缀为xlsx的excel文件的数据类
	@SuppressWarnings("deprecation")
	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow == null) {
			return "---";
		}
		if (xssfRow.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double cur = xssfRow.getNumericCellValue();
			long longVal = Math.round(cur);
			Object inputValue = null;
			if (Double.parseDouble(longVal + ".0") == cur)
				inputValue = longVal;
			else
				inputValue = cur;
			return String.valueOf(inputValue);
		} else if (xssfRow.getCellType() == Cell.CELL_TYPE_BLANK
				|| xssfRow.getCellType() == Cell.CELL_TYPE_ERROR) {
			return "---";
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	} 
	// 判断后缀为xls的excel文件的数据类型
	@SuppressWarnings({ "deprecation", "unused" })
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell == null) {
			return "---";
		}
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double cur = hssfCell.getNumericCellValue();
			long longVal = Math.round(cur);
			Object inputValue = null;
			if (Double.parseDouble(longVal + ".0") == cur)
				inputValue = longVal;
			else
				inputValue = cur;
			return String.valueOf(inputValue);
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_BLANK
				|| hssfCell.getCellType() == Cell.CELL_TYPE_ERROR) {
			return "---";
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	} 
	// 字符串修剪 去除所有空白符号 ， 问号 ， 中文空格
	static private String Trim_str(String str) {
		if (str == null)
			return null;
		return str.replaceAll("[\\s\\?]", "").replace("　", "");
	}
}
