package com.bcc.security.admin.mapper;

import java.util.List;

import com.bcc.security.admin.dataparse.dataobj.DaSystem;

public interface DaSystemMapper {
    int deleteByPrimaryKey(String taskdataid);

    int insert(DaSystem record);

    int insertSelective(DaSystem record);

    DaSystem selectByPrimaryKey(String taskdataid);
    
    List<DaSystem> selectByTaskId(String taskdataid);

    int updateByPrimaryKeySelective(DaSystem record);

    int updateByPrimaryKey(DaSystem record);
}