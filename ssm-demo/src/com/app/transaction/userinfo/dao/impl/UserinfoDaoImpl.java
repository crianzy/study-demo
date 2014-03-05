package com.app.transaction.userinfo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.common.model.EntityBase;
import com.app.common.model.ReturnData;
import com.app.transaction.common.impl.dao.BaseDaoImpl;
import com.app.transaction.userinfo.dao.UserinfoDao;

@SuppressWarnings("all")
@Repository("userinfoDaoImpl")
public class UserinfoDaoImpl extends BaseDaoImpl implements UserinfoDao{

	/**
	 * 查询用户信息
	 */
	@Override
	public Object findTPsUmUserinfo(EntityBase entity) throws Exception {
		return this.findByProperty("TPsUmUserinfo.selectTPsUmUserinfoByModel",
				entity);
	}

	/**
	 * 查询用户信息列表
	 */
	@Override
	public List listTPsUmUserinfo(EntityBase entity) throws Exception {
		return this.listByProperty("TPsUmUserinfo.selectTPsUmUserinfoByModel",
				entity);
	}

	/**
	 * 查询用户信息分页列表
	 */
	@Override
	public ReturnData listTPsUmUserinfoPage(EntityBase entity) throws Exception {
		return this.listDataPaging("TPsUmUserinfo.selectTPsUmUserinfo_list_page",
				entity);
	}

}
