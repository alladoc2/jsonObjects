package com.gfs.projects.mrgames.mgs.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="pkt")
@XmlAccessorType(XmlAccessType.FIELD)
public class PacketBean extends MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6562874844350349569L;
	@XmlElement
	private MethodResponseBean methodresponse;
	@XmlElement
	private MethodCallBean methodcall;
	
	public MethodResponseBean getMethodresponse() {
		return methodresponse;
	}
	public void setMethodresponse(MethodResponseBean methodresponse) {
		this.methodresponse = methodresponse;
	}
	public MethodCallBean getMethodcall() {
		return methodcall;
	}
	public void setMethodcall(MethodCallBean methodcall) {
		this.methodcall = methodcall;
	}
	
	

}
