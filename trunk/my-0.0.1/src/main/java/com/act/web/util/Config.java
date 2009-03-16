
package com.act.web.util;

public class Config {

	private static String sysEncoding = "GBK";

	public static String ajaxEncoding = "UTF-8";

	public static String SESSION_NAME = "userSession";
	
	public static String COOKIE_NAME = "EXPOPO_CLIENT_DATA";
	
	public static String HIT_OBJECT_ID = "hitObjId";

	public static String APPLY_TYPE_INDENT = "_apply";

	public static String SESSION_SETTING = "userSettingSession";

	public static String SESSION_PREMISSION = "userPremission";

	public static String SESSION_PREMISSION_CHILDREN = "childrens";

	public static String SESSION_CAPTCHA = "captchaValue";

	public static String getSysEncoding() {
		// TODO Auto-generated method stub
		return sysEncoding;
	}
	
	public static String ONLINE_PATH ;
	public static void setOnlinePath(String online_path) {
		ONLINE_PATH = online_path;
	}
}
