package com.bcc.security.admin.mapper;

import java.util.List;

import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.admin.entity.GateLog;
import com.bcc.security.admin.vo.FileInfoCountVo;

import tk.mybatis.mapper.common.Mapper;

public interface FileinfoMapper extends Mapper<FileInfo> {
	
	public List<FileInfoCountVo> getFileInfoCount();
}