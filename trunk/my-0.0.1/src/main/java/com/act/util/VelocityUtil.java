
package com.act.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class VelocityUtil {

	public static StringUtil stringUtil = new StringUtil();

	public static DateUtil dateUtil = new DateUtil();

	public static ArrayUtil arrayUtil = new ArrayUtil();

	private static VelocityUtil instance = new VelocityUtil();

	private VelocityUtil() {
	}

	public static VelocityUtil getInstance() {
		return instance;
	}

	public static StringUtil getStringUtil() {
		return stringUtil;
	}

	public static DateUtil getDateUtil() {
		return dateUtil;
	}

	public static ArrayUtil getArrayUtil() {
		return arrayUtil;
	}

	public static Object getElementAt(Object list, Integer x) {
		return getElementAt(list, x, null);
	}

	public static Object getElementAt(Object list, Integer x, Object def) {
		try {
			int i = x.intValue();
			Object o = null;
			if (list instanceof Object[]) {
				if (i < ((Object[]) list).length) {
					o = ((Object[]) list)[i];
				}
			}
			if (list instanceof int[]) {
				if (i < ((int[]) list).length) {
					o = new Integer(((int[]) list)[i]);
				}
			}
			if (list instanceof String[]) {
				if (i < ((String[]) list).length) {
					o = new Integer(((String[]) list)[i]);
				}
			}
			if (list instanceof List) {
				if (i < ((List) list).size()) {
					o = ((List) list).get(i);
				}
			}
			return (o == null) ? def : o;
		} catch (Exception e) {
		}
		return def;
	}

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	public static int getSize(Object obj) {
		if (obj instanceof Object[])
			return ((Object[]) obj).length;
		if (obj instanceof int[])
			return ((int[]) obj).length;
		if (obj instanceof String[])
			return ((String[]) obj).length;
		if (obj instanceof List)
			return ((List) obj).size();
		return -1;
	}

	public static String summarize(String str, int len) {
		if (str == null) {
			return "";
		}
		str = str.trim();
		char[] chars = str.toCharArray();
		len = Math.min(chars.length, len);
		StringBuffer sb = new StringBuffer(len);
		int n = 0;
		int i = 0;
		char c = 0;
		int tags1 = 0;
		int tags2 = 0;
		while (n < len && i < str.length()) {
			c = chars[i++];
			switch (c) {
				case '<':
					tags1++;
					continue;
				case '>':
					if (tags1 > 0)
						tags1--;
					continue;
				case '[':
					tags2++;
					continue;
				case ']':
					if (tags2 > 0)
						tags2--;
					continue;
				case ',':
				case '\n':
					tags1 = 0;
					tags2 = 0;
					continue;
				default:
			}
			if (tags1 == 0 && tags2 == 0) {
				sb.append(c);
				n++;
			}
		}
		if (sb.length() < str.length()) {
			sb.append("..");
		}
		return sb.toString();
	}

	public static String summarize2(String str, int len) {
		if (str == null) {
			return "";
		}
		str = str.trim();
		char[] chars = str.toCharArray();
		len = Math.min(chars.length, len);
		StringBuffer sb = new StringBuffer(len);
		int n = 0;
		int i = 0;
		char c = 0;
		int tags1 = 0;
		int tags2 = 0;
		while (n < len && i < str.length()) {
			c = chars[i++];
			switch (c) {
				case '<':
					tags1++;
					continue;
				case '>':
					if (tags1 > 0)
						tags1--;
					continue;
				case '[':
					tags2++;
					continue;
				case ']':
					if (tags2 > 0)
						tags2--;
					continue;
				case ',':
				case '\n':
					tags1 = 0;
					tags2 = 0;
					continue;
				default:
			}
			if (tags1 == 0 && tags2 == 0) {
				sb.append(c);
				n++;
			}
		}
		return sb.toString();
	}

	public String escapeHtml(String Str) {
		if (Str == null)
			return "";
		char[] data = Str.toCharArray();
		// byte[] data = Str.getBytes();
		int len = data.length;
		StringBuffer result = new StringBuffer(len);
		int begin = 0, count = 0, tt = 0;
		for (int i = 0; i < len; i++) {
			switch (data[i]) {
				case '&':
					result.append(new String(data, begin, count));
					result.append("&amp;");
					begin = i + 1;
					count = 0;
					break;
				case '\"':
					result.append(new String(data, begin, count));
					result.append("&quot;");
					begin = i + 1;
					count = 0;
					break;
				case '\'':
					result.append(new String(data, begin, count));
					result.append("&#39;");
					begin = i + 1;
					count = 0;
					break;
				case '<':
					result.append(new String(data, begin, count));
					result.append("&lt;");
					begin = i + 1;
					count = 0;
					break;
				case '>':
					result.append(new String(data, begin, count));
					result.append("&gt;");
					begin = i + 1;
					count = 0;
					break;
				case '\n':
					result.append(new String(data, begin, count));
					result.append("<br>");
					begin = i + 1;
					count = 0;
					break;
				default:
					count++;
					break;
			}
		}
		if (count > 0)
			result.append(new String(data, begin, count));
		return result.toString();
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static String guessValidURI(String uri) {
		uri = uri.trim();
		if (uri.indexOf('@') != -1) {
			if (!uri.startsWith("mailto:")) {
				// Assume this is an email address

				uri = "mailto:" + uri;
			}
		} else if (uri.length() > 0 && !((uri.startsWith("http://") || uri.startsWith("https://")))) {
			uri = "http://" + uri;
		}

		return uri;
	}

	public static String asHtml(Date d) {
		if (d == null) {
			return "";
		}
		return dateUtil.asHtml(d);
	}

	public static String asString(Date d) {
		if (d == null) {
			return "";
		}
		return dateUtil.asShortString(d, TimeZone.getDefault());
	}

	public static String asString1(Date d) {
		if (d == null) {
			return "";
		}
		return dateUtil.asShortString1(d, TimeZone.getDefault());
	}

	/** *********************************************************** */
	public static Map getExhiCategory(String body) {
		body = body.replaceAll(";", "£»").replaceAll(":", "£º").replaceAll(",", "£¬");
		body = StringUtil.html2txt(body);
		String root[] = body.split("£»");
		Map map = new HashMap();
		String key = "";
		String value = "";
		int pos = -1;

		if (root != null && root.length > 0) {
			for (int i = 0; i < root.length; i++) {
				pos = root[i].indexOf("£º");
				System.out.println("pos:" + pos);
				if (pos > -1) {
					map.put(root[i].substring(0, pos), root[i].substring(pos + 1).split("£¬"));
				} else {
					System.out.println("fsfds:" + root[i].split("£¬").length);
					map.put("root" + i, root[i].split("£¬"));
				}
			}
		} else {
			map.put("root0", body);
		}
		return map;

	}
}
