package com.bcc.security.admin.dataparse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;


public class TaskData extends AbstractTaskData {
	
	private SubTaskdesc subTaskdesc;
	private System system;
	private Vva vva;
	private Conditions conditions;
	private Meteorology meteorology;
	
	private List<Data> datas;
	
	public TaskData(){
		//initialize object property
		this.subTaskdesc = new SubTaskdesc();
		this.system = new System();
		this.vva = new Vva();
		this.conditions = new Conditions();
		this.meteorology = new Meteorology();
		this.datas = new ArrayList<Data>();
	}

	public SubTaskdesc getSubTaskdesc() {
		return subTaskdesc;
	}

	public void setSubTaskdesc(SubTaskdesc subTaskdesc) {
		this.subTaskdesc = subTaskdesc;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public Vva getVva() {
		return vva;
	}

	public void setVva(Vva vva) {
		this.vva = vva;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}

	public Meteorology getMeteorology() {
		return meteorology;
	}

	public void setMeteorology(Meteorology meteorology) {
		this.meteorology = meteorology;
	}

	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}
	
	
}
