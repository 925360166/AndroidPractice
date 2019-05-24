package com.example.songqwei.androidpractice.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                /*if (ZLog.ERROR) {
                    ZLog.e(TAG, e);
                }*/
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            try {
                cursor.close();
            } catch (Throwable e) {
                /*if (ZLog.ERROR) {
                    ZLog.e(TAG, e);
                }*/
            }
        }
    }

    public static void closeQuietly(SQLiteDatabase db) {
        if (db != null && db.isOpen()) {
            try {
                db.close();
            } catch (Throwable e) {
                /*if (ZLog.ERROR) {
                    ZLog.e(TAG, e);
                }*/
            }
        }
    }

    public static void closeQuietly(InputStream db) {
        if (db != null) {
            try {
                db.close();
            } catch (Throwable e) {
                /*if (ZLog.ERROR) {
                    ZLog.e(TAG, e);
                }*/
            }
        }
    }

    public static void closeQuietly(OutputStream db) {
        if (db != null) {
            try {
                db.close();
            } catch (Throwable e) {
                /*if (ZLog.ERROR) {
                    ZLog.e(TAG, e);
                }*/
            }
        }
    }

    /**
     * Throws {@code t}, even if the declared throws clause doesn't permit it.
     * This is a terrible – but terribly convenient – hack that makes it easy to
     * catch and rethrow exceptions after cleanup. See Java Puzzlers #43.
     */
//    public static void sneakyRethrow(Throwable t) {
//        sneakyThrow2(t);
//    }
//
//    @SuppressWarnings("unchecked")
//    private static <T extends Throwable> void sneakyThrow2(Throwable t) throws T {
//        throw (T) t;
//    }
}
