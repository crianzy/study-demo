package com.app.transaction.userinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.common.model.EntityBase;
import com.app.common.model.ReturnData;
import com.app.transaction.common.impl.service.BaseServiceImpl;
import com.app.transaction.userinfo.dao.UserinfoDao;
import com.app.transaction.userinfo.service.UserinfoService;

@SuppressWarnings("all")
@Service("userinfoServiceImpl")
public class UserinfoServiceImpl extends BaseServiceImpl implements UserinfoService{
	@Resource
	private UserinfoDao userinfoDao;
	/**
	 * 查询用户基本信息
	 */
	@Override
	public Object findTPsUmUserinfo(EntityBase entity) throws Exception {
		return this.userinfoDao.findTPsUmUserinfo(entity);
	}

	/**
	 * 查询用户基本信息列表
	 */
	@Override
	public List listTPsUmUserinfo(EntityBase entity) throws Exception {
		return this.userinfoDao.listTPsUmUserinfo(entity);
	}

	/**
	 * 查询用户信息分页列表
	 */
	@Override
	public ReturnData listTPsUmUserinfoPage(EntityBase entity) throws Exception {
		return this.userinfoDao.listTPsUmUserinfoPage(entity);
	}
}
