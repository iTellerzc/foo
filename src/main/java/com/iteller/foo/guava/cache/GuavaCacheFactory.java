package com.iteller.foo.guava.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 
 * @author iTeller_zc
 *
 * @date 2018年5月5日 上午10:04:39
 */
public class GuavaCacheFactory {
	
	private static Cache<String, String> myCache;
	
	public static Cache<String, String> buildGuavaCache() {
		if(myCache != null) {
			return myCache;
		}
		myCache = CacheBuilder.newBuilder()
				.concurrencyLevel(4)
				.expireAfterWrite(10, TimeUnit.SECONDS)
				.maximumSize(10000)
				.build();
		return myCache;
	}
}
