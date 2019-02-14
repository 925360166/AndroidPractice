package com.example.songwei.androidpractice.SimpleNet;

/**
 * Http请求方法枚举，这里我们只有GET,POST,PUT,DELETE四种
 */
public  enum HttpMethod {
    GET("GET"),

    POST("POST"),

    PUT("PUT"),

    DELETE("DELETE");

    /**
     * http request type
     */
    private String mHttpMethod = "";

    private HttpMethod(String method){
        mHttpMethod = method;
    }

    @Override
    public String toString() {
        return mHttpMethod;
    }
}
