/**
 * @Filename: MenuAction.java
 * @author 汤建东
 * @Date：2013-7-12
 */
package com.app.transaction.sys.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.app.common.model.ReturnData;
import com.app.module.sys.dto.TPsSysMenuinfo;
import com.app.module.sys.dto.TPsSysRolemenuRel;
import com.app.transaction.common.action.BaseAction;
import com.app.transaction.sys.service.SysService;

/**
 * @Class: MenuAction.java
 * @Description: 菜单管理Action
 * @author 汤建东
 * @Date：2013-7-12
 */
@SuppressWarnings("all")
public class MenuAction extends BaseAction {
	private static final long serialVersionUID = 5233796069870270496L;

	@Resource
	private SysService sysService;

	private TPsSysMenuinfo menuInfo = new TPsSysMenuinfo(); // 菜单信息对象
	// private List menuList = new ArrayList(); // 菜单信息列表 会和 baseAction 中得
	// menulist 冲突
	private List childMenuList = new ArrayList();
	private ReturnData rd; // 用户信息分页列表对象
	private Integer pid = 0; // 菜单编号
	private String pname; // 菜单名称
	private List<TPsSysMenuinfo> allMenuList = new ArrayList<TPsSysMenuinfo>();
	
	private List<TPsSysRolemenuRel> rolemenuList=new ArrayList<TPsSysRolemenuRel>();//用户存储根据角色ID查询的菜单名称
	private String menuArrayStr;  //菜单项集合拼凑成的字符串
	private int roleid;  //角色ID
	private String menuIds;//被选中的菜单ID组成的字符串

	public MenuAction() {
	}

	/**
	 * @author 熊自成
	 * @Description: 给角色添加菜单，初始化menuArrayStr 菜单项集合拼凑成的字符串
	 * @Date：2014-1-20 上午10:12:08
	 * @Return：String
	 */
	public String bandrole(){
		try {
			allMenuList = this.sysService
					.listTPsSysMenuinfo(new TPsSysMenuinfo());
			TPsSysRolemenuRel rolemenurel=new TPsSysRolemenuRel();
			rolemenurel.setRoleid(roleid);
			rolemenuList=this.sysService.listTPsSysRolemenuRel(rolemenurel);
			menuArrayStr="[";
		for (TPsSysMenuinfo menu : allMenuList) {
			String ischeck="";
			for(TPsSysRolemenuRel role_menu:rolemenuList){
				if(role_menu.getMenuid()==menu.getMid()){
					ischeck=",checked:true";
					break;
				}else{
					ischeck="";
				}
			}
			String str=String.format("{ id:%s, pId:%s, name:\"%s\"%s%s},", 
						menu.getMid(),menu.getParentid(),menu.getMname(),menu.getParentid()==0?",open:true":"",ischeck);
				menuArrayStr+=str;

		}
		menuArrayStr = menuArrayStr.substring(0,menuArrayStr.length()-1)+"]";//去掉最后一个字符逗号，然后加上]
		} catch (Exception e) {
			return "error";
		}
		return "bandrole";
	}
	
	/**
	 * @author 熊自成
	 * @Description: 给角色绑定菜单
	 * @Date：2014-1-20 上午18:24:08
	 */
	public String save(){
		PrintWriter out=this.getWriter();
		String flag = "";
		String[] ids=menuIds.split("x");
		TPsSysRolemenuRel rolemenurel=new TPsSysRolemenuRel();
		rolemenurel.setRoleid(roleid);
		try {
			sysService.deleteTPsSysRolemenuRel(rolemenurel);
			if (menuIds.length()>1){//当传进来的菜单为空的时候，就只执行清除菜单项！
				rolemenurel.setOptuserid(this.getLoginUserId());
				rolemenurel.setOptusername(this.getLoginUserName());
				rolemenurel.setOptdate(new Date());
				for(String menuid:ids){
					if (ids.length>0){
						rolemenurel.setMenuid(Integer.parseInt(menuid));
						sysService.addTPsSysRolemenuRel(rolemenurel);
					}
				}
			}
			flag = "0000";
		} catch (Exception e) {
			flag = "1111";
			log.error(e.getMessage(), e);
		}finally{
			this.setAjaxMessage(flag);
		}
		return "ajax";
	}
	
	
	
	
	
	
	
