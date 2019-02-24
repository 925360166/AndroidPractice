package com.example.songwei.androidpractice.SimpleNet;

import android.util.LruCache;

/**
 * 将请求结果缓存到内存中
 */
public class LruMemCache implements Cache<String, Response> {

    /**
     * Response缓存
     */
    private LruCache<String, Response> mResponseCache;

    public LruMemCache() {
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        //取八分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 8;
        mResponseCache = new LruCache<String, Response>(cacheSize) {
            @Override
            protected int sizeOf(String key, Response value) {
                return value.rawData.length / 1024;
            }
        };
    }

    @Override
    public Response get(String key) {
        return mResponseCache.get(key);
    }

    @Override
    public void put(String key, Response value) {
        mResponseCache.put(key, value);
    }

    @Override
    public void remove(String key) {
        mResponseCache.remove(key);
    }
}
