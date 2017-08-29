jpush 扩展封装
=============

# 安装
```
npm install jpush-react-native-ext --save
```

# 配置
## android
```
1 your react native project/android/app/build.gradle下添加
dependencies {
    ...
    compile project(':jpush-react-native-ext')
}

2 在android/settings.gradle下添加
include ':jpush-react-native-ext'
project(':jpush-react-native-ext').projectDir = new File(rootProject.projectDir, '../node_modules/jpush-react-native-ext/android')

3 在MainApplication.java下添加
import com.chf.jpush.JPushExtReactPackage; // add this
...
protected List<ReactPackage> getPackages() {
  return Arrays.<ReactPackage>asList(
      new MainReactPackage(), ...
      new JPushExtReactPackage() // add this
  );
}

4 在AndroidManifest.xml中
<!-- 配置receiver -->
<receiver
    android:name="com.chf.jpush.push.PushModule$PushReceiver"
    android:enabled="true">
    <intent-filter>
        <action android:name="cn.jpush.android.intent.REGISTRATION" />
        <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED" />
        <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED" />
        <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED" />
        <action android:name="cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK" />
        <action android:name="cn.jpush.android.intent.CONNECTION" />
        <category android:name="${applicationId}" />
    </intent-filter>
</receiver>

```

## ios
```
暂未实现
```

# Usage

```
import { DeviceEventEmitter } from 'react-native';

DeviceEventEmitter.addListener('JPUSH_RECEIVER', (res)=> {
            console.log(res);
});
```
