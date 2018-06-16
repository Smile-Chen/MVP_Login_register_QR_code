package com.example.hp.mn2.app;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by hp on 2018/6/16.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"\n" +
                        "5b220a278f4a9d7248000288"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


    }


}
