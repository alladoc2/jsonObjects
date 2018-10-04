package com.gfs.projects.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static final DateFormat timestampFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.sss");
	
	public static String getCurrentTimeStamp() {
		Date now = new Date();
		return timestampFormat.format(now.getTime());
	}
}
