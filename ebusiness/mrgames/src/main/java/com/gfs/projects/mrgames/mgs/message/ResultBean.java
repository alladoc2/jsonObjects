package com.gfs.projects.mrgames.mgs.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultBean extends MessageBean {

	@XmlAttribute
	private String seq;
	@XmlAttribute
	private String token;
	@XmlAttribute
	private String loginname;
	@XmlAttribute
	private String currency;
	@XmlAttribute
	private String city;
	@XmlAttribute
	private String country;
	@XmlAttribute
	private Integer balance;
	@XmlAttribute
	private Integer bonusbalance;
	@XmlAttribute
	private String wallet;
	@XmlAttribute
	private String type;
	@XmlAttribute
	private String exttransactionid;
	@XmlAttribute
	private String ipaddress;
	@XmlAttribute
	private String errorcode;
	@XmlAttribute
	private String errordescription;
	
	@XmlElementRef
	private ExtInfoBean ei;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getBonusbalance() {
		return bonusbalance;
	}
	public void setBonusbalance(Integer bonusbalance) {
		this.bonusbalance = bonusbalance;
	}
	public ExtInfoBean getEi() {
		return ei;
	}
	public void setEi(ExtInfoBean ei) {
		this.ei = ei;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrordescription() {
		return errordescription;
	}
	public void setErrordescription(String errordescription) {
		this.errordescription = errordescription;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExttransactionid() {
		return exttransactionid;
	}
	public void setExttransactionid(String exttransactionid) {
		this.exttransactionid = exttransactionid;
	}
	
}
