package com.bcc.security.admin.dataparse.dataobj;

public class DaMeteorology {
    private String taskdataid;

    private String meteorologyid;

    private String weath;

    private String airtemp;

    private String relahum;

    private String airpress;

    private String winvel;

    private String windir;

    private String horvis;

    private String soldirirra;

    private String skysolscatirra;

    private String skyinfirra;

    private String solaz;

    private String solel;

    private String amntskycld;

    private String weanum;

    private String note;

    public String getTaskdataid() {
        return taskdataid;
    }

    public void setTaskdataid(String taskdataid) {
        this.taskdataid = taskdataid == null ? null : taskdataid.trim();
    }

    public String getMeteorologyid() {
        return meteorologyid;
    }

    public void setMeteorologyid(String meteorologyid) {
        this.meteorologyid = meteorologyid == null ? null : meteorologyid.trim();
    }

    public String getWeath() {
        return weath;
    }

    public void setWeath(String weath) {
        this.weath = weath == null ? null : weath.trim();
    }

    public String getAirtemp() {
        return airtemp;
    }

    public void setAirtemp(String airtemp) {
        this.airtemp = airtemp == null ? null : airtemp.trim();
    }

    public String getRelahum() {
        return relahum;
    }

    public void setRelahum(String relahum) {
        this.relahum = relahum == null ? null : relahum.trim();
    }

    public String getAirpress() {
        return airpress;
    }

    public void setAirpress(String airpress) {
        this.airpress = airpress == null ? null : airpress.trim();
    }

    public String getWinvel() {
        return winvel;
    }

    public void setWinvel(String winvel) {
        this.winvel = winvel == null ? null : winvel.trim();
    }

    public String getWindir() {
        return windir;
    }

    public void setWindir(String windir) {
        this.windir = windir == null ? null : windir.trim();
    }

    public String getHorvis() {
        return horvis;
    }

    public void setHorvis(String horvis) {
        this.horvis = horvis == null ? null : horvis.trim();
    }

    public String getSoldirirra() {
        return soldirirra;
    }

    public void setSoldirirra(String soldirirra) {
        this.soldirirra = soldirirra == null ? null : soldirirra.trim();
    }

    public String getSkysolscatirra() {
        return skysolscatirra;
    }

    public void setSkysolscatirra(String skysolscatirra) {
        this.skysolscatirra = skysolscatirra == null ? null : skysolscatirra.trim();
    }

    public String getSkyinfirra() {
        return skyinfirra;
    }

    public void setSkyinfirra(String skyinfirra) {
        this.skyinfirra = skyinfirra == null ? null : skyinfirra.trim();
    }

    public String getSolaz() {
        return solaz;
    }

    public void setSolaz(String solaz) {
        this.solaz = solaz == null ? null : solaz.trim();
    }

    public String getSolel() {
        return solel;
    }

    public void setSolel(String solel) {
        this.solel = solel == null ? null : solel.trim();
    }

    public String getAmntskycld() {
        return amntskycld;
    }

    public void setAmntskycld(String amntskycld) {
        this.amntskycld = amntskycld == null ? null : amntskycld.trim();
    }

    public String getWeanum() {
        return weanum;
    }

    public void setWeanum(String weanum) {
        this.weanum = weanum == null ? null : weanum.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}