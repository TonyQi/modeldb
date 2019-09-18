package com.bcc.security.admin.dataparse.utils;

import org.apache.commons.io.FileUtils;  
import org.apache.commons.lang.ArrayUtils;  
import org.apache.commons.lang.RandomStringUtils;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
import java.io.*;  
import java.nio.charset.Charset;  
import java.util.HashSet;  
import java.util.Set;  
import java.util.zip.CRC32;  
import java.util.zip.CheckedOutputStream;  
import java.util.zip.ZipEntry;  
import java.util.zip.ZipOutputStream;  
  
/** 
 * Created with IntelliJ IDEA. 
 * User: yixian 
 * Date: 13-10-17 
 * Time: 下午2:52 
 * 将文件夹压缩并取回zip文件byte[] 
 */  
public class ZipUtil{  
    private Set<String> filePathSet = new HashSet<String>();  
    private File targetFile;  
    private Logger logger = LoggerFactory.getLogger(getClass());  
  
    public ZipUtil() {  
        String tempDirectory = FileUtils.getTempDirectoryPath();  
        if (!tempDirectory.endsWith(File.separator)) {  
            tempDirectory += File.separator;  
        }  
        targetFile = initRandomTarget(tempDirectory);  
    }  
  
    private synchronized File initRandomTarget(String tempDirectory) {  
        File target;  
        do {  
            String fileName = RandomStringUtils.random(20, true, true);  
            String targetFilePath = tempDirectory + fileName;  
            target = new File(targetFilePath);  
        } while (target.exists());  
        return target;  
    }  
  
    private boolean checkFilePath(String path) {  
        File file = new File(path);  
        return file.exists();  
    }  
  
    public boolean addFile(String filePath) {  
        if (checkFilePath(filePath)) {  
            filePathSet.add(filePath);  
            return true;  
        }  
        return false;  
    }  
  
    public String[] addFiles(String... paths) {  
        String[] notExistsFiles = new String[0];  
        for (String path : paths) {  
            if (!addFile(path)) {  
                ArrayUtils.add(notExistsFiles, path);  
            }  
        }  
        return notExistsFiles;  
    }  
  
    public byte[] doCompress(String comment) throws IOException {  
        FileOutputStream fos = new FileOutputStream(targetFile);  
        CheckedOutputStream cos = new CheckedOutputStream(fos, new CRC32());  
        ZipOutputStream zos = new ZipOutputStream(cos, Charset.forName("gbk"));  
        zos.setComment(comment);
        String baseDir = "";  
        for (String filePath : filePathSet) {  
            compress(new File(filePath), zos, baseDir);  
        }  
        zos.close();  
        return loadTargetFileBytes();  
    }  
  
    private void compress(File file, ZipOutputStream zos, String basePath) {  
        if (file.isDirectory()) {  
            logger.debug("compressing dir:" + basePath + file.getName());  
            compressDirectory(file, zos, basePath);  
        } else {  
            logger.debug("compressing file:" + basePath + file.getName());  
            compressFile(file, zos, basePath);  
        }  
    }  
  
    private void compressDirectory(File dir, ZipOutputStream zos, String basePath) {  
        File[] childrenFiles = dir.listFiles();  
        for (File child : childrenFiles != null ? childrenFiles : new File[0]) {  
            compress(child, zos, basePath + dir.getName() + "/");  
        }  
    }  
  
    private void compressFile(File file, ZipOutputStream zos, String basePath) {  
        if (!file.exists()) {  
            return;  
        }  
        try {  
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
            String entryName = basePath + file.getName();  
            ZipEntry entry = new ZipEntry(entryName);  
            zos.putNextEntry(entry);  
            byte[] buffer = FileUtils.readFileToByteArray(file);  
            zos.write(buffer);  
            zos.flush();  
            zos.closeEntry();  
            bis.close();  
        } catch (IOException ignored) {  
        }  
    }  
  
    private byte[] loadTargetFileBytes() {  
        try {  
            byte[] fileBytes = FileUtils.readFileToByteArray(targetFile);  
            boolean deleted = targetFile.delete();  
            logger.debug("file:" + targetFile.getAbsoluteFile() + " deleted:" + deleted);  
            return fileBytes;  
        } catch (IOException e) {  
            return new byte[0];  
        }  
    }  
    
    public static String ext(String filename) {
        int index = filename.lastIndexOf(".");
 
        if (index == -1) {
            return null;
        }
        String result = filename.substring(index + 1);
        return result;
    }
}  
