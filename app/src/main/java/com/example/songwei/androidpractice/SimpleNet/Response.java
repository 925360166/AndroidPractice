package com.example.songwei.androidpractice.SimpleNet;

import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Locale;

/**
 * 请求结果类，继承自BasicHttpResponse,将结果存储在rawData中。
 */
public class Response extends BasicHttpResponse {

    //结果存储在rawData中
    public byte[] rawData = new byte[0];

    public Response(StatusLine statusline, ReasonPhraseCatalog catalog, Locale locale) {
        super(statusline, catalog, locale);
    }

    public Response(StatusLine statusline) {
        super(statusline);
    }

    public Response(ProtocolVersion ver, int code, String reason) {
        super(ver, code, reason);
    }

    @Override
    public void setEntity(HttpEntity entity) {
        super.setEntity(entity);
        rawData = entityToBytes(getEntity());
    }

    public byte[] getRawData(){
        return rawData;
    }

    public int getStatusCode(){
        return getStatusLine().getStatusCode();
    }

    public String getMessage(){
        return getStatusLine().getReasonPhrase();
    }

    private byte[] entityToBytes(HttpEntity entity){
        try{
            return EntityUtils.toByteArray(entity);
        }catch(IOException e){
            e.printStackTrace();
        }
        return new byte[0];
    }
}

