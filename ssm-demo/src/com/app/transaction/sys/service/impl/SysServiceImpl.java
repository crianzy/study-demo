/**
 * @Filename: SysServiceImpl.java
 * @author 汤建东
 * @Date：2013-7-11
 */
package com.app.transaction.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.app.common.model.EntityBase;
import com.app.common.model.ReturnData;
import com.app.transaction.common.impl.dao.BaseDaoImpl;
import com.app.transaction.sys.dao.SysDao;
import com.app.transaction.sys.service.SysService;

/**
 * @Class: SysServiceImpl.java
 * @Description: 系统信息Service实现
 * @author 汤建东
 * @Date：2013-7-11
 */
@SuppressWarnings("all")
@Service("sysServiceImpl")
public class SysServiceImpl extends BaseDaoImpl implements SysService {
	@Resource
	private SysDao sysDao;

	/**
	 * 新增角色信息
	 */
	@Override
	public void addTPsSysRoleinfo(EntityBase entity) throws Exception {
		this.sysDao.addTPsSysRoleinfo(entity);
	}

	/**
	 * 修改角色信息
	 */
	@Override
	public void updateTPsSysRoleinfo(EntityBase entity) throws Exception {
		this.sysDao.updateTPsSysRoleinfo(entity);
	}

	/**
	 * 删除角色信息
	 */
	@Override
	public void deleteTPsSysRoleinfo(EntityBase entity) throws Exception {
		this.sysDao.deleteTPsSysRoleinfo(entity);
	}

	/**
	 * 查询角色信息
	 */
	@Override
	public Object findTPsSysRoleinfo(EntityBase entity) throws Exception {
		return this.sysDao.findTPsSysRoleinfo(entity);
	}

	/**
	 * 查询角色信息列表
	 */
	@Override
	public List listTPsSysRoleinfo(EntityBase entity) throws Exception {
		return this.sysDao.listTPsSysRoleinfo(entity);
	}

	/**
	 * 查询角色信息分页列表
	 */
	public ReturnData listTPsSysRoleinfoPage(EntityBase entity)
			throws Exception {
		return this.sysDao.listTPsSysRoleinfoPage(entity);
	}

	/**
	 * 新增角色用户关联信息
	 */
	@Override
	public void addTPsSysRoleuserRel(EntityBase entity) throws Exception {
		this.sysDao.addTPsSysRoleuserRel(entity);
	}

	/**
	 * 修改角色用户关联信息
	 */
	@Override
	public void updateTPsSysRoleuserRel(EntityBase entity) throws Exception {
		this.sysDao.updateTPsSysRoleuserRel(entity);
	}

	/**
	 * 删除角色用户关联信息
	 */
	@Override
	public void deleteTPsSysRoleuserRel(EntityBase entity) throws Exception {
		this.sysDao.deleteTPsSysRoleuserRel(entity);
	}

	/**
	 * 查询角色用户关联信息
	 */
	@Override
	public Object findTPsSysRoleuserRel(EntityBase entity) throws Exception {
		return this.sysDao.findTPsSysRoleuserRel(entity);
	}

	/**
	 * 查询角色用户关联信息列表
	 */
	@Override
	public List listTPsSysRoleuserRel(EntityBase entity) throws Exception {
		return this.sysDao.listTPsSysRoleuserRel(entity);
	}

	/**
	 * 新增菜单信息
	 */
	@Override
	public void addTPsSysMenuinfo(EntityBase entity) throws Exception {
		this.sysDao.addTPsSysMenuinfo(entity);
	}

	/**
	 * 修改菜单信息
	 */
	@Override
	public void updateTPsSysMenuinfo(EntityBase entity) throws Exception {
		this.sysDao.updateTPsSysMenuinfo(entity);
	}

	/**
	 * 删除菜单信息
	 */
	@Override
	public void deleteTPsSysMenuinfo(EntityBase entity) throws Exception {
		this.sysDao.deleteTPsSysMenuinfo(entity);
	}

	/**
	 * 查询菜单信息
	 */
	@Override
	public Object findTPsSysMenuinfo(EntityBase entity) throws Exception {
		return this.sysDao.findTPsSysMenuinfo(entity);
	}

	/**
	 * 查询菜单信息列表
	 */
	@Override
	public List listTPsSysMenuinfo(EntityBase entity) throws Exception {
		return this.sysDao.listTPsSysMenuinfo(entity);
	}

