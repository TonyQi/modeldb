package com.bcc.security.admin.dataparse.dataobj;

public class DaSubTaskdesc {
    private String taskdataid;
    public String getTaskdataid() {
		return taskdataid;
	}
	public void setTaskdataid(String taskdataid) {
		this.taskdataid = taskdataid;
	}
	private String subtaskdescid;
  //目标名称 
  	private String tgtname ;
  	//目标说明 
  	private String tgtdescpt ;
  	//背景名称
  	private String bkgndname ;
  	//背景说明 
  	private String bkgnddescpt ;
  	//试验名称 
  	private String testname ;
  	//试验负责人
  	private String testleader ;
  	//试验单位
  	private String testunit ;
  	//任务代号
  	private String taskcode ;
  	//任务来源 
  	private String taskorig ;
  	//数据来源  
  	private String dataorig ;
  	//数据类型 
  	private String charactdatatype ;
  	//测量波段  
  	private String band ;
  	//测量地点 
  	private String measpla ;
  	//测量日期 
  	private String measdate ;
  	//缩比  
  	private String scale ;
  	//定标形式  
  	private String calibform ;
  	//定标方法  
  	private String refstdmet ;
  	//定标体名称 
  	private String refstdname ;
  	//定标体尺寸  
  	private String refstddim ;
  	//不确定度  
  	private String uncertainty ;
  	//适用范围  
  	private String suitextdata ;
  	//质量等级  
  	private String datagrd ;
  	//用户评价 
  	private String usereval ;
  	//大气传输修正模式 
  	private String  airmode ;
  	//测试目标名称
  	private String  testtarname ;
  	//飞行航线  
  	private String airline ;
  	//测试目标说明  
  	private String testtarcomm ;
  	//测量波段范围
  	private String  testbandrange ;
  	//地面粗糙度
  	private String grorgh ;
  	//目标照片
  	private String tgtpho ;
  	//经度 
  	private String lon ;
  	//海区海域
  	private String seaarea ;
  	//海况 
  	private String seacon ;
  	//块长
  	private String blklng ;
  	//目标高
  	private String tgthigh ;
  	//面目标名称 
  	private String surftgtname ;
  	//目标红外隐身措施说明
  	private String  mbhwyscssm ;
  	//	面目标分类 
  	private String surtgtclg ;
  	//	目标分类 
  	private String tgtclg ;
  	//	目标发动机说明
  	private String mbfdjgksm ;
  	//	目标工作特性说明 
  	private String mbgztxsm ;
  	//	目标电磁隐身措施说明
  	private String mbdcyscssm ;
  	//	目标宽 
  	private String tgtwd ;
  	//	目标结构特点说明 
  	private String mbjgtdsm ;
  	//	目标长
  	private String tgtlg ;
  	//	面目标说明 
  	private String surtgtcomm ;
  	//	土壤重量含水量
  	private String moiscontsoilwgt ;
  	//	纬度
  	private String lat ;
  	//	植被覆盖率
  	private String vgtcv ;
  	//	植被平均间距 
  	private String vgtgpavg ;
  	//	植被重量含水量
  	private String vgtwwlv ;
  	//	植株密度
  	private String  vgtcst ;
  	//	植株平均高度
  	private String vgthavg ;
  	//	备注
  	private String note ;
  	
