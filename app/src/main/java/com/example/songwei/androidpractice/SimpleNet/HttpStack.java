package com.example.songwei.androidpractice.SimpleNet;

/**
 * 执行网络请求的接口
 */
public interface HttpStack {
    /**
     * 执行HTTP请求
     * @param request 待执行的请求
     * @return 返回Response
     */
    public Response performRequest(Request<?> request);
}
