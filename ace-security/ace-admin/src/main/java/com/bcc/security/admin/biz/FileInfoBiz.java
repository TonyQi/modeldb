package com.bcc.security.admin.biz;

import com.bcc.security.admin.entity.DaAims;
import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.admin.entity.FileItem;
import com.bcc.security.admin.entity.GateLog;
import com.bcc.security.admin.mapper.FileItemMapper;
import com.bcc.security.admin.mapper.FileinfoMapper;
import com.bcc.security.admin.mapper.GateLogMapper;
import com.bcc.security.admin.vo.FileInfoCountVo;
import com.bcc.security.admin.vo.FileInfoVo;
import com.bcc.security.common.biz.BaseBiz;
import com.bcc.security.common.msg.TableResultResponse;
import com.bcc.security.common.util.EntityUtils;
import com.bcc.security.common.util.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FileInfoBiz extends BaseBiz<FileinfoMapper,FileInfo> {
	@Autowired
	FileItemMapper fileItemMapper;
	@Autowired
	FileItemBiz fileItemBiz;
	@Autowired
	DaAimsBiz aimsBiz;
    public void insertWithFileItem(FileInfo entity,List<FileItem> fileItems) {
    	
    	String aimsId=entity.getAimsId();
    	DaAims aims=aimsBiz.selectById(aimsId);
    	if (aims!=null) {
    		entity.setAimsName(aims.getAimsName());
		}
        mapper.insert(entity);
        if (fileItems!=null&&fileItems.size()>0) {
			for (FileItem item :fileItems) {
				fileItemMapper.insertSelective(item);
			}
		}
    }

    public void insertWithFileItemSelective(FileInfo entity,List<FileItem> fileItems) {
    	String aimsId=entity.getAimsId();
    	DaAims aims=aimsBiz.selectById(aimsId);
    	if (aims!=null) {
    		entity.setAimsName(aims.getAimsName());
		}
    	int id=mapper.insertSelective(entity);
        if (fileItems!=null&&fileItems.size()>0) {
			for (FileItem item :fileItems) {
				fileItemMapper.insertSelective(item);
			}
		}
    }
    
    public void insertFileItem(FileItem fileItems) {
		fileItemMapper.insert(fileItems);
    }

	@Override
	public void deleteById(Object id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
		FileItem item=new FileItem();
		item.setFileInfoId((String)id);
		fileItemMapper.delete(item);
		
	}
    
	public TableResultResponse<FileInfoVo> queryFileInfoWithPage(Query query) throws ParseException{
			Example example = new Example(FileInfo.class);
            Example.Criteria criteria = example.createCriteria();
            String filename=(String) query.get("filename");
            if (StringUtils.isNotEmpty(filename)) {
            	criteria.andIn("id", fileItemBiz.selectFileInfoIdByFileName(filename));
			}
            String datatype=(String) query.get("datatype");
            if (StringUtils.isNotEmpty(datatype)) {
            	criteria.andEqualTo("dataType", datatype);
			}
            String aimsId=(String)query.get("aimsId");
            if (StringUtils.isNotEmpty(aimsId)) {
            	criteria.andEqualTo("aimsId", aimsId);
			} 
            String secret=(String) query.get("secret");
            if (StringUtils.isNotEmpty(secret)) {
            	criteria.andEqualTo("secret", secret);
			} 
            String inputname=(String) query.get("inputname");
            if (StringUtils.isNotEmpty(inputname)) {
            	criteria.andLike("uploadUser", "%" + inputname + "%");
			}
            String start=(String) query.get("startdate");
            String end=(String) query.get("enddate");
            if(StringUtils.isNotEmpty(start)&&StringUtils.isNotEmpty(end)) {
            	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
            	Date startDate=dateFormat.parse(start);
            	Date endDate=dateFormat.parse(end);
            	criteria.andBetween("uploadTime", startDate, endDate);
            }
            Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
            List<FileInfo> list = mapper.selectByExample(example);
            List<FileInfoVo> fileInfoVos=new LinkedList<FileInfoVo>();
            for(FileInfo fileInfo:list) {
            	FileInfoVo fileInfoVo=new FileInfoVo();
            	BeanUtils.copyProperties(fileInfo, fileInfoVo);
            	fileInfoVo.setItems(fileItemBiz.selectByFileInfoId(fileInfo.getId()));
            	fileInfoVos.add(fileInfoVo);
            }
            return new TableResultResponse<FileInfoVo>(result.getTotal(), fileInfoVos);
	}
	
	public List<FileInfoCountVo> getFileInfoCount(){
		return mapper.getFileInfoCount();
		
	}
    
}
