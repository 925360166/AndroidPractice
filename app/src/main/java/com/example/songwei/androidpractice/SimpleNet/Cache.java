package com.example.songwei.androidpractice.SimpleNet;

/**
 * 请求缓存的接口
 * @param <K> key的类型
 * @param <V> value的类型
 */
public interface Cache<K, V> {

    public V get(K key);

    public void put(K key, V value);

    public void remove(K key);
}
