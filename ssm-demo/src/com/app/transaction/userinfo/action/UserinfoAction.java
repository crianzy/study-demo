package com.app.transaction.userinfo.action;


import javax.annotation.Resource;

import com.app.common.model.ReturnData;
import com.app.module.userinfo.dto.TPsUmUserinfo;
import com.app.transaction.common.action.BaseAction;
import com.app.transaction.userinfo.service.UserinfoService;
import com.app.utils.tool.String_Util;

/**
 * @ClassName: UserinfoAction
 * @Description: 用户信息查询
 * @author: 张蜜
 * @date: 2014-2-20 下午3:29:36
 */
public class UserinfoAction extends BaseAction{
	private static final long serialVersionUID = -4823412623277532472L;

	@Resource
	private UserinfoService userinfoService;
	
	private TPsUmUserinfo userInfo; // 用户信息对象
	private ReturnData rd; // 用户信息分页列表对象
	private String mobile;//手机号
	private String username;
	private String starttime;
	private String endtime;
	
	public String list() {
		userInfo = new TPsUmUserinfo();
		//手机号
		if(!String_Util.checkObjectIsBank(mobile)){
			userInfo.setMobile(mobile);
		}
		//开始时间
		if(!String_Util.checkObjectIsBank(starttime)){
			userInfo.setStarttime(starttime);
		}
		//结束时间
		if(!String_Util.checkObjectIsBank(endtime)){
			userInfo.setEndtime(endtime);
		}
		//昵称
		if(!String_Util.checkObjectIsBank(username)){
			userInfo.setUsername(username);
		}
		try {
			/** 查询用户信息分页列表 */
			this.userInfo.setPageNumber(this.getPageNumber());
			this.userInfo.setPageSize(20);
			this.rd = this.userinfoService.listTPsUmUserinfoPage(this.userInfo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "list";
	}


	public UserinfoService getUserinfoService() {
		return userinfoService;
	}


	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}


	public TPsUmUserinfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(TPsUmUserinfo userInfo) {
		this.userInfo = userInfo;
	}


	public ReturnData getRd() {
		return rd;
	}


	public void setRd(ReturnData rd) {
		this.rd = rd;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getStarttime() {
		return starttime;
	}


	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}


	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	
}
