package com.gfs.projects.mrgames.mgs.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.gfs.projects.common.bean.UserInfo;
import com.gfs.projects.common.db.ConnectionPool;
import com.gfs.projects.common.exception.MGSException;
import com.gfs.projects.mrgames.mgs.message.CallBean;
import com.gfs.projects.mrgames.mgs.message.ResultBean;
import com.gfs.projects.mrgames.mgs.service.MGSPlayerTransService;

public class MGSPlayerTransServiceImpl implements MGSPlayerTransService {

	public static final Logger logger =  Logger.getLogger(MGSPlayerTransServiceImpl.class);
	
	/**
	 * Store player transaction in database
	 */
	public String storeTransaction(CallBean callBean) throws MGSException 
	{
		String methodName = ".storeTransaction";
		
		logger.info(methodName + " >>> Start");
		long startTime = System.currentTimeMillis();
		Connection conn = ConnectionPool.getInstanceForMSSQLDBConnection();
		
		String playType = callBean.getPlaytype();
		int gameId = callBean.getGameid();
		String gameReference = callBean.getGamereference();
		int actionId = callBean.getActionid();
		String actionDesc = callBean.getActiondesc();
		int amount = callBean.getAmount();
		boolean start = callBean.isStart();
		boolean finish = callBean.isFinish();
		boolean offline = callBean.isOffline();
		String currency = callBean.getCurrency();
		String seq = callBean.getSeq();
		String token = callBean.getToken();
		String extInfo = "";
		String freeGame = callBean.getFreegame();
		String id = null;
		
		try {
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("Insert INTO T_MGS_PlayerTrans (trans_id, play_type, game_id, game_reference, action_id, action_desc, amount, start, finish, offline, currency, seq, token, ext_info, free_game, loginname) ");
			sqlSb.append("OUTPUT Inserted.trans_id ");
			sqlSb.append("Values (newid(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(1, playType);
			ps.setInt(2, gameId);
			ps.setString(3, gameReference);
			ps.setInt(4, actionId);
			ps.setString(5, actionDesc);
			ps.setInt(6, amount);
			ps.setBoolean(7, start);
			ps.setBoolean(8, finish);
			ps.setBoolean(9, offline);
			ps.setString(10, currency);
			ps.setString(11, seq);
			ps.setString(12, token);
			ps.setString(13, extInfo);
			ps.setString(14, freeGame);
			ps.setString(15,  callBean.getLoginname());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getString("trans_id");
			}
			conn.commit();
		} catch (SQLException e) {
			throw new MGSException (e.getMessage());
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");		
		return id;
	}
	
	
	/**
	 * Update the player balance as per play type
	 * @param userId
	 * @param callBean
	 * @throws MGSException
	 */
	public void updatePlayerBalance(UserInfo userInfo, CallBean callBean) throws MGSException 
	{
		String methodName = ".updatePlayerBalance";
		logger.info(methodName + " >>> Start");
		long startTime = System.currentTimeMillis();
		Connection conn = ConnectionPool.getInstanceForMSSQLDBConnection();
		

		
		
		int amount = getAmountByPlayType(callBean);
		logger.info(methodName + " Amount : " + amount); 
		
		try {
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("UPDATE T_MGS_PlayerInfo ");
			sqlSb.append("SET balance = balance + (?) ");
			sqlSb.append("WHERE id = ? ");
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setInt(1, amount);
			ps.setString(2, userInfo.getUserid());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			throw new MGSException (e.getMessage());
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");		
	}	
	
	
	public boolean isPlayerTransAlreadyExist (CallBean callBean) throws MGSException 
	{
		String methodName = ".isPlayerTransactionExist";
		logger.info(methodName + " >>> Start");
		
		long startTime = System.currentTimeMillis();
		Connection conn = ConnectionPool.getInstanceForMSSQLDBConnection();
				
		String playType = callBean.getPlaytype();
		int gameId = callBean.getGameid();
		String gameRef = callBean.getGamereference();
		int actionId = callBean.getActionid();
		String loginName = callBean.getLoginname();
		boolean isExist = false;
		
		try {
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("Select * from T_MGS_PlayerTrans ");
			sqlSb.append("Where game_id = ? and play_type = ? and game_reference = ? and action_id = ? and loginname = ? ");
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(2, playType);
			ps.setInt(1, gameId);
			ps.setString(3, gameRef);
			ps.setInt(4, actionId);
			ps.setString(5, loginName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) isExist = true;
			
		} catch (SQLException e) {
			throw new MGSException (e.getMessage());
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + " " +  ((System.currentTimeMillis()-startTime)/1000) + " seconds "+ isExist);		
		return isExist;
	}	
	
	/**
	 * Convert amount as per play type
	 * @param callBean
	 * @return
	 * @throws MGSException
	 */
	public int getAmountByPlayType(CallBean callBean) throws MGSException 
	{
		String methodName = ".getAmountByPlayType";
		long startTime = System.currentTimeMillis();
		String playType = callBean.getPlaytype();
		int amount = callBean.getAmount();
		logger.info(methodName + " >>> Start");
		
		try {

			// Debit Balance
			if (BET.equalsIgnoreCase(playType) 
					|| TRANSFER_TO_MGS.equalsIgnoreCase(playType)
					|| TOURNAMENT_PURCHASE.equalsIgnoreCase(playType)) 
			{
				return -1 * amount;
			} 
			
			// Credit Balance
			else if (WIN.equalsIgnoreCase(playType) 
					|| PROGRESSIVE_WIN.equalsIgnoreCase(playType)
					|| REFUND.equalsIgnoreCase(playType)
					|| TRANSFER_FROM_MGS.equalsIgnoreCase(playType)) 
			{
				return amount;
			}
			
			
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		}
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");		
		return amount;
	}


	public void processPlayerTransaction(UserInfo userInfo, CallBean callBean, ResultBean resultBean) throws MGSException 
	{
		String methodName = "processPlayerTransaction";
		long startTime = System.currentTimeMillis();
		
		String transId = getPlayerTransId(callBean);
		if (transId == null) 
		{
			
			transId = storeTransaction(callBean);
			if (REFUND.equalsIgnoreCase(callBean.getPlaytype()) 
					&& isNonExistentBet(callBean.getToken(), callBean.getGameid(), callBean.getActionid(), callBean.getGamereference(), callBean.getLoginname())) {
				// do nothing
			} else {
				userInfo.setBalance(userInfo.getBalance() + (getAmountByPlayType(callBean)));
				updatePlayerBalance(userInfo, callBean);
			}
		}
		resultBean.setExttransactionid(transId);
		resultBean.setBalance(userInfo.getBalance());
		
		//TODO currently not used.
		resultBean.setBonusbalance(0);
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds " );	
	}




	public String getPlayerTransId(CallBean callBean) throws MGSException {
		String methodName = ".getPlayerTransId";
		logger.info(methodName + " >>> Start");
		
		long startTime = System.currentTimeMillis();
		Connection conn = ConnectionPool.getInstanceForMSSQLDBConnection();
				
		int gameId = callBean.getGameid();
		String gameRef = callBean.getGamereference();
		int actionId = callBean.getActionid();
		String loginName = callBean.getLoginname();
		String playType = callBean.getPlaytype();
		String transId = null;
		
		try {
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("Select trans_id from T_MGS_PlayerTrans ");
			sqlSb.append("Where game_id = ? and play_type = ? and game_reference = ? and action_id = ? and loginname = ? ");
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setInt(1, gameId);
			ps.setString(2, playType);
			ps.setString(3, gameRef);
			ps.setInt(4, actionId);
			ps.setString(5, loginName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				transId = rs.getString("trans_id");
			}
			
		} catch (SQLException e) {
			throw new MGSException (e.getMessage());
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end "  + ((System.currentTimeMillis()-startTime)/1000) + " seconds "+ transId);		
		return transId;	
	}

	public boolean isNonExistentBet(String liveToken, int gameId, int actionId, String gameReference, String loginName) throws MGSException 
	{
		String methodName = ".isNonExistentBet";
		logger.info(methodName + " >>> Start");
		
		long startTime = System.currentTimeMillis();
		Connection conn = ConnectionPool.getInstanceForMSSQLDBConnection();
		
		int noOfTrans = 0;
		try {
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("Select count(trans_id) as no_of_trans from T_MGS_PlayerTrans ");
			sqlSb.append("Where play_type = 'bet' and action_id = ? and token = ? and game_id = ? and loginname = ?");
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setInt(1, actionId);
			ps.setString(2, liveToken);
			ps.setInt(3, gameId);
			ps.setString(4, loginName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				noOfTrans = rs.getInt("no_of_trans");
			} 
			
		} catch (SQLException e) {
			throw new MGSException (e.getMessage());
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end "  + ((System.currentTimeMillis()-startTime)/1000) + " seconds "+ noOfTrans);		
		return noOfTrans <= 0;	
	}
	
	
}
