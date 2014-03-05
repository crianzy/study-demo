/**
 * 文件名: UserServiceImpl.java
 * 作者：caiqf
 * 完成日期：2012-12-13
 * 维护人员：
 * 维护日期：
 * 维护原因：
 */
package com.app.transaction.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.common.model.EntityBase;
import com.app.common.model.ReturnData;
import com.app.transaction.common.impl.service.BaseServiceImpl;
import com.app.transaction.user.dao.UserDao;
import com.app.transaction.user.service.UserService;

/**
 * Class: UserServiceImpl.java Description: 用户信息Service实现
 * 
 * @author caiqf
 * @date 2012-12-13
 */
@SuppressWarnings("all")
@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	/**
	 * 新增用户基本信息
	 */
	@Override
	public void addTPsSysUserinfo(EntityBase entity) throws Exception {
		this.userDao.addTPsSysUserinfo(entity);
	}

	/**
	 * 修改用户基本信息
	 */
	@Override
	public void updateTPsSysUserinfo(EntityBase entity) throws Exception {
		this.userDao.updateTPsSysUserinfo(entity);
	}

	/**
	 * 删除用户基本信息
	 */
	@Override
	public void deleteTPsSysUserinfo(EntityBase entity) throws Exception {
		this.userDao.deleteTPsSysUserinfo(entity);
	}

	/**
	 * 查询用户基本信息
	 */
	@Override
	public Object findTPsSysUserinfo(EntityBase entity) throws Exception {
		return this.userDao.findTPsSysUserinfo(entity);
	}

	/**
	 * 查询用户基本信息列表
	 */
	@Override
	public List listTPsSysUserinfo(EntityBase entity) throws Exception {
		return this.userDao.listTPsSysUserinfo(entity);
	}

	/**
	 * 查询用户信息分页列表
	 */
	@Override
	public ReturnData listTPsSysUserinfoPage(EntityBase entity) throws Exception {
		return this.userDao.listTPsSysUserinfoPage(entity);
	}
}
