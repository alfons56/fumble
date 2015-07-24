package com.bozoslivehere.fumble.database.models_foo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alfons on 7/3/2015.
 */
public class UserModel extends BaseModel {

    public String name = "";

    public UserModel(JSONObject data) {
        super(data);
        if (isValid) {
            try {
                name = data.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
                isValid = false;
            }
        }
    }

    public static ArrayList<UserModel> getList(JSONArray data) throws Exception {
        ArrayList<UserModel> result = new ArrayList<UserModel>();
        for(int i = 0 ; i < data.length(); i++){
            UserModel model = new UserModel(data.optJSONObject(i));
            if (null != model && model.isValid) {
                result.add(model);
            } else {
                throw new Exception("Parse failed");
            }
        }
        return result;
    }
}
