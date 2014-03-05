/**
 * @Filename: UserAction.java
 * @author 汤建东
 * @Date：2013-7-2
 */
package com.app.transaction.user.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.app.common.model.ReturnData;
import com.app.module.sys.dto.TPsSysRoleinfo;
import com.app.module.sys.dto.TPsSysRoleuserRel;
import com.app.module.user.dto.TPsSysUserinfo;
import com.app.transaction.common.action.BaseAction;
import com.app.transaction.sys.service.SysService;
import com.app.transaction.user.service.UserService;
import com.app.utils.constant.Constant;
import com.app.utils.constant.MD5;

/**
 * @Class: UserAction.java
 * @Description: 用户信息Action
 * @author 汤建东
 * @Date：2013-7-2
 */
@SuppressWarnings("all")
public class UserAction extends BaseAction {
	private static final long serialVersionUID = 3898545733452341762L;

	@Resource
	private UserService userService;
	@Resource
	private SysService sysService;

}
