package com.bcc.security.admin.rest;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ace.cache.service.IRedisService;
import com.alibaba.fastjson.JSONObject;
import com.bcc.security.admin.biz.DaConditionsBiz;
import com.bcc.security.admin.biz.DaDataBiz;
import com.bcc.security.admin.biz.DaMeteorologyBiz;
import com.bcc.security.admin.biz.DaSubTaskdescBiz;
import com.bcc.security.admin.biz.DaSystemBiz;
import com.bcc.security.admin.biz.DaVvaBiz;
import com.bcc.security.admin.biz.FileInfoBiz;
import com.bcc.security.admin.biz.FileItemBiz;
import com.bcc.security.admin.biz.UserBiz;
import com.bcc.security.admin.config.BccConfig;
import com.bcc.security.admin.dataparse.dataobj.DaConditions;
import com.bcc.security.admin.dataparse.dataobj.DaData;
import com.bcc.security.admin.dataparse.dataobj.DaMeteorology;
import com.bcc.security.admin.dataparse.dataobj.DaSubTaskdesc;
import com.bcc.security.admin.dataparse.dataobj.DaSystem;
import com.bcc.security.admin.dataparse.dataobj.DaVva;
import com.bcc.security.admin.dataparse.model.Point;
import com.bcc.security.admin.dataparse.model.SubTaskdesc;
import com.bcc.security.admin.dataparse.utils.ExcelConfigure;
import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.admin.entity.FileItem;
import com.bcc.security.admin.entity.User;
import com.bcc.security.admin.rpc.service.PermissionService;
import com.bcc.security.admin.vo.FileInfoCountVo;
import com.bcc.security.admin.vo.FileInfoVo;
import com.bcc.security.admin.vo.FrontUser;
import com.bcc.security.auth.client.annotation.IgnoreClientToken;
import com.bcc.security.auth.client.annotation.IgnoreUserToken;
import com.bcc.security.common.msg.ObjectRestResponse;
import com.bcc.security.common.msg.TableResultResponse;
import com.bcc.security.common.rest.BaseController;
import com.bcc.security.common.util.Query;
@Controller
@RequestMapping("file")
public class FileController extends BaseController<FileInfoBiz, FileInfo> {
	@Autowired
    private PermissionService permissionService;
	
	@Autowired
	private FileItemBiz fileItemBiz;
	@Autowired
	UserBiz userBiz;
	
	@Autowired
    private IRedisService redisCacheService;
	
	@Autowired
	private DaConditionsBiz daConditionsBiz;
	@Autowired
	private DaSystemBiz daSystemBiz;
	@Autowired
	private DaVvaBiz daVvaBiz;
	@Autowired
	private DaMeteorologyBiz daMeteorologyBiz;
	@Autowired
	private DaDataBiz daDataBiz;
	@Autowired
	private DaSubTaskdescBiz daSubTaskdescBiz;
	@Autowired
	private BccConfig bccConfig;
	
	
    //文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(HttpServletRequest request,HttpServletResponse response)  {
    	List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		String dataType=request.getParameter("datatype");
		String aimsId=request.getParameter("aimsId");
		String secretStr=request.getParameter("secret");
		Integer secret=Integer.parseInt(secretStr);
		String description=request.getParameter("description");
		String token=request.getHeader("Authorization");
		FrontUser fontUser=null;
		try {
			fontUser = permissionService.getUserInfo(token);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			response.setStatus(401, "Session TimeOut!!");
			return "Failed!!";
		}
		
