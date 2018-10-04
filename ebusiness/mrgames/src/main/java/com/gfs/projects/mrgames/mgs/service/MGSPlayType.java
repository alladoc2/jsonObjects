package com.gfs.projects.mrgames.mgs.service;

public enum MGSPlayType 
{
	BET("bet"), 
	WIN("win"), 
	PROGRESSIVEWIN("progressivewin"), 
	REFUND("refund"), 
	TRANSFER_TO_MGS("transfertomgs"), 
	TRANSFER_FROM_MGS("transferfrommgs"), 
	TOURNAMENT_PURCHASE("tournamentpurchase"), 
	ADMIN("admin");
	
	private String value;
	
	private MGSPlayType (String type) {value = type;}
	
	public String getValue() {
		return value;
	}
	
}
