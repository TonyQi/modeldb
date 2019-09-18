package com.bcc.security.admin.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcc.security.admin.biz.FileInfoBiz;
import com.bcc.security.admin.config.BccConfig;
import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.auth.client.annotation.IgnoreClientToken;
import com.bcc.security.auth.client.annotation.IgnoreUserToken;

@Controller
@RequestMapping("filedown")
@IgnoreUserToken
@IgnoreClientToken
public class DownFileController {
	@Autowired
	FileInfoBiz fileInfoBiz;
	@Autowired
	private BccConfig bccConfig;
	
	@RequestMapping(value = "/twoDData/{fileInfoId}",method = RequestMethod.GET)
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
	@RequestMapping(value = "/threeD/{fileInfoId}",method = RequestMethod.GET)
	public  void getThreeDData(@PathVariable String fileInfoId,HttpServletResponse response) {
		FileInfo fileInfo= fileInfoBiz.selectById(fileInfoId);
		if (fileInfo!=null&&fileInfo.getFileName()!=null) {
			String filePath = bccConfig.getFilelocation()+File.separator+fileInfo.getFileName();
			File file =  new File(filePath);
	        if (file.exists()){
	            try {
					download(file,response,fileInfoId+".obj");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					response.setStatus(500,"FILE CANNOT DOWNLOAD!");
		    		return;
				}
	        }
		}else {
			// TODO Auto-generated catch block
			response.setStatus(500,"FILE CANNOT DOWNLOAD!");
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
}