		FileInfo fileInfo=new FileInfo();
		fileInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		fileInfo.setDataType(dataType);
		fileInfo.setSecret(secret);
		fileInfo.setDescription(description);
		fileInfo.setUploadTime(new Date());
		fileInfo.setAimsId(aimsId);
		if (fontUser!=null) {
			fileInfo.setUploadUser(fontUser.getName());
			fileInfo.setUploadUserId(fontUser.getId());
		}
		List<FileItem> fileItems=new ArrayList<FileItem>();
		for (int i = 0; i < files.size(); ++i) {
			FileItem item=new FileItem();
			file = files.get(i);
			if (!file.isEmpty()) {
			    try {
			    	item.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			    	String fileName =file.getOriginalFilename();
			    	item.setFileName(fileName);
			    	if(i==0&&!dataType.equalsIgnoreCase("benchmark")){
			    		fileInfo.setFileName(file.getOriginalFilename());
			    	}
			    	String filePath = bccConfig.getFilelocation();
			    	String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
			        // 解决中文问题，liunx下中文路径，图片显示问题
			    	String suffixName = fileName.substring(fileName.lastIndexOf("."));
			        //fileName = item.getId() + suffixName;
			        File dest = new File(filePath+File.separator +date+File.separator+fileInfo.getId()+File.separator+ fileName);
			        item.setStorageUrl((date+File.separator+ fileInfo.getId()+File.separator+fileName));
			        item.setFileInfoId(fileInfo.getId());
			        // 检测是否存在目录
			        if (!dest.getParentFile().exists()) {
			            dest.getParentFile().mkdirs();
			        }
			        try {
			            file.transferTo(dest);
			        } catch (IllegalStateException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			
			    } catch (Exception e) {
			        stream = null;
			        return "You failed to upload " + i + " => "+ e.getMessage();
			    }
			} else {
			    return "You failed to upload " + i + " because the file was empty.";
			}
			fileItems.add(item);
		}
		baseBiz.insertWithFileItemSelective(fileInfo, fileItems);
		String obj=JSONObject.toJSONString(fileInfo);
		redisCacheService.rpush("bcc:admin:parseTask", obj);
		return "upload successful";
	}
    
    
    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<FileInfo> remove(@PathVariable String id){
        baseBiz.deleteById(id);
        return new ObjectRestResponse<FileInfo>();
    }
    
