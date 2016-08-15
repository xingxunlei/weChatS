/**
 * RetryRunner.java
 * com.xingxunlei.wechat.commons.utils.thread
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-11 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils.thread;

import org.apache.log4j.Logger;

/**
 * ClassName:RetryRunner
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-11		下午1:34:40
 *
 * @see 	 
 */
public abstract class RetryRunner implements Runnable {
    private static Logger LOG = Logger.getLogger(RetryRunner.class);
    private int retry = 0;

    @Override
    public void run() {
        boolean succeed = doExecute();
        if (!succeed) {
            if (retry ++ < 3) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOG.error("Exception happens when retry thread ", e);
                }
                AsyncRetryExecutor.execute(this);
            } else {
                LOG.error("Execute runner failed [" + toString() + "]");
            }
        }
    }
    
    public abstract boolean doExecute();

}

