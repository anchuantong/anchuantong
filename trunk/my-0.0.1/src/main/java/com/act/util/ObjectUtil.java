
package com.act.util;

import java.beans.IndexedPropertyDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Object的一些工具
 * 
 * @author anchuantong
 */
public class ObjectUtil {

	private Log log = LogFactory.getLog(getClass().getName());

	private static ObjectUtil instance = new ObjectUtil();

	private final static String DEFAULT_ENCODING = "UTF-8";

	private final static Object[] FORBID_PROP_CLASS_TYPE = new Object[] { Class.class };
	private  static Set<Object> ALLOW_PROP_CLASS_TYPE=null;
	static{
		ALLOW_PROP_CLASS_TYPE=new LinkedHashSet<Object>();
		ALLOW_PROP_CLASS_TYPE.add(String.class);
		ALLOW_PROP_CLASS_TYPE.add(Byte.class);
		ALLOW_PROP_CLASS_TYPE.add(Integer.class);
		ALLOW_PROP_CLASS_TYPE.add(Boolean.class);
		ALLOW_PROP_CLASS_TYPE.add(Long.class);
		ALLOW_PROP_CLASS_TYPE.add(Double.class);
		ALLOW_PROP_CLASS_TYPE.add(Float.class);
		ALLOW_PROP_CLASS_TYPE.add(BigDecimal.class);
		ALLOW_PROP_CLASS_TYPE.add(int.class);
		
		
		
	}
	
	private final static Object[] ALLOW_PROP_CLASS_TYPE1 = new Object[] { 
		String.class,
		Byte.class,Byte.TYPE,
		Integer.class,Integer.TYPE,
		Boolean.class,Boolean.TYPE,
		Long.class,Long.TYPE,
		Double.class,Double.TYPE,
		};

	private ObjectUtil() {
	}

	public static ObjectUtil getInstance() {
		return instance;
	}

	public String toXml(Object bean, String encoding) throws Exception {
		if (bean == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"");
		sb.append(encoding);
		sb.append("\"?>");
		renderXml(bean, encoding, sb);
		return sb.toString();
		
	}
	
	public void renderXml(Object bean, String encoding,StringBuffer sb) throws Exception {
		PropertyDescriptor[] beforeProps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
		
		for (int i = 0; i < beforeProps.length; i++) {
			String propname = beforeProps[i].getName();
			Method getter = beforeProps[i].getReadMethod();
			Object propvalue = getter.invoke(bean, new Object[] {});
			Class propclass = getter.getReturnType();
			if (propclass == Class.class) {
				continue;
			}
			if (propclass == Object.class) {
				continue;
			}
			if (propvalue != null) {
				sb.append("<").append(propname).append(">");
				if (propclass == String.class) {
					sb.append(propvalue);
				} else if (propclass == Byte.class || propclass == Byte.TYPE) {
					sb.append(propvalue);
				} else if (propclass == Integer.class || propclass == Integer.TYPE) {
					sb.append(propvalue);
				} else if (propclass == Long.class || propclass == Long.TYPE) {
					sb.append(propvalue);
				} else if (propclass == Boolean.class || propclass == Boolean.TYPE) {
					sb.append(propvalue);
				} else if (propclass == Double.class || propclass == Double.TYPE) {
					sb.append(propvalue);
				} else if (propclass == Float.class || propclass == Float.TYPE) {
					sb.append(propvalue);
				} else if (propclass == BigDecimal.class) {
					sb.append(propvalue);
				} else if (propclass == String[].class) {
					sb.append(StringUtil.arr2str((String[])propvalue));
				}else if (propclass == int[].class) {
				} else if (propclass == Integer[].class) {
				} else if (propclass == Date.class) {
					sb.append(DateUtil.asHtml((Date) propvalue));
				} else {
					renderXml(propvalue, encoding, sb);
				}

				sb.append("</").append(propname).append(">");
			}
		}
	}

	/**
	 * 复制before属性值到after想匹配的属性
	 * 
	 * @param before
	 * @param after
	 * @throws Exception
	 */
	public void copyObject(Object before, Object after) throws Exception {
		PropertyDescriptor[] beforeProps = Introspector.getBeanInfo(before.getClass()).getPropertyDescriptors();

		for (int i = 0; i < beforeProps.length; i++) {
			String propname = beforeProps[i].getName();
			Method getter = beforeProps[i].getReadMethod();
			Object propvalue = getter.invoke(before, new Object[] {});
			Class propclass = getter.getReturnType();
			if (propvalue != null) {
				propname = propname.substring(0, 1).toUpperCase() + propname.substring(1);
				setProperty(propname, propclass, propvalue, after);
			}
		}
	}

	private void setProperty(String propname, Class propclass, Object propvalue, Object after) {
		Method setter = null;
		try {
			setter = after.getClass().getMethod("set" + propname, propclass);
		} catch (SecurityException e) {
			log.info("method(set" + propname + ") is read only");
		} catch (NoSuchMethodException e) {
			log.info("method(set" + propname + ") does not found in " + after.getClass());
		}

		if (setter != null) {

			try {
				setter.invoke(after, new Object[] { propvalue });
			} catch (IllegalArgumentException e) {
				log.info("error copy property " + propname);
			} catch (IllegalAccessException e) {
				log.info("error copy property " + propname);
			} catch (InvocationTargetException e) {
				log.info("error copy property " + propname);
			}
		}
	}

	private static void setProperty(Object bean, PropertyDescriptor prop) throws Exception {
		if (prop instanceof IndexedPropertyDescriptor) {
			throw new Exception(prop.getName() + " is an indexed property (not supported)");
		}

		Method setter = prop.getWriteMethod();
		if (setter == null) {
			return;
		}

		Class propclass = prop.getPropertyType();
		Object[] args = { null };

		if (propclass == String.class) {
		} else if (propclass == Byte.class || propclass == Byte.TYPE) {
		} else if (propclass == Integer.class || propclass == Integer.TYPE) {
		} else if (propclass == Long.class || propclass == Long.TYPE) {
		} else if (propclass == Boolean.class || propclass == Boolean.TYPE) {
		} else if (propclass == Double.class || propclass == Double.TYPE) {
		} else if (propclass == Float.class || propclass == Float.TYPE) {
		} else if (propclass == BigDecimal.class) {
		} else if (propclass == String[].class) {
		} else if (propclass == Object.class) {
		} else if (propclass == int[].class) {
		} else if (propclass == Integer[].class) {
		} else if (propclass == Date.class) {
		} else {
			throw new Exception("property " + prop.getName() + " is of unsupported type " + propclass.toString());
		}

		setter.invoke(bean, args);
	}
}
