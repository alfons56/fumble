package com.bozoslivehere.fumble;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.bozoslivehere.fumble.network.VolleySingleton;

/**
 * Created by alfons on 7/3/2015.
 */
public class FumbleApplication extends Application {

    private static FumbleApplication sInstance = null;

    private static final String TAG = "FUMBLE_APP";

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static synchronized FumbleApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

}
