package com.app.utils.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MapUtil {
	protected  static final  Log log = LogFactory.getLog(MapUtil.class);
	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-28 下午12:34:11
	 * @describe 处理请求公共方法
	 * @return String
	 */
	public static String commonMethod(String url) {
		StringBuffer sb = new StringBuffer();
		URL urls;
		HttpURLConnection uc = null;
		BufferedReader in = null;
		try {
			urls = new URL(url);
			uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("GET");
			uc.connect();
			in = new BufferedReader(new InputStreamReader(uc.getInputStream(),
					"utf-8"));
			String readLine = "";
			while ((readLine = in.readLine()) != null) {
				sb.append(readLine);
			}
			if (in != null) {
				in.close();
			}
			if (uc != null) {
				uc.disconnect();
			}
		} catch (MalformedURLException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (IOException e) {

		} finally {
			if (uc != null) {
				uc.disconnect();
			}
		}
		return sb.toString();
	}

	public static String getMapLngAndLat(String city, String address) {

		try {
			String sendSMS_url = "http://api.map.baidu.com/geocoder/v2/?ak=AFb384572541f823e4ece035b73a54c6&callback=renderOption&output=json&address="
					+ address + "&city=" + city;
			String sb_res = commonMethod(sendSMS_url);
			JSONObject jsonObj;
			JSONObject jsonObj2;
			String json = sb_res.substring(sb_res.indexOf('(') + 1,
					sb_res.indexOf(')'));
			JSONObject jsonObject = JSONArray.fromObject("[" + json + "]")
					.getJSONObject(0);
			jsonObj = JSONArray.fromObject(jsonObject.get("result"))
					.getJSONObject(0);
			jsonObj2 = JSONArray.fromObject(jsonObj.get("location"))
					.getJSONObject(0);
			return jsonObj2.toString();
		} catch (Exception e) {
			// / e.printStackTrace();
			return "error";
		}

	}

	/**
	 *  获得百度地图经纬度
	 * @param city
	 * @param address
	 * @return {"lng":121.525665,"lat":31.231556}
	 * @author bill
	 * @date 2013-8-21
	 */
	public static String getBMapLngAndLat(String city, String address) {

		try {
			String sendSMS_url = "http://api.map.baidu.com/geocoder/v2/?ak=AFb384572541f823e4ece035b73a54c6&output=json&address="
					+ address + "&city=" + city;
			String sb_res = commonMethod(sendSMS_url);
			JSONObject jsonObj2;
			JSONObject jsonObject = JSONArray.fromObject("[" + sb_res + "]")
					.getJSONObject(0);
			if (null != jsonObject
					&& "0".equals(jsonObject.get("status").toString())) { // 成功
				JSONObject jsonObj = JSONArray.fromObject(
						jsonObject.get("result")).getJSONObject(0);
				if (!"[]".equals(jsonObject.get("result").toString())) {
					jsonObj2 = JSONArray.fromObject(jsonObj.get("location"))
							.getJSONObject(0);
					return jsonObj2.toString();
				}
			}
			return "1111";
		} catch (Exception e) {
			// / e.printStackTrace();
			return "1111";
		}

	}

	/**
	 * 根据地址返回经纬度(谷歌地图API)
	 * 
	 * @param addr
	 * @return 返回经纬度数据, latLng[0]经度,latLng[1]维度
	 */
	public static String[] getGoogleMapLngAndLat(String addr) throws Exception {
		String[] latLng = new String[2];
		String address = null;
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		String output = "csv";
		// 密钥可以随便写一个key=abc
		String key = "ABQIAAAAevysxt9O5lBUCrSalm80MxQx8gmx0K-_Fjj4Tf8bNXH3BBSxZRRmI_CuZM2zQyuXEpG_uxt-aqPr-A";
		String url = "http://maps.google.com/maps/geo?q=" + address+ "&output=" + output + "&key=" + key;
		URL googleMapURL = null;
		URLConnection httpsConn = null;
		// 进行转码
		try {
			googleMapURL = new URL(url);
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
		}
		try {
			httpsConn = (URLConnection) googleMapURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(
						httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					String[] retList = data.split(",");
					if (retList.length > 2 && ("200".equals(retList[0]))) {
						latLng[0] = retList[2];
						latLng[1] = retList[3];
					}
				}
				insr.close();
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return latLng;
	}

}
