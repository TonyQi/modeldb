package com.bcc.security.admin.dataparse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.bcc.security.admin.config.BccConfig;
import com.bcc.security.admin.dataparse.model.Column;
import com.bcc.security.admin.dataparse.model.Conditions;
import com.bcc.security.admin.dataparse.model.Data;
import com.bcc.security.admin.dataparse.model.Table;
import com.bcc.security.admin.dataparse.model.TaskData;
import com.bcc.security.admin.dataparse.utils.Configure;
import com.bcc.security.admin.dataparse.utils.Constants;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ImageParser {
	@Autowired
	private BccConfig bccConfig;
	
	public void querySubTaskDescToCopyFile(String taskDataId, TaskData taskData) {

		String destPath = bccConfig.getFilelocation()+File.separator+"twodata";

		File destDir = new File(destPath);
		if (!destDir.exists() && !destDir.isDirectory()) {
			destDir.mkdirs();
		}
		File destFile = new File(destPath + File.separator + taskDataId + ".txt");
		if (!destFile.exists() && !destFile.isFile()) {
			generateImageFile(taskDataId, taskData, destFile);
		}

	}
	
	
	private void generateImageFile(String taskDataId, TaskData taskData,
			File destFile) {

		
		String filepath = bccConfig.getFilelocation()+File.separator+"twodata"+File.separator+taskDataId+".txt";

		// 组装txt文件头内容
		StringBuffer content = new StringBuffer();
		content.append("#2DRCSIMAGD").append("\r\n").append("//")
				.append(taskData.getDataName()).append("\r\n").append("//")
				.append(taskData.getDataCode()).append("\r\n").append("[")
				.append(Constants.Block.CONDITIONS).append("]").append("\r\n");

		// 组装conditions模块
		Conditions conditions = taskData.getConditions();
		Field[] fields = conditions.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			String property=fields[j].getName();
			String methodName = "get" + property.substring(0,1).toUpperCase()+property.substring(1,property.length());
			try {
				Object temp = MethodUtils.invokeMethod(conditions, methodName,
						null);
				if (temp != null && !StringUtils.isEmpty(temp.toString())) {
					String columnName = getColumnNameByModel(
							fields[j].getName(),
							Constants.Block.CONDITIONS_TABLE);

					String value = temp.toString();
					// 获取单位
					String unit = Constants.getUnit(temp.toString());
					if (!StringUtils.isEmpty(unit)) {
						columnName = columnName + "(" + unit + ")";
						value = Constants.getStringOfNoUnit(temp.toString());
					}

					content.append(columnName).append("=").append(value)
							.append("\r\n");
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		// 特殊项
		content.append("目标名称").append("=")
				.append(taskData.getSubTaskdesc().getTgtname()).append("\r\n");
		content.append("径向采样点数").append("=").append("101").append("\r\n");
		content.append("横向采样点数").append("=").append("101").append("\r\n");

		// 组装data模块
		List<Data> datas = taskData.getDatas();
		for (int j = 0; j < datas.size(); j++) {
			content.append("[").append(Constants.Block.DATA).append("]")
					.append("\r\n").append(datas.get(0).getDataContent());
		}

		writeTxt(destFile, content);
	}
	
	private String getColumnNameByModel(String code, String model) {
		String name = null;
		Table table = Configure.table_column.get(model);
		List<Column> columns = table.getColumns();
		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i).getCode().equals(code)) {
				name = columns.get(i).getName();
				break;
			}
		}

		return name;
	}
	
	private void writeTxt(File destFile, StringBuffer content) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(destFile);
			fos.write(content.toString().getBytes());
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
