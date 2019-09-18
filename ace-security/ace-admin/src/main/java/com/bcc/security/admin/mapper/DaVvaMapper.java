package com.bcc.security.admin.mapper;

import java.util.List;

import com.bcc.security.admin.dataparse.dataobj.DaVva;

public interface DaVvaMapper {
    int deleteByPrimaryKey(String taskdataid);

    int insert(DaVva record);

    int insertSelective(DaVva record);

    DaVva selectByPrimaryKey(String taskdataid);

    List<DaVva> selectByTaskId(String taskdataid);
    
    int updateByPrimaryKeySelective(DaVva record);

    int updateByPrimaryKey(DaVva record);
}