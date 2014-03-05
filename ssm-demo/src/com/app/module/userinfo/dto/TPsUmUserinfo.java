package com.app.module.userinfo.dto;

import java.util.Date;

import com.app.common.model.EntityBase;

/**
 * @ClassName: TPsUmUserinfo
 * @Description: 用户信息
 * @author:张蜜
 * @date: 2014-2-20 下午2:50:33
 */
public class TPsUmUserinfo extends EntityBase{
	private static final long serialVersionUID = -6066661123789370361L;
	private Integer id;//主键(用户编号)
	private String username;//用户名
	private String realname;//真实姓名
	private String userpic;//用户图像
	private String mobile;//联系方式-手机
	private String paperstype;//证件类型(0身份证1护照2军官证)
	private String papersno;//证件号码
	private String email;//邮箱地址
	private String qq;//qq号码
	private String birthday;//生日
	private String usex;//性别(1男2女3保密)
	private String province;//所在省
	private String city;//所在城市
	private String area;//所在区域
	private String address;//详细地址
	private String postcard;//邮政编码
	private String isbindtel;//是否绑定手机(1是0否)
	private String isbindemail;//是否绑定邮箱(1是0否)
	private String isbandpapers;//是否已身份认证(1是0否)
	private Date regdate;//注册时间
	private String starttime;
	private String endtime;
	

	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPaperstype() {
		return paperstype;
	}
	public void setPaperstype(String paperstype) {
		this.paperstype = paperstype;
	}
	public String getPapersno() {
		return papersno;
	}
	public void setPapersno(String papersno) {
		this.papersno = papersno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcard() {
		return postcard;
	}
	public void setPostcard(String postcard) {
		this.postcard = postcard;
	}
	public String getIsbindtel() {
		return isbindtel;
	}
	public void setIsbindtel(String isbindtel) {
		this.isbindtel = isbindtel;
	}
	public String getIsbindemail() {
		return isbindemail;
	}
	public void setIsbindemail(String isbindemail) {
		this.isbindemail = isbindemail;
	}
	public String getIsbandpapers() {
		return isbandpapers;
	}
	public void setIsbandpapers(String isbandpapers) {
		this.isbandpapers = isbandpapers;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
