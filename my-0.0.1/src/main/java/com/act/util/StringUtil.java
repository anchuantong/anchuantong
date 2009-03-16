
package com.act.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.act.util.security.Blowfish;
import com.act.web.util.Config;

public class StringUtil {
	public static Blowfish cipher = new Blowfish("li,an,ch");
	private static String[] FALSE_STRINGS = { "false", "null", "nul", "off", "no", "n" };

	/**
	 * str是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}
	
	public static String indexOfLast(String str,String key) {
		String arr[]=str.split(key);
		if(arr!=null&&arr.length>1){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<arr.length-2;i++){
				if(i>0){
					sb.append(key);	
				}
				sb.append(arr[i]);
			}
			
		}
		return str;
	}
	
	/**
	 * str加密
	 * 
	 * @param str
	 * @param method
	 * @return
	 */
	public static String digest(String str, String method) {
		if (str == null) {
			return null;
		}
		if (method.equals("MD5")) {
			return createMD5(str);
		} else {
			return createMYSQL(str);
		}
	}
	
	public static String changeCharset(String str){
		return changeCharset(str,Config.getSysEncoding(),"UTF-8");
                
	}
	
	public static String changeCharset(String str,String charset1,String charset2){
		if(isEmpty(str)){
			return "";
		}
		try {
	                return new String(str.getBytes(charset1),charset2);
                } catch (UnsupportedEncodingException e) {
	               return str;
                }
                
	}
	private static final String toHex(byte[] hash) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if (((int) hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString((int) hash[i] & 0xff, 16));
		}

