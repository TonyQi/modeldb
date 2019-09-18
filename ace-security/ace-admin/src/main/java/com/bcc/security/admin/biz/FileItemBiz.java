package com.bcc.security.admin.biz;

import com.ace.cache.annotation.Cache;
import com.bcc.security.admin.entity.FileItem;
import com.bcc.security.admin.mapper.FileItemMapper;
import com.bcc.security.common.biz.BaseBiz;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author tj
 * @create 2017-07-01 14:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileItemBiz extends BaseBiz<FileItemMapper,FileItem> {
	
	@Cache(key = "fileinfo:items:u{1}")
	public List<FileItem> selectByFileInfoId(String fieldInfoId) {
		FileItem example=new FileItem();
		example.setFileInfoId(fieldInfoId);
        return mapper.select(example);
    }
    
	
	public Iterable selectFileInfoIdByFileName(String fileName) {
		Example example = new Example(FileItem.class);
		Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(fileName)) {
        	criteria.andLike("fileName", "%" + fileName + "%");
		}
        List<FileItem> list=mapper.selectByExample(example);
        HashSet<String> string=new HashSet<String>();
        for(FileItem fileItem:list) {
        	string.add(fileItem.getFileInfoId());
        }
        return string;
    }
	
	
}
