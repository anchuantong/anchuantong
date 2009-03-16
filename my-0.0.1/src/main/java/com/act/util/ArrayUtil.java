
package com.act.util;

import java.util.List;

/**
 * 数组操作工具类.<br>
 * 目前提供一些常用功能，可以增加方法
 * 
 * @author act
 */
public class ArrayUtil {

	public static boolean contains(Object[] array, Object value) {
		if (array == null || array.length == 0) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			Object o = array[i];
			if ((o == null && value == null) || (o != null && o.equals(value))) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(List list, Object value) {
		if (list == null || list.size() == 0) {
			return false;
		}
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			if ((o == null && value == null) || (o != null && o.equals(value))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将String a和String[] s1中a的删除
	 * @param s1
	 * @param a
	 * @return
	 */
	public static String[] removeItem(String[] s1, String a) {
		if (s1 == null || s1.length == 0)
			return s1;
		String[] s2 = (String[]) java.lang.reflect.Array.newInstance(s1.getClass().getComponentType(), s1.length - 1);
		int i = 0;
		while (i <= s2.length) {
			if (s1[i].equals(a)) {
				if (i == s2.length) {
					return s2;
				}
				System.arraycopy(s1, i + 1, s2, i, s2.length - i);
				return s2;
			}
			s2[i] = s1[i];
			i++;
		}
		if (s1[i] == a) {
			return s2;
		}
		return s1;
	}
	
	/**
	 * 将Object a和Object[] s1中a的删除
	 * @param s1
	 * @param a
	 * @return
	 */
	public static Object[] removeItem(Object[] s1, Object a) {
		if (s1 == null || s1.length == 0)
			return s1;
		Object[] s2 = (Object[]) java.lang.reflect.Array.newInstance(s1.getClass().getComponentType(), s1.length - 1);
		int i = 0;
		while (i <= s2.length) {
			if (s1[i].equals(a)) {
				if (i == s2.length) {
					return s2;
				}
				System.arraycopy(s1, i + 1, s2, i, s2.length - i);
				return s2;
			}
			s2[i] = s1[i];
			i++;
		}
		if (s1[i] == a) {
			return s2;
		}
		return s1;
	}

	/**
	 * 将Object a和Object[] s1中a的前一个元素位置对调，如果a出现多次，只调换第一个出现的a
	 * 
	 * @param s1
	 * @param a
	 * @return Object[]
	 */
	public static int[] moveItemUp(int[] s1, int a) {
		for (int i = 0; i < s1.length; i++) {
			if (s1[i] == a) {
				if (i > 0) {
					s1[i] = s1[i - 1];
					s1[i - 1] = a;
				}

				break;
			}
		}

		return s1;
	}

	/**
	 * 将Object a和Object[] s1中a的后一个元素位置对调，如果a出现多次，只调换第一个出现的a
	 * 
	 * @param s1
	 * @param a
	 * @return Object[]
	 */
	public static Object[] moveItemDown(Object[] s1, Object a) {
		for (int i = 0; i < s1.length; i++) {
			if (s1[i] == a) {
				if (i + 1 < s1.length) {
					s1[i] = s1[i + 1];
					s1[i + 1] = a;
				}
				break;
			}
		}

		return s1;
	}
}
