/**
 * DAOException.java
 * com.xingxunlei.wechat.exception
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.exception;
/**
 * ClassName:DAOException
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午7:38:40
 *
 * @see 	 
 */
public class DAOException extends RuntimeException {

    private static final long serialVersionUID = -5014768506751528629L;
    
    public DAOException() {
        super();
    }
    
    public DAOException(String message) {
        super(message);
    }
    
    public DAOException(Throwable cause) {
        super(cause);
    }
    
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}