	/**
	 * @author 汤建东
	 * @Description: 进入菜单管理页面
	 * @Date：2013-7-12 上午10:08:08
	 * @Return：String
	 */
	public String list() {
		try {
			/** 查询菜单信息分页列表 */
			this.menuInfo.setPageNumber(this.getPageNumber());
			this.menuInfo.setPageSize(20);
			this.menuInfo.setParentid(this.pid); // 父菜单编号
			this.rd = this.sysService.listTPsSysMenuinfoPage(this.menuInfo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
		}
		return "list";
	}

//	/**
//	 * @author 汤建东
//	 * @Description: 查询菜单信息列表
//	 * @Date：2013-7-29 下午5:29:38
//	 * @Return：void
//	 */
//	public String listAjax() {
//		try {
//			if (null == this.pid) {
//				return null;
//			}
//			/** 根据父分类编号查询内容分类信息列表 */
//			TPsSysMenuinfo menu = new TPsSysMenuinfo();
//			menu.setParentid(this.pid); // 父菜单编号
//			this.childMenuList = this.sysService.listTPsSysMenuinfo(menu);
//			// TODO 解决 url 传递中文乱码问题后 可以 省去 查数据库的过程。
//			// 查询父级菜单相关信息
//			TPsSysMenuinfo pMenu = new TPsSysMenuinfo();
//			pMenu.setMid(pid);
//			pMenu = (TPsSysMenuinfo) this.sysService.findTPsSysMenuinfo(pMenu);
//			pname = pMenu.getMname();
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		}
//		return "listAjax";
//	}
	
	/**
	 * 
	 * @author 陈志勇  
	 * @Description: 展现二级菜单
	 * @Date： 2014年2月17日 下午2:55:40
	 * @Return：String
	 */
	public String listSecond() {
		try {
			if (null == this.pid) {
				return null;
			}
			
			/** 查询菜单信息分页列表 */
			this.menuInfo.setPageNumber(this.getPageNumber());
			this.menuInfo.setParentid(this.pid);
			this.menuInfo.setPageSize(20);
			this.menuInfo.setParentid(this.pid); // 父菜单编号
			this.rd = this.sysService.listTPsSysMenuinfoPage(this.menuInfo);
			
			TPsSysMenuinfo pMenu = new TPsSysMenuinfo();
			pMenu.setMid(pid);
			// 查询父级菜单的相关信息, 需要在页面中展现 部门父级菜单信息
			pMenu = (TPsSysMenuinfo) this.sysService.findTPsSysMenuinfo(pMenu);
			pname = pMenu.getMname();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "listSecond";
	}

	/**
	 * @author 汤建东
	 * @Description: 进入创建菜单页面
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public String create() {
		try {
			if (null != this.pid && this.pid > 0) {
				/** 查询菜单信息 */
				TPsSysMenuinfo menu = new TPsSysMenuinfo();
				menu.setMid(this.pid); // 分类编号
				Object m = this.sysService.findTPsSysMenuinfo(menu);
				if (null != m) {
					menu = (TPsSysMenuinfo) m;
					this.pid = menu.getMid();
					this.pname = menu.getMname();
				}
			}
			TPsSysMenuinfo temp = new TPsSysMenuinfo();
			temp.setLevelno(1);
			allMenuList = this.sysService.listTPsSysMenuinfo(temp);
			// 查询所有 一级菜单用于 展现 下拉列表
			for (TPsSysMenuinfo menu : allMenuList) {
				String pre = "";
				for (int i = 0; i < menu.getLevelno(); i++) {
					pre += "--"; // 用于表现级数 
				}
				menu.setMname(pre + menu.getMname());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "create";
	}

	/**
	 * @author 汤建东
	 * @Description: 创建菜单提交
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public void createSub() {
		PrintWriter out = this.getWriter();
		String flag = "";
		try {
			/** 校验信息 */
			if (null == this.pid) {
				flag = "0"; // 父菜单编号为空
				return;
			}
			if (null == this.menuInfo.getMname()
					|| "".equals(this.menuInfo.getMname().trim())) {
				flag = "1"; // 菜单名称为空
				return;
			}
			if (null == this.menuInfo.getMdesc()
					|| "".equals(this.menuInfo.getMdesc().trim())) {
				flag = "2"; // 菜单描述为空
				return;
			}

			/** 根据菜单名称查询菜单信息 */
			TPsSysMenuinfo menu = new TPsSysMenuinfo();
			menu.setMname(this.menuInfo.getMname()); // 菜单名称
			menu.setParentid(this.pid); // 父菜单编号
			Object m = this.sysService.findTPsSysMenuinfo(menu);
			if (null != m) {
				flag = "4"; // 菜单名称已存在
				return;
			}

