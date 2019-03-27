package com.example.songwei.androidpractice.ImageLoader.loader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

public class ImageResizer {

    private static final String TAG = "ImageLoader";

    public ImageResizer(){
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res,
                                                 int resId, int reqWidth, int reqHeight){
        //用inJustDecodeBounds = true 进行解析尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        //计算图片尺寸
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        //通过设置的尺寸解码图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor fd, int reqWidth, int reqHeight){
        //用inJustDecodeBounds = true 进行解析尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        //计算解析的尺寸
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        //按照设置的尺寸解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        if(reqWidth == 0 || reqHeight == 0){
            return 1;
        }

        //图片的原始height、width
        final int height = options.outHeight;
        final int width = options.outWidth;
        Log.d(TAG, "origin, w = " + width + ", h = " + height);
        int inSampleSize = 1;
        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height / 2;
            final int halfWidth = width /2;
            //计算是2次幂且最大的图片尺寸
            //并确保height、width都要大于要求的height、width
            while((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }
        Log.d(TAG, "sampleSzie: " + inSampleSize);
        return inSampleSize;
    }
}
