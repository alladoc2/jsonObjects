package com.gfs.projects.mrgames.mgs.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="call")
@XmlAccessorType(XmlAccessType.FIELD)
public class CallBean extends MessageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3032556278527730109L;
	@XmlAttribute
	private String seq;
	@XmlAttribute
	private String playtype;
	@XmlAttribute
	private String token;
	@XmlAttribute
	private int gameid;
	@XmlAttribute
	private String gamereference;
	@XmlAttribute
	private int actionid;
	@XmlAttribute
	private String actiondesc;
	@XmlAttribute
	private int amount;
	@XmlAttribute
	private boolean start;
	@XmlAttribute
	private boolean finish;
	@XmlAttribute
	private boolean offline;
	@XmlAttribute
	private String currency;
	@XmlAttribute
	private String freegame;
	@XmlAttribute
	private String loginname;
	@XmlAttribute
	private String password;
	
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

	public String getPlaytype() {
		return playtype;
	}

	public void setPlaytype(String playtype) {
		this.playtype = playtype;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getGamereference() {
		return gamereference;
	}

	public void setGamereference(String gamereference) {
		this.gamereference = gamereference;
	}

	public int getActionid() {
		return actionid;
	}

	public void setActionid(int actionid) {
		this.actionid = actionid;
	}

	public String getActiondesc() {
		return actiondesc;
	}

	public void setActiondesc(String actiondesc) {
		this.actiondesc = actiondesc;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public boolean isOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFreegame() {
		return freegame;
	}

	public void setFreegame(String freegame) {
		this.freegame = freegame;
	}
	
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}	 
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	 
	
}
