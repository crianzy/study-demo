/*
 * 文件名:BaseAction.java
 * 作者：caiqf
 * 完成日期：2012-06-13
 * 维护人员：
 * 维护日期：
 * 维护原因：
 * 当前版本：1.0
 */
package com.app.transaction.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.app.module.sys.dto.TPsSysMenuinfo;
import com.app.module.sys.dto.TPsSysRolemenuRel;
import com.app.module.sys.dto.TPsSysRoleuserRel;
import com.app.transaction.sys.service.SysService;
import com.app.transaction.user.service.UserService;
import com.app.utils.constant.Constant;
import com.app.utils.util.LoginUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Class: BaseAction Description: 所有action的父类
 * 
 * @author tangjiandong
 * @version 10.0, 2012-06-13
 * @since JDK1.6
 */
@SuppressWarnings("all")
public class BaseAction extends ActionSupport implements ModelDriven<Object>,
		ServletRequestAware {
	protected final Log log = LogFactory.getLog(getClass());
	@Resource
	private UserService userService;
	@Resource
	private SysService sysService;
	public BaseAction getBaseAction() {
		return this;
	}

	// ModelDriven中方法的重写
	public Object getModel() {
		return null;
	}

	/** Ajax请求缓存控制 */
	protected String d;

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	/** 权限编码标识 */
	protected String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/** 导航栏菜单标识 */
	protected String nav;

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	/** 左边导航栏菜单标识 */
	protected String lnav;

	public String getLnav() {
		return lnav;
	}

	public void setLnav(String lnav) {
		this.lnav = lnav;
	}

	/** 返回跳转前的页面 */
	protected String fromurl;

	public String getFromurl() {
		return fromurl;
	}

	public void setFromurl(String fromurl) {
		this.fromurl = fromurl;
	}
	/** 获取登陆用户的菜单信息 */
	protected String menustr = "";

	public String getMenustr() {
		try {
		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return menustr;
	}
	
	//获取第一级菜单
	
	protected List menuList=new ArrayList();
	
	public List getMenuList() {
		try {
			
		 if (this.getRequest().getSession().getAttribute(Constant.USER_MENU_LIST) == null) {
				this.loadMenu();
			}
		//保存第一级菜单
		  menuList=(List)this.getRequest().getSession().getAttribute(Constant.USER_MENU_LIST);
			if(nav==null){
				nav="0";
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return menuList;
	}
	//获取第二级级菜单
	
	protected List lmenuList=new ArrayList();
	
	public List getLmenuList() {
		try {
			
			
			if (this.getRequest().getSession().getAttribute(Constant.USER_MENU_MAP) == null) {
				this.loadMenu();
			}
			
			 Map menuMap=new HashMap();
			//获取第二级级菜单
			 menuMap=(Map)this.getRequest().getSession().getAttribute(Constant.USER_MENU_MAP);
			 
			 lmenuList=(List)menuMap.get(this.nav);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return lmenuList;
	}
	
	

	/**
	 * 加载用户菜单
	 * 
	 * 
	 * @author 汤建东
	 * @date 2014-1-15 下午6:08:20
	 * 
	 */

	public void loadMenu() {

		// 保存用户菜单权限
		Map menuMap = new HashMap();
		try {
			/** 查询角色用户关联信息 */
			TPsSysRoleuserRel ru = new TPsSysRoleuserRel();
			ru.setUserid(this.getLoginUserId()); // 用户编号
			List ruList = this.sysService.listTPsSysRoleuserRel(ru);
			if (null != ruList && ruList.size() > 0) {
				String roleids = "";
				for (int i = 0; i < ruList.size(); i++) {
					ru = (TPsSysRoleuserRel) ruList.get(i);
					roleids += ru.getRoleid().toString() + ",";
				}
				if (!"".equals(roleids)) {
					roleids = roleids.substring(0, roleids.length() - 1);
					/** 查询角色菜单关联信息 */
					TPsSysRolemenuRel rm = new TPsSysRolemenuRel();
					rm.setSpeciallist(new ArrayList(Arrays.asList(roleids
							.split(",")))); // 角色编号集合

					List rmList = this.sysService
							.listTPsSysRolemenuRelDistinct(rm);
					if (null != rmList && rmList.size() > 0) {
						String menuids = "";
						for (int i = 0; i < rmList.size(); i++) {
							rm = (TPsSysRolemenuRel) rmList.get(i);
							menuids += rm.getMenuid().toString() + ",";
						}
						if (!"".equals(menuids)) {
							menuids = menuids
									.substring(0, menuids.length() - 1);
							/** 查询菜单信息列表(一级) */
							TPsSysMenuinfo menu1 = new TPsSysMenuinfo();
							menu1.setParentid(0); // 上级菜单编号
							menu1.setSpeciallist(new ArrayList(Arrays
									.asList(menuids.split(",")))); // 菜单编号集合

							List level1 = this.sysService
									.listTPsSysMenuinfo(menu1);
							if (null != level1 && level1.size() > 0) {

								// 保存第一级菜单
								this.getRequest()
										.getSession()
										.setAttribute(Constant.USER_MENU_LIST,
												level1);

								for (int i = 0; i < level1.size(); i++) {
									menu1 = (TPsSysMenuinfo) level1.get(i);
									/** 查询菜单信息列表(一级) */
									TPsSysMenuinfo menu2 = new TPsSysMenuinfo();
									menu2.setParentid(menu1.getMid()); // 上级菜单编号
									menu2.setSpeciallist(new ArrayList(Arrays
											.asList(menuids.split(",")))); // 菜单编号集合
									/** 查询菜单信息列表(二级) */
									List level2 = this.sysService
											.listTPsSysMenuinfo(menu2);

									// 保存用户第二级菜单
									menuMap.put("" + menu1.getMid(), level2);

								}
							}
						}
					}
				}
			}

			// 左边导航菜单（第二级菜单）
			this.getRequest().getSession()
					.setAttribute(Constant.USER_MENU_MAP, menuMap);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {

		}
	}

	/** 错误信息反馈 */
	protected String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/** 分页-当前页数 */
	protected Integer pageNumber = 0;

	/** 分页-每页显示的记录数 */
	protected Integer pagesize = 10;

	/** 分页-总页数 */
	protected Integer pageCount;

	public Integer getPageNumber() {
		pageNumber = (pageNumber == null || pageNumber <= 0) ? 1 : pageNumber;
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		pageNumber = (pageNumber == null || pageNumber <= 0) ? 1 : pageNumber;
		this.pageNumber = pageNumber;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/** 获取登陆用户的ID */
	public Integer getLoginUserId() {
		return LoginUtil.getLoginUserId(this.getRequest());
	}

	/** 获取登陆用户的Name */
	public String getLoginUserName() {
		return LoginUtil.getLoginUserName(this.getRequest());
	}



	/*** 获取获取项目绝对路径 **/
	public String getRealPath() {
		String realpath = "" + getRequest().getScheme() + "://"
				+ getRequest().getServerName() + ":"
				+ getRequest().getServerPort() + ""
				+ getRequest().getContextPath();
		return realpath;
	}

	/** 得到请求的request */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/** 得到响应的response */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/** 得到用户请求的ip */
	public final String getRemoteIP() {
		HttpServletRequest request = getRequest();
		return getRemoteIP(request);
	}

	public String getRemoteIP(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("Cdn-Src-Ip");
		if (ip == null) {
			ip = request.getHeader("X-Forwarded-For");
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		}
		return ip;
	}

	/** 设置页面的缓冲 */
	public void setPagecache(int pagecache) {
		if (pagecache == 1) {
			HttpServletResponse response = this.getResponse();
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
		}
	}

	/** 判断字符串是否为空或者是字符串 */
	public boolean isNull(String str) {
		return str == null || str.trim().equals("");
	}

	/** 得到Writer */
	public PrintWriter getWriter() {
		try {
			return this.getResponse().getWriter();
		} catch (IOException e) {
			this.log.error(" PrintWriter failure to obtain ", e);
			return null;
		}
	}

	public ServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 返回传入对象的字符串 若该对象为空则返回空字符串
	 * 
	 * @param obj
	 *            传入对象
	 * @return String 返回字符串
	 */
	public String getValue(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	/**
	 * 设置ajax操作结果字符串信息 true时返回信息设为yes false时返回信息设为no
	 * 
	 * @param request
	 *            HttpServletRequest请求
	 * @param b
	 *            传入boolean值
	 */
	public void setAjaxBoolean(boolean b) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (b) {
			request.setAttribute("ajax", "yes");
		} else {
			request.setAttribute("ajax", "no");
		}
	}

	/**
	 * 设置ajax操作结果字符串信息
	 * 
	 * @param request
	 *            HttpServletRequest请求
	 * @param String
	 *            ajax
	 * 
	 */
	public void setAjaxMessage(String ajax) {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("ajax", ajax);
	}

	/**
	 * 设置ajax操作结果字符串信息 传入参数错误 设置信息为 param_error
	 * 
	 * @param request
	 *            HttpServletRequest请求
	 */
	public void setAjaxParamError() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("ajax", "param_error");
	}

	/**
	 * 设置ajax操作结果单个对象信息 若操作结果为对象 将其转化为json list并放入ajax中
	 * 
	 * @param request
	 *            HttpServletRequest请求
	 * @param obj
	 *            传入单个对象
	 */
	public void setAjaxObj(Object obj) {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONArray jsonList = JSONArray.fromObject(obj); // 数据obj转化为json-list
		request.setAttribute("ajax", jsonList);
	}

	/**
	 * 设置ajax操作结果list信息 若操作结果为list结果集 将其转化为json list并放入ajax中
	 * 
	 * @param request
	 *            HttpServletRequest请求
	 * @param list
	 *            传入list结果集
	 */
	public void setAjaxList(List list) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (list != null && list.size() > 0) {
			JSONArray jsonList = JSONArray.fromObject(list); // 数据list转化为json-list
			request.setAttribute("ajax", jsonList);
		}
	}

	/**
	 * 传入HttpServletRequest请求返回session会话
	 * 
	 * @param request
	 *            HttpServletRequest请求
	 * @return HttpSession session会话
	 */
	public HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession();
	}

	/**
	 * 传入HttpServletRequest请求清空所有session
	 * 
	 * @param request
	 *            HttpServletRequest请求
	 */
	public void clearSession() {
		Enumeration e = this.getSession().getAttributeNames();
		while (e.hasMoreElements()) {
			String sessionName = (String) e.nextElement();
			this.getSession().removeAttribute(sessionName);
		}
	}

	/**
	 * 获取服务器时间
	 */
	public String getTime() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		request.setAttribute("ajax", dateFormat.format(date));
		return "ajax";
	}

	/***
	 * 动态获取页面参数值
	 */

	public Map getRequestParams() throws Exception {
		Map paramsMap = new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map map = request.getParameterMap();
		Set s = map.keySet();
		Iterator it = s.iterator();
		String col = "";
		while (it.hasNext()) {
			col = (String) it.next();
			String value = getValue(request.getParameter(col));
			paramsMap.put(col, value);
		}
		return paramsMap;
	}

	/**
	 * 从定向发送请求
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void sendRedirect(String url) throws Exception {
		ServletActionContext.getResponse().sendRedirect(url);
	}

	/**
	 * 获取Ip地址
	 * 
	 * @return
	 */
	public String getIpAddr() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
