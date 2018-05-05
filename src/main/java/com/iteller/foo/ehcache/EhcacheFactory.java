package com.iteller.foo.ehcache;

import java.io.File;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.PooledExecutionServiceConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.impl.config.persistence.CacheManagerPersistenceConfiguration;

/**
 * 
 * @author iTeller_zc
 *
 * @date 2018年5月5日 上午10:30:05
 */
public class EhcacheFactory {
	
	private static Cache<String, String> myCache;//堆内缓存
	
	private static Cache<String, String> outMyCache;//堆外缓存
	
	private static Cache<String, String> diskMyCache;//磁盘缓存
	
	public static Cache<String, String> buildEhcache(){
		if(myCache != null) {
			return myCache;
		}
		
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
		CacheConfigurationBuilder<String, String> cacheConfig = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, 
						String.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, EntryUnit.ENTRIES))
						.withDispatcherConcurrency(4)
						.withExpiry(Expirations.timeToLiveExpiration(Duration.of(10, TimeUnit.SECONDS)));
		
		myCache = cacheManager.createCache("myCache", cacheConfig);
		return myCache;
	}
	
	public static Cache<String, String> buildOutEhcache(){
		if(outMyCache != null) {
			return outMyCache;
		}
		
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
		CacheConfigurationBuilder<String, String> cacheConfig = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, 
						String.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder().
						offheap(100, MemoryUnit.MB))
						.withDispatcherConcurrency(4)
						.withExpiry(Expirations.timeToLiveExpiration(Duration.of(10, TimeUnit.SECONDS)))
						.withSizeOfMaxObjectGraph(3)
						.withSizeOfMaxObjectSize(1, MemoryUnit.MB);
		
		myCache = cacheManager.createCache("outMyCache", cacheConfig);
		return myCache;
	}
	
	public static Cache<String, String> buildDiskEhcache(){
		if(diskMyCache != null) {
			return diskMyCache;
		}
		
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
						.defaultPool("default", 1, 10).build())//默认线程池
				.with(new CacheManagerPersistenceConfiguration(new File("f:\\ehcache")))//存储位置
				.build(true);
		
		CacheConfigurationBuilder<String, String> cacheConfig = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, 
						String.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder()
						.disk(100, MemoryUnit.MB, true))//磁盘缓存
						.withDiskStoreThreadPool("default", 5)//使用默认线程池dump到磁盘
						.withExpiry(Expirations.timeToLiveExpiration(Duration.of(10, TimeUnit.SECONDS)))
						.withSizeOfMaxObjectGraph(3)
						.withSizeOfMaxObjectSize(1, MemoryUnit.KB);
		
		diskMyCache = cacheManager.createCache("diskMyCache", cacheConfig);
		return diskMyCache;
	}
}
