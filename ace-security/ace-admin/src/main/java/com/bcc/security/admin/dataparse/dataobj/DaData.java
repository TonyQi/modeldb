package com.bcc.security.admin.dataparse.dataobj;

import java.math.BigDecimal;

public class DaData {
    private String dataid;

    private String taskdataid;

    private String type;

    private byte[] databody;

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getTaskdataid() {
        return taskdataid;
    }

    public void setTaskdataid(String taskdataid) {
        this.taskdataid = taskdataid == null ? null : taskdataid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public byte[] getDatabody() {
        return databody;
    }

    public void setDatabody(byte[] databody) {
        this.databody = databody;
    }
}