package com.iteller.foo.ehcache;

import org.ehcache.Cache;
import org.junit.Test;

public class TestEhcache {
	
	private Cache<String, String> myCache = EhcacheFactory.buildEhcache();
	
	private Cache<String, String> outMyCache = EhcacheFactory.buildOutEhcache();
	
	private Cache<String, String> diskMyCache = EhcacheFactory.buildDiskEhcache();
	
	@Test
	public void testCache() throws InterruptedException {
		myCache.put("name", "iTeller_zc");
		System.out.println(myCache.get("name"));
		System.err.println(myCache.get("age"));
		Thread.sleep(10*1000);
		System.out.println(myCache.get("name"));
	}
	
	@Test
	public void testOutCache() throws InterruptedException {
		outMyCache.put("name", "iTeller_zc");
		System.out.println(outMyCache.get("name"));
		Thread.sleep(10*1000);
		System.out.println(outMyCache.get("name"));
	}
	
	@Test
	public void testDiskCache() throws InterruptedException {
		diskMyCache.put("name", "iTeller_zc");
		System.out.println(diskMyCache.get("name"));
		Thread.sleep(10*1000);
		System.out.println(diskMyCache.get("name"));
	}
}
