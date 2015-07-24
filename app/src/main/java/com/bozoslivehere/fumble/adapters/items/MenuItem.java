package com.bozoslivehere.fumble.adapters.items;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bozoslivehere.fumble.R;

/**
 * Created by alfons on 7/3/2015.
 */
public class MenuItem extends AdapterItem {
    public TextView titleTextView;
    public ImageView iconImageView;

    public MenuItem() {

    }

    public MenuItem(Context context) {
        super(context);
        titleTextView = (TextView) view.findViewById(R.id.title_text);
        iconImageView = (ImageView) view.findViewById(R.id.icon);
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public View populateView() {
        titleTextView.setText("test");
        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.menu_list_item;
    }
}
