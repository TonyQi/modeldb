package com.bcc.security.admin.mapper;

import com.bcc.security.admin.dataparse.dataobj.DaData;
import java.math.BigDecimal;
import java.util.List;

public interface DaDataMapper {
    int deleteByPrimaryKey(BigDecimal dataid);

    int insert(DaData record);

    int insertSelective(DaData record);

    DaData selectByPrimaryKey(String dataid);
    
    List<DaData> selectByTaskId(String taskId);

    int updateByPrimaryKeySelective(DaData record);

    int updateByPrimaryKeyWithBLOBs(DaData record);

    int updateByPrimaryKey(DaData record);
}