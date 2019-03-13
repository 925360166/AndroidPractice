// INotifyCallback.aidl
package com.example.songwei.androidpractice.AIDL;

// Declare any non-default types here with import statements

interface INotifyCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void notifyMainUiThread(String name, boolean isJoin);
}
