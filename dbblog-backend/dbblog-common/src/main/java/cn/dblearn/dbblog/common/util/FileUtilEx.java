package cn.dblearn.dbblog.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.dblearn.dbblog.common.Constant;


/**
 * 文件操作工具拓展类
 * @author developer001
 */
public class FileUtilEx extends org.apache.commons.io.FileUtils{

	private static Log logger = LogFactory.getLog(FileUtilEx.class);

	/**
	 * 下载文件
	 * @param response
	 * @param bytes 字节
	 * @param realName 下载的文件名字
	 * @throws IOException
	 */
	public static void downloadFile(HttpServletResponse response, byte[] bytes, String realName) throws IOException {
		InputStream is = new ByteArrayInputStream(bytes, 0, bytes.length);
		downloadFile(response, is, realName);
		is.close();
	}

	/**
	 * 下载(流的方式)
	* @param response
	* @param is 输入流
	* @param realName 下载的文件名字
	 */
	public static void downloadFile(HttpServletResponse response, InputStream is, String realName) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			//response.setContentType("text/html;charset=UTF-8");
			//request.setCharacterEncoding("UTF-8");
			long fileLength = is.available();

			response.setContentType("application/octet-stream");
			realName = new String(realName.getBytes(Constant.CHARSET_ENCODING), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ realName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			// e.printStackTrace();//如果取消下载，这里会捕捉到异常
		} finally {
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 多个文件压缩成一个zip包
	 * @param files
	 * @param zipFile
	 * @throws IOException
	 */
	public static void zipFiles(File[] files,String zipFile) throws IOException{
		zipFiles(Arrays.asList(files), zipFile);
	}

	/**
	 * 多个文件压缩成一个zip包
	 * @param list
	 * @param zipFile
	 * @throws IOException
	 */
	public static void zipFiles(List<File> list,String
			zipFile) throws IOException {
		ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(
				zipFile));
		for (int i = 0; i < list.size(); i++) {
			File file = list.get(i);
			FileInputStream fin = null;
			ZipEntry entry = null;
			// 创建复制缓冲区
			byte[] buf = new byte[4096];
			int readByte = 0;
			if (file.isFile()) {
				try {
					// 创建一个文件输入流
					fin = new FileInputStream(file);
					// 创建一个ZipEntry
					entry = new ZipEntry(FilenameUtils.getName(file.getName()));
					// 存储信息到压缩文件
					zouts.putNextEntry(entry);
					// 复制字节到压缩文件
					while ((readByte = fin.read(buf)) != -1) {
						zouts.write(buf, 0, readByte);
					}
					zouts.closeEntry();
					fin.close();
				} catch (Exception e) {
					logger.error("文件压缩失败："+e.getLocalizedMessage());
				}
			}
		}
		zouts.close();
	}

	/**
	 * 解压缩zip文件
	 * @param zipFile
	 * @param unzipFileName
	 */
	@SuppressWarnings({ "rawtypes", "resource" })
	public static List<String> unZipFile(File zipFile,String unzipFileName){
		List<String> zipfile = new ArrayList<String>();
		try {
		      ZipFile zf=new ZipFile(zipFile);
		      for(Enumeration entries=zf.entries();entries.hasMoreElements();){
		        ZipEntry entry= (ZipEntry) entries.nextElement();
		        String zipEntryName=entry.getName();
		       /* String unzipDir = unzipFileName + zipEntryName;*/
				String path = StringUtilEx.substringAfter(zipEntryName, "@@");
				String p = path.replace("@@","\\");

		        String unzipDir = p;
		        if (entry.isDirectory()) {
					// 如果entry是一个目录，则创建目录
					new File(unzipDir).mkdirs();
					continue;
				} else {
					// 如果entry是一个文件，则创建父目录
					new File(unzipDir).getParentFile().mkdirs();
				}
		        zipfile.add("\\"+unzipDir);
		        InputStream in=zf.getInputStream(entry);
		        OutputStream out=new FileOutputStream(unzipDir);

		        byte[] buf1=new byte[4096];
		        int len;
		        while((len=in.read(buf1))>0){
		          out.write(buf1,0,len);
		        }
		        in.close();
		        out.close();
		      }
		    } catch (Exception e) {
		    	logger.error("文件解压缩失败："+e.getLocalizedMessage());
		    }
		return zipfile;
	}

	/**
	 * 创建文件，可以包括创建多级文件目录 。
	 * <p>
	 * 根据抽象字串文件名新建文件，若文件的上级目录不存在，则先创建目录，再创建文件，返回新文件. 若文件存在,直接返回.
	 * </p>
	 *
	 * @param filename
	 *            待创建的文件的抽象文件名称,若为null返回null;若此名称的文件已存在,则直接返回该文件.
	 * @return File 创建的文件
	 * @throws IOException
	 */
	public static File createFile(final String filename) throws IOException {

		if (filename == null) {
			return null;
		}
		else {
			return createFile(new File(filename));
		}
	}

	/**
	 * 创建文件，可以包括创建多级文件目录
	 * <p>
	 * 由文件对象创建文件，若文件的上级目录不存在，则先创建目录，再创建文件，返回新文件. 若文件存在,直接返回.
	 * </p>
	 *
	 * @param file
	 *            待创建的文件
	 * @return File 创建的文件
	 * @throws IOException
	 */
	public static File createFile(final File file) throws IOException {

		if (!file.exists()) {
			createDirectoryRecursively(file.getParent());
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 创建文件目录(包括子目录) 支持创建多级文件目录，如“d:/aaa/bbb/ccc”
	 *
	 * @param directory
	 *            待创建的文件(夹),支持多级路径. 若为文件或null返回false; 若目录已存在则返回true;
	 * @return boolean
	 */
	public static boolean createDirectoryRecursively(String directory) {

		if (directory == null) {
			return false;
		}
		File pathname = new File(directory);
		if (pathname.exists()) {
			return !pathname.isFile();
		}
		else if (!pathname.isAbsolute()) {
			pathname = new File(pathname.getAbsolutePath());
		}
		final String parent = pathname.getParent();
		if ((parent == null) || !createDirectoryRecursively(parent)) {
			return false;
		}
		pathname.mkdir();
		return pathname.exists();
	}

}