		return buf.toString();
	}

	private static String createMD5(String passwordSource) {
		String Password;
		byte[] PasswordByte;
		Password = passwordSource;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(Password.getBytes());
			PasswordByte = md.digest();
		} catch (Exception e) {
			return passwordSource;
		}

		String ReturnPassword = toHex(PasswordByte);

		return ReturnPassword;
	}

	private static String createMYSQL(String passwordSource) {
		String Password;
		String hashOne;
		String hashTwo;
		long resultLong1;
		long resultLong2;
		long tempLong1 = 1345345333;
		long tempLong2 = 0x12345671;
		long tempLong3;
		long addLong = 7;
		long templtLong2;
		char charOne;
		Password = passwordSource;

		for (int i = 0; i < Password.length(); i++) {
			charOne = Password.charAt(i);

			if ((charOne == ' ') || (charOne == '\t')) {
				continue;
			}

			tempLong3 = (long) charOne;
			tempLong1 ^= (((tempLong1 & 63) + addLong) * tempLong3) + (tempLong1 << 8);
			tempLong2 += (tempLong2 << 8) ^ tempLong1;
			addLong += tempLong3;
		}

		resultLong1 = tempLong1 & (((long) 1 << 31) - (long) 1);
		resultLong2 = tempLong2 & (((long) 1 << 31) - (long) 1);

		String ReturnPassword;
		String ReturnPassword1;
		int j = 0;
		ReturnPassword = Long.toHexString(resultLong1);
		j = ReturnPassword.length();

		for (int i = 0; i < (8 - j); i++) {
			ReturnPassword = "0" + ReturnPassword;
		}

		ReturnPassword1 = Long.toHexString(resultLong2);
		j = ReturnPassword1.length();

		for (int i = 0; i < (8 - j); i++) {
			ReturnPassword1 = "0" + ReturnPassword1;
		}

		ReturnPassword = ReturnPassword + ReturnPassword1;

		return ReturnPassword;
	}


	public static String[] parseEnglishTerms(String str) {
		List terms = new ArrayList(5);
		char[] chars = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		char c = 0;
		boolean wordsBegins = false;
		boolean wordsEnds = false;
		while (i < chars.length) {
			c = chars[i];
			if (!Character.isLetterOrDigit(c) || Character.isSpaceChar(c)) {
				if (wordsBegins) {
					wordsEnds = true;
				}
			} else {
				wordsBegins = true;
				sb.append(c);
			}

			if (wordsEnds) {
				terms.add(sb.toString());
				sb.setLength(0);
				wordsEnds = false;
				wordsBegins = false;
			}
			i++;
		}
		if (sb.length() > 0) {
			terms.add(sb.toString());
		}
		return (String[]) terms.toArray(new String[terms.size()]);
	}

	
	public static final String highlightEnglishWords(String str, String[] words) {
		String tmp = null;
		try {
			tmp = highlightEnglishWordsInHtml(str, words);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (tmp == null) {
			return str;
		}
		return tmp;
	}

	public static final String highlightWords(String str, String[] words) {
		String tmp = null;
		try {
			tmp = highlightWordsInHtml(str, words);
		} catch (Exception ex) {
		}
		if (tmp == null) {
			return str;
		}
		return tmp;
	}

	final static String[] startHighlight = new String[] { "<span class='highlight1'><b>", "<span class='highlight2'><b>", "<span class='highlight3'><b>" };

	final static String endHighlight = "</b></span>";

	private static final String highlightEnglishWordsInHtml(String string, String[] words) throws Exception {
		if (string == null || words == null) {
			return null;
		}
		char[] source = null;
		StringBuffer sb = new StringBuffer(string);
		for (int wk = 0; wk < words.length; wk++) {
			if (words[wk] == null) {
				continue;
			}
			source = sb.toString().toCharArray();
			sb.setLength(0);
			int sourceOffset = 0;
			int sourceCount = source.length;
			char[] target = words[wk].toLowerCase().toCharArray();
			int targetOffset = 0;
			int targetCount = target.length;
			int fromIndex = 0;
			if (fromIndex >= sourceCount) {
				continue;
			}
			if (targetCount == 0) {
				continue;
			}
			char first = target[targetOffset];
			int i = sourceOffset + fromIndex;
			int max = sourceOffset + (sourceCount - targetCount);

			int sbPos = 0;
			int tags1 = 0;
			char c = 0;
			startSearchForFirstChar: while (true) {
				while (i <= max) {
					c = source[i];
					switch (c) {
						case '<':
							tags1++;
							break;
						case '>':
							if (tags1 > 0)
								tags1--;
							break;
						case ',':
						case '\n':
							tags1 = 0;
							break;
						default:
					}
					if (Character.toLowerCase(c) == first) {
						break;
					}
					i++;
				}

				if (i > max) {
					break;
				}

				if (tags1 != 0 || (i > 0 && Character.isLetterOrDigit(source[i - 1]))) {
					i++;
					continue startSearchForFirstChar;
				}

				/*
				 * Found first character, now look at the rest
				 * of v2
				 */
				int j = i + 1;
				int end = j + targetCount - 1;
				int k = targetOffset + 1;
				while (j < end) {
					if (Character.toLowerCase(source[j++]) != target[k++]) {
						i++;
						/*
						 * Look for str's first char
						 * again.
						 */
						continue startSearchForFirstChar;
					}
				}
				if (end < source.length - 1 && Character.isLetterOrDigit(source[end])) {
					i++;
					continue startSearchForFirstChar;
				}
				int pos = i - sourceOffset; /*
								 * Found whole
								 * string.
								 */
				sb.append(source, sbPos, pos - sbPos);
				sb.append(startHighlight[wk % startHighlight.length]);
				sb.append(source, pos, targetCount);
				sb.append(endHighlight);
				sbPos = pos + targetCount;
				i += targetCount;
			}
			sb.append(source, sbPos, sourceCount - sbPos);
		}
		return sb.toString();
	}

	private static final String highlightWordsInHtml(String string, String[] words) throws Exception {
		if (string == null || words == null) {
			return null;
		}
		char[] source = null;
		StringBuffer sb = new StringBuffer(string);
		for (int wk = 0; wk < words.length; wk++) {
			if (words[wk] == null) {
				continue;
			}
			source = sb.toString().toCharArray();
			sb.setLength(0);
			int sourceOffset = 0;
			int sourceCount = source.length;
			char[] target = words[wk].toLowerCase().toCharArray();
			int targetOffset = 0;
			int targetCount = target.length;
			int fromIndex = 0;
			if (fromIndex >= sourceCount) {
				continue;
			}
			if (targetCount == 0) {
				continue;
			}
			char first = target[targetOffset];
			int i = sourceOffset + fromIndex;
			int max = sourceOffset + (sourceCount - targetCount);

			int sbPos = 0;
			int tags1 = 0;
			char c = 0;
			startSearchForFirstChar: while (true) {
				while (i <= max) {
					c = source[i];
					switch (c) {
						case '<':
							tags1++;
							break;
						case '>':
							if (tags1 > 0)
								tags1--;
							break;
						case ',':
						case '\n':
							tags1 = 0;
							break;
						default:
					}
					if (Character.toLowerCase(c) == first) {
						break;
					}
					i++;
				}
				if (i > max) {
					break;
				}

				if (tags1 != 0) {
					i++;
					continue startSearchForFirstChar;
				}

				/*
				 * Found first character, now look at the rest
				 * of v2
				 */
				int j = i + 1;
				int end = j + targetCount - 1;
				int k = targetOffset + 1;
				while (j < end) {
					if (Character.toLowerCase(source[j++]) != target[k++]) {
						i++;
						/*
						 * Look for str's first char
						 * again.
						 */
						continue startSearchForFirstChar;
					}
				}
				int pos = i - sourceOffset; /*
								 * Found whole
								 * string.
								 */
				sb.append(source, sbPos, pos - sbPos);
				sb.append(startHighlight[wk % startHighlight.length]);
				sb.append(source, pos, targetCount);
				sb.append(endHighlight);
				sbPos = pos + targetCount;
				i += targetCount;
			}
			sb.append(source, sbPos, sourceCount - sbPos);
		}
		return sb.toString();
	}

	/**
	 * arr数组转String，用逗号隔开
	 * 
	 * @param arr
	 * @return
	 */
	public static String arr2str(String[] arr) {
		return arr2str(arr, ",");
	}

	public static int[] arr2int(String[] sChecked) {
		if (sChecked == null || sChecked.length <= 0) {
			return null;
		}

		int[] iChecked = new int[sChecked.length];

		for (int i = 0; i < sChecked.length; i++) {
			iChecked[i] = Integer.parseInt(sChecked[i]);
		}

		return iChecked;
	}

	public static String arr2str(String[] arr, String key) {
		if (arr == null) {
			return ("");
		}

		if (arr.length == 0) {
			return ("");
		}

		int length = arr.length;
		StringBuffer s = new StringBuffer();

		if (arr[0] != null && arr[0].length() > 0) {
			s.append(encode(arr[0]));
		} else {
			s.append("");

		}
		for (int i = 1; i < length; i++) {
			s.append(key);

			if (arr[i] != null && arr[i].length() > 0) {
				s.append(encode(arr[i]));
			} else {
				s.append("");
			}
		}

		return (s.toString());
	}

	public static int[] str2intarr(String str) {
		if (str == null || str.length() < 1) {
			return new int[0];
		}

		StringTokenizer st = new StringTokenizer(str, ",");
		int[] new_int = new int[st.countTokens()];
		int i = 0;

		while (st.hasMoreTokens()) {
			String tmp = (String) st.nextToken();

			try {
				new_int[i++] = Integer.parseInt(tmp);
			} catch (Exception e) {
			}

		}

		return new_int;
	}

	/**
	 * 将逗号隔开的数组转换成数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] str2arr(String str) {
		return str2arr(str, ',');
	}

	public static String[] str2arr(String str, char key) {
		if (str == null || str.length() < 1) {
			return new String[0];
		}

		int counter = 0;
		int pos = -1;
		int maxPosition = str.length() - 1;
		while (pos < maxPosition) {
			pos++;
			if (str.charAt(pos) == key) {
				if (pos == 0 || str.charAt(pos - 1) != '\\') {
					counter++;
				}
			}
		}
		String[] new_str = new String[counter + 1];

		int cur = -1;
		int i = 0;
		pos = -1;
		boolean should_decode = false;
		while (pos < maxPosition) {
			pos++;
			if (str.charAt(pos) == key) {
				if (pos != 0 && str.charAt(pos - 1) == '\\') {
					// skip
					should_decode = true;
				} else {
					if (should_decode) {
						new_str[i++] = decode(str.substring(cur + 1, pos));
						should_decode = false;
					} else {
						new_str[i++] = str.substring(cur + 1, pos);
					}
					cur = pos;
				}
			}
		}
		if (should_decode) {
			new_str[counter] = decode(str.substring(cur + 1));
		} else {
			new_str[counter] = str.substring(cur + 1);
		}
		return new_str;
	}

	/**
	 * str转换成Hashtable，以分号隔开，等号前字符为key，等号后字符为value
	 * 
	 * @param str
	 * @return
	 */
	public static Hashtable str2hash(String str) {
		return str2hash(str, ',', "=");
	}

	public static Hashtable str2hash(String str, char k, String indexOf) {
		Hashtable hash = new Hashtable();

		if (str == null || str.length() < 1) {
			return hash;
		}

		String[] pairs = str2arr(str, k);

		for (int i = 0; i < pairs.length; i++) {
			try {
				int index = pairs[i].indexOf(indexOf);
				String key1 = decode(pairs[i].substring(0, index));
				if (index == pairs[i].length() || index < 0) {
					hash.put(key1, "");
				} else {
					hash.put(key1, pairs[i].substring(index + 1));
				}
			} catch (Exception e) {
			}

		}

		return hash;
	}

	public static String hash2str(Map hash) {
		return hash2str(hash, ",", "=");
	}

	public static String hash2str(Map hash, String k, String indexOf) {
		if (hash == null) {
			return "";
		}

		int max = hash.size() - 1;
		StringBuffer buf = new StringBuffer();
		Iterator it = hash.entrySet().iterator();

		for (int i = 0; i <= max; i++) {
			Map.Entry e = (Map.Entry) (it.next());
			buf.append(encode((String) e.getKey()) + indexOf + encode((String) e.getValue()));

			if (i < max) {
				buf.append(k);
			}
		}

		return buf.toString();
	}

	/**
	 * 字符串转换成boolean. 转换规则如下: 如果字符串看起来像一个整数, 且整数值等于0, 则为false, 否则true;
	 * 如果字符串为空串或null, 则返回指定默认值; 如果字符串为下列值之一(大小写不敏感): "false", "null", "nul",
	 * "off", "no", "n" 则为false, 否则为true.
	 * 
	 * @param String 字符串
	 * @param Boolean 默认值
	 * @return Boolean
	 */
	public static Boolean asBoolean(String str, Boolean defaultValue) {
		try {
			str = str.trim();
			return Integer.decode(str).intValue() != 0 ? Boolean.TRUE : Boolean.FALSE;
		} catch (NullPointerException e) {
			return defaultValue;
		} catch (NumberFormatException e) {
			if (str.equals("")) {
				return defaultValue;
			}
			for (int i = 0; i < FALSE_STRINGS.length; i++) {
				if (str.equalsIgnoreCase(FALSE_STRINGS[i])) {
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		}
	}

	/**
	 * 字符串转换成boolean. 转换规则如下: 如果字符串看起来像一个整数, 且整数值等于0, 则为false, 否则true;
	 * 如果字符串为空串或null, 则返回指定默认值; 如果字符串为下列值之一(大小写不敏感): "false", "null", "nul",
	 * "off", "no", "n" 则为false, 否则为true.
	 * 
	 * @param String 字符串
	 * @param boolean 默认值
	 * @return boolean
	 */
	public static boolean asBoolean(String str, boolean defaultValue) {
		try {
			str = str.trim();
			return Integer.decode(str).intValue() != 0;
		} catch (NullPointerException e) {
			return defaultValue;
		} catch (NumberFormatException e) {
			if (str.equals("")) {
				return defaultValue;
			}
			for (int i = 0; i < FALSE_STRINGS.length; i++) {
				if (str.equalsIgnoreCase(FALSE_STRINGS[i])) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 字符串转换成long. 如果字符串为null, 字符串不是整数或整数超过取值范围, 则返回0.
	 * 
	 * @param String 字符串
	 * @return long
	 */
	public static long asLong(String str) {
		return asLong(str, 0L);
	}

	/**
	 * 字符串转换成long. 如果字符串为null, 字符串不是整数或整数超过取值范围, 则返回指定默认值.
	 * 
	 * @param String 字符串
	 * @param Long 默认值
	 * @return Long
	 */
	public static Long asLong(String str, Long defaultValue) {
		try {
			return Long.decode(str.trim());
		} catch (NullPointerException e) {
			return defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换成long. 如果字符串为null, 字符串不是整数或整数超过取值范围, 则返回指定默认值.
	 * 
	 * @param String 字符串
	 * @param long 默认值
	 * @return long
	 */
	public static long asLong(String str, long defaultValue) {
		try {
			return Long.decode(str.trim()).longValue();
		} catch (NullPointerException e) {
			return defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换成int. 如果字符串为null, 字符串不是整数或整数超过取值范围, 则返回指定默认值.
	 * 
	 * @param String 字符串
	 * @param Integer 默认值
	 * @return Integer
	 */
	public static Integer asInteger(String str, Integer defaultValue) {
		try {
			return Integer.decode(str.trim());
		} catch (NullPointerException e) {
			return defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换成int. 如果字符串为null, 字符串不是整数或整数超过取值范围, 则返回指定默认值.
	 * 
	 * @param String 字符串
	 * @param int 默认值
	 * @return int
	 */
	public static int asInteger(String str, int defaultValue) {
		try {
			return Integer.decode(str.trim()).intValue();
		} catch (NullPointerException e) {
			return defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public static String encode(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char[] cs = s.toCharArray();
		StringBuffer sb = new StringBuffer(cs.length + 2);
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == ',') {
				sb.append('\\');
			}
			sb.append(cs[i]);
		}
		return sb.toString();
	}

	public static String encodeURL(String url, String encoding) {
		try {
			return URLEncoder.encode(url, encoding);
		} catch (Exception ex) {
			return url == null ? "" : url;
		}
	}

	public static String decodeURL(String str, String encoding) {
		try {
			return URLDecoder.decode(str, encoding);
		} catch (Exception ex) {
			return str == null ? "" : str;
		}
	}
	
	public static String decode(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		char[] cs = s.toCharArray();
		StringBuffer sb = new StringBuffer(cs.length);

		for (int i = 0, n = cs.length; i < n; i++) {
			if (!(cs[i] == '\\' && i < n - 1 && cs[i + 1] == ',')) {
				sb.append(cs[i]);
			}
		}
		return sb.toString();
	}

	public static String html2txt(String html) {
		if (html == null) {
			return "";
		}
		char[] chars = html.toCharArray();
		StringBuffer sb = new StringBuffer();
		int n = 0;
		int i = 0;
		char c = 0;
		int tags1 = 0;
		int tags2 = 0;
		while (i < html.length()) {
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
			sb.append(" ...");
		}
		return sb.toString();
	}

	public static String[] parse(String pathInfo, int level) {
		String[] values = new String[level];
		int pos1 = pathInfo.lastIndexOf(".") - 1;
		if (pos1 < pathInfo.lastIndexOf('/')) {
			pos1 = pathInfo.length() - 1;
		}
		for (int i = level - 1; i >= 0; i--) {
			int tmp = pathInfo.lastIndexOf("/", pos1);
			values[i] = pathInfo.substring(tmp + 1, pos1 + 1);
			pos1 = tmp - 1;
		}
		return values;
	}

	public static String toUpperCase(String key) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int j = 0; j < key.length(); j++) {
				char c = key.charAt(j);
				if (c == '_') {
					sb.append(Character.toUpperCase(key.charAt(++j)));
				} else if (j==0) {
					sb.append(Character.toUpperCase(c));
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		} catch (Exception ex) {
			return key;
		}
	}

	public static boolean isNumber(String str) {
		if (str == null) {
			return false;
		}
		char[] asc = str.toCharArray();
		for (int i = 0; i < asc.length; i++) {
			if (!Character.isDigit(asc[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumber1(String str) {
		if (str == null) {
			return false;
		}
		if (str.startsWith("-")) {
			str = str.substring(1);
		}
		char[] asc = str.toCharArray();
		for (int i = 0; i < asc.length; i++) {
			if (!Character.isDigit(asc[i])) {
				return false;
			}
		}
		return true;
	}
}