  	public String getSubtaskdescid() {
  		return subtaskdescid;
  	}
  	public void setSubtaskdescid(String subtaskdescid) {
  		this.subtaskdescid = subtaskdescid;
  	}
  	public String getTgtname() {
  		return tgtname;
  	}
  	public void setTgtname(String tgtname) {
  		this.tgtname = tgtname;
  	}
  	public String getTgtdescpt() {
  		return tgtdescpt;
  	}
  	public void setTgtdescpt(String tgtdescpt) {
  		this.tgtdescpt = tgtdescpt;
  	}
  	public String getBkgndname() {
  		return bkgndname;
  	}
  	public void setBkgndname(String bkgndname) {
  		this.bkgndname = bkgndname;
  	}
  	public String getBkgnddescpt() {
  		return bkgnddescpt;
  	}
  	public void setBkgnddescpt(String bkgnddescpt) {
  		this.bkgnddescpt = bkgnddescpt;
  	}
  	public String getTestname() {
  		return testname;
  	}
  	public void setTestname(String testname) {
  		this.testname = testname;
  	}
  	public String getTestleader() {
  		return testleader;
  	}
  	public void setTestleader(String testleader) {
  		this.testleader = testleader;
  	}
  	public String getTestunit() {
  		return testunit;
  	}
  	public void setTestunit(String testunit) {
  		this.testunit = testunit;
  	}
  	public String getTaskcode() {
  		return taskcode;
  	}
  	public void setTaskcode(String taskcode) {
  		this.taskcode = taskcode;
  	}
  	public String getTaskorig() {
  		return taskorig;
  	}
  	public void setTaskorig(String taskorig) {
  		this.taskorig = taskorig;
  	}
  	public String getDataorig() {
  		return dataorig;
  	}
  	public void setDataorig(String dataorig) {
  		this.dataorig = dataorig;
  	}
  	public String getCharactdatatype() {
  		return charactdatatype;
  	}
  	public void setCharactdatatype(String charactdatatype) {
  		this.charactdatatype = charactdatatype;
  	}
  	public String getBand() {
  		return band;
  	}
  	public void setBand(String band) {
  		this.band = band;
  	}
  	public String getMeaspla() {
  		return measpla;
  	}
  	public void setMeaspla(String measpla) {
  		this.measpla = measpla;
  	}
  	public String getMeasdate() {
  		return measdate;
  	}
  	public void setMeasdate(String measdate) {
  		this.measdate = measdate;
  	}
  	public String getScale() {
  		return scale;
  	}
  	public void setScale(String scale) {
  		this.scale = scale;
  	}
  	public String getCalibform() {
  		return calibform;
  	}
  	public void setCalibform(String calibform) {
  		this.calibform = calibform;
  	}
  	public String getRefstdmet() {
  		return refstdmet;
  	}
  	public void setRefstdmet(String refstdmet) {
  		this.refstdmet = refstdmet;
  	}
  	public String getRefstdname() {
  		return refstdname;
  	}
  	public void setRefstdname(String refstdname) {
  		this.refstdname = refstdname;
  	}
  	public String getRefstddim() {
  		return refstddim;
  	}
  	public void setRefstddim(String refstddim) {
  		this.refstddim = refstddim;
  	}
  	public String getUncertainty() {
  		return uncertainty;
  	}
  	public void setUncertainty(String uncertainty) {
  		this.uncertainty = uncertainty;
  	}
  	public String getSuitextdata() {
  		return suitextdata;
  	}
  	public void setSuitextdata(String suitextdata) {
  		this.suitextdata = suitextdata;
  	}
  	public String getDatagrd() {
  		return datagrd;
  	}
  	public void setDatagrd(String datagrd) {
  		this.datagrd = datagrd;
  	}
  	public String getUsereval() {
  		return usereval;
  	}
  	public void setUsereval(String usereval) {
  		this.usereval = usereval;
  	}
  	public String getAirmode() {
  		return airmode;
  	}
  	public void setAirmode(String airmode) {
  		this.airmode = airmode;
  	}
  	public String getTesttarname() {
  		return testtarname;
  	}
  	public void setTesttarname(String testtarname) {
  		this.testtarname = testtarname;
  	}
  	public String getAirline() {
  		return airline;
  	}
  	public void setAirline(String airline) {
  		this.airline = airline;
  	}
  	public String getTesttarcomm() {
  		return testtarcomm;
  	}
  	public void setTesttarcomm(String testtarcomm) {
  		this.testtarcomm = testtarcomm;
  	}
  	public String getTestbandrange() {
  		return testbandrange;
  	}
  	public void setTestbandrange(String testbandrange) {
  		this.testbandrange = testbandrange;
  	}
  	public String getGrorgh() {
  		return grorgh;
  	}
  	public void setGrorgh(String grorgh) {
  		this.grorgh = grorgh;
  	}
  	public String getTgtpho() {
  		return tgtpho;
  	}
  	public void setTgtpho(String tgtpho) {
  		this.tgtpho = tgtpho;
  	}
  	public String getLon() {
  		return lon;
  	}
  	public void setLon(String lon) {
  		this.lon = lon;
  	}
  	public String getSeaarea() {
  		return seaarea;
  	}
  	public void setSeaarea(String seaarea) {
  		this.seaarea = seaarea;
  	}
  	public String getSeacon() {
  		return seacon;
  	}
  	public void setSeacon(String seacon) {
  		this.seacon = seacon;
  	}
  	public String getBlklng() {
  		return blklng;
  	}
  	public void setBlklng(String blklng) {
  		this.blklng = blklng;
  	}
  	public String getTgthigh() {
  		return tgthigh;
  	}
  	public void setTgthigh(String tgthigh) {
  		this.tgthigh = tgthigh;
  	}
  	public String getSurftgtname() {
  		return surftgtname;
  	}
  	public void setSurftgtname(String surftgtname) {
  		this.surftgtname = surftgtname;
  	}
  	public String getMbhwyscssm() {
  		return mbhwyscssm;
  	}
  	public void setMbhwyscssm(String mbhwyscssm) {
  		this.mbhwyscssm = mbhwyscssm;
  	}
  	public String getSurtgtclg() {
  		return surtgtclg;
  	}
  	public void setSurtgtclg(String surtgtclg) {
  		this.surtgtclg = surtgtclg;
  	}
  	public String getTgtclg() {
  		return tgtclg;
  	}
  	public void setTgtclg(String tgtclg) {
  		this.tgtclg = tgtclg;
  	}
  	public String getMbfdjgksm() {
  		return mbfdjgksm;
  	}
  	public void setMbfdjgksm(String mbfdjgksm) {
  		this.mbfdjgksm = mbfdjgksm;
  	}
  	public String getMbgztxsm() {
  		return mbgztxsm;
  	}
  	public void setMbgztxsm(String mbgztxsm) {
  		this.mbgztxsm = mbgztxsm;
  	}
  	public String getMbdcyscssm() {
  		return mbdcyscssm;
  	}
  	public void setMbdcyscssm(String mbdcyscssm) {
  		this.mbdcyscssm = mbdcyscssm;
  	}
  	public String getTgtwd() {
  		return tgtwd;
  	}
  	public void setTgtwd(String tgtwd) {
  		this.tgtwd = tgtwd;
  	}
  	public String getMbjgtdsm() {
  		return mbjgtdsm;
  	}
  	public void setMbjgtdsm(String mbjgtdsm) {
  		this.mbjgtdsm = mbjgtdsm;
  	}
  	public String getTgtlg() {
  		return tgtlg;
  	}
  	public void setTgtlg(String tgtlg) {
  		this.tgtlg = tgtlg;
  	}
  	public String getSurtgtcomm() {
  		return surtgtcomm;
  	}
  	public void setSurtgtcomm(String surtgtcomm) {
  		this.surtgtcomm = surtgtcomm;
  	}
  	public String getMoiscontsoilwgt() {
  		return moiscontsoilwgt;
  	}
  	public void setMoiscontsoilwgt(String moiscontsoilwgt) {
  		this.moiscontsoilwgt = moiscontsoilwgt;
  	}
  	public String getLat() {
  		return lat;
  	}
  	public void setLat(String lat) {
  		this.lat = lat;
  	}
  	public String getVgtcv() {
  		return vgtcv;
  	}
  	public void setVgtcv(String vgtcv) {
  		this.vgtcv = vgtcv;
  	}
  	public String getVgtgpavg() {
  		return vgtgpavg;
  	}
  	public void setVgtgpavg(String vgtgpavg) {
  		this.vgtgpavg = vgtgpavg;
  	}
  	public String getVgtwwlv() {
  		return vgtwwlv;
  	}
  	public void setVgtwwlv(String vgtwwlv) {
  		this.vgtwwlv = vgtwwlv;
  	}
  	public String getVgtcst() {
  		return vgtcst;
  	}
  	public void setVgtcst(String vgtcst) {
  		this.vgtcst = vgtcst;
  	}
  	public String getVgthavg() {
  		return vgthavg;
  	}
  	public void setVgthavg(String vgthavg) {
  		this.vgthavg = vgthavg;
  	}
  	public String getNote() {
  		return note;
  	}
  	public void setNote(String note) {
  		this.note = note;
  	}
}