/**
 * 文件名: DefaultAction.java
 * 作者：caiqf
 * 完成日期：2012-12-7
 * 维护人员：
 * 维护日期：
 * 维护原因：
 */
package com.app.transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.app.module.sys.dto.TPsSysMenuinfo;
import com.app.module.sys.dto.TPsSysRolemenuRel;
import com.app.module.sys.dto.TPsSysRoleuserRel;
import com.app.transaction.common.action.BaseAction;
import com.app.transaction.sys.service.SysService;
import com.app.utils.constant.Constant;
import com.app.utils.tool.String_Util;
import com.app.utils.util.DateUtil;

/**
 * Class: DefaultAction.java Description: 网站首页
 * 
 * @author caiqf
 * @date 2012-12-7
 */
@SuppressWarnings("all")
public class DefaultAction extends BaseAction {
	private static final long serialVersionUID = 8029706494886407357L;

	@Resource
	private SysService sysService;
	/**
	 * 
	 * @author tangjiandong
	 * @date 2012-12-7 下午04:05:17
	 * @describe 网站首页
	 * @return String
	 */
	public String gotoHome() {
		try {
			//this.nav = "0"; // 首页导航栏标识
			
			String menumdem="";
			
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
						rm.setSpeciallist(new ArrayList(Arrays.asList(roleids.split(",")))); // 角色编号集合
						
						List rmList = this.sysService.listTPsSysRolemenuRelDistinct(rm);
						if (null != rmList && rmList.size() > 0) {
							String menuids = "";
							for (int i = 0; i < rmList.size(); i++) {
								rm = (TPsSysRolemenuRel) rmList.get(i);
								menuids += rm.getMenuid().toString() + ",";
							}
							if (!"".equals(menuids)) {
								menuids = menuids.substring(0, menuids.length() - 1);
								/** 查询菜单信息列表(一级) */
								TPsSysMenuinfo menu1 = new TPsSysMenuinfo();
								menu1.setParentid(0); // 上级菜单编号
								menu1.setSpeciallist(new ArrayList(Arrays.asList(menuids.split(",")))); // 菜单编号集合
								
								
								
								List level1 = this.sysService.listTPsSysMenuinfo(menu1);
								if (null != level1 && level1.size() > 0) {
									for (int i = 0; i < level1.size(); i++) {
										menu1 = (TPsSysMenuinfo) level1.get(i);
										String sStart = "<h3>" + menu1.getMname()
												+ "</h3>", sEnd = "</ul>";
										if (null != this.nav
												&& !"".equals(this.nav)
												&& this.nav.split(",").length == 2
												&& String
														.valueOf(menu1.getMid())
														.equals(this.nav.split(",")[0])) {
											sStart += "<ul class='cur'>";
										} else {
											sStart += "<ul>";
										}
										
										
										
										/** 查询菜单信息列表(一级) */
										TPsSysMenuinfo menu2 = new TPsSysMenuinfo();
										menu2.setParentid(menu1.getMid()); // 上级菜单编号
										menu2.setSpeciallist(new ArrayList(Arrays
												.asList(menuids.split(",")))); // 菜单编号集合
										List level2 = this.sysService
												.listTPsSysMenuinfo(menu2);
										if (null != level2 && level2.size() > 0) {
											for (int y = 0; y < level2.size(); y++) {
												menu2 = (TPsSysMenuinfo) level2
														.get(y);
												String c = "";
												if (null != this.nav
														&& !"".equals(this.nav)
														&& this.nav.split(",").length == 2
														&& String
																.valueOf(
																		menu2.getMid())
																.equals(this.nav
																		.split(",")[1])) {
													c = " class='cur'";
												}
												sStart += "<li><a href='"
														+ (null == menu2
																.getActionurl()
																|| "".equals(menu2
																		.getActionurl()) ? "javascript:void(0);"
																: menu2.getActionurl()
																		+ "?"
																		+ Constant.PARAMETER_MENU_SIGN
																		+ "="
																		+ menu2.getParentid()
																				.toString()
																		+ ","
																		+ menu2.getMid())
														+ "' " + c + ">"
														+ menu2.getMname()
														+ "</a></li>";
											}
										}
										menumdem += sStart + sEnd;
									}
								}
							}
						}
					}
				}
				
				System.out.println(menumdem);
			
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "home";
	}


}
