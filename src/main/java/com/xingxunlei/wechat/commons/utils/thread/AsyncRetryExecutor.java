/**
 * AsyncRetryExecutor.java
 * com.xingxunlei.wechat.commons.utils.thread
 *
 * Function： 重试机制线程池
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-11 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 重发机制线程池
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-11		下午1:34:20
 *
 * @see 	 
 */
public class AsyncRetryExecutor {
    private static final ExecutorService executorPool = Executors.newCachedThreadPool();
    
    public static void execute (RetryRunner runner) {
        executorPool.execute(runner);
    }
    
    public static void destroy () {
        executorPool.shutdown();
    }

}