    @RequestMapping(value = "/fileinfopage",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<FileInfoVo> fileInfoPage(@RequestParam Map<String, Object> params) throws ParseException{
    	Query query = new Query(params);
    	return baseBiz.queryFileInfoWithPage(query);
    }
   
    @RequestMapping("/downloadFile") 
    @IgnoreUserToken
    public void downloadFile(@RequestParam Map<String, Object> params,HttpServletResponse response) {
    	String id=(String) params.get("id");
    	String token=(String) params.get("token");
    	FileItem item=fileItemBiz.selectById(id);
    	if(item!=null) {
    		try {
				if(!checkDownPrimession(item.getFileInfoId(),token)) {
					response.setStatus(401,"你没有足够的权限下载改文件!");
					return;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				response.setStatus(401,"FILE CANNOT DOWNLOAD!");
				e.printStackTrace();
			}
    	}else {
    		response.setStatus(404);
    		return;
    	}
    	String filePath = bccConfig.getFilelocation()+File.separator+item.getStorageUrl();
        File file =  new File(filePath);
        if (file.exists()){
            try {
				download(file,response,item.getFileName());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				response.setStatus(500,"FILE CANNOT DOWNLOAD!");
	    		return;
			}
        }
    }
    
    protected void  download(File file, HttpServletResponse response,String fileName) throws UnsupportedEncodingException{
        response.setHeader("content-type", "application/octet-stream");
        //response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(fileName,"UTF-8"));

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(file.getPath());
            int len = 0;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            while((len = in.read(buffer)) > 0) {
                out.write(buffer,0,len);
            }

        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(in != null) {
                try {
                    in.close();
                }catch(Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
    
	private boolean checkDownPrimession(String fileInfoId,String token) throws Exception {
    	
    	int fileSecret=3,userSecret=1;
		FileInfo fileInfo=baseBiz.selectById(fileInfoId);
		if(fileInfo!=null) {
			fileSecret=fileInfo.getSecret();
			
		}
		FrontUser fontUser=permissionService.getUserInfo(token);
		if (fontUser!=null) {
			User user=userBiz.getUserByUsername(fontUser.username);
	    	if(user!=null) {
	    		userSecret=user.getSecret();
	    		
	    	}
		}
    	return userSecret>=fileSecret;
    }
    
	@RequestMapping(value = "/twoDData/{fileInfoId}",method = RequestMethod.GET)
	@IgnoreUserToken
	@IgnoreClientToken
	public  void getTwoDData(@PathVariable String fileInfoId,HttpServletResponse response) {
		String filePath = bccConfig.getFilelocation()+File.separator+"twodata"+File.separator+fileInfoId+".txt";
        File file =  new File(filePath);
        if (file.exists()){
            try {
				download(file,response,fileInfoId+".txt");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				response.setStatus(500,"FILE CANNOT DOWNLOAD!");
	    		return;
			}
        }
		
	}
	
	
	
	@RequestMapping(value = "/fileInfoDetail/{fileInfoId}",method = RequestMethod.GET)
    @ResponseBody
	public Map<String,String> getFileInfoDetailByfileId(@PathVariable String fileInfoId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		Map<String, String> result=new HashMap<String,String>();
		List<DaSubTaskdesc> subTaskdescs =daSubTaskdescBiz.selectByTaskId(fileInfoId);
		Field[] fileds = DaSubTaskdesc.class.getDeclaredFields();
		if (subTaskdescs!=null&&subTaskdescs.size()>0) {
			for (Field field : fileds) {
				String methodName = "get" + field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1, field.getName().length());
				Object temp = MethodUtils.invokeMethod(subTaskdescs.get(0), methodName, null);
				result.put(field.getName(), temp==null?"":temp.toString());
			}
			
		}else {
			for (Field field : fileds) {
				result.put(field.getName(), "");
			}
		}
		return result;
	}
	
	
	
	
	@RequestMapping(value = "/lineData/{fileInfoId}",method = RequestMethod.GET)
    @ResponseBody
	public List<Map<String,Object>> getLineDataByTaskId(@PathVariable String fileInfoId){
		List<DaSubTaskdesc> subTaskdescs =daSubTaskdescBiz.selectByTaskId(fileInfoId);
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		if (subTaskdescs!=null &&subTaskdescs.size()>0) {
			String charactdatatype=subTaskdescs.get(0).getCharactdatatype();
			Point points=ExcelConfigure.cache_excel.get(charactdatatype);
			if (points!=null) {
				List<DaData> daDatas=daDataBiz.selectByTaskId(fileInfoId);
				for(DaData daData:daDatas) {
					byte[] data=daData.getDatabody();
					//System.out.println(new String(data));
					Map temp=dataStringToList(data,points);
					result.add(temp);
				}
			}
		}
		return result;
	}
	
	
	public Map dataStringToList(byte[] data,Point p){
		Map result=new HashMap();
		String x=p.getX();
		String[] y=p.getY();
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data))); 
			String str=br.readLine();
			String[] title=org.apache.commons.lang.StringUtils.split(str);
			List<String[]> list=new ArrayList<String[]>();
			while((str=br.readLine())!=null){
				String[] columns = org.apache.commons.lang.StringUtils.split(str);
				list.add(columns);
			}
			
			int xIndex=getIndex(x,title);
			int[] yIndex=new int[y.length];
			for(int j=0;j<y.length;j++) {
				int yde=getIndex(y[j], title);
				yIndex[j]=yde;
			}
			List<Double> xData=new ArrayList<Double>();
			List<Double[]> yData=new ArrayList<Double[]>();
			for(String[] s:list) {
				String datax=s[xIndex];
				xData.add(Double.parseDouble(datax));
			}
			for(int yd:yIndex) {
				List<Double> ydatas=new ArrayList<Double>();
				for(String[] s:list) {
					if(yd<s.length) {
						String datay=s[yd];
						ydatas.add(Double.parseDouble(datay));
					}else {
					}
					
					
				}
				Double[] ydatas1=(Double[]) ydatas.toArray(new Double[ydatas.size()]);
				yData.add(ydatas1);
				
			}
			result.put("x", xData);
			List<Map> yResult=new ArrayList<Map>();
			for (int i = 0; i < y.length; i++) {
				Map temp=new HashMap<>();
				temp.put("name", y[i]);
				temp.put("data", yData.get(i));
				yResult.add(temp);
			} 
			result.put("y", yResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int getIndex(String clumnName,String[] titles) {
		int index=-1;
		for(int i=0;i<titles.length;i++) {
			if (titles[i].equalsIgnoreCase(clumnName)) {
				index= i;
				break;
			}
		}
		return index;
	}
	
	@RequestMapping(value = "/getfileinfocount",method = RequestMethod.GET)
    @ResponseBody
	public List<FileInfoCountVo> getFileInfoCount(){
		return baseBiz.getFileInfoCount();
	}
	
	public static void main(String[] args) {
		String s="fo        RCS	    GlintPHI	\r\n" + 
				"12      -11.17          0        \r\n" + 
				"12.015  -8.17          .05 \r\n" + 
				"12.03   -5.82          .08 \r\n" + 
				"12.045  -4.22         9.000001E-02 \r\n" + 
				"12.06   -3.42          .11    \r\n" + 
				"12.075  -3.62          .07 \r\n" + 
				"12.09   -5.23          .06                        \r\n" + 
				"12.105  -9.03          .03 ";
		System.out.println(s);
		String[] ss=s.split("\r\n");
		for (String a:ss) {
			String[] aa=a.split("\t");
			System.out.println(aa[0]);
		}
	}
}
