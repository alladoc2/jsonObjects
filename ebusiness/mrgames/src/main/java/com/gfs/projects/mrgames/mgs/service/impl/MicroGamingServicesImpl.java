package com.gfs.projects.mrgames.mgs.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.gfs.projects.common.bean.UserInfo;
import com.gfs.projects.common.exception.MGSException;
import com.gfs.projects.common.service.UserService;
import com.gfs.projects.common.service.impl.UserServiceJdbcImpl;
import com.gfs.projects.common.util.TokenManagerUtil;
import com.gfs.projects.mrgames.mgs.message.ExtInfoBean;
import com.gfs.projects.mrgames.mgs.message.MethodCallBean;
import com.gfs.projects.mrgames.mgs.message.ResultBean;
import com.gfs.projects.mrgames.mgs.service.MGSPlayerTransService;
import com.gfs.projects.mrgames.mgs.service.MicroGamingServices;
import com.gfs.projects.mrgames.mgs.service.MicroGamingServicesConstant;

public class MicroGamingServicesImpl implements MicroGamingServices {

	public static final Logger logger =  Logger.getLogger(MicroGamingServicesImpl.class);
	
	private UserService userService = new UserServiceJdbcImpl();
	private MGSPlayerTransService playerTrans = new MGSPlayerTransServiceImpl();
	
	public MicroGamingServicesImpl(){}
    

