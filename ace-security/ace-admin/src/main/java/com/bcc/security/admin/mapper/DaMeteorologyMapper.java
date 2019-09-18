package com.bcc.security.admin.mapper;

import java.util.List;

import com.bcc.security.admin.dataparse.dataobj.DaMeteorology;

public interface DaMeteorologyMapper {
    int deleteByPrimaryKey(String taskdataid);

    int insert(DaMeteorology record);

    int insertSelective(DaMeteorology record);

    DaMeteorology selectByPrimaryKey(String taskdataid);
    
    List<DaMeteorology> selectByTaskId(String taskdataid);

    int updateByPrimaryKeySelective(DaMeteorology record);

    int updateByPrimaryKey(DaMeteorology record);
}