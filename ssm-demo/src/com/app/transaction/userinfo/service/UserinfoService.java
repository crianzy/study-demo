package com.app.transaction.userinfo.service;

import java.util.List;

import com.app.common.model.EntityBase;
import com.app.common.model.ReturnData;
import com.app.transaction.common.service.BaseService;

@SuppressWarnings("all")
public interface UserinfoService extends BaseService{
	/**
	 * 查询用户信息
	 */
	public Object findTPsUmUserinfo(EntityBase entity) throws Exception;

	/**
	 * 查询用户信息列表
	 */
	public List listTPsUmUserinfo(EntityBase entity) throws Exception;

	/**
	 * 查询用户信息分页列表
	 */
	public ReturnData listTPsUmUserinfoPage(EntityBase entity) throws Exception;
}
