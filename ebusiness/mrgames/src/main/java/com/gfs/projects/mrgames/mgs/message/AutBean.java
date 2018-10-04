package com.gfs.projects.mrgames.mgs.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="auth")
@XmlAccessorType(XmlAccessType.FIELD)
public class AutBean extends MessageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8078893224244220558L;
	@XmlAttribute
	String login;
	@XmlAttribute
	String password;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
