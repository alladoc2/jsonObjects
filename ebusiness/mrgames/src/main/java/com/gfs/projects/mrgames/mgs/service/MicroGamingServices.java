package com.gfs.projects.mrgames.mgs.service;

import com.gfs.projects.mrgames.mgs.message.MethodCallBean;
import com.gfs.projects.mrgames.mgs.message.ResultBean;

public interface MicroGamingServices {
	public ResultBean login(MethodCallBean callBean);
	public ResultBean getBalance(MethodCallBean callBean);
	public ResultBean play(MethodCallBean callBean);
	public ResultBean endGame(MethodCallBean callBean);
	public ResultBean refreshToken(MethodCallBean callBean);
}