			/** 查询父菜单信息 */
			Integer levelno = 1; // 菜单级别数
			menu = new TPsSysMenuinfo();
			menu.setMid(this.pid); // 主键(菜单编号)
			m = this.sysService.findTPsSysMenuinfo(menu);
			if (null != m) {
				menu = (TPsSysMenuinfo) m;
				/** 修改菜单信息 */
				if (this.pid > 0) {
					menu.setIsleaf("1"); // 是否有下级节点(1是0否)
					this.sysService.updateTPsSysMenuinfo(menu);
					levelno = menu.getLevelno() + 1;
				}
			}
			// 获取排序的最大值
			TPsSysMenuinfo entityTemp = new TPsSysMenuinfo();
			entityTemp.setParentid(pid);
			Integer maxSort = (Integer) this.sysService
					.findTPsSysMenuinfoMaxMSort(entityTemp);
			if(maxSort == null){
				maxSort = 0;
			}
			/** 保存菜单信息 */
			TPsSysMenuinfo entity = new TPsSysMenuinfo();
			entity.setParentid(this.pid); // 二级菜单
			entity.setMname(this.menuInfo.getMname()); // 菜单名称
			entity.setMdesc(this.menuInfo.getMdesc()); // 菜单描述
			entity.setActionurl(this.menuInfo.getActionurl()); // 链接地址
			entity.setMsort(maxSort + 1); // 排序号
			entity.setIsvalid(this.menuInfo.getIsvalid()); // 是否有效(1是0否)
			entity.setRemark(this.menuInfo.getRemark()); // 备注
			entity.setOptuserid(this.getLoginUserId()); // 操作人编号
			entity.setOptusername(this.getLoginUserName()); // 操作人姓名
			entity.setOptdate(new Date()); // 操作时间
			entity.setIsleaf("0"); // 是否有下级节点(1是0否)
			entity.setLevelno(levelno); // 菜单级别数
			this.sysService.addTPsSysMenuinfo(entity);
			
