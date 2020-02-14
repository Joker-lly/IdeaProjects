package Tools.FTPUttils;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/*
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;



*/




public class FtpConnect {
	/*public static Properties ftpProps = null;
	public static String ftpName=null;
	private FTPClient  ftpClient = null;
	public String ftpPath=null;
	public String downloadlocalpath=null;
	public String upzippath=null;

	
	//采用单例模式
	private static FtpConnect instance=null;
	public synchronized static FtpConnect getInstance(){
		if(null==instance){
			instance=new FtpConnect();
		}
		return instance;
	}
	
	//先加载配置文件
	static {
		//本地main方法使用
		//File file = new File("src/main/resources/ftpdownload.properties");
		ftpProps = new Properties();
		try {
			//InputStream is = new FileInputStream(file);
			//项目运行使用
			InputStream is = FtpConnect.class.getClassLoader().getResourceAsStream("ftpdownload.properties");
			ftpProps.load(is);
			
		} catch (Exception e) {
			logger.error("不能读取属性文件. " + "请确保db.properties在CLASSPATH指定的路径中");
		}
	}
	//定义构造函数，根据传过来的ftp名字去连接ftp
	public FtpConnect(String ftpName){
		initialzeFtp(ftpName);
	}
	public FtpConnect(){
		
	}
	//定义根据ftp名称在配置文件中获取相关信息，并连接ftp
	private void initialzeFtp(String ftpName){
		 // ftpClient
        if (null != ftpClient && ftpClient.isConnected() && ftpClient.isAvailable()) {
            // 防止假卡死
            ftpClient.enterLocalPassiveMode();
            return;
        }
        String ftpHostname=ftpProps.getProperty(ftpName+".hostname");
        int ftpPort=Integer.valueOf(ftpProps.getProperty(ftpName+".port"));
        String ftpUsername=ftpProps.getProperty(ftpName+".username");
        String ftpPassword=ftpProps.getProperty(ftpName+".password");
        ftpPath=ftpProps.getProperty(ftpName+".ftppath");
        downloadlocalpath=ftpProps.getProperty(ftpName+".downloadlocalpath");
        upzippath=ftpProps.getProperty(ftpName+".upzippath");
        ftpClient = new FTPClient();
        // GBK可下载中文命名的文件
        ftpClient.setControlEncoding("GBK");
        try {
            // 连接FTP服务器
            ftpClient.connect(ftpHostname, ftpPort);
            // 登录FTP服务器
            ftpClient.login(ftpUsername, ftpPassword);
            // 是否登录成功
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                // 登录失败
                logger.info("用户名或密码错误，登录FTP服务器失败：" + ftpHostname + ":" + ftpPort);
                // 关闭连接
                closeConn();
                return;
            } else {
                // 登录成功
                logger.info("登录FTP服务器成功：" + ftpHostname + ":" + ftpPort);
                // 设置文件类型
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                // 设置Linux环境:如果ftp服务器部署在linux系统中，此处注释应该打开，若为Windows服务器则不需要
                // FTPClientConfig config = new
                // FTPClientConfig(FTPClientConfig.SYST_UNIX);
                // ftpClient.configure(config);
                // 防止假卡死
                ftpClient.enterLocalPassiveMode();
                // 中文支持
                ftpClient.setControlEncoding("GBK");
                // 服务器获取自身Ip地址和提交的host进行匹配
                ftpClient.setRemoteVerificationEnabled(false);
                // 设置连接超时时间
                ftpClient.setConnectTimeout(60 * 1000);
                // 设置数据传输超时时间
                ftpClient.setDataTimeout(2 * 60 * 1000);
                // 设置缓冲大小
                ftpClient.setReceiveBufferSize(1024 * 1024);
                ftpClient.setBufferSize(1024 * 1024);
            }

        } catch (Exception e) {
            logger.info("无法登录FTP服务器："+ ftpHostname + ":" + ftpPort);
            e.printStackTrace();
        }
        
	}
	*//**
     * 退出并关闭FTP连接
     *//*
	public void closeConn() {
	     if (null != ftpClient) {
	            try {
	                // 退出登录
	                ftpClient.logout();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	            try {
	                // 断开连接
	                ftpClient.disconnect();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}	
	
	*//**
	 * 下载指定目录下的前一天所属月份文件夹前一天上传的所有文件
	 * @param ftpName
	 * @return
	 * @throws IOException
	 *//*
	
    public List<String> downloadDir(String ftpName,String date) throws IOException {
    	//下载完成后返回下载的文件信息名字和上传ftp的时间
  
    	List<String>fileNamePath=new ArrayList<String>();
    	if(null!=ftpName){
    		//初始化ftp连接信息
    		initialzeFtp(ftpName);
    		//将unzippath 和下载到本地的localpath 放到map中
    		
    		fileNamePath.add(upzippath);
    		
            File localFile = null;
            FileOutputStream fos = null;
            InputStream fis = null;
            //执行 ftp删除过于陈旧的文件夹和文件
            //delDirBySysTime();
            //删除本地电脑已下载的过于陈旧的文件夹,
            //delLocalFile(downloadlocalpath);
            try {
                //获取传递过来的日期的前一天
    			date=DateUtil.getOperaDate(date,-1);
    			   	       
    	        //下载到本地的电脑（服务器）路径，此规则影响到删除
                String localPath=downloadlocalpath+"/"+date.substring(0,6);
                //这个后面给文件重命名的时候能用到
                fileNamePath.add(localPath);
                // 检查localpath,不存在则创建
                checkLocalpath(localPath);
                // 更改工作路径
                String ftppathname=ftpPath+"/"+date.substring(0,6)+"/";
                
                //先回到根目录，更改工作路径之前需要先回到根目录
				ftpClient.changeWorkingDirectory("//");
                boolean b = ftpClient.changeWorkingDirectory(ftppathname);
                // 工作路径中的所有文件
                FTPFile[] ftpFiles = ftpClient.listFiles();                
                              
                for (FTPFile ftpFile : ftpFiles) {
                    System.out.println(ftpFile.getName());
                    // 判断是否是文件            
                    if (ftpFile.isFile()) {
                    	  //获取文件上传时间
                    	Calendar t = ftpFile.getTimestamp();
                    	int year = t.get(Calendar.YEAR);
                    	int month = t.get(Calendar.MONTH)+1;
                    	int day = t.get(Calendar.DAY_OF_MONTH);
                    	String daystr=day<10?"0"+day:""+day;
                    	String monthstr=month<10?"0"+month:""+month;
                    	String timetemp=year+""+monthstr+""+daystr;
                    
                    	//只对当前时间前一天上传的文件进行下载,或者比传参时间以后的
                    	if(DateUtil.getDateDifference(date,timetemp)>=0){  
                    	
                            // 创建本地文件
                            localFile = new File(localPath + "/" + ftpFile.getName());
                            // 判断文件是否存在
                            if (localFile.exists()) {
                                // localFile已存在
                                // 判断是否是文件
                                if (localFile.isFile()) {
                                    // 判断localFile与ftpFile大小是否相同
                                    if (localFile.length() == ftpFile.getSize()) {
                                        // 大小相同说明已下载
                                      //  logger.info("文件{}已下载完成", localFile.getName());
                                        continue;
                                    } else if (localFile.length() >= 0 && localFile.length() < ftpFile.getSize()) {
                                        // 创建输出流，需要断点续传
                                        fos = new FileOutputStream(localFile, true);
                                        logger.info("文件{}已开始断点续传"+ localFile.getName());
                                       
                                    } else {
                                        logger.error("本地文件{}大于服务器文件,终止下载"+localFile.getName());
                                        continue;
                                    }
                                } else {
                                    // localFile不是文件
                                    localFile.delete();
                                    // 创建路径
                                    localFile.mkdirs();
                                    // 删除与文件名重名的文件夹
                                    localFile.delete();
                                    // 创建文件
                                    localFile.createNewFile();
                                    // 创建输出流
                                    fos = new FileOutputStream(localFile);
                                  //  logger.info("文件{}已开始断点续传", localFile.getName());
                                }
                            } else {
                                // 文件不存在，创建新的文件
                                localFile.createNewFile();
                                // 创建输出流
                                fos = new FileOutputStream(localFile);
                               
                                fileNamePath.add(localPath + "/" + ftpFile.getName());
                                
                            }

                            byte[] bytes = new byte[1024 * 1024];
                            if (localFile.length() >= 0) {
                                ftpClient.setRestartOffset(localFile.length());
                            }

                            fis = ftpClient.retrieveFileStream(ftpFile.getName());
                            if (null == fis) {
                                logger.info("连接异常，将退出登录");
                                closeConn();
                            }
                            int rs = -2;
                            while ((rs = fis.read(bytes)) != -1) {
                            	
                                fos.write(bytes, 0, rs);
                            }
                            *//**
                             * 官方说法 完成文件传输必须调用completePendingCommand 此方法 检查它的返回值来验证成功
                             * 如果没有这样做，后续第二个文件的下载可能会意外出错 
                             * 不调用此文件的 inputstream.close方法  会造成下一个下载的文件 的 fis 为空 直接异常退出
                             *//*
                            //用完流，一定要关闭啊
                            fos.close();
                            fis.close();
                            ftpClient.completePendingCommand();                    
                    	}
                    }
                }
               

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {

                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }

                closeConn();
            }
    		return fileNamePath;
        
    	}
    	return fileNamePath;
    }


	

    *//**
     * 判断本地文件夹是否存在,不存在则创建
     * 
     * @param localpath
     * @return
     *//*
    private  void checkLocalpath(String localpath) {
        File localDir = new File(localpath);
        // 判断localDir是否存在
        if (localDir.exists()) {
            // localDir存在
            // 判断localDir是否是文件
            if (localDir.isFile()) {
                // 是文件则删除
                localDir.delete();
                // 创建文件夹
                localDir.mkdirs();
            }
        } else {
            // localDir不存在，创建文件夹
            localDir.mkdirs();
        }
    }

	*//**
	 * 删除ftp上配置文件上指定目录下的文件夹
	 * 包含文件夹中的文件夹
	 * 注意：此方法执行完后，工作目录切换到了ftp的根目录
	 * 配合下载功能使用
	 * @param dirPathName 
	 *//*
	private void delDir(String dirPathName){		
		try {
			 //先回到根目录，更改工作路径之前需要先回到根目录
			ftpClient.changeWorkingDirectory("//");
			boolean b = ftpClient.changeWorkingDirectory(ftpPath+"\\"+dirPathName);
			System.out.println("更改工作路径"+b);			
			 FTPFile[] ftpFiles = ftpClient.listFiles();
			 if(ftpFiles.length==0){
				//先回到根目录
				 ftpClient.changeWorkingDirectory("//");
				 ftpClient.removeDirectory(ftpPath+"\\"+dirPathName);
				 System.out.println("移除空目录"+ftpPath+"\\"+dirPathName);
			 }else{
				 for (FTPFile ftpFile : ftpFiles) {					 
					 if(ftpFile.isDirectory()){
						 //先回到根目录，更改工作路径之前需要先回到根目录
						 ftpClient.changeWorkingDirectory("//");
						 delDir(dirPathName+"\\"+ftpFile.getName());						
					 }else{
						 System.out.println(ftpFile.getName());
						 //删除文件的时候需要全路径
						 boolean dele = ftpClient.deleteFile("\\"+ftpPath+"\\"+dirPathName+"\\"+ftpFile.getName());						
						 System.out.println(dele);
					 }					 					
				 }				 
				//先回到根目录
				 ftpClient.changeWorkingDirectory("//");
				 ftpClient.removeDirectory(ftpPath+"\\"+dirPathName);
				 System.out.println("删除目录中文件后，删除目录"+ftpPath+"\\"+dirPathName);			 
			 }		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	*//**
	 * 按照系统时间，删除ftp上距离现在>6个月的文件夹以及文件
	 * 注意：此方法执行完后，工作目录切换到了ftp的根目录
	 * 配合下载功能使用
	 *//*
	private void delDirBySysTime(){
		//配合下载使用，不需要初始化
		//initialzeFtp("Yzjzxfk");
		//循环给出的根目录中的文件夹
	
		try {
			ftpClient.changeWorkingDirectory(ftpPath);			
			FTPFile[] listFiles = ftpClient.listFiles();
			for(FTPFile files: listFiles){	
				//建时间判断
				Calendar timestamp = files.getTimestamp();
				Date time = timestamp.getTime();
				//如果是六个月以前的则删除
				if(getMonthDateDisparity(time,new Date())>6){
					//则删除该文件夹以及内容
					delDir(files.getName());
				}
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	*//**
	 * 删除本地过去陈旧的数据
	 * 严重依赖下载功能创建本地文件夹的规则
	 * @param path
	 *//*
	public  void delLocalFile(String path){
		File file=new File(path);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		if(file.isDirectory()){
			File[] listFiles = file.listFiles();
			//
			for(File files :listFiles){
				if(files.isDirectory()&&files.getName().contains("20")){
					try {
						sf.parse(files.getName());
						//只删除下载时自己创建的文件夹
						int time =getMonthDateDisparity(sf.parse(files.getName()),new Date());
						if(time>6){
							FileUtil.deleteDir(files.getPath());
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				 
				}				
			}
		}		
	}
	

	
	*//**
	 * 获取两个日期相差多少个月
	 * @param beginTime
	 * @param endTime
	 * @return
	 *//*
	public static int getMonthDateDisparity(Date beginTime, Date endTime) {
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		begin.setTime(beginTime);
		end.setTime(endTime);
		
		int begin_year = begin.get(Calendar.YEAR);
		int begin_month = begin.get(Calendar.MONTH);
		
		int end_year = end.get(Calendar.YEAR);
		int end_month = end.get(Calendar.MONTH);
		
		return (end_year - begin_year) * 12 + (end_month - begin_month);
	}

	public static void main(String[] args) throws IOException {

	
	}*/

}
