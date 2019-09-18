package com.bcc.security.admin.dataparse.model;

public class Meteorology {
	
//	气象条件ID 
	private String meteorologyid ;
//	天气 
	private String weath ;
//	气温
	private String  airtemp ;
//	相对湿度
	private String  relahum ;
//	气压 
	private String airpress ;
//	风速
	private String  winvel ;
//	风向
	private String windir ;
//	能见度
	private String  horvis ;
//	太阳直射辐照度
	private String  soldirirra ;
//	天空散射辐照度
	private String  skysolscatirra ;
//	天空红外辐照度
	private String  skyinfirra ;
//	太阳方位角
	private String  solaz ;
//	太阳高低角
	private String  solel ;
//	天空云量 
	private String amntskycld ;
//	气象参数序号
	private String  weanum ;
//	备注
	private String  note ;
	public String getMeteorologyid() {
		return meteorologyid;
	}
	public void setMeteorologyid(String meteorologyid) {
		this.meteorologyid = meteorologyid;
	}
	public String getWeath() {
		return weath;
	}
	public void setWeath(String weath) {
		this.weath = weath;
	}
	public String getAirtemp() {
		return airtemp;
	}
	public void setAirtemp(String airtemp) {
		this.airtemp = airtemp;
	}
	public String getRelahum() {
		return relahum;
	}
	public void setRelahum(String relahum) {
		this.relahum = relahum;
	}
	public String getAirpress() {
		return airpress;
	}
	public void setAirpress(String airpress) {
		this.airpress = airpress;
	}
	public String getWinvel() {
		return winvel;
	}
	public void setWinvel(String winvel) {
		this.winvel = winvel;
	}
	public String getWindir() {
		return windir;
	}
	public void setWindir(String windir) {
		this.windir = windir;
	}
	public String getHorvis() {
		return horvis;
	}
	public void setHorvis(String horvis) {
		this.horvis = horvis;
	}
	public String getSoldirirra() {
		return soldirirra;
	}
	public void setSoldirirra(String soldirirra) {
		this.soldirirra = soldirirra;
	}
	public String getSkysolscatirra() {
		return skysolscatirra;
	}
	public void setSkysolscatirra(String skysolscatirra) {
		this.skysolscatirra = skysolscatirra;
	}
	public String getSkyinfirra() {
		return skyinfirra;
	}
	public void setSkyinfirra(String skyinfirra) {
		this.skyinfirra = skyinfirra;
	}
	public String getSolaz() {
		return solaz;
	}
	public void setSolaz(String solaz) {
		this.solaz = solaz;
	}
	public String getSolel() {
		return solel;
	}
	public void setSolel(String solel) {
		this.solel = solel;
	}
	public String getAmntskycld() {
		return amntskycld;
	}
	public void setAmntskycld(String amntskycld) {
		this.amntskycld = amntskycld;
	}
	public String getWeanum() {
		return weanum;
	}
	public void setWeanum(String weanum) {
		this.weanum = weanum;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
