package com.bozoslivehere.fumble.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bozoslivehere.fumble.adapters.items.AdapterItem;
import com.bozoslivehere.fumble.database.models_foo.BaseModel;

import java.util.List;

/**
 * Created by alfons on 7/3/2015.
 */
public class MenuAdapter extends ArrayAdapter<BaseModel> {

    public MenuAdapter(Context context, int resource, List<? extends BaseModel> objects) {
        super(context, resource, (List<BaseModel>) objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterItem viewHolder;
        if (convertView == null) {
           // viewHolder = new AdapterItem(getContext());
           // convertView = LayoutInflater.from(getContext()).inflate(viewHolder.getLayout(), null);
//            viewHolder.shopname = (TextView) view.findViewById(R.id.titleTextView);
//            viewHolder.shopcategory = (TextView) view.findViewById(R.id.category);
//            viewHolder.shopstreet = (TextView) view.findViewById(R.id.address);
//            viewHolder.shopdistance = (TextView) view.findViewById(R.id.distance);
//            viewHolder.shopimage = (ImageView) view.findViewById(R.id.image);
//            viewHolder.street = (TextView) view.findViewById(R.id.street);
          //  convertView.setTag(viewHolder.getView());
        } else {
            viewHolder = (AdapterItem) convertView.getTag();
        }


        return convertView;// viewHolder.populateView();
    }

}
