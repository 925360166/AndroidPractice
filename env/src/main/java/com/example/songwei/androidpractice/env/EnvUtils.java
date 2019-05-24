package com.example.songwei.androidpractice.env;

import android.content.Context;
import android.content.pm.PackageManager;

import com.example.songqwei.androidpractice.utils.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;

public class EnvUtils {
    private static final String TAG = EnvUtils.class.getSimpleName();

    /**
     * 返回当前进程名
     *
     * @return
     */
    public static String getProcessName(){
        FileInputStream in = null;
        try{
            String fn = "/proc/self/cmdline";
            in = new FileInputStream(fn);
            byte[] buffer = new byte[256];
            int len = 0;
            int b;
            while((b = in.read()) > 0 && len < buffer.length){
                buffer[len++] = (byte) b;
            }
            return new String(buffer, 0, len, "UTF-8");
        }catch (Throwable e){
            throw new AssertionError(e);
        }finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static int getVersionCode(Context context, String pkgName){
        try{
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(pkgName, 0).versionCode;
        }catch (Exception ignored){
        }
        return 0;
    }

}
