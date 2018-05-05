package com.iteller.foo.guava.cache;

import org.junit.Test;

import com.google.common.cache.Cache;

/**
 * 
 * @author iTeller_zc
 *
 * @date 2018年5月5日 上午10:12:02
 */
public class TestGuavaCache {
	
	private Cache<String, String> myCache = GuavaCacheFactory.buildGuavaCache();
	
	@Test
	public void testCache() throws InterruptedException {
		myCache.put("name", "iTeller_zc");
		System.out.println(myCache.getIfPresent("name"));
		System.out.println(myCache.getIfPresent("age"));
		Thread.sleep(1*1000);
		System.out.println(myCache.getIfPresent("name"));
		Thread.sleep(9*1000);
		System.out.println(myCache.getIfPresent("name"));
	}
}
