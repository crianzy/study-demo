/**
 * @Filename: UploadCommonAction.java
 * @author 汤建东
 * @Date：2013-8-19
 */
package com.app.transaction.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;

import com.app.utils.tool.ResourceReader;
import com.app.utils.util.DateUtil;

/**
 * @Class: UploadCommonAction.java
 * @Description: 文件上传Action
 * @author 汤建东
 * @Date：2013-8-19
 */
@SuppressWarnings("all")
public class UploadCommonAction extends BaseAction {
	private static final long serialVersionUID = -7914395488136167452L;

	private File file; // 普通上传
	private String fileName; // 文件名
	private String flag; // 文件类型
	private String userid; // 用户编号

	/**
	 * 上传图片
	 */
	public void uploadImage() {
		PrintWriter out = this.getWriter();
		String r = "";
		try {
			
			if(fileName==null ||"".equals(fileName.trim())){
				// 获取上传文件
				MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
				// 获取上传文件
				fileName = wrapper.getFileNames("file")[0];
			}
			if (null == this.file || null == this.fileName) {
				r = "error";
				return;
			}
			if (null == this.flag || "".equals(this.flag)) {
				r = "error";
				return;
			}
			String path = ResourceReader.readValue("UPLOAD_PICTURE_PATH"); // 图片物理根路径
			String outPath = ResourceReader.readValue("UPLOAD_PICTURE_URL"); // 图片服务器URL
			// 上传文件方法
			r = this.uploadCommon(path, outPath, this.file, this.fileName,this.userid, false);
			// 响应客户端
			this.getResponse().setContentType("text/html");
			this.getResponse().setCharacterEncoding("utf-8");
		} catch (Exception e) {
			r = "error";
			log.error(e.getMessage(), e);
		} finally {
			out.print(r);
			System.out.println(r);
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 上传视频
	 */
	public void uploadVideo() {
		PrintWriter out = this.getWriter();
		String r = "";
		try {
			if (null == this.file || null == this.fileName) {
				r = "error";
				return;
			}
			if (null == this.flag || "".equals(this.flag)) {
				r = "error";
				return;
			}

			String path = ResourceReader.readValue("UPLOAD_VIDEO_PATH"); // 图片物理根路径
			String outPath = ResourceReader.readValue("UPLOAD_VIDEO_URL"); // 图片服务器URL

			// 上传文件方法
			r = this.uploadCommon(path, outPath, this.file, this.fileName,
					this.userid, false);

			// 响应客户端
			this.getResponse().setContentType("text/html");
			this.getResponse().setCharacterEncoding("utf-8");
		} catch (Exception e) {
			r = "error";
			log.error(e.getMessage(), e);
		} finally {
			out.print(r);
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 上传语音
	 */
	public void uploadVoice() {
		PrintWriter out = this.getWriter();
		String r = "";
		try {
			if (null == this.file || null == this.fileName) {
				r = "error";
				return;
			}
			if (null == this.flag || "".equals(this.flag)) {
				r = "error";
				return;
			}

			String path = ResourceReader.readValue("UPLOAD_VOICE_PATH"); // 图片物理根路径
			String outPath = ResourceReader.readValue("UPLOAD_VOICE_URL"); // 图片服务器URL

			// 上传文件方法
			r = this.uploadCommon(path, outPath, this.file, this.fileName,
					this.userid, false);

			// 响应客户端
			this.getResponse().setContentType("text/html");
			this.getResponse().setCharacterEncoding("utf-8");
		} catch (Exception e) {
			r = "error";
			log.error(e.getMessage(), e);
		} finally {
			out.print(r);
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 上传电子文档
	 */
	public void uploadDocument() {
		PrintWriter out = this.getWriter();
		String r = "";
		try {
			if (null == this.file || null == this.fileName) {
				r = "error";
				return;
			}
			if (null == this.flag || "".equals(this.flag)) {
				r = "error";
				return;
			}

			String path = ResourceReader.readValue("UPLOAD_DOCUMENT_PATH"); // 图片物理根路径
			String outPath = ResourceReader.readValue("UPLOAD_DOCUMENT_URL"); // 图片服务器URL

			// 上传文件方法
			r = this.uploadCommon(path, outPath, this.file, this.fileName,
					this.userid, false);

			// 响应客户端
			this.getResponse().setContentType("text/html");
			this.getResponse().setCharacterEncoding("utf-8");
		} catch (Exception e) {
			r = "error";
			log.error(e.getMessage(), e);
		} finally {
			out.print(r);
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * KingEditor编辑器上传文件
	 */
	public void kindEditorUpload() {
		PrintWriter out = this.getWriter();
		String r = "", error = "";
		try {
			// 定义上传文件的扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media",
					"swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file",
					"doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

			long maxSize = 0; // 上传文件大小
			String path = null; // 图片物理根路径
			String outPath = null; // 图片服务器URL
			String dirName = this.getRequest().getParameter("dir"); // 上传文件类型
			if (null == dirName || "".equals(dirName)) {
				dirName = "image";
			}
			if ("image".equals(dirName)) {
				maxSize = 1024000; // 1M
				path = ResourceReader.readValue("UPLOAD_IMAGE_PATH");
				outPath = ResourceReader.readValue("UPLOAD_IMAGE_URL");
			} else if ("flash".equals(dirName)) {
				maxSize = 5120000; // 5M
				path = ResourceReader.readValue("UPLOAD_FLASH_PATH");
				outPath = ResourceReader.readValue("UPLOAD_FLASH_URL");
			} else if ("media".equals(dirName)) {
				maxSize = 10240000; // 10M
				path = ResourceReader.readValue("UPLOAD_MEDIA_PATH");
				outPath = ResourceReader.readValue("UPLOAD_MEDIA_URL");
			} else if ("file".equals(dirName)) {
				maxSize = 5120000; // 5M
				path = ResourceReader.readValue("UPLOAD_FILE_PATH");
				outPath = ResourceReader.readValue("UPLOAD_FILE_URL");
			}

			// 获取上传文件
			MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this
					.getRequest();

			// 校验上传文件拓展名
			String fileName = wrapper.getFileNames("imgFile")[0];
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if (!Arrays.<String> asList(extMap.get(dirName).split(","))
					.contains(fileExt)) {
				r = "Only allowed to upload the " + extMap.get(dirName)
						+ " format file";
				error = "1";
				return;
			}

			// 校验文件大小(默认大小2M)
			File file = wrapper.getFiles("imgFile")[0];
			if (file.length() > maxSize) {
				r = "The file size can't more than " + (maxSize / 1024000)
						+ "M";
				error = "1";
				return;
			}

			// 上传文件方法
			r = this.uploadCommon(path, outPath, file, fileName, this.userid,
					true);
			error = "0";

			// 响应客户端
			this.getResponse().setContentType("text/html");
			this.getResponse().setCharacterEncoding("utf-8");
		} catch (Exception e) {
			r = "Upload failed";
			error = "1";
			log.error(e.getMessage(), e);
		} finally {
			// 封装返回结果
			JSONObject obj = new JSONObject();
			obj.put("error", error);
			if ("0".equals(error)) {
				obj.put("url", r);
			} else {
				obj.put("message", r);
			}
			out.print(obj.toJSONString());
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * @author 汤建东
	 * @Description：上传文件公共处理方法
	 * @Date：2013-12-5 上午9:24:48
	 * @Return：String
	 */
	public String uploadCommon(String path, String outPath, File file,
			String fileName, String userid, boolean isChange) {
		String r = "";
		try {
			
			
			
			if(fileName==null ||"".equals(fileName.trim())){
				// 获取上传文件
				MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();

				// 获取上传文件
				fileName = wrapper.getFileNames("imgFile")[0];
			}
	
			// 获取扩展名
			String extName = "";
			if (fileName.lastIndexOf(".") >= 0) {
				extName = fileName.substring(fileName.lastIndexOf("."));
			}
			if(userid==null){
				userid="0";
			}

			
			
			
			
			// 生成文件夹：用户编号+时间（相对路径）
			String midPath = (null != userid && !"".equals(userid) ? userid
					+ "/" : "")
					+ DateUtil.date2String(new Date(), "yyyyMMdd") + "/";

			// 生成文件名称
			String imgName = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date()) + new Random().nextInt(100) + extName;

			// 图片物理路径=WEB物理路径+相对路径+文件名
			String filePath = path + midPath + imgName;

			// 完整的图片访问URL
			String imgUrl = outPath + midPath + imgName;

			// 取HTTP请求流,将其写成图片文件
			FileInputStream inFile = new FileInputStream(file);
			double fileSize = inFile.available() / 1024; // 单位KB
			File f_img = new File(path + midPath);
			if (!f_img.exists()) {
				f_img.mkdirs();
			}
			FileOutputStream outFile = new FileOutputStream(filePath);
			byte[] buffer = new byte[1024];
			int i = 0;
			while ((i = inFile.read(buffer)) != -1) {
				outFile.write(buffer, 0, i);
			}
			outFile.flush();
			outFile.close();
			inFile.close();

			// 是否改变返回值
			if (isChange) {
				r = imgUrl;
			} else {
				r = "[{\"imgUrl\":\"" + imgUrl + "\",\"fileSize\":\""
						+ fileSize + "\",\"fileName\":\"" + imgName + "\"}]";
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return r;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
