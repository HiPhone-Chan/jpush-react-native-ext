package com.chf.jpush.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by chhfeng on 2017/8/29.
 */

public class PushModule extends ReactContextBaseJavaModule {

    private final static String JPUSH_RECEIVER = "JPUSH_RECEIVER";

    public PushModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "JPushPush";
    }

    public void sendEvent(String eventName, WritableMap params) {
        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);

    }

    public class PushReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
           String action = intent.getAction();

            WritableMap params = Arguments.createMap();
            params.putString("action", action);

            sendEvent(JPUSH_RECEIVER, params);
        }
    }

}
