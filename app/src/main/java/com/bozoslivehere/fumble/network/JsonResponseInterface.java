package com.bozoslivehere.fumble.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by alfons on 7/3/2015.
 */
public interface JsonResponseInterface {

    void onResult(JSONObject response);

    void onApiError(ApiError error);

    void onError(VolleyError error);

}
