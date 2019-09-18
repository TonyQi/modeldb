package com.bcc.security.admin.dataparse;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcc.security.admin.dataparse.model.TaskData;
import com.bcc.security.admin.dataparse.model.Vva;
import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.admin.biz.DaConditionsBiz;
import com.bcc.security.admin.biz.DaDataBiz;
import com.bcc.security.admin.biz.DaMeteorologyBiz;
import com.bcc.security.admin.biz.DaSubTaskdescBiz;
import com.bcc.security.admin.biz.DaSystemBiz;
import com.bcc.security.admin.biz.DaVvaBiz;
import com.bcc.security.admin.dataparse.dataobj.DaConditions;
import com.bcc.security.admin.dataparse.dataobj.DaData;
import com.bcc.security.admin.dataparse.dataobj.DaMeteorology;
import com.bcc.security.admin.dataparse.dataobj.DaSubTaskdesc;
import com.bcc.security.admin.dataparse.dataobj.DaSystem;
import com.bcc.security.admin.dataparse.dataobj.DaVva;
import com.bcc.security.admin.dataparse.model.Conditions;
import com.bcc.security.admin.dataparse.model.Data;
import com.bcc.security.admin.dataparse.model.Meteorology;
import com.bcc.security.admin.dataparse.model.SubTaskdesc;
import com.bcc.security.admin.dataparse.model.System;
import com.bcc.security.admin.dataparse.utils.Constants;

/**
 * 主要功能微操作TaskData入库
 * @author qipen
 *
 */
@Component
public class TaskDataService {
	
	@Autowired
	private DaSubTaskdescBiz daSubTaskdescBiz;
	@Autowired
	private DaSystemBiz daSystemBiz;
	@Autowired
	private DaVvaBiz daVvaBiz;
	@Autowired
	private DaConditionsBiz daConditionsBiz;
	@Autowired
	private DaMeteorologyBiz daMeteorologyBiz;
	@Autowired
	private DaDataBiz daDataBiz;
	public boolean insertTaskDataToDB(FileInfo fileInfo,TaskData taskData) {
		
		
		String taskdataid = fileInfo.getId();
		

		SubTaskdesc taskTarget = taskData.getSubTaskdesc();
		System system = taskData.getSystem();
		Vva vva = taskData.getVva();
		Conditions conditions = taskData.getConditions();
		Meteorology meteorology = taskData.getMeteorology();
		List<Data> datas = taskData.getDatas();
		
		DaSubTaskdesc dasubtaskdesc = new DaSubTaskdesc();
		DaSystem daSystem = new DaSystem();
		DaVva daVva = new DaVva();
		DaConditions daConditions = new DaConditions();
		DaMeteorology daMeteorology = new DaMeteorology();
		
		boolean isMerged = mergePropertyValue(dasubtaskdesc, taskTarget);
		boolean isMerged2 = mergePropertyValue(daSystem, system);
		boolean isMerged3 = mergePropertyValue(daVva, vva);
		boolean isMerged4 = mergePropertyValue(daConditions, conditions);
		boolean isMerged5 = mergePropertyValue(daMeteorology, meteorology);
		
		if (isMerged) {
			dasubtaskdesc.setTaskdataid(taskdataid);
			dasubtaskdesc.setCharactdatatype("#" + taskData.getDataType());
			dasubtaskdesc.setSubtaskdescid(UUID.randomUUID().toString());
			daSubTaskdescBiz.insert(dasubtaskdesc);
			Constants.printLog("------------------------insert daSubbtaskdesc------------------------");
			Constants.printDataObjectProperty(dasubtaskdesc);
		}
		if (isMerged2) {
			daSystem.setTaskdataid(taskdataid);
//			daSystem.setEquipmentid(buDatacondition.getString("equipmentid"));
			daSystem.setSystemid(UUID.randomUUID().toString());
			daSystemBiz.insert(daSystem);
			Constants.printLog("------------------------insert daSystem------------------------");
			Constants.printDataObjectProperty(daSystem);
		}
		if (isMerged3) {
			daVva.setTaskdataid(taskdataid);
			daVvaBiz.insert(daVva);
			Constants.printLog("------------------------insert daVva------------------------");
			Constants.printDataObjectProperty(daVva);
		}
		if (isMerged4) {
			daConditions.setTaskdataid(taskdataid);
			daConditions.setConditionid(UUID.randomUUID().toString());
			daConditionsBiz.insert(daConditions);
			Constants.printLog("------------------------insert daConditions------------------------");
			Constants.printDataObjectProperty(daConditions);
		}
		if (isMerged5) {
			daMeteorology.setTaskdataid(taskdataid);
			daMeteorology.setMeteorologyid(UUID.randomUUID().toString());
			daMeteorologyBiz.insert(daMeteorology);
			Constants.printLog("------------------------insert daMeteorology------------------------");
			Constants.printDataObjectProperty(daMeteorology);
		}
		for (Data temp : datas) {
			DaData data = new DaData();
			data.setDataid(UUID.randomUUID().toString());
			data.setTaskdataid(taskdataid);
			data.setType(temp.getDataType());
			data.setDatabody(temp.getDataContent().getBytes());
			daDataBiz.insert(data);
		}
		Constants.printLog("------------------------insert datas------------------------");
		return true;
	}
	
	
	/**
	 * <pre>
	 * 返回true，合并过属性 ，反之,false
	 * 
	 * <pre>
	 * @param target
	 * @param object
	 * @return
	 */
	private boolean mergePropertyValue(Object target, Object source) {
		Field[] fileds = source.getClass().getDeclaredFields();
		boolean isMerged = false;
		for (Field field : fileds) {
			String methodName = "get" + field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1, field.getName().length());
			try {
				Object temp = MethodUtils.invokeMethod(source, methodName, null);
				if (temp != null) {
					boolean flag=setPropertyByField(target, field, temp);
					isMerged = isMerged||flag;
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return isMerged;
	}
	
	/**
	 * set the property of the target
	 * 
	 * @param target
	 * @param field
	 * @param temp
	 */
	private boolean setPropertyByField(Object target, Field field,Object temp) {
		Field[] fields = target.getClass().getDeclaredFields();
		for (Field fieldTemp : fields) {
				if (fieldTemp.getName().equals(field.getName())) {
					String methodName = "set" + field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1, field.getName().length());
					try {
						MethodUtils.invokeMethod(target, methodName, temp);
						return true;
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}

		}
		return true;
	}
	
}
