package com.iteller.foo.mapdb;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

/**
 * 
 * @author iTeller_zc
 *
 * @date 2018年5月5日 上午11:09:56
 */
public class MapDBFactory {
	
	private static HTreeMap myCache;//堆内缓存
	
	private static HTreeMap outMyCache;//堆外缓存
	
	private static HTreeMap diskMyCache;//磁盘缓存
	
	public static HTreeMap buildMapDB(){
		if(myCache != null) {
			return myCache;
		}
		
		myCache = DBMaker.heapDB().concurrencyScale(16)
				.make().hashMap("myCache")
				.expireMaxSize(10000)
				.expireAfterCreate(10, TimeUnit.SECONDS)
				.expireAfterUpdate(10, TimeUnit.SECONDS)
				.expireAfterGet(10, TimeUnit.SECONDS)
				.create();
		
		return myCache;
	}
	
	public static HTreeMap buildOutMapDB(){
		if(outMyCache != null) {
			return outMyCache;
		}
		
		outMyCache = DBMaker.memoryDirectDB()
				.concurrencyScale(16)
				.make().hashMap("outMyCache")
				.expireStoreSize(64*1024*104)//指定堆外缓存大小64M
				.expireMaxSize(10000)
				.expireAfterCreate(10, TimeUnit.SECONDS)
				.expireAfterUpdate(10, TimeUnit.SECONDS)
				.expireAfterGet(10, TimeUnit.SECONDS)
				.create();
		
		return outMyCache;
	}
	
	public static HTreeMap buildDiskMapDB(){
		if(diskMyCache != null) {
			return diskMyCache;
		}
		
		DB db = DBMaker.fileDB(new File("d:\\mapdb\\a.data"))
				.fileMmapEnable() //启用mmap
				.fileMmapEnableIfSupported() //在支持的平台上启用mmap
				.fileMmapPreclearDisable() //让mmap更快
				.cleanerHackEnable() //一些bug处理
				.transactionEnable() //启用事务
				.closeOnJvmShutdown()
				.concurrencyScale(16)
				.make();
		
		diskMyCache = db.hashMap("diskMyCache")
				.expireMaxSize(10000)
				.expireAfterCreate(10, TimeUnit.SECONDS)
				.expireAfterUpdate(10, TimeUnit.SECONDS)
				.expireAfterGet(10, TimeUnit.SECONDS)
				.createOrOpen();
		
		return diskMyCache;
	}
}
