package com.camerademo

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class MainReactPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        // Return an empty list or any other native modules if needed
        return emptyList()
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        // Create a list of custom view managers that you want to register
        val viewManagers = mutableListOf<ViewManager<*, *>>()

        // Register the CustomViewManager
        viewManagers.add(CustomNestedViewManager())

        // Add other custom view managers if needed

        return viewManagers
    }
}
