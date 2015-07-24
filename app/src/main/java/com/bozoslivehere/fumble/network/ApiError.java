package com.bozoslivehere.fumble.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alfons on 7/3/2015.
 */
public class ApiError {

    private String mMessage = "Unknown error.";
    private int mErrorcode = 0;

    enum ERROR_CODES {
        UNKNOWN_ENTITY(1), UNKNOWN(100);

        private int value;

        ERROR_CODES(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public ApiError(JSONObject errorOject) {
        try {
            mErrorcode = errorOject.getInt("error_code");
            mMessage = errorOject.getString("error_message");
        } catch (JSONException e) {
            e.printStackTrace();
            mErrorcode = 1;
            mMessage = "Unknown error.";
        }
    }

    public ApiError(ERROR_CODES errorCode, String message) {
        mErrorcode = errorCode.getValue();
        mMessage = message;
    }

    @Override
    public String toString() {
        return mErrorcode + ": " + mMessage;
    }
}