			flag = "0000";
		} catch (Exception e) {
			flag = "1111"; // 失败
			log.error(e.getMessage(), e);
		} finally {
			out.print(flag);
			out.flush();
			out.close();
		}
	}

	/**
	 * @author 汤建东
	 * @Description: 进入编辑菜单页面
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public String edit() {
		try {
			/** 查询菜单信息 */
			if (null == this.menuInfo || null == this.menuInfo.getMid()) {
				return "error";
			}
			Object m = this.sysService.findTPsSysMenuinfo(this.menuInfo);
			if (null != m) {
				this.menuInfo = (TPsSysMenuinfo) m;
				TPsSysMenuinfo temp = new TPsSysMenuinfo();
				temp.setLevelno(1);
				allMenuList = this.sysService.listTPsSysMenuinfo(temp);
				for (TPsSysMenuinfo menu : allMenuList) {
					String pre = "";
					for (int i = 0; i < menu.getLevelno(); i++) {
						pre += "--";
					}
					menu.setMname(pre + menu.getMname());
				}

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
	 * @Description: 编辑菜单提交
	 * @Date：2013-7-2 下午3:59:24
	 * @Return：String
	 */
	public void editSub() {
		PrintWriter out = this.getWriter();
		String flag = "";
		try {
			/** 校验信息 */
			if (null == this.menuInfo || null == this.menuInfo.getMid()) {
				flag = "0"; // 菜单编号为空
				return;
			}
			if (null == this.menuInfo.getMname()
					|| "".equals(this.menuInfo.getMname().trim())) {
				flag = "1"; // 菜单名称为空
				return;
			}
			if (null == this.menuInfo.getMdesc()
					|| "".equals(this.menuInfo.getMdesc().trim())) {
				flag = "2"; // 菜单描述为空
				return;
			}
			if (null == pid) {
				flag = "3"; // 上级菜单描述为空
				return;
			}

			/** 根据菜单名称查询菜单信息 */
			TPsSysMenuinfo menu = new TPsSysMenuinfo();
			menu.setMid(this.menuInfo.getMid()); // 菜单编号
			Object m = this.sysService.findTPsSysMenuinfo(menu);
			menu = (TPsSysMenuinfo) m;
			if (null != menu) {
				if (pid != menu.getParentid()) {//表示修改了 父级菜单 
					TPsSysMenuinfo menutemp = new TPsSysMenuinfo();
					menutemp.setParentid(pid);
					Integer maxSort = (Integer) this.sysService
							.findTPsSysMenuinfoMaxMSort(menutemp);
					this.menuInfo.setMsort(maxSort + 1);//跳转到另外一个父级菜单中  排序树要为最大
					this.menuInfo.setParentid(pid);
				}
				
				if (!this.menuInfo.getMname().equals(menu.getMname())) {
					TPsSysMenuinfo entity = new TPsSysMenuinfo();
					entity.setMname(this.menuInfo.getMname()); // 菜单名称
					entity.setParentid(this.menuInfo.getParentid()); // 父菜单编号
					m = this.sysService.findTPsSysMenuinfo(entity);
					if (null != m) {
						flag = "4"; // 菜单名称已存在
						return;
					}
				}

				/** 修改菜单信息 */
				this.sysService.updateTPsSysMenuinfo(this.menuInfo);

				/** 如果设置菜单无效时删除角色菜单关联信息 */
				if ("0".equals(this.menuInfo.getIsvalid())) {
					TPsSysRolemenuRel rm = new TPsSysRolemenuRel();
					rm.setMenuid(this.menuInfo.getMid()); // 菜单编号
					this.sysService.deleteTPsSysRolemenuRel(rm);
					if (this.menuInfo.getParentid()==null||this.menuInfo.getParentid() == 0){// TODO 以 pid ==0  来表示 以及菜单 
						TPsSysMenuinfo menut = new TPsSysMenuinfo();
						menut.setParentid(this.menuInfo.getMid());
						List<TPsSysMenuinfo> menuList = sysService.listTPsSysMenuinfo(menut);
						// 遍历子菜单 删除关联信息
						for (TPsSysMenuinfo me : menuList) {
							//将子菜单也设置为 无效
							// me.setIsvalid("0");
							// sysService.updateTPsSysMenuinfo(me);
							rm.setMenuid(me.getMid()); // 菜单编号
							this.sysService.deleteTPsSysRolemenuRel(rm);
						}
					}

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

	/**
	 * 
	 * @author 陈志勇
	 * @Description: 菜单向上排序
	 * @Date： 2014年2月17日 上午11:06:10
	 * @Return：void
	 */
	public void sortUp() {
		PrintWriter out = this.getWriter();
		TPsSysMenuinfo menut = new TPsSysMenuinfo();
		menut.setParentid(pid);
		String flag = "";
		try {
			menuInfo = (TPsSysMenuinfo) this.sysService
					.findTPsSysMenuinfo(menuInfo);//获取当前 菜单
			Integer maxsort = (Integer) this.sysService
					.findTPsSysMenuinfoMaxMSort(menuInfo);//获取 小于当前菜单的 最大排序数
			// 交换排序数
			menut.setMsort(maxsort);
			menut = (TPsSysMenuinfo) this.sysService.findTPsSysMenuinfo(menut);
			menut.setMsort(menuInfo.getMsort());
			menuInfo.setMsort(maxsort);
			this.sysService.updateTPsSysMenuinfo(menut);
			this.sysService.updateTPsSysMenuinfo(menuInfo);
			flag = "0000";
		} catch (Exception e) {
			flag = "1111"; // 失败
			log.error(e.getMessage(), e);
		} finally {
			out.print(flag);
			out.flush();
			out.close();
		}

	}

	/**
	 * 
	 * @author 陈志勇
	 * @Description: 向下排序
	 * @Date： 2014年2月17日 上午11:06:36
	 * @Return：void
	 */
	public void sortDown() {
		PrintWriter out = this.getWriter();
		TPsSysMenuinfo menut = new TPsSysMenuinfo();
		menut.setParentid(pid);
		String flag = "";
		try {
			menuInfo = (TPsSysMenuinfo) this.sysService
					.findTPsSysMenuinfo(menuInfo);// 获取当前 菜单
			Integer minsort = (Integer) this.sysService
					.findTPsSysMenuinfoMinMSort(menuInfo);// 获取大于当前菜单的最小  排序数
			// 交换排序数
			menut.setMsort(minsort);
			menut = (TPsSysMenuinfo) this.sysService.findTPsSysMenuinfo(menut);
			menut.setMsort(menuInfo.getMsort());
			menuInfo.setMsort(minsort);
			this.sysService.updateTPsSysMenuinfo(menut);
			this.sysService.updateTPsSysMenuinfo(menuInfo);
			flag = "0000";
		} catch (Exception e) {
			flag = "1111"; // 失败
			log.error(e.getMessage(), e);
		} finally {
			out.print(flag);
			out.flush();
			out.close();
		}

	}

	//-------------get set-------------------------
	public SysService getSysService() {
		return sysService;
	}

	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	public TPsSysMenuinfo getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(TPsSysMenuinfo menuInfo) {
		this.menuInfo = menuInfo;
	}

	public ReturnData getRd() {
		return rd;
	}

	public void setRd(ReturnData rd) {
		this.rd = rd;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public List getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List childMenuList) {
		this.childMenuList = childMenuList;
	}

	public List<TPsSysMenuinfo> getAllMenuList() {
		return allMenuList;
	}

	public void setAllMenuList(List<TPsSysMenuinfo> allMenuList) {
		this.allMenuList = allMenuList;
	}
	public String getMenuArrayStr() {
		return menuArrayStr;
	}

	public void setMenuArrayStr(String menuArrayStr) {
		this.menuArrayStr = menuArrayStr;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
	
}
