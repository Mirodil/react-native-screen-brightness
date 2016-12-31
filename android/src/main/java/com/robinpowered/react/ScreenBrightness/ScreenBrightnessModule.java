package com.robinpowered.react.ScreenBrightness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.BatteryManager;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.LifecycleEventListener;

import javax.annotation.Nullable;

public class ScreenBrightnessModule extends ReactContextBaseJavaModule {

    public ScreenBrightnessModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override
    public String getName() {
        return "ScreenBrightness";
    }

    @ReactMethod
    public void getBrightness(Promise promise) {
        final Activity activity = getCurrentActivity();
        float brightness = activity.getWindow().getAttributes().screenBrightness;
        promise.resolve(brightness);
    }

    @ReactMethod
    public void setBrightness(final float brightness, final Promise promise) {
        final Activity activity = getCurrentActivity();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.screenBrightness=brightness;
                activity.getWindow().setAttributes(lp);
                promise.resolve(brightness);
            }
        });
    }
}