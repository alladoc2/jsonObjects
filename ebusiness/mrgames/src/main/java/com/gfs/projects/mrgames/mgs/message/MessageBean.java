package com.gfs.projects.mrgames.mgs.message;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public abstract class MessageBean {
	
	public MessageBean convertToObject(String xml) {
		
		MessageBean msgBean = null;
//		XMLInputFactory in = XMLInputFactory.newInstance();
		
		try {
			JAXBContext context = JAXBContext.newInstance(this.getClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			msgBean = (MessageBean) unmarshaller.unmarshal(reader);
			
		} catch (Exception e) {
			// TODO use Logger
			System.out.println(e);
		}
		return msgBean;
	}
	
	public String convertToXml (boolean pretty) {
		String xml = "";
		try {
			JAXBContext context = JAXBContext.newInstance(this.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, pretty);
			
			StringWriter writer = new StringWriter();
			marshaller.marshal(this, writer);
			xml = writer.toString();
		} catch (Exception e) {
			
		}
		return xml;
	}
}
