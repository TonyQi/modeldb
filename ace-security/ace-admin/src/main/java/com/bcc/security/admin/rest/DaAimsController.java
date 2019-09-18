package com.bcc.security.admin.rest;

import com.ace.cache.service.IRedisService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcc.security.admin.biz.DaAimsBiz;
import com.bcc.security.admin.biz.FileInfoBiz;
import com.bcc.security.admin.biz.FileItemBiz;
import com.bcc.security.admin.config.BccConfig;
import com.bcc.security.admin.dataparse.utils.UnZipUtil;
import com.bcc.security.admin.dataparse.utils.ZipUtil;
import com.bcc.security.admin.entity.DaAims;
import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.admin.entity.FileItem;
import com.bcc.security.admin.rpc.service.PermissionService;
import com.bcc.security.admin.vo.FileInfoVo;
import com.bcc.security.admin.vo.FrontUser;
import com.bcc.security.auth.client.annotation.IgnoreUserToken;
import com.bcc.security.common.msg.ObjectRestResponse;
import com.bcc.security.common.msg.TableResultResponse;
import com.bcc.security.common.rest.BaseController;
import com.bcc.security.common.util.Query;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipFile;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 11:51
 */
@RestController
@RequestMapping("aims")
public class DaAimsController extends BaseController<DaAimsBiz,DaAims> {
	@Autowired
	private FileInfoBiz fileInfoBiz;
	
	@Autowired
	private FileItemBiz fileItemBiz;
	@Autowired
	private BccConfig bccConfig;
	@Autowired
    private PermissionService permissionService;
	@Autowired
	private DaAimsBiz daAimsBize;
	@Autowired
    private IRedisService redisCacheService;
	
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
	public ObjectRestResponse<DaAims> remove(@PathVariable String id) {
		// TODO Auto-generated method stub
		baseBiz.deleteById(id);
		return new ObjectRestResponse<DaAims>();
	}

	
	@RequestMapping(value = "/getbynamelike",method = RequestMethod.GET)
    @ResponseBody
    public List<DaAims> getNameLike(@RequestParam Map<String, Object> params) throws ParseException{
    	Query query = new Query(params);
    	return baseBiz.selectByNameLike(query);
    }
	
