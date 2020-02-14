package Tools.rarutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;


/**
 * 用于rar解压文件， 方式为：使用cmd命令调用本地的WinRar，解压rar文件
 * 
 * @author liuliye
 *
 */
public class RarUtil {
	public static Properties ftpProps = null;

	static {
		
		File file = new File("src/main/resources/ftpdownload.properties");
		ftpProps = new Properties();
		try {
			//本地main方法调试用
			InputStream is = new FileInputStream(file);
			//项目部署使用
			 //InputStream is = RarUtil.class.getClassLoader().getResourceAsStream("ftpdownload.properties");
			ftpProps.load(is);
			
		} catch (Exception e) {
			System.out.println("不能读取属性文件. " + "请确保db.properties在CLASSPATH指定的路径中");
		}
	}

	/**
	 * 
	 * @param filePath
	 *            要解压文件的全路径，包含后缀
	 * @param unRarpath
	 *            文件解压到的文件夹路径
	 * 
	 */
	public static void unRar( String filePath, String unRarpath) {
		String rarname="WinRar";
		String WinRarPath=ftpProps.getProperty(rarname+".path");
		//判断是否存在unRarpath，不存在则建立
		File unRarpathFile = new File(unRarpath);
		if (!unRarpathFile.exists()) {
			unRarpathFile.mkdirs();
		}
		File filePathnew = new File(filePath);
		if (filePathnew.exists()) {
			// 拼接的cmd命令
			String cmd="";
			//windos 对应的rar解压命令
			if(System.getProperty("os.name").toLowerCase().contains("windows")){				
				cmd = WinRarPath + " x -r -p-  -o " + filePath + " " + unRarpath;
			}
			//linux 对应的rar解压命令
			if(System.getProperty("os.name").toLowerCase().contains("linux")){
				cmd=" rar x "+filePath +" "+unRarpath ;
			}
			
			try {
				Runtime runtime = Runtime.getRuntime(); // 得到命令对象

				Process process = runtime.exec(cmd); // 获取执行命令过程中返回的流

				InputStreamReader isr = new InputStreamReader(process.getInputStream());

				BufferedReader br = new BufferedReader(isr);

				String str = null;

				while ((str = br.readLine()) != null) {

					if (!"".equals(str.trim()) && str != null) // 如果当前行不为空
					{
						System.out.println(str);
					}
				}
				br.close();
				// 遍历解压后的文件夹，查看里面是否还有压缩文件，有的话继续解压当前文件
				getFilesFullName(unRarpath, filePath, unRarpath);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(cmd);
		}

	}

	/**
	 * 1想要的效果是：一次解压完成后，循环解压完后的文件夹，查看是否还有压缩文件，有则，再次对该文件进行解压
	 * 2，循环的文件夹，需要的条件：文件夹路径，又因为，还需要解压，则，winrar路径，password，文件路径，解压到的路径
	 * 3.解压完后的文件还需要删掉
	 *
	 */
	public static void getFilesFullName(String newDirPath, String filePath,
			String unRarpath) {
		File root = new File(newDirPath);
		if (!root.exists()) {
		} else {
			File[] files = root.listFiles();
			Arrays.sort(files, new CompratorByLastModified());
			for (File file : files) {
				if (file.getName().contains(".rar")) {
					String childDirPath = newDirPath + "\\" + "child";
					//调用解压方法再次解压
					unRar( file.getPath(), childDirPath);
					//删除压缩包中的压缩包
					file.delete();
				}
				if (file.isDirectory()) {
					//如果是文件夹，则再次调用本方法进行循环
					getFilesFullName(file.getPath(),  "", file.getPath());
				}
			}
		}
	}

	// 根据文件名进行比较的内部类
	static class CompratorByLastModified implements Comparator<File> {
		public int compare(File f1, File f2) {
			long diff = f1.getName().compareTo(f2.getName());
			if (diff > 0) {
				return 1;
			} else if (diff == 0) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	public static void main(String[] args) {
		

	}
}
