package com.gfs.projects.mrgames.mgs.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="methodresponse")
@XmlAccessorType(XmlAccessType.FIELD)

public class MethodResponseBean extends MessageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3553526267550030574L;

	@XmlAttribute
	private String name;
	@XmlAttribute
	private String timestamp;
	
	@XmlElement
	private ResultBean result;

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

	public ResultBean getResult() {
		return result;
	}

	public void setResult(ResultBean result) {
		this.result = result;
	}
	
		
}
