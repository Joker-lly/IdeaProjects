package Tools.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


public class DownloadUtil {


	/**
	 * 文件系在
	 * @param response

	 */
	public static void download(HttpServletResponse response, String filePath, String fileName, String encode) {
		response.setContentType("text/html;charset=" + encode);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String downLoadPath = filePath;
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859-1"));// ;
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}
	}

	public static void download(HttpServletResponse response, String filePath, String fileName) {
		DownloadUtil.download(response, filePath, fileName, "UTF-8");
	}
}
