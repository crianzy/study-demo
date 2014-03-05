/**
 * 文件名: UlposTool.java
 * 作者：caiqf
 * 完成日期：2012-8-27
 * 维护人员：
 * 维护日期：
 * 维护原因：
 */
package com.app.utils.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.utils.constant.MD5;
import com.app.utils.tool.ResourceReader;

/**
 * Class: UlposTool.java Description: U联生活工具类
 * 
 * @author caiqf
 * @date 2012-8-27
 */
public class UlposTool {
	protected static final Log log = LogFactory.getLog(UlposTool.class);

	public static String page = "1"; // 分页数
	public static String pagesize = "20"; // 分页数量，最大20

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 下午02:41:16
	 * @describe 获取最新的时间戳和签名(邮政优生活)
	 * @return String
	 */
	public static String getNewTimestampAndSignUlife() {
		String key = ResourceReader.readValue("ulife_appkey");
		String secret = ResourceReader.readValue("ulife_appsecret");
		String method = ResourceReader.readValue("ulpos_signature_method");
		long times = new Date().getTime() / 1000;
		return key
				+ ","
				+ method
				+ ","
				+ times
				+ ","
				+ MD5.getInstance().getMD5(key + times + secret + method)
						.toLowerCase();
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 下午02:41:16
	 * @describe 获取最新的时间戳和签名
	 * @return String
	 */
	public static String getNewTimestampAndSign() {
		String key = ResourceReader.readValue("ulpos_appkey");
		String secret = ResourceReader.readValue("ulpos_appsecret");
		String method = ResourceReader.readValue("ulpos_signature_method");
		long times = new Date().getTime() / 1000;
		return key
				+ ","
				+ method
				+ ","
				+ times
				+ ","
				+ MD5.getInstance().getMD5(key + times + secret + method)
						.toLowerCase();
	}

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
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (uc != null) {
				uc.disconnect();
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取全国省份城市区县数据
	 * @return String
	 */
	public static String transportCityInfo(String province, String city,
			String county) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_cityInfo")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3];
		if (null != province && !"".equals(province)) {
			url += "&province=" + province;
		}
		if (null != city && !"".equals(city)) {
			url += "&city=" + city;
		}
		if (null != county && !"".equals(county)) {
			url += "&county=" + county;
		}
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取银行数据
	 * @return String
	 */
	public static String transportBankInfo() {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_bankInfo")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3];
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取商户分类数据
	 * @return String
	 */
	public static String transportShopClassInfo() {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_shopClassInfo")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3];
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取商户所属分类数据
	 * @return String
	 */
	public static String transportShopClassRel(String shopid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_shopClassRel")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&shopid="
				+ shopid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取商户图片数据
	 * @return String
	 */
	public static String transportShopImage(String shopid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_shopImage")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&shopid="
				+ shopid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取我的优惠券(供应商)数据
	 * @return String
	 */
	public static String transportMyCouponInfo(String page, String pagesize) {
		String[] ts = UlposTool.getNewTimestampAndSignUlife().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_myCouponInfo")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&page=" + page
				+ "&pagesize=" + pagesize;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取优惠券、商户数据
	 * @return String
	 */
	public static String transportCouponInfo(String page, String pagesize) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_couponInfo")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&page=" + page
				+ "&pagesize=" + pagesize;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 绑定优惠券
	 * @return String
	 */
	public static String transportCouponAdd(String couid, String mobile,
			String card, String orderid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_couponAdd")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&couid=" + couid
				+ "&mobile=" + mobile + "&card=" + card + "&orderid=" + orderid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取优惠券关联的分店数据
	 * @return String
	 */
	public static String transportCouponBranch(String couid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_couponBranch")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&couid=" + couid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取会员卡关联的分店数据
	 * @return String
	 */
	public static String transportCardBranch(String svcid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_couponBranch")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&svcid=" + svcid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取商户的分店数据
	 * @return String
	 */
	public static String transportShopBranch(String shopid, String page,
			String pagesize) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_shopBranch")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&shopid="
				+ shopid + "&page=" + page + "&pagesize=" + pagesize;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取用户绑定的会员卡数据
	 * @return String
	 */
	public static String transportUserCardInfo(String page, String pagesize,
			String uvcid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_userCardInfo")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3];
		if (null != uvcid && !"".equals(uvcid)) {
			url += "&uvcid=" + uvcid;
		}
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取会员卡、会员卡商户数据
	 * @return String
	 */
	public static String transportCardInfo(String page, String pagesize) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_cardInfo")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&page=" + page
				+ "&pagesize=" + pagesize;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 绑定会员卡
	 * @return String
	 */
	public static String transportCardAdd(String mobile, String cardno,
			String svcid, String orderid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_cardAdd") + "?appkey="
				+ ts[0] + "&signature_method=" + ts[1] + "&timestamp=" + ts[2]
				+ "&sign=" + ts[3] + "&mobile=" + mobile + "&cardno=" + cardno
				+ "&svcid=" + svcid + "&orderid=" + orderid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 更改会员卡绑定的银行卡号
	 * @return String
	 */
	public static String transportCardChange(String mobile, String cardno,
			String uvcid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_cardChange")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&mobile="
				+ mobile + "&cardno=" + cardno + "&uvcid=" + uvcid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 验证银行卡号格式是否正确
	 * @return String
	 */
	public static String transportBankCheck(String cardno) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_bankCheck")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&cardno="
				+ cardno;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 获取用户下载的优惠券数据
	 * @return String
	 */
	public static String transportCouponDownload(String page, String pagesize) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_couponDownload")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&page=" + page
				+ "&pagesize=" + pagesize + "&used=1&usestime="
				+ DateUtil.date2String(new Date(), "yyyy-MM-dd") + "&useetime="
				+ DateUtil.date2String(new Date(), "yyyy-MM-dd");
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 积分充值
	 * @return String
	 */
	public static String transportPointCharge(String mobile, String cardno,
			String amount, String orderid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_pointCharge")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&ptype="
				+ ResourceReader.readValue("ulpos_url_pointType") + "&mobile="
				+ mobile + "&cardno=" + cardno + "&amount=" + amount
				+ "&orderid=" + orderid;
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 更改用户积分帐户
	 * @return String
	 */
	public static String transportPointEdit(String userid, String cardno,
			String sw) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_pointEdit")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&ptype="
				+ ResourceReader.readValue("ulpos_url_pointType") + "&userid="
				+ userid;
		if (null != cardno && !"".equals(cardno)) {
			url += "&cardno=" + cardno;
		}
		if (null != sw && !"".equals(sw)) {
			url += "&sw=" + sw;
		}
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 用户积分账户
	 * @return String
	 */
	public static String transportPointUser(String page, String pagesize,
			String userid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_pointUser")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&ptype="
				+ ResourceReader.readValue("ulpos_url_pointType") + "&page="
				+ page + "&pagesize=" + pagesize;
		if (null != userid && !"".equals(userid)) {
			url += "&userid=" + userid;
		}
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 积分交易流水
	 * @return String
	 */
	public static String transportPointTradeid(String page, String pagesize,
			String tradeid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_pointTradeid")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&ptype="
				+ ResourceReader.readValue("ulpos_url_pointType") + "&page="
				+ page + "&pagesize=" + pagesize;
		if (null != tradeid && !"".equals(tradeid)) {
			url += "&tradeid=" + tradeid;
		}
		return UlposTool.commonMethod(url);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-27 上午11:50:33
	 * @describe 积分扣除
	 * @return String
	 */
	public static String transportPointDeduct(String mobile, String amount,
			String orderid) {
		String[] ts = UlposTool.getNewTimestampAndSign().split(","); // 时间戳+签名
		String url = ResourceReader.readValue("ulpos_url_pointsDeduct")
				+ "?appkey=" + ts[0] + "&signature_method=" + ts[1]
				+ "&timestamp=" + ts[2] + "&sign=" + ts[3] + "&ptype="
				+ ResourceReader.readValue("ulpos_url_pointType") + "&mobile="
				+ mobile + "&amount=" + amount + "&orderid=" + orderid;
		return UlposTool.commonMethod(url);
	}
}
