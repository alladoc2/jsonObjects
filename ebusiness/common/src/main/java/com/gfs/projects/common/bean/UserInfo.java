package com.gfs.projects.common.bean;

import java.math.BigDecimal;

public class UserInfo {

	//UUID
	private String userid="";
	private String loginname = "";
	private String userpassword = "";
	private String username;
	
	private String birthday;
	private String byear;
	private String bmonth;
	private String bday;
	
	private String sex;
	private String email;
	private String mobile;
	private String registertime;
	private Integer status;
	private String registerip;
	private Integer visits;
	//?
	private Integer loginnum;
	//余额
	private int balance;
	//冻结
	private Long frozenamount=0l;
	private Long totalwithdrawal=0l;
	//货币种类
	private String currency;  
	//?
	private Integer errorloginnum;
	//?
	private String risk_level;
	//最后存款时间
	private String lastdeposittime;
	private String lastloginip;
	private String lastlogintime;
	
	private String updateby;
	private String updatetime;
	private String updateip;
	private BigDecimal totaldeposit;
	
	private Integer depositnum;
	//
	private String vendorid="3";
	
	private String method;
	
	private int userlevel=-1;
	
	private int lockhg;
	private int lock188;
	private int lockea;
	private int lockbb;
	private int lockkeno;
	private int lockag;
	private int lockgame;
	
	
	private boolean emailstatus;
	private boolean mobilestatus;
	private String agentcode;
	private String pwd;
	
	private int rc_id;
	
	private String tokenA;
	private String tokenB;
	private long tokenExpiry;
	
	public long getTokenExpiry() {
		return tokenExpiry;
	}

	public void setTokenExpiry(long tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}

	public BigDecimal getTotaldeposit() {
		return totaldeposit.setScale( 2 , BigDecimal.ROUND_DOWN );
	
	}

	public void setTotaldeposit( BigDecimal totaldeposit ) {
		this.totaldeposit = totaldeposit;
	}

	
	
	public Integer getDepositnum() {
		return depositnum;
	}

	public void setDepositnum( Integer depositnum ) {
		this.depositnum = depositnum;
	}

	public int getRc_id() {
		return rc_id;
	}

	public void setRc_id(int rc_id) {
		this.rc_id = rc_id;
	}

	public String getAgentcode() {
		return agentcode;
	}

	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}

	private int hgtest=1;
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getByear() {
		return byear;
	}

	public void setByear(String byear) {
		this.byear = byear;
	}

	public String getBmonth() {
		return bmonth;
	}

	public void setBmonth(String bmonth) {
		this.bmonth = bmonth;
	}

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRegisterip() {
		return registerip;
	}

	public void setRegisterip(String registerip) {
		this.registerip = registerip;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Integer getLoginnum() {
		return loginnum;
	}

	public void setLoginnum(Integer loginnum) {
		this.loginnum = loginnum;
	}


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getErrorloginnum() {
		return errorloginnum;
	}

	public void setErrorloginnum(Integer errorloginnum) {
		this.errorloginnum = errorloginnum;
	}

	public String getRisk_level() {
		return risk_level;
	}

	public void setRisk_level(String risk_level) {
		this.risk_level = risk_level;
	}

	
	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}


	public String getUpdateip() {
		return updateip;
	}

	public void setUpdateip(String updateip) {
		this.updateip = updateip;
	}

	public String getVendorid() {
		return vendorid;
	}

	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Long getFrozenamount() {
		return frozenamount;
	}

	public void setFrozenamount(Long frozenamount) {
		this.frozenamount = frozenamount;
	}

	public Long getTotalwithdrawal() {
		return totalwithdrawal;
	}

	public void setTotalwithdrawal(Long totalwithdrawal) {
		this.totalwithdrawal = totalwithdrawal;
	}

	public int getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(int userlevel) {
		this.userlevel = userlevel;
	}
	
	public int getLockhg() {
		return lockhg;
	}

	public void setLockhg(int lockhg) {
		this.lockhg = lockhg;
	}

	public int getLock188() {
		return lock188;
	}

	public void setLock188(int lock188) {
		this.lock188 = lock188;
	}

	public int getLockea() {
		return lockea;
	}

	public void setLockea(int lockea) {
		this.lockea = lockea;
	}

	public boolean isEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(boolean emailstatus) {
		this.emailstatus = emailstatus;
	}

	public boolean isMobilestatus() {
		return mobilestatus;
	}

	public void setMobilestatus(boolean mobilestatus) {
		this.mobilestatus = mobilestatus;
	}

	public int getHgtest() {
		return hgtest;
	}

	public void setHgtest(int hgtest) {
		this.hgtest = hgtest;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public String getLastdeposittime() {
		return lastdeposittime;
	}

	public void setLastdeposittime(String lastdeposittime) {
		this.lastdeposittime = lastdeposittime;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public int getLockbb() {
		return lockbb;
	}

	public void setLockbb(int lockbb) {
		this.lockbb = lockbb;
	}

	public int getLockkeno() {
		return lockkeno;
	}

	public void setLockkeno(int lockkeno) {
		this.lockkeno = lockkeno;
	}

	public int getLockag() {
		return lockag;
	}

	public void setLockag(int lockag) {
		this.lockag = lockag;
	}

	public int getLockgame() {
		return lockgame;
	}

	public void setLockgame(int lockgame) {
		this.lockgame = lockgame;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	

	public String getTokenA() {
		return tokenA;
	}

	public void setTokenA(String tokenA) {
		this.tokenA = tokenA;
	}

	public String getTokenB() {
		return tokenB;
	}

	public void setTokenB(String tokenB) {
		this.tokenB = tokenB;
	}

	@Override
	public String toString() {
		return "UserItem [userid=" + userid + ", loginname=" + loginname
				+ ", userpassword=" + userpassword + ", username=" + username
				+ ", birthday=" + birthday + ", byear=" + byear + ", bmonth="
				+ bmonth + ", bday=" + bday + ", sex=" + sex + ", email="
				+ email + ", mobile=" + mobile + ", registertime="
				+ registertime + ", status=" + status + ", registerip="
				+ registerip + ", visits=" + visits + ", loginnum=" + loginnum
				+ ", balance=" + balance + ", frozenamount=" + frozenamount
				+ ", totalwithdrawal=" + totalwithdrawal + ", currency="
				+ currency + ", errorloginnum=" + errorloginnum
				+ ", risk_level=" + risk_level + ", lastdeposittime="
				+ lastdeposittime + ", lastloginip=" + lastloginip
				+ ", lastlogintime=" + lastlogintime + ", updateby=" + updateby
				+ ", updatetime=" + updatetime + ", updateip=" + updateip
				+ ", vendorid=" + vendorid + ", method=" + method
				+ ", userlevel=" + userlevel + ", lockhg=" + lockhg
				+ ", lock188=" + lock188 + ", lockea=" + lockea + ", lockbb="
				+ lockbb + ", lockkeno=" + lockkeno + ", lockag=" + lockag
				+ ", lockgame=" + lockgame + ", emailstatus=" + emailstatus
				+ ", mobilestatus=" + mobilestatus + ", agentcode=" + agentcode
				+ ", pwd=" + pwd + ", hgtest="
				+ hgtest + "]";
	}

}
