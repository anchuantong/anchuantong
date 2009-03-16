
package com.act.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author an_chuantong<an_chuantong@yahoo.com.cn>:
 * @version 创建时间：Mar 20, 2008 11:34:10 PM
 */
public class FileUtil {

	public final static String[] IMAGE_TYPES = new String[] { "jpg", "gif", "png" };
	
	public static void deleteFile(String path) {
		if (path != null && path.length() > 0) {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	public static void deleteAttFile(File file, String path) {
		if (!StringUtil.isEmpty(path)) {
			File f = new File(file, path);
			if (f.exists()) {
				f.delete();
			}
			File initFile = new File(file, "init/" + path);
			if (initFile.exists()) {
				initFile.delete();
			}
		}
	}

	/**
	 * d得到文件后缀名
	 * 
	 * @param name
	 * @return
	 */
	public static String getExtension(String name) {
		int i = name.lastIndexOf('.');
		if (i >= 0) {
			return name.substring(i + 1).toLowerCase();
		}
		return "";
	}

	public static String getFileName(String path) {
		try {
			char separatorChar = '/';
			int index = path.lastIndexOf(separatorChar);
			if (index < 0) {
				separatorChar = '\\';
				index = path.lastIndexOf(separatorChar);
			}
			return path.substring(index + 1);
		} catch (Exception ex) {
			return "Unknown";
		}
	}

	public static SimpleDateFormat DIR_PATH_DATA_FORMAT = new SimpleDateFormat("yyyy/MM/dd/");

	private static int currTime = (int) (System.currentTimeMillis() / 1000);

	private static synchronized long getNext() {
		return currTime++;
	}

	public static String getUniqueFileName() {
		return DIR_PATH_DATA_FORMAT.format(new Date()) + getNext();
	}
	
	public static boolean copy(File inFile, File outFile){
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(inFile));
				out = new BufferedOutputStream(new FileOutputStream(outFile));
				byte[] buffer = new byte[1024 * 10];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
				return true;
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean copy(InputStream in , File outFile){
		if(in==null){
			return false;
		}
		try {
			OutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(outFile));
				byte[] buffer = new byte[1024 * 10];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
				return true;
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
