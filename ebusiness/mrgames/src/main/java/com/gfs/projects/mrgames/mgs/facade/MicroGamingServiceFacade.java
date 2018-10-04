package com.gfs.projects.mrgames.mgs.facade;

import com.gfs.projects.common.exception.MGSException;
import com.gfs.projects.mrgames.mgs.message.MethodCallBean;
import com.gfs.projects.mrgames.mgs.message.ResultBean;
import com.gfs.projects.mrgames.mgs.service.MicroGamingServicesConstant;
import com.gfs.projects.mrgames.mgs.service.impl.MicroGamingServicesImpl;
import com.gfs.projects.mrgames.mgs.service.MicroGamingServices;

/**
 * 
 * @author jojo
 *
 */
public class MicroGamingServiceFacade {
	
	/**
	 * Main method that will handle MGS Request
	 */
	public ResultBean processRequest (MethodCallBean callBean) 
	{
		ResultBean resultBean = new ResultBean();
		MicroGamingServices services = new MicroGamingServicesImpl();
		if (MicroGamingServicesConstant.LOGIN_SERVICE.equalsIgnoreCase(callBean.getName())) {
			resultBean = services.login(callBean);
			
		} else if (MicroGamingServicesConstant.GET_BALANCE_SERVICE.equalsIgnoreCase(callBean.getName())) {
			resultBean =  services.getBalance(callBean);
			
		} else if (MicroGamingServicesConstant.PLAY_SERVICE.equalsIgnoreCase(callBean.getName())) {
			resultBean =  services.play(callBean);
			
		} else if (MicroGamingServicesConstant.END_GAME_SERVICE.equalsIgnoreCase(callBean.getName())) {
			resultBean =  services.endGame(callBean);
			
		} else if (MicroGamingServicesConstant.REFRESH_TOKEN_SERVICE.equalsIgnoreCase(callBean.getName())) {
			resultBean = services.refreshToken(callBean);
			
		} else {
			resultBean.setErrorcode(MGSException.ERROR_CODE_UNSPECIFIED);
			resultBean.setErrordescription("Unsupported method call = " + callBean.getName());
		}
		return resultBean;
	}	
}
