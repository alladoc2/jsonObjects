package com.gfs.projects.mrgames.mgs.service;

import com.gfs.projects.common.bean.UserInfo;
import com.gfs.projects.common.exception.MGSException;
import com.gfs.projects.mrgames.mgs.message.CallBean;
import com.gfs.projects.mrgames.mgs.message.ResultBean;

public interface MGSPlayerTransService {

	public static final String BET = "bet";
	public static final String WIN = "win";
	public static final String PROGRESSIVE_WIN = "progressivewin";
	public static final String REFUND = "refund";
	public static final String TRANSFER_TO_MGS = "transformtomgs";
	public static final String TRANSFER_FROM_MGS = "transformfrommgs";
	public static final String TOURNAMENT_PURCHASE = "tournamentpurchase";
	public static final String ADMIN = "admin";
	
	
	public String storeTransaction(CallBean callBean) throws MGSException;
	public void updatePlayerBalance (UserInfo userInfo, CallBean callBean) throws MGSException;
	public int getAmountByPlayType(CallBean callBean) throws MGSException;
	public void processPlayerTransaction(UserInfo userInfo, CallBean callBean, ResultBean resultBean) throws MGSException;
	public boolean isPlayerTransAlreadyExist(CallBean callBean) throws MGSException;
	public String getPlayerTransId(CallBean callBean) throws MGSException;
}
