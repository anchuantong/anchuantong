
package com.act.util;

/**
 * @author an_chuantong
 * @version created：Aug 27, 2008 8:21:06 PM
 * 不允许操作抛出异常
 */

public class InsufficientPermissionException extends java.lang.IllegalStateException {

	/**
         * 
         */
        private static final long serialVersionUID = 1L;

	public InsufficientPermissionException(String msg) {
		super(msg);
	}
}
