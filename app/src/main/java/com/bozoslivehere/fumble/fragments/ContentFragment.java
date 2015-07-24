package com.bozoslivehere.fumble.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bozoslivehere.fumble.FumbleApplication;
import com.bozoslivehere.fumble.R;
import com.bozoslivehere.fumble.activities.MainActivity;
import com.bozoslivehere.fumble.network.ApiError;
import com.bozoslivehere.fumble.network.JsonResponseInterface;
import com.bozoslivehere.fumble.network.NetworkHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alfons on 7/3/2015.
 */
public class ContentFragment extends Fragment implements JsonResponseInterface {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String TAG = "CONTENT_FRAGMENT";

    private TextView mContentText;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ContentFragment newInstance(int sectionNumber) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TextView titleText = (TextView) view.findViewById(R.id.title_text);
        int section = getArguments().getInt(ARG_SECTION_NUMBER);
        titleText.setText("section: " + section);
        mContentText = (TextView) view.findViewById(R.id.content_text);
        String url = "";

        switch (section) {
            case 0:
                url = "response.json";
                break;
            case 1:
                url = "error.json";
                break;
            case 2:
                url = "dontknow.json";
                break;
        }
        NetworkHelper.getInstance().getJson(url, this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));



    }

    public void initList() {

    }

    @Override
    public void onStop () {
        super.onStop();
    }

    @Override
    public void onResult(JSONObject response) {
        try {
            mContentText.setText(response.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
            mContentText.setText("JSONException: " + e.getMessage());
        }
    }

    @Override
    public void onApiError(ApiError error) {
        mContentText.setText("ApiError: " + error.toString());
    }

    @Override
    public void onError(VolleyError error) {
        mContentText.setText("VolleyError: " + error.getMessage());
    }
}
