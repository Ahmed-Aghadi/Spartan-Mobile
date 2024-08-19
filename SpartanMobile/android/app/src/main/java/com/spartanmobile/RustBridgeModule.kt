package com.spartanmobile

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.util.RNLog

class RustBridgeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    // Load the Rust library
    companion object {
        init {
            System.loadLibrary("SpartanWrapper")
        }
    }

    external fun nativeAddNumbers(a: Int, b: Int): Int
    external fun test(): Boolean

    override fun getName() = "RustModule"

    @ReactMethod
    fun addNumbers(a: Int, b: Int, promise: Promise) {
        val result = nativeAddNumbers(a, b)
        RNLog.w(this.reactApplicationContext, "Rust says: " + result);
        promise.resolve(result)
    }

    @ReactMethod
    fun test(promise: Promise) {
        val result = test()
        RNLog.w(this.reactApplicationContext, "Rust test says: " + result);
        promise.resolve(result)
    }
}