
package Tools.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang3.StringUtils; 

public class ZipUtil {
    private static final int buffer = 2048;  
    
    /** 
     * 解压Zip文件 
     * @param path 文件目录 
     */  
    public static void unZip(String path)  
        {  
         int count = -1;  
         String savepath = "";  

         File file = null;  
         InputStream is = null;  
         FileOutputStream fos = null;  
         BufferedOutputStream bos = null;  

         savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; //保存解压文件目录  
         new File(savepath).mkdir(); //创建保存目录  
         ZipFile zipFile = null;  
         try  
         {  
             zipFile = new ZipFile(path,Charset.forName("GBK"));
             Enumeration<?> entries = zipFile.entries();  

             while(entries.hasMoreElements())  
             {  
                 byte buf[] = new byte[buffer];  

                 ZipEntry entry = (ZipEntry)entries.nextElement();  

                 String filename = entry.getName();  
                 boolean ismkdir = false;  
                 if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹  
                    ismkdir = true;  
                 }  
                 filename = savepath + filename;  

                 if(entry.isDirectory()){ //如果是文件夹先创建  
                    file = new File(filename);  
                    file.mkdirs();  
                     continue;  
                 }  
                 file = new File(filename);  
                 if(!file.exists()){ //如果是目录先创建  
                    if(ismkdir){  
                    new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建  
                    }  
                 }  
                 file.createNewFile(); //创建文件  

                 is = zipFile.getInputStream(entry);  
                 fos = new FileOutputStream(file);  
                 bos = new BufferedOutputStream(fos, buffer);  

                 while((count = is.read(buf)) > -1)  
                 {  
                     bos.write(buf, 0, count);  
                 }  
                 bos.flush();  
                 bos.close();  
                 fos.close();  

                 is.close();  
             }  

             zipFile.close();  

         }catch(IOException ioe){  
             ioe.printStackTrace();  
         }finally{  
                try{  
                if(bos != null){  
                    bos.close();  
                }  
                if(fos != null) {  
                    fos.close();  
                }  
                if(is != null){  
                    is.close();  
                }  
                if(zipFile != null){  
                    zipFile.close();  
                }  
                }catch(Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  

    /**  
     * 解压缩zip包  
     * @param zipFilePath zip文件的全路径  
     * @param unzipFilePath 解压后的文件保存的路径  
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含  
     */    
    @SuppressWarnings("unchecked")    
    public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception    
    {    
        if (StringUtils.isEmpty(zipFilePath) || StringUtils.isEmpty(unzipFilePath))    
        {    
        }    
        File zipFile = new File(zipFilePath);  
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径    
        if (includeZipFileName)    
        {    
            String fileName = zipFile.getName();    
            if (StringUtils.isNotEmpty(fileName))    
            {    
                fileName = fileName.substring(0, fileName.lastIndexOf("."));    
            }    
            unzipFilePath = unzipFilePath + File.separator + fileName;    
        }    
        //创建解压缩文件保存的路径    
        File unzipFileDir = new File(unzipFilePath);    
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory())    
        {    
            unzipFileDir.mkdirs();    
        }    
            
        //开始解压    
        ZipEntry entry = null;    
        String entryFilePath = null, entryDirPath = null;    
        File entryFile = null, entryDir = null;    
        int index = 0, count = 0, bufferSize = 1024;    
        byte[] buffer = new byte[bufferSize];    
        BufferedInputStream bis = null;    
        BufferedOutputStream bos = null;    
        ZipFile zip = new ZipFile(zipFile,Charset.forName("GBK"));    
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zip.entries();    
        //循环对压缩包里的每一个文件进行解压         
        while(entries.hasMoreElements())    
        {    
            entry = entries.nextElement(); 
            if(!entry.toString().contains(".")){
                continue;
            }
            //0004h/
            //构建压缩包中一个文件解压后保存的文件全路径    
            entryFilePath = unzipFilePath + File.separator + entry.getName().replaceAll("/", "\\\\");
            //构建解压后保存的文件夹路径    
            index = entryFilePath.lastIndexOf(File.separator);    
            if (index != -1)    
            {    
                entryDirPath = entryFilePath.substring(0, index);    
            }    
            else    
            {    
                entryDirPath = "";    
            }               
            entryDir = new File(entryDirPath);    
            //如果文件夹路径不存在，则创建文件夹    
            if (!entryDir.exists() || !entryDir.isDirectory())    
            {    
                entryDir.mkdirs();    
            }    
                
            //创建解压文件    
            entryFile = new File(entryFilePath);//D:\worklyw\ loadFiles\zjfk\2019-07-29-19-15-0608605H_西延公司\08605H_西延公司\
            if(!entryFile.isDirectory()){
            //写入文件    
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));    
            bis = new BufferedInputStream(zip.getInputStream(entry));    
            while ((count = bis.read(buffer, 0, bufferSize)) != -1)    
            {    
                bos.write(buffer, 0, count);    
            }    
            }
            bos.flush();    
            bos.close();                
        }    
    }    
   

      public static void main(String[] args)  
      {  
          String zipFilePath = "E:\\test.zip";
          String unzipFilePath = "E:\\opt";    
          try     
          {    
              ZipUtil.unzip(zipFilePath, unzipFilePath, true);  
          }    
          catch (Exception e)    
          {    
              e.printStackTrace();    
          }    
      } 
}

