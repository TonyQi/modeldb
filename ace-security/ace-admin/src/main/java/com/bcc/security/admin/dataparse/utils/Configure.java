package com.bcc.security.admin.dataparse.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.bcc.security.admin.dataparse.model.Column;
import com.bcc.security.admin.dataparse.model.Table;
import com.bcc.security.admin.dataparse.utils.Constants.Block;


public class Configure {
	
	private static final String dataDictFile = "config/shujuzidian.txt";
	
	public static AbstractMap<String,String> cache = new ConcurrentHashMap<String,String>();//block->table name
	public static AbstractMap<String,Table> table_column = new ConcurrentHashMap<String,Table>();//table->column cache
	public static String[] BLOCKS;
	
	static{
		//initialize
		initDataDict();
		initBlockTable();
	}
	
	
	private static void initBlockTable(){
		cache.put(Block.TASK, Block.TASK_TABLE);
		cache.put(Block.TARGET, Block.TARGET_TABLE);
		cache.put(Block.SYSTEM, Block.SYSTEM_TABLE);
		cache.put(Block.VVA, Block.VVA_TABLE);
		cache.put(Block.CONDITIONS, Block.CONDITIONS_TABLE);
		cache.put(Block.DATA, Block.DATA_TABLE);
		cache.put(Block.METEOROLOGY, Block.METEOROLOGY_TABLE);
		cache.put(Block.ENVIRONMENT, Block.ENVIRONMENT_TABLE);
		
		BLOCKS = cache.keySet().toArray(new String[0]);
	}
	
	private static void initDataDict(){
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(Configure.class.getClassLoader().getResourceAsStream(dataDictFile),Constants.ENCODING_GBK);
			BufferedReader br = new BufferedReader(reader);
			String lineTxt = null;
			while ((lineTxt = br.readLine()) != null) {
				String[] array = lineTxt.split(" ");
				if(array.length < 3){
					continue;
				}
				String key = array[2];
				
				//initialize table
				if(table_column.get(key) == null){
					table_column.put(key, new Table());
				}
				//initialize columns
				if(table_column.get(key).getColumns() == null){
					table_column.get(key).setColumns(new ArrayList<Column>());
				}
				Table table = table_column.get(key);
				table.setCode(array[2]);
				table.setName(array[2]);
				Column column  = new Column();
				column.setCode(array[1]);
				column.setName(array[0]);
				List<Column> columns = table.getColumns();
				columns.add(column);
			}
			Constants.printLog(JSON.toJSONString(table_column));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Configure.initDataDict();
    }
	
}
