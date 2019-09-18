package com.bcc.security.admin.mapper;

import java.util.List;

import com.bcc.security.admin.dataparse.dataobj.DaConditions;

public interface DaConditionsMapper {
    int deleteByPrimaryKey(String taskdataid);

    int insert(DaConditions record);

    int insertSelective(DaConditions record);

    DaConditions selectByPrimaryKey(String taskdataid);
    
    List<DaConditions> selectByTaskId(String taskdataid);

    int updateByPrimaryKeySelective(DaConditions record);

    int updateByPrimaryKey(DaConditions record);
}