	@RequestMapping(value = "/getbyname",method = RequestMethod.GET)
    @ResponseBody
    public List<DaAims> getByName(@RequestParam Map<String, Object> params) throws ParseException{
    	Query query = new Query(params);
    	return baseBiz.selectByName(query);
    }
	
	
	@RequestMapping(value = "/exportaims",method = RequestMethod.GET) 
    @IgnoreUserToken
    public void downloadFile(@RequestParam Map<String, Object> params,HttpServletResponse response) throws IOException {
    	String aimsId=(String) params.get("id");
    	DaAims aims=baseBiz.selectById(aimsId);
    	List<String> fileUrls=new ArrayList<String>();
    	if (aims!=null) {
    		FileInfo example=new FileInfo();
        	example.setAimsId(aimsId);
        	List<FileInfo> fileInfos=fileInfoBiz.selectList(example);
        	List<FileInfoVo> fileInfoVos=new LinkedList<FileInfoVo>();
            for(FileInfo fileInfo:fileInfos) {
            	FileInfoVo fileInfoVo=new FileInfoVo();
            	BeanUtils.copyProperties(fileInfo, fileInfoVo);
            	fileInfoVo.setItems(fileItemBiz.selectByFileInfoId(fileInfo.getId()));
            	fileInfoVos.add(fileInfoVo);
            }
        	Map<String,Object> result=new HashMap<String,Object>();
        	result.put("aimsName", aims.getAimsName());
        	result.put("note", aims.getNote());
        	List<Map<String,Object>> files=new ArrayList<Map<String,Object>>();
        	ZipUtil zipUtil=new ZipUtil();
        	for(FileInfoVo fileInfo:fileInfoVos) {
        		Map<String,Object> file=new HashMap<String,Object>();
        		file.put("dataType", fileInfo.getDataType());
        		file.put("secret", fileInfo.getSecret()) ;
        		file.put("description",fileInfo.getDescription());
        		List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        		for(FileItem item:fileInfo.getItems()) {
        			Map<String,Object> itemInfo=new HashMap<String,Object>();
        			String fileName=item.getFileName();
        			itemInfo.put("fileName", fileName);
        			zipUtil.addFiles(bccConfig.getFilelocation()+File.separator+item.getStorageUrl());
        			items.add(itemInfo);
        		}
        		file.put("items", items);
        		files.add(file);
        	}
        	result.put("files", files);
        	String comment=JSONObject.toJSONString(result);
        	byte[] bytes =zipUtil.doCompress(comment);
        	response.setHeader("content-type", "application/octet-stream");
        	response.setHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(aims.getAimsName()+".zip","UTF-8"));
        	OutputStream out = null; 
        	int len = bytes.length;
            out = response.getOutputStream();
            out.write(bytes,0,len);
		}
    	
    }
	 
	//文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(HttpServletRequest request,HttpServletResponse response)  {
    	List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		String token=request.getHeader("Authorization");
		FrontUser fontUser=null;
		try {
			fontUser = permissionService.getUserInfo(token);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			response.setStatus(401, "Session TimeOut!!");
			return "Failed!!";
		}
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
			    try {
			    	String fileName=file.getOriginalFilename();
			    	if(fileName.endsWith(".zip")) {
			    		Map<String,Object> info=parseZipFile(saveFile(file));
			    		saveInfo(info,fontUser);
			    	}
			    } catch (Exception e) {
			        stream = null;
			        e.printStackTrace();
			        return "You failed to upload " + i + " => "+ e.getMessage();
			    }
			} else {
			    return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}
    
    
    private Map<String,Object> parseZipFile(File file) throws Exception {
    	Map<String,Object> result=new HashMap<String,Object>();
    	ZipFile zipFile=new ZipFile(file, Charset.forName("GBK"));
    	String filePath = bccConfig.getFilelocation();
    	String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
		String descDir= filePath+File.separator +date+File.separator;
    	UnZipUtil.unZipFiles(file, descDir);
    	String comment=zipFile.getComment();
    	JSONObject jsonObject=JSONObject.parseObject(comment);
    	DaAims aims=new DaAims();
    	aims.setAimsName(jsonObject.getString("aimsName"));
    	aims.setNote(jsonObject.getString("note"));
    	result.put("aims", aims);
    	JSONArray files=jsonObject.getJSONArray("files");
    	List<FileInfoVo> fileInfos=new ArrayList<FileInfoVo>();
    	for (Object object:files) {
    		FileInfoVo infoVo=JSONObject.parseObject(String.valueOf(object), FileInfoVo.class);
    		fileInfos.add(infoVo);
    	}
    	result.put("file", fileInfos);
    	return result;
    }
	
    
    private File saveFile(MultipartFile file) throws IllegalStateException, IOException {
    	String filePath = bccConfig.getFilelocation();
    	String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
		File dest = new File(filePath+File.separator +date+File.separator+ UUID.randomUUID().toString()+".zip");
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        return dest;
    }
    
    private void saveInfo(Map<String,Object> result,FrontUser fontUser) throws IOException {
    	DaAims aims=(DaAims) result.get("aims");
    	Map<String, Object> map=new HashMap<String,Object>();
    	map.put("name", aims.getAimsName());
    	Query query=new Query(map);
    	List<DaAims> list=daAimsBize.selectByName(query);
    	String aimsId=UUID.randomUUID().toString();
    	if (list!=null&&list.size()>0) {
    		aims=list.get(0);
		}else {
			aims.setId(aimsId);
			aims.setCreDate(new Date());
			daAimsBize.insertSelective(aims);
		}
    	List<FileInfoVo> fileInfos=(List<FileInfoVo>) result.get("file");
    	for (FileInfoVo infoVo:fileInfos) {
    		FileInfo fileInfo=new FileInfo();
    		BeanUtils.copyProperties(infoVo, fileInfo);
    		String fileInfoId=UUID.randomUUID().toString().replaceAll("-", "");
    		fileInfo.setId(fileInfoId);
    		fileInfo.setUploadTime(new Date());
    		fileInfo.setAimsId(aimsId);
			fileInfo.setAimsName(aims.getAimsName());
    		if (fontUser!=null) {
    			fileInfo.setUploadUser(fontUser.getName());
    			fileInfo.setUploadUserId(fontUser.getId());
    		}
    		List<FileItem> fileItems=getFileItem(fileInfoId,infoVo.getItems());
    		fileInfoBiz.insertWithFileItemSelective(fileInfo, fileItems);
    		String obj=JSONObject.toJSONString(fileInfo);
    		redisCacheService.rpush("bcc:admin:parseTask", obj);
		}
    	
    }
    
    private List<FileItem> getFileItem(String fileInfoId,List<FileItem> fileItems) throws IOException{
    	for (FileItem item:fileItems) {
    		item.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    		item.setFileInfoId(fileInfoId);
    		String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
	        // 解决中文问题，liunx下中文路径，图片显示问题
	    	//String suffixName = item.getFileName().substring(item.getFileName().lastIndexOf("."));
	        String fileName = item.getFileName();
	        String filePath = bccConfig.getFilelocation();
	        File dest = new File(filePath+File.separator +date+File.separator+ fileName);
	        copyFileUsingFileStreams(new File(filePath+File.separator +date+File.separator+fileInfoId+File.separator+item.getFileName()), dest);
	        item.setStorageUrl((date+File.separator+ fileName));
		}
    	return fileItems;
    }
    
    private void copyFileUsingFileStreams(File source, File dest)
            throws IOException {    
        InputStream input = null;    
        OutputStream output = null;    
        try {
               input = new FileInputStream(source);
               output = new FileOutputStream(dest);        
               byte[] buf = new byte[1024];        
               int bytesRead;        
               while ((bytesRead = input.read(buf)) > 0) {
                   output.write(buf, 0, bytesRead);
               }
        } finally {
            input.close();
            output.close();
        }
    }
}