	/**
	 * 查询菜单信息分页列表
	 */
	public ReturnData listTPsSysMenuinfoPage(EntityBase entity)
			throws Exception {
		return this.sysDao.listTPsSysMenuinfoPage(entity);
	}

	/**
	 * 新增角色菜单关联信息
	 */
	@Override
	public void addTPsSysRolemenuRel(EntityBase entity) throws Exception {
		this.sysDao.addTPsSysRolemenuRel(entity);
	}

	/**
	 * 修改角色菜单关联信息
	 */
	@Override
	public void updateTPsSysRolemenuRel(EntityBase entity) throws Exception {
		this.sysDao.updateTPsSysRolemenuRel(entity);
	}

	/**
	 * 删除角色菜单关联信息
	 */
	@Override
	public void deleteTPsSysRolemenuRel(EntityBase entity) throws Exception {
		this.sysDao.deleteTPsSysRolemenuRel(entity);
	}

	/**
	 * 查询角色菜单关联信息
	 */
	@Override
	public Object findTPsSysRolemenuRel(EntityBase entity) throws Exception {
		return this.sysDao.findTPsSysRolemenuRel(entity);
	}

	/**
	 * 查询角色菜单关联信息列表
	 */
	@Override
	public List listTPsSysRolemenuRel(EntityBase entity) throws Exception {
		return this.sysDao.listTPsSysRolemenuRel(entity);
	}

	/**
	 * 查询不重复的菜单编号信息列表
	 */
	@Override
	public List listTPsSysRolemenuRelDistinct(EntityBase entity)
			throws Exception {
		return this.sysDao.listTPsSysRolemenuRelDistinct(entity);
	}

	/**
	 * 新增网站模版信息
	 */
	@Override
	public void addTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		this.sysDao.addTPsSysTemplaterinfo(entity);
	}

	/**
	 * 删除网站模版信息
	 */
	@Override
	public void deleteTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		this.sysDao.deleteTPsSysTemplaterinfo(entity);
	}

	/**
	 * 查询网站模版信息
	 */
	@Override
	public Object findTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		return this.sysDao.findTPsSysTemplaterinfo(entity);
	}

	/**
	 * 查询网站模版信息列表
	 */
	@Override
	public List listTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		return this.sysDao.listTPsSysTemplaterinfo(entity);
	}

	/**
	 * 查询网站模版信息分页列表
	 */
	@Override
	public ReturnData listTPsSysTemplaterinfoPage(EntityBase entity)
			throws Exception {
		return this.sysDao.listTPsSysTemplaterinfoPage(entity);
	}

	/**
	 * 修改网站模版信息
	 */
	@Override
	public void updateTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		this.sysDao.updateTPsSysTemplaterinfo(entity);
	}

	/**
	 * 新增词林库信息
	 */
	@Override
	public void addTPsSysWordlin(EntityBase entity) throws Exception {
		this.sysDao.addTPsSysWordlin(entity);
	}

	/**
	 * 修改词林库信息
	 */
	@Override
	public void updateTPsSysWordlin(EntityBase entity) throws Exception {
		this.sysDao.updateTPsSysWordlin(entity);
	}

	/**
	 * 删除词林库信息
	 */
	@Override
	public void deleteTPsSysWordlin(EntityBase entity) throws Exception {
		this.sysDao.deleteTPsSysWordlin(entity);
	}

	/**
	 * 查询词林库信息
	 */
	@Override
	public Object findTPsSysWordlin(EntityBase entity) throws Exception {
		return this.sysDao.findTPsSysWordlin(entity);
	}

	/**
	 * 查询词林库信息列表
	 */
	@Override
	public List listTPsSysWordlin(EntityBase entity) throws Exception {
		return this.sysDao.listTPsSysWordlin(entity);
	}

	/**
	 * 查询词林库信息分页列表
	 */
	public ReturnData listTPsSysWordlinPage(EntityBase entity) throws Exception {
		return this.sysDao.listTPsSysWordlinPage(entity);
	}

	@Override
	public Object findTPsSysMenuinfoMaxMSort(EntityBase entity)
			throws Exception {
		return this.sysDao.findTPsSysMenuinfoMaxMSort(entity);
	}

	@Override
	public Object findTPsSysMenuinfoMinMSort(EntityBase entity)
			throws Exception {
		return this.sysDao.findTPsSysMenuinfoMinMSort(entity);
	}
}
