/**
 * @Filename: RoleAction.java
 * @author 汤建东
 * @Date：2013-7-12
 */
package com.app.transaction.sys.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.app.common.model.ReturnData;
import com.app.module.sys.dto.TPsSysMenuinfo;
import com.app.module.sys.dto.TPsSysRoleinfo;
import com.app.module.sys.dto.TPsSysRolemenuRel;
import com.app.module.sys.dto.TPsSysRoleuserRel;
import com.app.transaction.common.action.BaseAction;
import com.app.transaction.sys.service.SysService;

/**
 * @Class: RoleAction.java
 * @Description: 角色管理Action
 * @author 汤建东
 * @Date：2013-7-12
 */
@SuppressWarnings("all")
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 7987147853611232921L;

	@Resource
	private SysService sysService;

	private TPsSysRoleinfo roleInfo = new TPsSysRoleinfo(); // 角色信息对象
	private ReturnData rd; // 用户信息分页列表对象
	private Map dataMap = new HashMap(); // 菜单信息列表对象
	private List dataList = new ArrayList(); // 菜单信息列表对象
	private String ids; // 主键编号字符串

	/**
	 * @author 汤建东
	 * @Description: 进入角色管理页面
	 * @Date：2013-7-12 上午10:08:08
	 * @Return：String
	 */
	public String list() {
		try {
			/** 查询角色信息分页列表 */
			this.roleInfo.setPageNumber(this.getPageNumber());
			this.roleInfo.setPageSize(20);
			this.rd = this.sysService.listTPsSysRoleinfoPage(this.roleInfo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "list";
	}

	/**
	 * @author 汤建东
	 * @Description: 进入创建角色页面
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public String create() {
		return "create";
	}

	/**
	 * @author 汤建东
	 * @Description: 创建角色提交
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public void createSub() {
		PrintWriter out = this.getWriter();
		String flag = "";
		try {
			/** 校验信息 */
			if (null == this.roleInfo.getRolename()
					|| "".equals(this.roleInfo.getRolename().trim())) {
				flag = "角色名称为空"; // 角色名称为空
				return;
			}
			if (null == this.roleInfo.getRoledesc()
					|| "".equals(this.roleInfo.getRoledesc().trim())) {
				flag = "角色描述为空"; // 角色描述为空
				return;
			}

			/** 根据角色名称查询角色信息 */
			TPsSysRoleinfo role = new TPsSysRoleinfo();
			role.setRolename(this.roleInfo.getRolename()); // 角色名称
			Object r = this.sysService.findTPsSysRoleinfo(role);
			if (null != r) {
				flag = "角色名称已存在"; // 角色名称已存在
				return;
			}

			/** 保存角色信息 */
			role.setRolename(this.roleInfo.getRolename()); // 角色名称
			role.setRoledesc(this.roleInfo.getRoledesc()); // 角色描述
			role.setOptuserid(this.getLoginUserId()); // 操作人编号
			role.setOptusername(this.getLoginUserName()); // 操作人姓名
			role.setOptdate(new Date()); // 操作时间
			this.sysService.addTPsSysRoleinfo(role);

			flag = "添加成功";
		} catch (Exception e) {
			flag = "添加失败"; 
			log.error(e.getMessage(), e);
		} finally {
			out.print(flag);
			out.flush();
			out.close();
		}
	}
	/**
	 * 删除角色
	 */
	public void del(){
		roleInfo.setId(Integer.parseInt(ids));
		PrintWriter out = this.getWriter();
		try {
			sysService.deleteTPsSysRoleinfo(roleInfo);
			out.print("删除成功！");
		} catch (Exception e) {
			out.print("删除失败！");
		}finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 * @author 汤建东
	 * @Description: 进入编辑角色页面
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public String edit() {
		try {
			/** 查询角色信息 */
			if (null == this.roleInfo.getId()) {
				return "error";
			}
			Object r = this.sysService.findTPsSysRoleinfo(this.roleInfo);
			if (null != r) {
				this.roleInfo = (TPsSysRoleinfo) r;
			} else {
				return "error";
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "edit";
	}

	/**
	 * @author 汤建东
	 * @Description: 编辑角色提交
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public void editSub() {
		PrintWriter out = this.getWriter();
		String flag = "";
		try {
			/** 校验信息 */
			if (null == this.roleInfo || null == this.roleInfo.getId()) {
				flag = "角色编号为空"; // 
				return;
			}
			if (null == this.roleInfo.getRolename()
					|| "".equals(this.roleInfo.getRolename().trim())) {
				flag = "角色名称为空"; // 
				return;
			}
			if (null == this.roleInfo.getRoledesc()
					|| "".equals(this.roleInfo.getRoledesc().trim())) {
				flag = "角色描述为空"; // 
				return;
			}

			/** 根据角色名称查询角色信息 */
			TPsSysRoleinfo role = new TPsSysRoleinfo();
			role.setId(this.roleInfo.getId()); // 角色编号
			Object r = this.sysService.findTPsSysRoleinfo(role);
			if (null != r) {
				role = (TPsSysRoleinfo) r;
				if (!this.roleInfo.getRolename().equals(role.getRolename())) {
					role = new TPsSysRoleinfo();
					role.setRolename(this.roleInfo.getRolename()); // 角色名称
					r = this.sysService.findTPsSysRoleinfo(role);
					if (null != r) {
						flag = "角色名称已存在"; // 
						return;
					}
				}
			}

			/** 保存角色信息 */
			this.sysService.updateTPsSysRoleinfo(this.roleInfo);

			flag = "修改成功！";
		} catch (Exception e) {
			flag = "修改失败！"; // 
			log.error(e.getMessage(), e);
		} finally {
			out.print(flag);
			out.flush();
			out.close();
		}
	}

	/**
	 * @author 汤建东
	 * @Description: 进入分配菜单页面
	 * @Date：2013-7-12 上午10:08:08
	 * @Return：String
	 */
	public String menu() {
		try {
			if (null == this.roleInfo || null == this.roleInfo.getId()) {
				return "error"; // 角色编号为空
			}

			/** 查询一级菜单信息列表 */
			TPsSysMenuinfo menu = new TPsSysMenuinfo();
			menu.setParentid(0); // 一级菜单
			menu.setIsvalid("1"); // 是否有效(1是0否)
			this.dataList = this.sysService.listTPsSysMenuinfo(menu);
			if (null != this.dataList && this.dataList.size() > 0) {
				for (int i = 0; i < this.dataList.size(); i++) {
					menu = (TPsSysMenuinfo) this.dataList.get(i);
					/** 查询二级菜单信息列表 */
					TPsSysMenuinfo m = new TPsSysMenuinfo();
					m.setParentid(menu.getMid()); // 上级菜单编号
					m.setIsvalid("1"); // 是否有效(1是0否)
					List mList = this.sysService.listTPsSysMenuinfo(m);
					if (null != mList && mList.size() > 0) {
						this.dataMap.put(m.getParentid(), mList);
					}
				}

				/** 查询角色菜单关联信息列表 */
				TPsSysRolemenuRel rm = new TPsSysRolemenuRel();
				rm.setRoleid(this.roleInfo.getId()); // 角色编号
				List list = this.sysService.listTPsSysRolemenuRel(rm);
				if (null != list && list.size() > 0) {
					this.ids = "";
					for (int i = 0; i < list.size(); i++) {
						rm = (TPsSysRolemenuRel) list.get(i);
						this.ids += "[" + rm.getMenuid().toString() + "]" + ",";
					}
					if (!"".equals(this.ids)) {
						this.ids = this.ids.substring(0, this.ids.length() - 1);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "menu";
	}

	/**
	 * @author 汤建东
	 * @Description: 分配菜单提交
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public void menuSub() {
		PrintWriter out = this.getWriter();
		String flag = "";
		try {
			/** 校验信息 */
			if (null == this.roleInfo || null == this.roleInfo.getId()) {
				flag = "1111"; // 角色编号为空
				return;
			}
			if (null == this.ids || "".equals(this.ids)) {
				flag = "1111";
				return;
			}

			// 角色编号字符串
			String[] temp = this.ids.split(",");
			if (temp.length > 0) {
				/** 先删除后保存角色菜单关联信息 */
				TPsSysRolemenuRel rm = new TPsSysRolemenuRel();
				rm.setRoleid(this.roleInfo.getId()); // 角色编号
				this.sysService.deleteTPsSysRolemenuRel(rm);
				for (int i = 0; i < temp.length; i++) {
					rm = new TPsSysRolemenuRel();
					rm.setRoleid(this.roleInfo.getId()); // 角色编号
					rm.setMenuid(Integer.valueOf(temp[i])); // 角色编号
					rm.setOptuserid(this.getLoginUserId()); // 操作人编号
					rm.setOptusername(this.getLoginUserName()); // 操作人名称
					rm.setOptdate(new Date()); // 创建时间
					this.sysService.addTPsSysRolemenuRel(rm);
				}

				flag = "0000";
			}
		} catch (Exception e) {
			flag = "1111"; // 失败
			log.error(e.getMessage(), e);
		} finally {
			out.print(flag);
			out.flush();
			out.close();
		}
	}

	public TPsSysRoleinfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(TPsSysRoleinfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public ReturnData getRd() {
		return rd;
	}

	public void setRd(ReturnData rd) {
		this.rd = rd;
	}

	public Map getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
