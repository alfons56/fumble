package com.bozoslivehere.fumble.database.models;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import com.bozoslivehere.fumble.database.ContentDescriptor.UserDescriptor;

/**
 * This file was generated, don't edit here!
 */

public class User extends Model {

    private Integer _id;
    private String description;
    private Boolean active;
    private String address;
    private String phone;
    private String since;
    private String lastUpdate;
    private String email;

    public User(Cursor cursor) {
        if (null != cursor) {
            _id = cursor.getInt(cursor.getColumnIndex(UserDescriptor.Cols._ID));
            description = cursor.getString(cursor.getColumnIndex(UserDescriptor.Cols.DESCRIPTION));
            active = cursor.getInt(cursor.getColumnIndex(UserDescriptor.Cols.ACTIVE))>0;
            address = cursor.getString(cursor.getColumnIndex(UserDescriptor.Cols.ADDRESS));
            phone = cursor.getString(cursor.getColumnIndex(UserDescriptor.Cols.PHONE));
            since = cursor.getString(cursor.getColumnIndex(UserDescriptor.Cols.SINCE));
            lastUpdate = cursor.getString(cursor.getColumnIndex(UserDescriptor.Cols.LASTUPDATE));
            email = cursor.getString(cursor.getColumnIndex(UserDescriptor.Cols.EMAIL));
        }
    }

    public static View newView(Context context, Cursor cursor, ViewGroup parent) {
        // inflating and view binding goes here
        return null;
    }

    @Override
    public void bindView(View view, final Context context) {
        // data binding goes here
    }

    public static class ViewHolder {
        // views go here
    }

}