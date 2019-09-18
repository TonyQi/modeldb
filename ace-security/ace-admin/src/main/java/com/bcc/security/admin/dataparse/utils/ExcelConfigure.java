package com.bcc.security.admin.dataparse.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.bcc.security.admin.dataparse.model.Point;

public class ExcelConfigure {
	
	private static final String dataDictFile = "config/dataTypeConfig.xls";
	public static AbstractMap<String,Point> cache_excel = new ConcurrentHashMap<String,Point>();//block->table name
	
	static{
		initCache();
	}
	
	public static void initCache(){
		List<String[]> list = parse();
		String[] title = list.get(0);
		int index = getIndex(title, Constants.Title.DATA_TYPE);
		int index2 = getIndex(title, Constants.Title.POSITION_X);
		int index3 = getIndex(title, Constants.Title.POSITION_Y);
		
		for (int i = 1; i < list.size(); i++) {
			String[] array = list.get(i);
			String[] values = StringUtils.split(array[index3]);
			String x=array[index2].toUpperCase();
			List<String> y=new ArrayList<String>();
			for (int j = 0; j < values.length; j++) {
				y.add(values[j].toUpperCase());
			}
			cache_excel.put(array[index].toUpperCase(), new Point(x, y.toArray(new String[y.size()])));
		}
		Constants.printLog("cache_excel:" + JSON.toJSONString(cache_excel));
	}

	private static int getIndex(String[] title, String key) {
		int index = 0;
		for (int i = 0; i < title.length; i++) {
			if(key.equals(title[i])){
				index = i;
				break;
			}
			
		}
		return index;
	}
	
	public static List<String[]> parse(){
		InputStream is = Configure.class.getClassLoader().getResourceAsStream(dataDictFile);
		List<String[]> list = new ArrayList<String[]>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				HSSFRow row = sheet.getRow(i);
				String[] array = new String[row.getPhysicalNumberOfCells()];
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					HSSFCell cell = row.getCell((short) j);
					// 获取单元格的值
					String value = getCellValue(cell);
					array[j] = value;
				}
				list.add(array);
			}
			Constants.printLog("excel:" + JSON.toJSONString(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
    //获取单元格的值  
    private static String getCellValue(HSSFCell cell) {  
        String cellValue = "";  
        if (cell != null) {
            //判断单元格数据的类型，不同类型调用不同的方法  
            switch (cell.getCellType()) {  
                case HSSFCell.CELL_TYPE_STRING:  
                    cellValue = cell.getStringCellValue();  
                    break;  
                case HSSFCell.CELL_TYPE_BLANK:  
                    cellValue = "";  
                    break;  
                case HSSFCell.CELL_TYPE_ERROR:  
                    cellValue = "";  
                    break;  
                default:  
                    cellValue = cell.toString().trim();  
                    break;  
            }  
        }  
        return cellValue.trim();  
    }  
	
    public static void main(String[] args){
    	ExcelConfigure.initCache();
    }
    
}
