package com.bozoslivehere.fumble.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.bozoslivehere.fumble.R;

public class DatabaseContentProvider extends ContentProvider {

    private DatabaseHelper mDataBaseHelper;

    @Override
    public boolean onCreate() {
        init();
        return true;
    }

    public void reset() {
        init();
    }

    public void init() {
        if (null != mDataBaseHelper) {
            mDataBaseHelper.close();
            mDataBaseHelper = null;
        }
        mDataBaseHelper = new DatabaseHelper(getContext(), getRawResource());
        ContentDescriptor.init();
    }

    /**
     * Override this method to change your raw resource
     * By default we assume 'database.sqlite' is present in the res/raw folder
     *
     */
    protected int getRawResource() {
        return R.raw.database;
    }

    @Override
    public String getType(Uri uri) {
        final int match = ContentDescriptor.URI_MATCHER.match(uri);
        switch (match) {

            // User
            case ContentDescriptor.UserDescriptor.PATH_TOKEN:
                return ContentDescriptor.UserDescriptor.CONTENT_TYPE_DIR;
            case ContentDescriptor.UserDescriptor.PATH_FOR_ID_TOKEN:
                return ContentDescriptor.UserDescriptor.CONTENT_TYPE_ITEM;
            default:
                throw new UnsupportedOperationException("URI " + uri
                        + " is not supported.");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final int match = ContentDescriptor.URI_MATCHER.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match) {

            // User
            case ContentDescriptor.UserDescriptor.PATH_TOKEN:
                queryBuilder.setTables(ContentDescriptor.UserDescriptor.NAME);
                break;
            case ContentDescriptor.UserDescriptor.PATH_FOR_ID_TOKEN:
                queryBuilder.setTables(ContentDescriptor.UserDescriptor.NAME);
                queryBuilder.appendWhere(ContentDescriptor.UserDescriptor.Cols._ID + "="
                        + uri.getLastPathSegment());
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int match = ContentDescriptor.URI_MATCHER.match(uri);
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        int rowsDeleted = 0;
        String id;
        switch (match) {

            // User
            case ContentDescriptor.UserDescriptor.PATH_TOKEN:
                rowsDeleted = db.delete(ContentDescriptor.UserDescriptor.NAME, selection,
                        selectionArgs);
                break;
            case ContentDescriptor.UserDescriptor.PATH_FOR_ID_TOKEN:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(ContentDescriptor.UserDescriptor.NAME,
                            ContentDescriptor.UserDescriptor.Cols._ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = db.delete(ContentDescriptor.UserDescriptor.NAME,
                            ContentDescriptor.UserDescriptor.Cols._ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = ContentDescriptor.URI_MATCHER.match(uri);
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        long id = 0;
        switch (match) {

            // User
            case ContentDescriptor.UserDescriptor.PATH_TOKEN:
                id = db.insert(ContentDescriptor.UserDescriptor.NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(ContentDescriptor.UserDescriptor.PATH + "/" + id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        final int match = ContentDescriptor.URI_MATCHER.match(uri);
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        int rowsUpdated = 0;
        String id;
        switch (match) {

            // User
            case ContentDescriptor.UserDescriptor.PATH_TOKEN:
                rowsUpdated = db.update(ContentDescriptor.UserDescriptor.NAME, values, selection, selectionArgs);
                break;
            case ContentDescriptor.UserDescriptor.PATH_FOR_ID_TOKEN:
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(ContentDescriptor.UserDescriptor.NAME,
                            values,
                            ContentDescriptor.UserDescriptor.Cols._ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = db.update(ContentDescriptor.UserDescriptor.NAME,
                            values,
                            ContentDescriptor.UserDescriptor.Cols._ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }


}
