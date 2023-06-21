package com.camerademo

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule


//class KotlinNativeModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {
class KotlinNativeModule internal constructor(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

        override fun getName(): String {

        return "KotlinNativeModule";
    }

    @ReactMethod
    fun doSomething(data: ReadableMap, promise: Promise) {
        val name = data.getString("name")
        val age = data.getInt("age")

        // Process the data and return a result to React Native
//        val result = "Hello, $name! You are $age years old."

//        val image = MainActivity.base64
//        if(image != null){
////            promise.resolve(image)
//            callback.invoke(null, image)
//        }else {
//            promise.resolve(result)
//        }
    }

//    @ReactMethod
//    fun sendEvent(eventName: String, eventData: Any?) {
//        val params = Arguments.createMap().apply {
//            putString("data", eventData?.toString())
//        }
////        reactApplicationContext
////            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
////            .emit(eventName, params)
////        println("eventName: " + eventName + eventData)
//    }

    @ReactMethod
    fun sendMessageToNative(rnMessage: String?) {
        Log.d("This log is from java", "" + rnMessage)
    }

    @ReactMethod
    fun sendCallbackToNative(rnCallback: Callback) {
        rnCallback.invoke("A greeting from java")
    }

    @ReactMethod
    fun finishActivity() {
        if (currentActivity != null) {
            currentActivity!!.finish()
        }
    }

    @ReactMethod
    fun goToSecondActivity() {
//        val reactInstanceManager = reactContext.currentActivity?.application?.let {
//            (it as ReactApplication).reactNativeHost.reactInstanceManager
//        }
//        val currentReactContext = reactInstanceManager?.currentReactContext
//        if (currentReactContext != null && currentReactContext.hasActiveCatalystInstance()) {
//            sendEvent(currentReactContext, "myEvent", null)
//        }
        if (currentActivity != null) {
            val intent = Intent(currentActivity, CameraModule::class.java)
            currentActivity!!.startActivity(intent)
        }
    }


     private fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap?) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }

    @ReactMethod
    fun handleButtonClick() {
        // Handle button click event
        Toast.makeText(reactApplicationContext, "Button clicked!", Toast.LENGTH_SHORT).show()
    }


//    private var listenerCount = 0
//
//    @ReactMethod
//    fun addListener(eventName: String) {
//        if (listenerCount == 0) {
//            // Set up any upstream listeners or background tasks as necessary
//        }
//        listenerCount += 1
//    }
//
//    @ReactMethod
//    fun removeListeners(count: Int) {
//        listenerCount -= count
//        if (listenerCount == 0) {
//            // Remove upstream listeners, stop unnecessary background tasks
//        }
//    }


    fun brodcastImage(image: String) {
//        val reactInstanceManager = reactContext.currentActivity?.application?.let {
//            (it as ReactApplication).reactNativeHost.reactInstanceManager
//        }
//        val currentReactContext = reactInstanceManager?.currentReactContext
//        if (currentReactContext != null && currentReactContext.hasActiveCatalystInstance()) {
//            sendEvent(currentReactContext, "myEvent", null)
//        }
        println("Image: " + image)
        val params = Arguments.createMap().apply {
            putString("eventProperty", "image")
        }
        sendEvent(reactContext, "EventReminder", params)

    }


    companion object {
        private lateinit var instance: KotlinNativeModule

        @JvmStatic
        fun getInstance(): KotlinNativeModule {
            return instance
        }
    }

    init {
        instance = this
    }
}