package com.example.songwei.androidpractice.SimpleNet;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求类，注意GET,DELETE不能传递请求参数，因为其请求的性质所致，
 * 用户可以将参数构建到URL后传递进来并到Request中
 * @param <T> T为返回的数据类型
 */
public abstract class Request<T> implements Comparable<Request<T>> {

    //默认的编码方式
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    /**
     * Default Content-type
     */
    public final static String HEADER_CONTENT_TYPE = "Content-Type";

    //请求序列号
    protected int mSerialNum = 0;
    //优先级默认设置为Normal
    protected Priority mPriority = Priority.NORMAL;
    //是否取消该请求
    protected boolean isCancel = false;
    /**
     * 该请求是否应该缓存
     */
    private boolean mShouldCache = true;
    //请求Listener
    protected RequestListener<T> mRequestListener;
    //请求的URL
    private String mUrl = "";
    //请求的方法
    HttpMethod mHttpMethod = HttpMethod.GET;
    //请求的 header
    private Map<String, String> mHeaders = new HashMap<String, String>();
    //请求参数
    private Map<String, String> mBodyParams = new HashMap<String, String>();

    /**
     *
     * @param method 请求方式
     * @param url 请求的目标URL
     * @param listener 请求回调，将结果返回给用户
     */
    public Request(HttpMethod method, String url, RequestListener<T> listener){
        mHttpMethod = method;
        mUrl = url;
        mRequestListener = listener;
    }

    //从原生的网络请求中解析结果，子类必须覆写
    public abstract T parseResponse(Response response);

    //处理Response，该方法需要运行在UI线程
    public final void deliveryResponse(Response response){
        //解析得到的请求结果
        T result = parseResponse(response);
        if(mRequestListener != null){
            int stCode = response != null ? response.getStatusCode() : -1;
            String msg = response != null ? response.getMessage() : "unknown error";
            Log.e("", "### 执行回调 : stCode = " + stCode + ", result : " + result + ", err : " + msg);
            mRequestListener.onComplete(stCode, result, msg);
        }
    }

    public String getUrl(){
        return mUrl;
    }

    public int getSerialNumber(){
        return mSerialNum;
    }

    public void setSerialNumber(int mSerialNum){
        this.mSerialNum = mSerialNum;
    }

    public Priority getPriority(){
        return mPriority;
    }

    public void setPriority(Priority mPriority){
        this.mPriority = mPriority;
    }

    protected String getParamsEncoding(){
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType(){
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    public HttpMethod getHttpMethod(){
        return mHttpMethod;
    }

    public Map<String, String> getHeaders(){
        return mHeaders;
    }

    public Map<String, String> getParams(){
        return mBodyParams;
    }

    public boolean isHttps() {
        return mUrl.startsWith("https");
    }

    /**
     * 该请求是否应该缓存
     *
     * @param shouldCache
     */
    public void setShouldCache(boolean shouldCache) {
        this.mShouldCache = shouldCache;
    }

    public boolean shouldCache() {
        return mShouldCache;
    }

    public void cancel(){
        this.isCancel = true;
    }

    public boolean isCanceled(){
        return this.isCancel;
    }

    //返回POST或者PUT请求时的BODY参数字节数组
    public byte[] getBody(){
        Map<String, String> params = getParams();
        if(params != null && params.size() > 0){
            return encodeParameters(params, getParamsEncoding());
        }
        return null;
    }

    //将参数转换为URL编码的参数串，格式为key1=value1&key2=value2
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding){
        StringBuilder encodedParams = new StringBuilder();
        try{
            for(Map.Entry<String, String> entry : params.entrySet()){
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        }catch(UnsupportedEncodingException uee){
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    //用于对请求的排序处理，根据优先级和加入到队列的序号进行排序
    @Override
    public int compareTo(@NonNull Request<T> another) {
        Priority myPriority = this.getPriority();
        Priority anotherPriority = another.getPriority();
        //如果优先级相等，那么按照添加到队伍的序号排序
        return myPriority.equals(another) ? this.getSerialNumber() - another.getSerialNumber()
                : myPriority.ordinal() - anotherPriority.ordinal();
    }

    /**
     * 网络请求Listener,会被执行在UI线程
     * @param <T> 请求的response类型
     */
    public static interface RequestListener<T> {
        //请求完成的回调
        public void onComplete(int stCode, T response, String errMsg);
    }
}














