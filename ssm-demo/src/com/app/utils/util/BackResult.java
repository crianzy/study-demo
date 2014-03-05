package com.app.utils.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("all")
public class BackResult {
//	private static   Log log = LogFactory.getLog(getClass());
	/**
	 * 返回response结果响应
	 * 
	 * @param data 返回数据集
	 * @param response
	 */
	@SuppressWarnings("null")
	public static void returnResult(Object obj, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {

			String data = "";
			if (obj != null) {
				data = JsonUtil.getJsonStr(obj);
			}
			
			out.print(data);
			out.flush();
			out.close();
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
	}
	
	
	public static void returnText(String obj, HttpServletResponse response)
	throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("cache-control","no-cache");
		PrintWriter out;
		try {
			out = response.getWriter();
			String data = "";
			if (obj != null) {
				data = obj;
			}
			out.println(data);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}	

		}

	
	
	
}
