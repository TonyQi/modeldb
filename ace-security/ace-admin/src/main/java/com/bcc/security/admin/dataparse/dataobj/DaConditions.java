package com.bcc.security.admin.dataparse.dataobj;

public class DaConditions {
    public String getTaskdataid() {
		return taskdataid;
	}
	public void setTaskdataid(String taskdataid) {
		this.taskdataid = taskdataid;
	}
	private String taskdataid;
//	测量条件ID
	private String conditionid ;
//	子任务编号
	private String subtaskid ;
//	测量系统ID
	private String systemid ;
//	气象条件ID
	private String meteorologyid ;
//	测量时刻
	private String meastime ;
//	天气 
	private String weath ;
//	飞行动作
	private String flyact ;
//	飞行架次 
	private String flight ;
//	频率点数
	private String freqpts ;
//	工作频率
	private String opefreq ;
//	起始工作频率
	private String begnopefreq ;
//	终止工作频率
	private String endopefreq ;
//	工作频率步长 
	private String stpopefrerq ;
//	极化组合 
	private String polcomb ;
//	目标距离
	private String tgtrng ;
//	目标距离范围
	private String exttgtrng ;
//	目标高度 
	private String tgtalt ;
//	目标速度
	private String tgtvel ;
//	目标航向角 
	private String tgtheadanl ;
//	天线高度
	private String antalt ;
//	目标俯仰角
	private String tgtpit ;
//	目标俯仰角范围
	private String exttgtpit ;
//	目标横滚角
	private String tgtrl ;
//	目标横滚角范围
	private String exttgtrl ;
//	双站角
	private String bistaanl ;
//	起始目标方位角
	private String begntgtaz ;
//	终止目标方位角
	private String endtgtaz ;
//	目标方位角步长
	private String steptgtaz ;
//	目标方位角
	private String tgtaz ;
//	起始双站角
	private String  begnbistaanl ;
//	终止双站角 
	private String endbistaanl ;
//	双站角步长
	private String  stpbistaanl ;
//	起始径向距离坐标
	private String  begndownrng ;
//	终止径向距离坐标
	private String  enddownrng ;
//	径向距离坐标步长 
	private String stepdownrng ;
//	起始横向距离坐标 
	private String begncrossrng ;
//	终止横向距离坐标
	private String  endcrossrng ;
//	横向距离坐标步长
	private String  stepcrossrng ;
//	回波采样率
	private String signsampfreq ;
//	目标姿态数据率
	private String  sampfreqtgtatt ;
//	雷达姿态数据率
	private String  sampfreqrdratt ;
//	发动机状态 
	private String engstus ;
//	测量起止时刻 
	private String begnendmeastime ;
//	图像水平像素
	private String  txspxs ;
//	图像垂直像素 
	private String txczxs ;
//	测量高度
	private String  measalt ;
//	探测器高度
	private String  detalt ;
//	背景高度 
	private String bkgndalt ;
//	材料高度
	private String matlalt ;
//	探测方位角
	private String  detaz ;
//	探测方位角范围
	private String  extdetaz ;
//	探测俯仰角
	private String  detel ;
//	探测偏振角
	private String  detpolanl ;
//	探测俯仰角范围
	private String  extdetel ;
//	入射方位角范围
	private String  extincaz ;
//	入射方位角 
	private String incaz ;
//	入射俯仰角 
	private String incel ;
//	入射偏振角
	private String incpolanl ;
//	方向半球反射率
	private String dirsemispherefl ;
//	方位向像素尺寸
	private String  dircpixsize ;
//	方位向起始值 
	private String azistart ;
//	飞行高度 
	private String flgalt ;
//	飞行速度
	private String  flightvel ;
//	方位分辨率
	private String  azires ;
//	成像模式
	private String  vmode ;
//	目标纬度 
	private String tgtlat ;
//	降采
	private String descsamp ;
//	距离向像素尺寸
	private String  pirngrespixsize ;
//	距离分辨率
	private String  rngresdistlv ;
//	距离门数
	private String  rngatc ;
//	块数
	private String  blkcnt ;
//	脉冲重复频率
	private String  sreptfreq ;
//	目标经度
	private String  tgtlong ;
//	目标方位角范围
	private String  exttgtaz ;
//	入射角
	private String incanl ;
//	视数
	private String  mullookfact ;
//	数据采集起始时间
	private String  datacolstart ;
//	数据采集终止时间
	private String  datacolend ;
//	图像类型
	private String imgctl ;
//	备注
	private String  note ;
	public String getConditionid() {
		return conditionid;
	}
	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}
	public String getSubtaskid() {
		return subtaskid;
	}
	public void setSubtaskid(String subtaskid) {
		this.subtaskid = subtaskid;
	}
	public String getSystemid() {
		return systemid;
	}
	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}
	public String getMeteorologyid() {
		return meteorologyid;
	}
	public void setMeteorologyid(String meteorologyid) {
		this.meteorologyid = meteorologyid;
	}
	public String getMeastime() {
		return meastime;
	}
	public void setMeastime(String meastime) {
		this.meastime = meastime;
	}
	public String getWeath() {
		return weath;
	}
	public void setWeath(String weath) {
		this.weath = weath;
	}
	public String getFlyact() {
		return flyact;
	}
	public void setFlyact(String flyact) {
		this.flyact = flyact;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getFreqpts() {
		return freqpts;
	}
	public void setFreqpts(String freqpts) {
		this.freqpts = freqpts;
	}
	public String getOpefreq() {
		return opefreq;
	}
	public void setOpefreq(String opefreq) {
		this.opefreq = opefreq;
	}
	public String getBegnopefreq() {
		return begnopefreq;
	}
	public void setBegnopefreq(String begnopefreq) {
		this.begnopefreq = begnopefreq;
	}
	public String getEndopefreq() {
		return endopefreq;
	}
	public void setEndopefreq(String endopefreq) {
		this.endopefreq = endopefreq;
	}
	public String getStpopefrerq() {
		return stpopefrerq;
	}
	public void setStpopefrerq(String stpopefrerq) {
		this.stpopefrerq = stpopefrerq;
	}
	public String getPolcomb() {
		return polcomb;
	}
	public void setPolcomb(String polcomb) {
		this.polcomb = polcomb;
	}
	public String getTgtrng() {
		return tgtrng;
	}
	public void setTgtrng(String tgtrng) {
		this.tgtrng = tgtrng;
	}
	public String getExttgtrng() {
		return exttgtrng;
	}
	public void setExttgtrng(String exttgtrng) {
		this.exttgtrng = exttgtrng;
	}
	public String getTgtalt() {
		return tgtalt;
	}
	public void setTgtalt(String tgtalt) {
		this.tgtalt = tgtalt;
	}
	public String getTgtvel() {
		return tgtvel;
	}
	public void setTgtvel(String tgtvel) {
		this.tgtvel = tgtvel;
	}
	public String getTgtheadanl() {
		return tgtheadanl;
	}
	public void setTgtheadanl(String tgtheadanl) {
		this.tgtheadanl = tgtheadanl;
	}
	public String getAntalt() {
		return antalt;
	}
	public void setAntalt(String antalt) {
		this.antalt = antalt;
	}
	public String getTgtpit() {
		return tgtpit;
	}
	public void setTgtpit(String tgtpit) {
		this.tgtpit = tgtpit;
	}
	public String getExttgtpit() {
		return exttgtpit;
	}
	public void setExttgtpit(String exttgtpit) {
		this.exttgtpit = exttgtpit;
	}
	public String getTgtrl() {
		return tgtrl;
	}
	public void setTgtrl(String tgtrl) {
		this.tgtrl = tgtrl;
	}
	public String getExttgtrl() {
		return exttgtrl;
	}
	public void setExttgtrl(String exttgtrl) {
		this.exttgtrl = exttgtrl;
	}
	public String getBistaanl() {
		return bistaanl;
	}
	public void setBistaanl(String bistaanl) {
		this.bistaanl = bistaanl;
	}
	public String getBegntgtaz() {
		return begntgtaz;
	}
	public void setBegntgtaz(String begntgtaz) {
		this.begntgtaz = begntgtaz;
	}
	public String getEndtgtaz() {
		return endtgtaz;
	}
	public void setEndtgtaz(String endtgtaz) {
		this.endtgtaz = endtgtaz;
	}
	public String getSteptgtaz() {
		return steptgtaz;
	}
	public void setSteptgtaz(String steptgtaz) {
		this.steptgtaz = steptgtaz;
	}
	public String getTgtaz() {
		return tgtaz;
	}
	public void setTgtaz(String tgtaz) {
		this.tgtaz = tgtaz;
	}
	public String getBegnbistaanl() {
		return begnbistaanl;
	}
	public void setBegnbistaanl(String begnbistaanl) {
		this.begnbistaanl = begnbistaanl;
	}
	public String getEndbistaanl() {
		return endbistaanl;
	}
	public void setEndbistaanl(String endbistaanl) {
		this.endbistaanl = endbistaanl;
	}
	public String getStpbistaanl() {
		return stpbistaanl;
	}
	public void setStpbistaanl(String stpbistaanl) {
		this.stpbistaanl = stpbistaanl;
	}
	public String getBegndownrng() {
		return begndownrng;
	}
	public void setBegndownrng(String begndownrng) {
		this.begndownrng = begndownrng;
	}
	public String getEnddownrng() {
		return enddownrng;
	}
	public void setEnddownrng(String enddownrng) {
		this.enddownrng = enddownrng;
	}
	public String getStepdownrng() {
		return stepdownrng;
	}
	public void setStepdownrng(String stepdownrng) {
		this.stepdownrng = stepdownrng;
	}
	public String getBegncrossrng() {
		return begncrossrng;
	}
	public void setBegncrossrng(String begncrossrng) {
		this.begncrossrng = begncrossrng;
	}
	public String getEndcrossrng() {
		return endcrossrng;
	}
	public void setEndcrossrng(String endcrossrng) {
		this.endcrossrng = endcrossrng;
	}
	public String getStepcrossrng() {
		return stepcrossrng;
	}
	public void setStepcrossrng(String stepcrossrng) {
		this.stepcrossrng = stepcrossrng;
	}
	public String getSignsampfreq() {
		return signsampfreq;
	}
	public void setSignsampfreq(String signsampfreq) {
		this.signsampfreq = signsampfreq;
	}
	public String getSampfreqtgtatt() {
		return sampfreqtgtatt;
	}
	public void setSampfreqtgtatt(String sampfreqtgtatt) {
		this.sampfreqtgtatt = sampfreqtgtatt;
	}
	public String getSampfreqrdratt() {
		return sampfreqrdratt;
	}
	public void setSampfreqrdratt(String sampfreqrdratt) {
		this.sampfreqrdratt = sampfreqrdratt;
	}
	public String getEngstus() {
		return engstus;
	}
	public void setEngstus(String engstus) {
		this.engstus = engstus;
	}
	public String getBegnendmeastime() {
		return begnendmeastime;
	}
	public void setBegnendmeastime(String begnendmeastime) {
		this.begnendmeastime = begnendmeastime;
	}
	public String getTxspxs() {
		return txspxs;
	}
	public void setTxspxs(String txspxs) {
		this.txspxs = txspxs;
	}
	public String getTxczxs() {
		return txczxs;
	}
	public void setTxczxs(String txczxs) {
		this.txczxs = txczxs;
	}
	public String getMeasalt() {
		return measalt;
	}
	public void setMeasalt(String measalt) {
		this.measalt = measalt;
	}
	public String getDetalt() {
		return detalt;
	}
	public void setDetalt(String detalt) {
		this.detalt = detalt;
	}
	public String getBkgndalt() {
		return bkgndalt;
	}
	public void setBkgndalt(String bkgndalt) {
		this.bkgndalt = bkgndalt;
	}
	public String getMatlalt() {
		return matlalt;
	}
	public void setMatlalt(String matlalt) {
		this.matlalt = matlalt;
	}
	public String getDetaz() {
		return detaz;
	}
	public void setDetaz(String detaz) {
		this.detaz = detaz;
	}
	public String getExtdetaz() {
		return extdetaz;
	}
	public void setExtdetaz(String extdetaz) {
		this.extdetaz = extdetaz;
	}
	public String getDetel() {
		return detel;
	}
	public void setDetel(String detel) {
		this.detel = detel;
	}
	public String getDetpolanl() {
		return detpolanl;
	}
	public void setDetpolanl(String detpolanl) {
		this.detpolanl = detpolanl;
	}
	public String getExtdetel() {
		return extdetel;
	}
	public void setExtdetel(String extdetel) {
		this.extdetel = extdetel;
	}
	public String getExtincaz() {
		return extincaz;
	}
	public void setExtincaz(String extincaz) {
		this.extincaz = extincaz;
	}
	public String getIncaz() {
		return incaz;
	}
	public void setIncaz(String incaz) {
		this.incaz = incaz;
	}
	public String getIncel() {
		return incel;
	}
	public void setIncel(String incel) {
		this.incel = incel;
	}
	public String getIncpolanl() {
		return incpolanl;
	}
	public void setIncpolanl(String incpolanl) {
		this.incpolanl = incpolanl;
	}
	public String getDirsemispherefl() {
		return dirsemispherefl;
	}
	public void setDirsemispherefl(String dirsemispherefl) {
		this.dirsemispherefl = dirsemispherefl;
	}
	public String getDircpixsize() {
		return dircpixsize;
	}
	public void setDircpixsize(String dircpixsize) {
		this.dircpixsize = dircpixsize;
	}
	public String getAzistart() {
		return azistart;
	}
	public void setAzistart(String azistart) {
		this.azistart = azistart;
	}
	public String getFlgalt() {
		return flgalt;
	}
	public void setFlgalt(String flgalt) {
		this.flgalt = flgalt;
	}
	public String getFlightvel() {
		return flightvel;
	}
	public void setFlightvel(String flightvel) {
		this.flightvel = flightvel;
	}
	public String getAzires() {
		return azires;
	}
	public void setAzires(String azires) {
		this.azires = azires;
	}
	public String getVmode() {
		return vmode;
	}
	public void setVmode(String vmode) {
		this.vmode = vmode;
	}
	public String getTgtlat() {
		return tgtlat;
	}
	public void setTgtlat(String tgtlat) {
		this.tgtlat = tgtlat;
	}
	public String getDescsamp() {
		return descsamp;
	}
	public void setDescsamp(String descsamp) {
		this.descsamp = descsamp;
	}
	public String getPirngrespixsize() {
		return pirngrespixsize;
	}
	public void setPirngrespixsize(String pirngrespixsize) {
		this.pirngrespixsize = pirngrespixsize;
	}
	public String getRngresdistlv() {
		return rngresdistlv;
	}
	public void setRngresdistlv(String rngresdistlv) {
		this.rngresdistlv = rngresdistlv;
	}
	public String getRngatc() {
		return rngatc;
	}
	public void setRngatc(String rngatc) {
		this.rngatc = rngatc;
	}
	public String getBlkcnt() {
		return blkcnt;
	}
	public void setBlkcnt(String blkcnt) {
		this.blkcnt = blkcnt;
	}
	public String getSreptfreq() {
		return sreptfreq;
	}
	public void setSreptfreq(String sreptfreq) {
		this.sreptfreq = sreptfreq;
	}
	public String getTgtlong() {
		return tgtlong;
	}
	public void setTgtlong(String tgtlong) {
		this.tgtlong = tgtlong;
	}
	public String getExttgtaz() {
		return exttgtaz;
	}
	public void setExttgtaz(String exttgtaz) {
		this.exttgtaz = exttgtaz;
	}
	public String getIncanl() {
		return incanl;
	}
	public void setIncanl(String incanl) {
		this.incanl = incanl;
	}
	public String getMullookfact() {
		return mullookfact;
	}
	public void setMullookfact(String mullookfact) {
		this.mullookfact = mullookfact;
	}
	public String getDatacolstart() {
		return datacolstart;
	}
	public void setDatacolstart(String datacolstart) {
		this.datacolstart = datacolstart;
	}
	public String getDatacolend() {
		return datacolend;
	}
	public void setDatacolend(String datacolend) {
		this.datacolend = datacolend;
	}
	public String getImgctl() {
		return imgctl;
	}
	public void setImgctl(String imgctl) {
		this.imgctl = imgctl;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}