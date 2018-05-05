package com.iteller.foo.mapdb;

import org.junit.Test;
import org.mapdb.HTreeMap;

/**
 * 
 * @author iTeller_zc
 *
 * @date 2018年5月5日 上午11:15:31
 */
public class TestMapDB {
	
	private HTreeMap myCache = MapDBFactory.buildMapDB();
	
	private HTreeMap outMyCache = MapDBFactory.buildOutMapDB();
	
	private HTreeMap diskMyCache = MapDBFactory.buildDiskMapDB();
	
	@Test
	public void testCache() throws InterruptedException {
		myCache.put("name", "iTeller_zc");
		System.out.println(myCache.get("name"));
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
