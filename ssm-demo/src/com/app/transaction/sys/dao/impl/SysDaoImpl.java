/**
 * @Filename: SysDaoImpl.java
 * @author 汤建东
 * @Date：2013-7-11
 */
package com.app.transaction.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.common.model.EntityBase;
import com.app.common.model.ReturnData;
import com.app.transaction.common.impl.dao.BaseDaoImpl;
import com.app.transaction.sys.dao.SysDao;

/**
 * @Class: SysDaoImpl.java
 * @Description: 系统信息Dao实现
 * @author 汤建东
 * @Date：2013-7-11
 */
@SuppressWarnings("all")
@Repository("sysDaoImpl")
public class SysDaoImpl extends BaseDaoImpl implements SysDao {
	/**
	 * 新增角色信息
	 */
	@Override
	public void addTPsSysRoleinfo(EntityBase entity) throws Exception {
		this.insert("TPsSysRoleinfo.insertTPsSysRoleinfoByModel", entity);
	}

	/**
	 * 修改角色信息
	 */
	@Override
	public void updateTPsSysRoleinfo(EntityBase entity) throws Exception {
		this.update("TPsSysRoleinfo.updateTPsSysRoleinfoByModel", entity);
	}

	/**
	 * 删除角色信息
	 */
	@Override
	public void deleteTPsSysRoleinfo(EntityBase entity) throws Exception {
		this.delete("TPsSysRoleinfo.deleteTPsSysRoleinfoByModel", entity);
	}

	/**
	 * 查询角色信息
	 */
	@Override
	public Object findTPsSysRoleinfo(EntityBase entity) throws Exception {
		return this.findByProperty(
				"TPsSysRoleinfo.selectTPsSysRoleinfoByModel", entity);
	}

	/**
	 * 查询角色信息列表
	 */
	@Override
	public List listTPsSysRoleinfo(EntityBase entity) throws Exception {
		return this.listByProperty(
				"TPsSysRoleinfo.selectTPsSysRoleinfoByModel", entity);
	}

	/**
	 * 查询角色信息分页列表
	 */
	public ReturnData listTPsSysRoleinfoPage(EntityBase entity)
			throws Exception {
		return this.listDataPaging(
				"TPsSysRoleinfo.selectTPsSysRoleinfo_list_page", entity);
	}

	/**
	 * 新增角色用户关联信息
	 */
	@Override
	public void addTPsSysRoleuserRel(EntityBase entity) throws Exception {
		this.insert("TPsSysRoleuserRel.insertTPsSysRoleuserRelByModel", entity);
	}

	/**
	 * 修改角色用户关联信息
	 */
	@Override
	public void updateTPsSysRoleuserRel(EntityBase entity) throws Exception {
		this.update("TPsSysRoleuserRel.updateTPsSysRoleuserRelByModel", entity);
	}

	/**
	 * 删除角色用户关联信息
	 */
	@Override
	public void deleteTPsSysRoleuserRel(EntityBase entity) throws Exception {
		this.delete("TPsSysRoleuserRel.deleteTPsSysRoleuserRelByModel", entity);
	}

	/**
	 * 查询角色用户关联信息
	 */
	@Override
	public Object findTPsSysRoleuserRel(EntityBase entity) throws Exception {
		return this.findByProperty(
				"TPsSysRoleuserRel.selectTPsSysRoleuserRelByModel", entity);
	}

	/**
	 * 查询角色用户关联信息列表
	 */
	@Override
	public List listTPsSysRoleuserRel(EntityBase entity) throws Exception {
		return this.listByProperty(
				"TPsSysRoleuserRel.selectTPsSysRoleuserRelByModel", entity);
	}

	/**
	 * 新增菜单信息
	 */
	@Override
	public void addTPsSysMenuinfo(EntityBase entity) throws Exception {
		this.insert("TPsSysMenuinfo.insertTPsSysMenuinfoByModel", entity);
	}

	/**
	 * 修改菜单信息
	 */
	@Override
	public void updateTPsSysMenuinfo(EntityBase entity) throws Exception {
		this.update("TPsSysMenuinfo.updateTPsSysMenuinfoByModel", entity);
	}

	/**
	 * 删除菜单信息
	 */
	@Override
	public void deleteTPsSysMenuinfo(EntityBase entity) throws Exception {
		this.delete("TPsSysMenuinfo.deleteTPsSysMenuinfoByModel", entity);
	}

	/**
	 * 查询菜单信息
	 */
	@Override
	public Object findTPsSysMenuinfo(EntityBase entity) throws Exception {
		return this.findByProperty(
				"TPsSysMenuinfo.selectTPsSysMenuinfoByModel", entity);
	}

	/**
	 * 查询菜单信息列表
	 */
	@Override
	public List listTPsSysMenuinfo(EntityBase entity) throws Exception {
		return this.listByProperty(
				"TPsSysMenuinfo.selectTPsSysMenuinfoByModel", entity);
	}

	/**
	 * 查询菜单信息分页列表
	 */
	public ReturnData listTPsSysMenuinfoPage(EntityBase entity)
			throws Exception {
		return this.listDataPaging(
				"TPsSysMenuinfo.selectTPsSysMenuinfo_list_page", entity);
	}

