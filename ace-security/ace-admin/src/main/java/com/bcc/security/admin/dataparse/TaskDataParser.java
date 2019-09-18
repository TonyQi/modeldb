package com.bcc.security.admin.dataparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bcc.security.admin.dataparse.model.Column;
import com.bcc.security.admin.dataparse.model.Conditions;
import com.bcc.security.admin.dataparse.model.Data;
import com.bcc.security.admin.dataparse.model.Meteorology;
import com.bcc.security.admin.dataparse.model.SubTaskdesc;
import com.bcc.security.admin.dataparse.model.Table;
import com.bcc.security.admin.dataparse.model.TaskData;
import com.bcc.security.admin.dataparse.model.Vva;
import com.bcc.security.admin.dataparse.utils.Configure;
import com.bcc.security.admin.dataparse.utils.Constants;
@Component
public class TaskDataParser {
	
	public TaskData parse(InputStream in){
		return parseData(in,Constants.ENCODING_GBK);
	}
	
	public TaskData parse(InputStream in,String charsetName){
		return parseData(in,charsetName);
	}
	
	private TaskData parseData(InputStream in,String charsetName) {
		InputStreamReader reader;
		TaskData taskData = new TaskData();
		try {
			reader = new InputStreamReader(in,charsetName);
			BufferedReader br = new BufferedReader(reader);
			String lineTxt = null;
			
			String currentBlock = null;
			boolean isParsingBlockBegin = false;
			boolean isParsingBlockEnd = false;
			
			Data data = null;
			StringBuffer dataContent = null;
			
			while ((lineTxt = br.readLine()) != null) {
				if(StringUtils.isBlank(lineTxt)){
					continue;
				}
				
				//parse header
				if(StringUtils.contains(lineTxt, "#")){
					String dataType = StringUtils.substringAfter(lineTxt, "#").trim();
					taskData.setDataType(dataType);
				}
				if(StringUtils.contains(lineTxt, "//")){
					if((StringUtils.contains(lineTxt, "(") ||StringUtils.contains(lineTxt, "（")) && (StringUtils.contains(lineTxt, ")") ||StringUtils.contains(lineTxt, "）") )){
						String dataCode = StringUtils.substringBetween(lineTxt, StringUtils.contains(lineTxt, "(") ? "(" : "（", StringUtils.contains(lineTxt, ")") ? ")" : "）");
						taskData.setDataCode(dataCode);
					}else{
						String dataName = StringUtils.substringAfter(lineTxt, "//").trim();
						taskData.setDataName(dataName);
					}
				}
				
				//parse block
				if(!isParsingBlockBegin){//find parse begin flag
					for (int i = 0; i < Configure.BLOCKS.length; i++) {
						String block = Configure.BLOCKS[i];
						String temp = "["+block+"]";
						if(lineTxt.trim().equalsIgnoreCase(temp)){
							currentBlock = block;
							isParsingBlockBegin = true;//begin parse this block
							isParsingBlockEnd = false;
							if(temp.equals("[DATA]")){
								data = new Data();
								dataContent = new StringBuffer();
							}
							Constants.printLog(temp + " begin parse");
							break;
						}
						
					}
					continue;//skip to the next line
				}
				if(!isParsingBlockEnd){//find parse end flag
					String temp = "[/"+currentBlock+"]";
					if(lineTxt.trim().equalsIgnoreCase(temp)){
						isParsingBlockEnd = true;//end parse this block
						isParsingBlockBegin = false;
						Constants.printLog(temp + " end parse");
					}
				}
				
				if(currentBlock.equals(Constants.Block.DATA)){//parsing data
					if(isParsingBlockBegin && !isParsingBlockEnd){
						dataContent.append(lineTxt).append("\r\n");
					}
					if(!isParsingBlockBegin && isParsingBlockEnd){
						data.setDataContent(dataContent.toString());
						data.setDataType("0");
						taskData.getDatas().add(data);
					}
				}
				
				if(!currentBlock.equals(Constants.Block.DATA) && isParsingBlockBegin && !isParsingBlockEnd){//parsing
					String[] array = lineTxt.split("=");
					String tableName = Configure.cache.get(currentBlock);
					Table table = Configure.table_column.get(tableName);
					List<Column> columns = table.getColumns();
					
					String key = "";
					String value = "";
					if(array.length >= 1){
						key = array[0].trim();
					}
					if(array.length > 1){
						value = array[1].trim();
					}
					
					//获取单位
					String unit = getUnit(key);
					if(!StringUtils.isEmpty(unit)){
						key = getStringOfNoUnit(key);
						if(!StringUtils.isBlank(value)){
							value = value + "(" + unit + ")";
						}
					}
					
					String property = searchProperty(key, columns);
					
					if(currentBlock.equals(Constants.Block.TASK) || currentBlock.equals(Constants.Block.TARGET)){
						SubTaskdesc taskTarget = taskData.getSubTaskdesc();
						injectProperty(taskTarget, property, value);
						taskData.setSubTaskdesc(taskTarget);
					}
					
					if(currentBlock.equals(Constants.Block.SYSTEM)){
						com.bcc.security.admin.dataparse.model.System system = taskData.getSystem();
						injectProperty(system, property, value);
						taskData.setSystem(system);
					}
					
					if(currentBlock.equals(Constants.Block.VVA)){
						Vva vva = taskData.getVva();
						injectProperty(vva, property, value);
						taskData.setVva(vva);
					}
					
					if(currentBlock.equals(Constants.Block.CONDITIONS)){
						Conditions conditions = taskData.getConditions();
						injectProperty(conditions, property, value);
						taskData.setConditions(conditions);
					}
					
					if(currentBlock.equals(Constants.Block.METEOROLOGY) || currentBlock.equals(Constants.Block.ENVIRONMENT)){
						Meteorology meteorology = taskData.getMeteorology();
						injectProperty(meteorology, property, value);
						taskData.setMeteorology(meteorology);
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Constants.printLog(JSON.toJSONString(taskData));
		return taskData;
	}
	
	/**
	 * inject property
	 * @param obj
	 * @param property
	 * @param value
	 */
	private static void injectProperty(Object obj, String property, String value) {
		if(!StringUtils.isEmpty(property)){
			String methodName = "set" + property.substring(0,1).toUpperCase()+property.substring(1,property.length());
			try {
				MethodUtils.invokeMethod(obj, methodName, value);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * search property code
	 * @param key
	 * @param columns
	 * @return
	 */
	private static String searchProperty(String key, List<Column> columns) {
		String property = null;
		for (int i = 0; i < columns.size(); i++) {
			if(columns.get(i).getName().equals(key)){
				property = columns.get(i).getCode();
				break;
			}
		}
		return property;
	}
	
	/**
	 * 获取单位，获取不到返回null
	 * @param str
	 * @return
	 */
	private static String getUnit(String str) {
		String unit = null;
		if((StringUtils.contains(str, "(") ||StringUtils.contains(str, "（")) && (StringUtils.contains(str, ")") ||StringUtils.contains(str, "）") )){
			int start = str.indexOf("(") > 0 ?  str.indexOf("(") : str.indexOf("（");
			int end = str.indexOf(")") > 0 ?  str.indexOf(")") : str.indexOf("）");
			unit = StringUtils.substring(str, start+1, end);
		}
		return unit;
	}
	
	/**
	 * 获取属性名，不带单位
	 * @param str
	 * @return
	 */
	private static String getStringOfNoUnit(String str) {
		int end = str.indexOf("(") > 0 ?  str.indexOf("(") : str.indexOf("（");
		return StringUtils.substring(str, 0, end).trim();
	}
	
	public static void main(String[] args) {
		File file = new File("C:\\Users\\qipen\\Desktop\\任务相关\\TECDM 测试报文\\测试报文\\A.2.1点频目标雷达散射幅相特性\\22.txt");
		try {
			InputStream in = new FileInputStream(file);
			
			TaskDataParser parser = new TaskDataParser();
			@SuppressWarnings("unused")
			TaskData taskData = parser.parse(in);
			System.out.println(1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
