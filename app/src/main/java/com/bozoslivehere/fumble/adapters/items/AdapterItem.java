package com.bozoslivehere.fumble.adapters.items;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bozoslivehere.fumble.fragments.ContentFragment;

import org.json.JSONObject;

/**
 * Created by alfons on 7/3/2015.
 */
public abstract class AdapterItem {

    public View view;

    public AdapterItem() {

    }

    public AdapterItem(Context context) {}

    public abstract int getLayout();

    public abstract View getView();

    public abstract View populateView();


}
