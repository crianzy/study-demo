/**
 * @Filename：CreateFileUtil.java
 * @author 汤建东
 * @Date：2013-12-9
 */
package com.app.utils.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Class：CreateFileUtil.java
 * @Description：创建文件夹及文件工具类
 * @author 汤建东
 * @Date：2013-12-9
 */
public class CreateFileUtil {
	protected static final Log log = LogFactory.getLog(CreateFileUtil.class);

	/**
	 * 创建单个文件
	 * 
	 * @param destFileName
	 *            文件名
	 * @return 创建成功返回true，否则返回false
	 */
	public static boolean CreateFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			return false;
		}
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 创建临时文件
	 * 
	 * @param prefix
	 *            临时文件的前缀
	 * @param suffix
	 *            临时文件的后缀
	 * @param dirName
	 *            临时文件所在的目录，如果输入null，则在用户的文档目录下创建临时文件
	 * @return 临时文件创建成功返回抽象路径名的规范路径名字符串，否则返回null
	 */
	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		try {
			if (dirName == null) {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				return tempFile.getCanonicalPath();
			} else {
				File dir = new File(dirName);
				// 如果临时文件所在目录不存在，首先创建
				if (!dir.exists()) {
					if (!CreateFileUtil.createDir(dirName)) {
						return null;
					}
				}
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	public static void main(String[] args) {
		// 创建目录
		String dirName = "E:/test/test0/test1";
		CreateFileUtil.createDir(dirName);
		// 创建文件
		String fileName = dirName + "/test2/testFile.txt";
		CreateFileUtil.CreateFile(fileName);
		// 创建临时文件
		String prefix = "temp";
		String suffix = ".txt";
		for (int i = 0; i < 10; i++) {
			System.out.println("创建了临时文件:"
					+ CreateFileUtil.createTempFile(prefix, suffix, dirName));
		}
	}
}
