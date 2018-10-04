package com.gfs.projects.mrgames.mgs.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="methodcall")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodCallBean extends MessageBean {
	

	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String timestamp;
	
	@XmlAttribute
	private String system;

	@XmlElement
	CallBean call;
	
	@XmlElement
	AutBean auth;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public CallBean getCall() {
		return call;
	}

	public void setCall(CallBean call) {
		this.call = call;
	}

	public AutBean getAuth() {
		return auth;
	}

	public void setAuth(AutBean auth) {
		this.auth = auth;
	}

	
}