	/**
	 * Login API
	 */
	public ResultBean login(MethodCallBean callBean) 
	{
		logger.info("." + MicroGamingServicesConstant.LOGIN_SERVICE + " >>> start");
		
		long startTime = System.currentTimeMillis();
		
		ResultBean resultBean = new ResultBean();
		
		try {
			
			String loginName = callBean.getCall().getLoginname();
			String password = callBean.getCall().getPassword();
			String tokenString = callBean.getCall().getToken();
			
			boolean isToken = !StringUtils.isEmpty(tokenString);
			
			resultBean.setSeq(callBean.getCall().getSeq());
			resultBean.setEi(new ExtInfoBean());
			
			UserInfo userInfo;
			if (isToken) {
				userInfo = userService.getPlayerInfoByToken(tokenString);
			} else {
				userInfo = userService.getPlayerInfoByLoginNameAndPassword(loginName, password);
			}
			
			if (userInfo == null || TokenManagerUtil.isMGSTokenHasExpired(userInfo)) {
				throw new MGSException(MGSException.ERROR_CODE_TOKEN_EXPIRED, MGSException.MSG_TOKEN_EXPIRED);
			}
			
			String generatedToken = TokenManagerUtil.generateToken();
			userService.updateTokens(userInfo.getUserid(), generatedToken);
			userInfo.setTokenA(generatedToken);
			userInfo.setTokenB(generatedToken);

			// Populate Result with the user information
			resultBean.setCurrency(userInfo.getCurrency());
			resultBean.setCity("");
			resultBean.setCountry("");
			resultBean.setBalance(userInfo.getBalance());
			
			//TODO get the bonus balance
			resultBean.setBonusbalance(0);
			resultBean.setLoginname(userInfo.getLoginname());
			resultBean.setToken(userInfo.getTokenB());
			
		} catch (MGSException e) {
			resultBean.setErrorcode(e.getErrorCode());
			resultBean.setErrordescription(e.getErrorDescription());
			logger.error(e);
		} catch (Exception e) {
			MGSException mgsErrObj = new MGSException(e.getMessage());
			resultBean.setErrorcode(mgsErrObj.getErrorCode());
			resultBean.setErrordescription(mgsErrObj.getErrorDescription());
			logger.error(e);
		} 
		
		logger.info("." + MicroGamingServicesConstant.LOGIN_SERVICE + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		return resultBean;
	}

	
	/**
	 * Get Balance API
	 */
	public ResultBean getBalance(MethodCallBean callBean) 
	{
		logger.info("."  + MicroGamingServicesConstant.GET_BALANCE_SERVICE + " >>> start");
		long startTime = System.currentTimeMillis();
		ResultBean resultBean = new ResultBean();
		
		try {
			String loginName = callBean.getAuth().getLogin();
			String password = callBean.getAuth().getPassword();		
			String token = callBean.getCall().getToken();
						
			resultBean.setSeq(callBean.getCall().getSeq());
			resultBean.setEi(new ExtInfoBean());

			
			boolean isToken = !StringUtils.isEmpty(token);
			
			UserInfo userInfo;
			if (isToken) {
				userInfo = userService.getPlayerInfoByToken(token);
			} else {
				userInfo = userService.getPlayerInfoByLoginNameAndPassword(loginName, password);
			}
			
			if (userInfo == null ) throw new MGSException(MGSException.ERROR_CODE_INVALID_TOKEN, MGSException.MSG_INVALID_PLAYER_TOKEN);
			
			if (TokenManagerUtil.isMGSTokenHasExpired(userInfo)) {
				throw new MGSException(MGSException.ERROR_CODE_TOKEN_EXPIRED, MGSException.MSG_TOKEN_EXPIRED);
			}			
			resultBean.setToken(token);
			resultBean.setBalance(userInfo.getBalance());
			
			resultBean.setBonusbalance(0);
		} catch (MGSException e) {
			resultBean.setErrorcode(e.getErrorCode());
			resultBean.setErrordescription(e.getErrorDescription());
			logger.error(e);			
		} catch (Exception e) {
			resultBean.setErrorcode(MGSException.ERROR_CODE_UNSPECIFIED);
			resultBean.setErrordescription(e.getMessage());
			logger.error(e);
		}
		logger.info("." + MicroGamingServicesConstant.GET_BALANCE_SERVICE + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		
		return resultBean;
	}

	/**
	 * Play Game
	 */
	public ResultBean play(MethodCallBean callBean) 
	{
		logger.info("."  + MicroGamingServicesConstant.PLAY_SERVICE + " >>> start");
		
		long startTime = System.currentTimeMillis();
		ResultBean resultBean = new ResultBean();

		resultBean.setSeq(callBean.getCall().getSeq());
		resultBean.setEi(new ExtInfoBean());
		
		
		try {
			
			if (callBean.getCall().getLoginname() == null) {
				callBean.getCall().setLoginname(callBean.getAuth().getLogin());
			}
			
			if (callBean.getCall().getPassword() == null ) {
				callBean.getCall().setPassword(callBean.getAuth().getPassword());
			
			}
			
			UserInfo userInfo = null;
			
			// Check Token
			String token = callBean.getCall().getToken();
			boolean isOffline = callBean.getCall().isOffline();
			if (isOffline) {
				String[] tokenArray = token.split("_");
				if (tokenArray.length > 0) {
					userInfo = userService.getPlayerInfoByLoginName(tokenArray[0]);
				}
			} else {
				userInfo = userService.getPlayerInfoByToken(token);
			}
			
			if (userInfo == null ) throw new MGSException(MGSException.ERROR_CODE_INVALID_TOKEN, MGSException.MSG_INVALID_PLAYER_TOKEN);
			
			if (TokenManagerUtil.isMGSTokenHasExpired(userInfo)) {
				throw new MGSException(MGSException.ERROR_CODE_TOKEN_EXPIRED, MGSException.MSG_TOKEN_EXPIRED);
			}
			resultBean.setToken(token);
			
			playerTrans.processPlayerTransaction(userInfo, callBean.getCall(), resultBean);
			
		} catch (MGSException e) {
			resultBean.setErrorcode(e.getErrorCode());
			resultBean.setErrordescription(e.getErrorDescription());
			logger.error(e);			
		} catch (Exception e) {
			resultBean.setErrorcode(MGSException.ERROR_CODE_UNSPECIFIED);
			resultBean.setErrordescription(e.getMessage());
			logger.error(e);
		}
		
		logger.info("." + MicroGamingServicesConstant.PLAY_SERVICE + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		
		return resultBean;
	}

	
	public ResultBean endGame(MethodCallBean callBean) {
		logger.info("."  + MicroGamingServicesConstant.END_GAME_SERVICE + " >>> start");
		long startTime = System.currentTimeMillis();
		ResultBean resultBean = new ResultBean();
		
		try {
			
			String token = callBean.getCall().getToken();
			UserInfo userInfo;
			userInfo = userService.getPlayerInfoByToken(token);
			
			resultBean.setSeq(callBean.getCall().getSeq());
			resultBean.setEi(new ExtInfoBean());

			if (userInfo == null) {
				throw new MGSException(MGSException.ERROR_CODE_LOGIN_VALIDATION_FAILED, MGSException.CANNOT_VALIDATE_TOKEN);
			}							
			
			if (TokenManagerUtil.isMGSTokenHasExpired(userInfo)) {
			//	throw new MGSException(MGSException.ERROR_CODE_TOKEN_EXPIRED, MGSException.MSG_TOKEN_EXPIRED);
			}
			
			resultBean.setBalance(userInfo.getBalance());
			resultBean.setToken(userInfo.getTokenB());
			resultBean.setBonusbalance(0);
		} catch (MGSException e) {
			resultBean.setErrorcode(e.getErrorCode());
			resultBean.setErrordescription(e.getErrorDescription());
			logger.error(e);			
		} catch (Exception e) {
			resultBean.setErrorcode(MGSException.ERROR_CODE_UNSPECIFIED);
			resultBean.setErrordescription(e.getMessage());
			logger.error(e);
		}
		
		logger.info("." + MicroGamingServicesConstant.END_GAME_SERVICE + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		
		return resultBean;
	}

	/**
	 * Refresh Token
	 */
	public ResultBean refreshToken(MethodCallBean callBean) 
	{
		logger.info("."  + MicroGamingServicesConstant.REFRESH_TOKEN_SERVICE + " >>> start");
		long startTime = System.currentTimeMillis();
		ResultBean resultBean = new ResultBean();
		
		try {
			
			String token = callBean.getCall().getToken();
			UserInfo userInfo;
			userInfo = userService.getPlayerInfoByToken(token);
			
			resultBean.setSeq(callBean.getCall().getSeq());
			resultBean.setEi(new ExtInfoBean());

			if (userInfo == null) {
				throw new MGSException(MGSException.ERROR_CODE_LOGIN_VALIDATION_FAILED, MGSException.CANNOT_VALIDATE_TOKEN);
			}							
			
			if (TokenManagerUtil.isMGSTokenHasExpired(userInfo)) {
				throw new MGSException(MGSException.ERROR_CODE_TOKEN_EXPIRED, MGSException.MSG_TOKEN_EXPIRED);
			}
			
			String newToken = TokenManagerUtil.generateToken();
			userService.updateTokens(userInfo.getUserid(), newToken);
			resultBean.setToken(newToken);
		} catch (MGSException e) {
			resultBean.setErrorcode(e.getErrorCode());
			resultBean.setErrordescription(e.getErrorDescription());
			logger.error(e);			
		} catch (Exception e) {
			resultBean.setErrorcode(MGSException.ERROR_CODE_UNSPECIFIED);
			resultBean.setErrordescription(e.getMessage());
			logger.error(e);
		}
		
		logger.info("." + MicroGamingServicesConstant.REFRESH_TOKEN_SERVICE + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		
		return resultBean;
	}

}
