/**
 * @Filename: SysDao.java
 * @author 汤建东
 * @Date：2013-7-11
 */
package com.app.transaction.sys.dao;

import java.util.List;

import com.app.common.model.EntityBase;
import com.app.common.model.ReturnData;
import com.app.transaction.common.dao.BaseDao;

/**
 * @Class: SysDao.java
 * @Description: 系统信息Dao
 * @author 汤建东
 * @Date：2013-7-11
 */
@SuppressWarnings("all")
public interface SysDao extends BaseDao {
	/**
	 * 新增角色信息
	 */
	public void addTPsSysRoleinfo(EntityBase entity) throws Exception;

	/**
	 * 修改角色信息
	 */
	public void updateTPsSysRoleinfo(EntityBase entity) throws Exception;

	/**
	 * 删除角色信息
	 */
	public void deleteTPsSysRoleinfo(EntityBase entity) throws Exception;

	/**
	 * 查询角色信息
	 */
	public Object findTPsSysRoleinfo(EntityBase entity) throws Exception;

	/**
	 * 查询角色信息列表
	 */
	public List listTPsSysRoleinfo(EntityBase entity) throws Exception;

	/**
	 * 查询角色信息分页列表
	 */
	public ReturnData listTPsSysRoleinfoPage(EntityBase entity)
			throws Exception;

	/**
	 * 新增角色用户关联信息
	 */
	public void addTPsSysRoleuserRel(EntityBase entity) throws Exception;

	/**
	 * 修改角色用户关联信息
	 */
	public void updateTPsSysRoleuserRel(EntityBase entity) throws Exception;

	/**
	 * 删除角色用户关联信息
	 */
	public void deleteTPsSysRoleuserRel(EntityBase entity) throws Exception;

	/**
	 * 查询角色用户关联信息
	 */
	public Object findTPsSysRoleuserRel(EntityBase entity) throws Exception;

	/**
	 * 查询角色用户关联信息列表
	 */
	public List listTPsSysRoleuserRel(EntityBase entity) throws Exception;

	/**
	 * 新增菜单信息
	 */
	public void addTPsSysMenuinfo(EntityBase entity) throws Exception;

	/**
	 * 修改菜单信息
	 */
	public void updateTPsSysMenuinfo(EntityBase entity) throws Exception;

	/**
	 * 删除菜单信息
	 */
	public void deleteTPsSysMenuinfo(EntityBase entity) throws Exception;

	/**
	 * 查询菜单信息
	 */
	public Object findTPsSysMenuinfo(EntityBase entity) throws Exception;
	
	/**
	 * 查询 最大的排序值
	 */
	public Object findTPsSysMenuinfoMaxMSort(EntityBase entity) throws Exception ;
	/**
	 * 查询 最小的排序值
	 */
	public Object findTPsSysMenuinfoMinMSort(EntityBase entity) throws Exception ;

	/**
	 * 查询菜单信息列表
	 */
	public List listTPsSysMenuinfo(EntityBase entity) throws Exception;

	/**
	 * 查询菜单信息分页列表
	 */
	public ReturnData listTPsSysMenuinfoPage(EntityBase entity)
			throws Exception;

	/**
	 * 新增角色菜单关联信息
	 */
	public void addTPsSysRolemenuRel(EntityBase entity) throws Exception;

	/**
	 * 修改角色菜单关联信息
	 */
	public void updateTPsSysRolemenuRel(EntityBase entity) throws Exception;

	/**
	 * 删除角色菜单关联信息
	 */
	public void deleteTPsSysRolemenuRel(EntityBase entity) throws Exception;

	/**
	 * 查询角色菜单关联信息
	 */
	public Object findTPsSysRolemenuRel(EntityBase entity) throws Exception;

	/**
	 * 查询角色菜单关联信息列表
	 */
	public List listTPsSysRolemenuRel(EntityBase entity) throws Exception;

	/**
	 * 查询不重复的菜单编号信息列表
	 */
	public List listTPsSysRolemenuRelDistinct(EntityBase entity)
			throws Exception;

	/**
	 * 新增网站模版信息
	 */
	public void addTPsSysTemplaterinfo(EntityBase entity) throws Exception;

	/**
	 * 修改网站模版信息
	 */
	public void updateTPsSysTemplaterinfo(EntityBase entity) throws Exception;

	/**
	 * 删除网站模版信息
	 */
	public void deleteTPsSysTemplaterinfo(EntityBase entity) throws Exception;

	/**
	 * 查询网站模版信息
	 */
	public Object findTPsSysTemplaterinfo(EntityBase entity) throws Exception;

	/**
	 * 查询网站模版信息列表
	 */
	public List listTPsSysTemplaterinfo(EntityBase entity) throws Exception;

	/**
	 * 查询网站模版信息分页列表
	 */
	public ReturnData listTPsSysTemplaterinfoPage(EntityBase entity)
			throws Exception;

	/**
	 * 新增词林库信息
	 */
	public void addTPsSysWordlin(EntityBase entity) throws Exception;

	/**
	 * 修改词林库信息
	 */
	public void updateTPsSysWordlin(EntityBase entity) throws Exception;

	/**
	 * 删除词林库信息
	 */
	public void deleteTPsSysWordlin(EntityBase entity) throws Exception;

	/**
	 * 查询词林库信息
	 */
	public Object findTPsSysWordlin(EntityBase entity) throws Exception;

	/**
	 * 查询词林库信息列表
	 */
	public List listTPsSysWordlin(EntityBase entity) throws Exception;

	/**
	 * 查询词林库信息分页列表
	 */
	public ReturnData listTPsSysWordlinPage(EntityBase entity) throws Exception;
}
