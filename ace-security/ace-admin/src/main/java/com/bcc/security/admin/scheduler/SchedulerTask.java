package com.bcc.security.admin.scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ace.cache.service.IRedisService;
import com.alibaba.fastjson.JSONObject;
import com.bcc.security.admin.biz.FileInfoBiz;
import com.bcc.security.admin.biz.FileItemBiz;
import com.bcc.security.admin.config.BccConfig;
import com.bcc.security.admin.dataparse.ImageParser;
import com.bcc.security.admin.dataparse.TaskDataParser;
import com.bcc.security.admin.dataparse.TaskDataService;
import com.bcc.security.admin.dataparse.model.TaskData;
import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.admin.entity.FileItem;

@Component
public class SchedulerTask {
	
	@Autowired
    private IRedisService redisCacheService;
	@Autowired
    private TaskDataParser taskDataParser;
	@Autowired
	ImageParser imageParser;
	@Autowired
	TaskDataService taskDataService;
	@Autowired
	private FileItemBiz fileItemBiz;
	@Autowired
	private FileInfoBiz fileInfoBiz;
	@Autowired
	private BccConfig bccConfig;
	public int BUFFER_LENGTH=512;
	
	@Scheduled(fixedRate=2000)  
    public void testTasks() {      
		Object obj=redisCacheService.lpop("bcc:admin:parseTask");
		if (obj!=null) {
			FileInfo fileInfo=JSONObject.parseObject(String.valueOf(obj), FileInfo.class);
			beginParseData(fileInfo);
		}
    }
	
	private void beginParseData(FileInfo fileInfo) {
		switch(fileInfo.getDataType())
		{
			case "onedimen":
				InputStream in=getTaskData(fileInfo);
				if(in!=null) {
					TaskData taskData=taskDataParser.parse(in);
					taskDataService.insertTaskDataToDB(fileInfo, taskData);
				}
				break;
			case "twodimen":
				
				InputStream tdIn=getTaskData(fileInfo);
				if(tdIn!=null) {
					TaskData taskData=taskDataParser.parse(tdIn);
					taskDataService.insertTaskDataToDB(fileInfo, taskData);
					imageParser.querySubTaskDescToCopyFile(fileInfo.getId(), taskData);
				}
				break;
			case "benchmark":
				List<FileItem> fileItems=fileItemBiz.selectByFileInfoId(fileInfo.getId());
				if (fileItems!=null&&fileItems.size()>0) {
					for(FileItem item:fileItems) {
						String fileName=item.getFileName();
						if (fileName.endsWith(".obj")) {
							fileInfo.setFileName(item.getStorageUrl());
							System.out.println(item.getStorageUrl());
							System.out.println(JSONObject.toJSON(fileInfo));
							fileInfoBiz.updateSelectiveById(fileInfo);
							break;
						}
					}
				}
				break;
		
		}
		
	}
	
	
	private InputStream getTaskData(FileInfo fileInfo) {
		Date uploadDate=fileInfo.getUploadTime();
		String id=fileInfo.getId();
    	List<FileItem> items=fileItemBiz.selectByFileInfoId(id);
    	InputStream in=null;
    	if(items.size()>0) {
    		String uri=bccConfig.getFilelocation()+File.separator+items.get(0).getStorageUrl();
    		try {
    			in=new FileInputStream(new File(uri));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return in;
	}
}
