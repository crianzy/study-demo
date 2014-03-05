/*
 * 文件名:Constant.java
 * 作者：caiqf
 * 完成日期：2012-6-26
 * 维护人员：
 * 维护日期：
 * 维护原因：
 * 当前版本：1.0
 * 前继版本：1.0
 */
package com.app.utils.constant;

/**
 * Class: Constant Description: 自定义常量类
 * 
 * @author caiqf
 * @version 10.0, 2012-6-26
 * @since JDK1.6
 */
public final class Constant {
	/** 验证信息 */
	public final static String AZ09 = "^[A-Za-z0-9]+$"; // 匹配由数字和26个英文字母组成的字符串
	public final static String ZW = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$"; // 匹配中文
	public final static String C09 = "^[0-9]+$"; // 纯数字
	public final static String MOBILE = "^0{0,1}(13[0-9]|15[0-9]|18[0-9])[0-9]{8}+$"; // 手机号码
	public final static String TELPHONE = "^((0\\d{2,5})-)(\\d{5,10})(-(\\d{1,6}))?+$"; // 电话号码
	public final static String POSTCARD = "^[1-9][0-9]{5}+$"; // 邮政编码
	public final static String AZ09ZW = "^[A-Za-z0-9_\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$"; // 匹配由字母、数字、中文或下划线组成的字符串
	public final static String AZ09ZWDH = "^([A-Za-z0-9\\u4E00-\\u9Fa5\\uF900-\\uFA2D]{1,},){0,}([A-Za-z0-9\\u4E00-\\u9Fa5\\uF900-\\uFA2D]{1,})$"; //用逗号分隔的汉字的正则表达式 

	/** 登录信息 */
	public final static String LOGIN_OF_WEB = "loginTimeOut"; // 登录拦截器
	public final static String LOGIN_ERROR = "error"; // 登录拦截器-错误页面跳转
	public final static String LOGIN_SESSION_NAME = "malls_ulk"; // 登录session
	public final static String LOGIN_COOKIE_NAME = "malls_ulk"; // 登录cookie
	
	/** 其它信息 */
	public final static String DEFAULT_USER_PWD = "123456"; // 默认用户密码
	public final static String PARAMETER_MENU_SIGN = "nav"; // 导航菜单标识
	public final static String USER_MENU_LIST = "user_menu_list"; // 第一级菜单（头部导航）
	public final static String USER_MENU_MAP = "user_menu_map"; // 第二级菜单（左边导航）
	
	/** 短信信息 */
	public final static String SMS_CONTENT_TEMPLATE1 = "您本次操作的短信验证码是：${code}，请在${step}分钟之内完成操作，否则将会过期。"; // 短信内容模板
	public final static Integer SMS_MOBILE_STEP = 3; // 短信验证码失效时长(分)
}
