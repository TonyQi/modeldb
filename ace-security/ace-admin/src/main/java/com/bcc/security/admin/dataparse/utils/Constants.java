package com.bcc.security.admin.dataparse.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;

import com.alibaba.fastjson.JSON;


public class Constants {
	
	public static class Type{
		public static final String DATA = "0";
		public static final String BMP = "1";
		public static final String SEQ = "2";
	}
	
	public static class Title{
		public static final String DATA_TYPE = "数据类型";
		public static final String POSITION_X = "横坐标";
		public static final String POSITION_Y = "纵坐标";
	}
	
	public static class Block{
		
		//模块名
		public static final String TASK = "TASK";
		public static final String TARGET = "TARGET";
		public static final String SYSTEM = "SYSTEM";
		public static final String VVA = "VV&A";
		public static final String CONDITIONS = "CONDITIONS";
		public static final String DATA = "DATA";
		public static final String METEOROLOGY = "METEOROLOGY";
		public static final String ENVIRONMENT = "ENVIRONMENT";
		
		//模块对应表名
		public static final String TASK_TABLE = "DaSubTaskdesc";
		public static final String TARGET_TABLE = "DaSubTaskdesc";
		public static final String SYSTEM_TABLE= "DaSystem";
		public static final String VVA_TABLE = "DaVva";
		public static final String CONDITIONS_TABLE = "DaConditions";
		public static final String DATA_TABLE = "DaData";
		public static final String METEOROLOGY_TABLE = "DaMeteorology";
		public static final String ENVIRONMENT_TABLE = "DaMeteorology";
	}
	
	public static final String ENCODING_GBK = "GBK";
	
	public static final String DEFAULT_DATASOURCE = "default";
	
	public static void printLog(String msg){
		System.out.println(msg);
	}
	
	/**
	 * DataObject start with “index_”
	 * @param object
	 */
	public static void printDataObjectProperty(Object object) {
		Field[] fileds = object.getClass().getDeclaredFields();
		for (Field field : fileds) {
			if(StringUtils.contains(field.getName(), "INDEX_")){
				String methodName = "get" + StringUtils.substringAfter(field.getName(), "INDEX_");
				try {
					Object temp = MethodUtils.invokeMethod(object, methodName, null);
					if(temp != null){
						Constants.printLog(StringUtils.substringAfter(field.getName(), "INDEX_") + ":" +JSON.toJSONString(temp));
					}
				} catch (NoSuchMethodException e) {
					//e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			

		}
	}
	
	/**
	 * 获取单位，获取不到返回null
	 * @param str
	 * @return
	 */
	public static String getUnit(String str) {
		String unit = null;
		if((StringUtils.contains(str, "(") ||StringUtils.contains(str, "（")) && (StringUtils.contains(str, ")") ||StringUtils.contains(str, "）") )){
			int start = str.indexOf("(") > 0 ?  str.indexOf("(") : str.indexOf("（");
			int end = str.indexOf(")") > 0 ?  str.indexOf(")") : str.indexOf("）");
			unit = StringUtils.substring(str, start+1, end).trim();
		}
		return unit;
	}
	
	/**
	 * 获取属性名，不带单位
	 * @param str
	 * @return
	 */
	public static String getStringOfNoUnit(String str) {
		int end = str.indexOf("(") > 0 ?  str.indexOf("(") : str.indexOf("（");
		return StringUtils.substring(str, 0, end).trim();
	}
	
	/**
	 * 获取UUID
	 */
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }
	
    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    
    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
    
    public static boolean isEnglish(String charaString) {
    	  return charaString.matches(".*[a-zA-Z].*");
    }
	
}