	/**
	 * 新增角色菜单关联信息
	 */
	@Override
	public void addTPsSysRolemenuRel(EntityBase entity) throws Exception {
		this.insert("TPsSysRolemenuRel.insertTPsSysRolemenuRelByModel", entity);
	}

	/**
	 * 修改角色菜单关联信息
	 */
	@Override
	public void updateTPsSysRolemenuRel(EntityBase entity) throws Exception {
		this.update("TPsSysRolemenuRel.updateTPsSysRolemenuRelByModel", entity);
	}

	/**
	 * 删除角色菜单关联信息
	 */
	@Override
	public void deleteTPsSysRolemenuRel(EntityBase entity) throws Exception {
		this.delete("TPsSysRolemenuRel.deleteTPsSysRolemenuRelByModel", entity);
	}

	/**
	 * 查询角色菜单关联信息
	 */
	@Override
	public Object findTPsSysRolemenuRel(EntityBase entity) throws Exception {
		return this.findByProperty(
				"TPsSysRolemenuRel.selectTPsSysRolemenuRelByModel", entity);
	}

	/**
	 * 查询角色菜单关联信息列表
	 */
	@Override
	public List listTPsSysRolemenuRel(EntityBase entity) throws Exception {
		return this.listByProperty(
				"TPsSysRolemenuRel.selectTPsSysRolemenuRelByModel", entity);
	}

	/**
	 * 查询不重复的菜单编号信息列表
	 */
	@Override
	public List listTPsSysRolemenuRelDistinct(EntityBase entity)
			throws Exception {
		return this.listByProperty(
				"TPsSysRolemenuRel.selectTPsSysRolemenuRelByModel_distinct",
				entity);
	}

	/**
	 * 新增网站模版信息
	 */
	@Override
	public void addTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		this.insert("TPsSysTemplaterinfo.insertTPsSysTemplaterinfoByModel",
				entity);
	}

	/**
	 * 删除网站模版信息
	 */
	@Override
	public void deleteTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		this.delete("TPsSysTemplaterinfo.deleteTPsSysTemplaterinfoByModel",
				entity);
	}

	/**
	 * 查询网站模版信息
	 */
	@Override
	public Object findTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		return this.findByProperty(
				"TPsSysTemplaterinfo.selectTPsSysTemplaterinfoByModel", entity);
	}

	/**
	 * 查询网站模版信息列表
	 */
	@Override
	public List listTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		return this.listByProperty(
				"TPsSysTemplaterinfo.selectTPsSysTemplaterinfoByModel", entity);
	}

	/**
	 * 查询网站模版信息分页列表
	 */
	@Override
	public ReturnData listTPsSysTemplaterinfoPage(EntityBase entity)
			throws Exception {
		return this.listDataPaging(
				"TPsSysTemplaterinfo.selectTPsSysTemplaterinfo_list_page",
				entity);
	}

	/**
	 * 修改网站模版信息
	 */
	@Override
	public void updateTPsSysTemplaterinfo(EntityBase entity) throws Exception {
		this.update("TPsSysTemplaterinfo.updateTPsSysTemplaterinfoByModel",
				entity);
	}

	/**
	 * 新增词林库信息
	 */
	@Override
	public void addTPsSysWordlin(EntityBase entity) throws Exception {
		this.insert("TPsSysWordlin.insertTPsSysWordlinByModel", entity);
	}

	/**
	 * 修改词林库信息
	 */
	@Override
	public void updateTPsSysWordlin(EntityBase entity) throws Exception {
		this.update("TPsSysWordlin.updateTPsSysWordlinByModel", entity);
	}

	/**
	 * 删除词林库信息
	 */
	@Override
	public void deleteTPsSysWordlin(EntityBase entity) throws Exception {
		this.delete("TPsSysWordlin.deleteTPsSysWordlinByModel", entity);
	}

	/**
	 * 查询词林库信息
	 */
	@Override
	public Object findTPsSysWordlin(EntityBase entity) throws Exception {
		return this.findByProperty("TPsSysWordlin.selectTPsSysWordlinByModel",
				entity);
	}

	/**
	 * 查询词林库信息列表
	 */
	@Override
	public List listTPsSysWordlin(EntityBase entity) throws Exception {
		return this.listByProperty("TPsSysWordlin.selectTPsSysWordlinByModel",
				entity);
	}

	/**
	 * 查询词林库信息分页列表
	 */
	public ReturnData listTPsSysWordlinPage(EntityBase entity) throws Exception {
		return this.listDataPaging(
				"TPsSysWordlin.selectTPsSysWordlin_list_page", entity);
	}

	@Override
	public Object findTPsSysMenuinfoMaxMSort(EntityBase entity)
			throws Exception {
		return this.findByProperty("selectTPsSysMenuinfoMaxMSortByModel",
				entity);
	}

	@Override
	public Object findTPsSysMenuinfoMinMSort(EntityBase entity)
			throws Exception {
		return this.findByProperty("selectTPsSysMenuinfoMinMSortByModel",
				entity);
	}
}
