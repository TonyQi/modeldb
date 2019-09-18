package com.bcc.security.admin.mapper;

import java.util.List;

import com.bcc.security.admin.dataparse.dataobj.DaSubTaskdesc;

public interface DaSubTaskdescMapper {
    int deleteByPrimaryKey(String taskdataid);

    int insert(DaSubTaskdesc record);

    int insertSelective(DaSubTaskdesc record);

    DaSubTaskdesc selectByPrimaryKey(String taskdataid);

    List<DaSubTaskdesc> selectByTaskId(String taskdataid);
    
    int updateByPrimaryKeySelective(DaSubTaskdesc record);

    int updateByPrimaryKey(DaSubTaskdesc record);
}