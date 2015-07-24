package com.bozoslivehere.fumble.database.models_foo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alfons on 7/3/2015.
 */
public abstract class BaseModel {

    protected int id = 0;
    boolean isValid = false;

    protected JSONObject mOriginalData;

    BaseModel(JSONObject data) {
        try {
            id = data.getInt("id");
            isValid = true;
        } catch (JSONException e) {
            e.printStackTrace();
            isValid = false;
        }
    }

}
