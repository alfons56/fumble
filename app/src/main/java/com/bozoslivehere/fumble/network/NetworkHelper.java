package com.bozoslivehere.fumble.network;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bozoslivehere.fumble.FumbleApplication;
import com.bozoslivehere.fumble.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by alfons on 7/3/2015.
 */
public class NetworkHelper {

    private static NetworkHelper sInstance = null;

    private static final String TAG = "FUMBLE_NETWORK_HELPER";

    private String mServerUrl = "";

    private enum EntityNames {
        USERS("users"), PLACES("places"), EVENTS("events");

        private String name;

        EntityNames(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private NetworkHelper() {
        mServerUrl = FumbleApplication.getAppContext().getResources().getString(R.string.server_url);
    }

    public static synchronized NetworkHelper getInstance() {
        if (null == sInstance) {
            sInstance = new NetworkHelper();
        }
        return sInstance;
    }

    public void getJson(String url, JsonResponseInterface listener) {
        url = mServerUrl + url;
        doJsonRequest(url, null, listener);
    }

    public void postJson(String url, JSONObject postData, JsonResponseInterface listener) {
        url = mServerUrl + url;
        doJsonRequest(url, postData, listener);
    }

    private void doJsonRequest(String url, JSONObject postData, final JsonResponseInterface listener) {

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != listener) {
                    listener.onError(error);
                }
            }
        };

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (null != listener) {
                    JSONObject errorObject = null;
                    boolean hasError = false;
                    errorObject = response.optJSONObject("error");
                    if (null != errorObject) {
                        listener.onApiError(new ApiError(errorObject));
                        hasError = true;
                    } else {
                        JSONArray names = response.names();
                        for (int i = 0 ; i < names.length(); i++) {
                            String name = (String) names.opt(i);
                            if (!isEntity(name)) {
                                errorListener.onErrorResponse(new VolleyError("Unknown entity: " + name));
                                hasError = true;
                            }
                        }
                        if (!hasError) {
                            listener.onResult(response);
                        }
                    }
                }
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(url, postData, responseListener, errorListener);
        queueRequest(request);
    }

    private boolean isEntity(String name) {
        boolean result = false;
        for (EntityNames entity : EntityNames.values()) {
            if (entity.getName().equals(name)) {
                return true;
            }
        }
        return result;
    }

    private RequestQueue getRequestQueue() {
        return VolleySingleton.getInstance().getRequestQueue();
    }

    private <T> void queueRequest(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    private <T> void queueRequest(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests() {
        cancelPendingRequests(TAG);
    }

    public void cancelPendingRequests(Object tag) {
        tag = (null == tag) ? TAG : tag;
        getRequestQueue().cancelAll(tag);
    }

    public String getTag() {
        return TAG;
    }
}
