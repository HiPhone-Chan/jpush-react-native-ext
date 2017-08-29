package com.chf.jpush.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by chhfeng
 * on 2017/8/29.
 */

public class PushModule extends ReactContextBaseJavaModule {

    private final static String JPUSH_RECEIVER = "JPUSH_RECEIVER";

    private final static String JPUSH_INTENT_PREFIX = "cn.jpush.android.intent.";
    private final static String ACTION_MESSAGE_RECEIVED = JPUSH_INTENT_PREFIX + "MESSAGE_RECEIVED";
    private final static String ACTION_NOTIFICATION_RECEIVED = JPUSH_INTENT_PREFIX + "NOTIFICATION_RECEIVED";

    private static ReactApplicationContext mReactContext;

    public PushModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "JPushPush";
    }

    private static void sendEvent(String eventName, WritableMap params) {
        if (mReactContext != null) {
            mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        }
    }

    public static class PushReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle bundle = intent.getExtras();

            WritableMap params = Arguments.createMap();
            params.putString("action", action);

            switch (action) {
                case  ACTION_MESSAGE_RECEIVED:
                    params.putString("title", bundle.getString(JPushInterface.EXTRA_TITLE));
                    params.putString("message", bundle.getString(JPushInterface.EXTRA_MESSAGE));
                    params.putString("extras", bundle.getString(JPushInterface.EXTRA_EXTRA));
                    break;
                case  ACTION_NOTIFICATION_RECEIVED:
                    params.putString("title", bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));
                    params.putString("content", bundle.getString(JPushInterface.EXTRA_ALERT));
                    params.putString("extras", bundle.getString(JPushInterface.EXTRA_EXTRA));
                    break;
                default:
            }

            sendEvent(JPUSH_RECEIVER, params);
        }
    }

}
