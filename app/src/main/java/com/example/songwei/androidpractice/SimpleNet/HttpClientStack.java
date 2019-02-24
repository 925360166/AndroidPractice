package com.example.songwei.androidpractice.SimpleNet;

import android.net.http.AndroidHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;


/**
 * api 9以下使用HttpClient执行网络请求，https配置参考http://jackyrong.iteye.com/blog/1606444
 */
public class HttpClientStack implements HttpStack {

    /**
     * 使用HttpClient执行网络请求时的Https配置
     */
    HttpClientConfig mConfig = HttpClientConfig.getConfig();

    /**
     * HttpClient
     */
    HttpClient mHttpClient = AndroidHttpClient.newInstance(mConfig.userAgent);

    @Override
    public Response performRequest(Request<?> request) {
        try{
            HttpUriRequest httpRequest = createHttpRequest(request);
            //添加连接参数
            setConnectionParams(httpRequest);
            //添加header
            addHeaders(httpRequest, request.getHeaders());
            //https配置
            configHttps(request);
            //执行请求
            HttpResponse response = mHttpClient.execute(httpRequest);
            //构建Response
            Response rawResponse = new Response(response.getStatusLine());
            //设置Entity
            rawResponse.setEntity(response.getEntity());
            return rawResponse;
        }catch (Exception e){
        }
        return null;
    }

    /**
     * 如果是https请求，则使用用户配置的SSLSocketFactory进行配置。
     *
     * @param request
     */
    private void configHttps(Request<?> request){
        SSLSocketFactory sslSocketFactory = mConfig.getSocketFactory();
        if(request.isHttps() && sslSocketFactory != null){
            Scheme sch = new Scheme("https", sslSocketFactory, 443);
            mHttpClient.getConnectionManager().getSchemeRegistry().register(sch);
        }
    }



}
