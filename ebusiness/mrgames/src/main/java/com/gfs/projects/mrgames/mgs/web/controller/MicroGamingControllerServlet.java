package com.gfs.projects.mrgames.mgs.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.gfs.projects.common.exception.MGSException;
import com.gfs.projects.common.util.DateUtil;
import com.gfs.projects.mrgames.mgs.facade.MicroGamingServiceFacade;
import com.gfs.projects.mrgames.mgs.message.MethodResponseBean;
import com.gfs.projects.mrgames.mgs.message.PacketBean;
import com.gfs.projects.mrgames.mgs.message.ResultBean;


@WebServlet("/mgs")
public class MicroGamingControllerServlet extends HttpServlet
{
	
	public static final Logger logger =  Logger.getLogger(MicroGamingControllerServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	/**
	 * Handles Request from Client Application / Other Web App
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		MethodResponseBean responseBean = new MethodResponseBean();
		ResultBean resultBean = new ResultBean();
		PacketBean responsePktBean = new PacketBean();
		MicroGamingServiceFacade mgsFacade = new MicroGamingServiceFacade();
		try 
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		    String str = null;
		    StringBuffer xml = new StringBuffer(); 
		    while((str = in.readLine()) != null) {  
		    	xml.append(str);
		    }
		    
		    logger.debug(" REQUEST XML: "  + xml.toString());
		    
		    // Convert XML to PacketBean
			JAXBContext context = JAXBContext.newInstance(PacketBean.class);
			Unmarshaller unMarshaller = context.createUnmarshaller();
			
			PacketBean requestPktBean = (PacketBean) unMarshaller.unmarshal(new StringReader(xml.toString())); 			
		
			resultBean  = mgsFacade.processRequest(requestPktBean.getMethodcall());
			String timeStamp = DateUtil.getCurrentTimeStamp();
			responseBean.setTimestamp(timeStamp);  
			responseBean.setName(requestPktBean.getMethodcall().getName());

		} catch (Exception e) {
			resultBean.setErrorcode(MGSException.ERROR_CODE_UNSPECIFIED);
			resultBean.setErrordescription(e.getMessage());
			logger.error(e);
		}
		
		responseBean.setResult(resultBean);
		responsePktBean.setMethodresponse(responseBean);
		
		PrintWriter writer = response.getWriter();
		String responseXml = responsePktBean.convertToXml(false);
		
		logger.debug(" RESPONSE XML: " + responseXml);
		
		writer.append(responseXml);
		writer.flush();
		writer.close();
	}
}
