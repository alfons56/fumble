package com.bozoslivehere.fumble.database;


import android.content.UriMatcher;
import android.net.Uri;

/**
 * This file was generated, don't edit here!
 */

public class ContentDescriptor {
    public static final String AUTHORITY = "com.bozoslivehere.fumble.database.DatabaseContentProvider";

    private static final Uri BASE_URI = Uri.parse("content://"
            + AUTHORITY);

    public static final UriMatcher URI_MATCHER = buildUriMatcher();

    private ContentDescriptor() {
        super();
    }

    public static void init() {
        UserDescriptor.init();
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        // User
        matcher.addURI(AUTHORITY, UserDescriptor.PATH, UserDescriptor.PATH_TOKEN);
        matcher.addURI(AUTHORITY, UserDescriptor.PATH_FOR_ID, UserDescriptor.PATH_FOR_ID_TOKEN);
        return matcher;
    }

    public static class UserDescriptor {
        public static final String PATH = "user";
        public static final String PATH_FOR_ID = "user/#";
        public static final int PATH_TOKEN = 100; //Unique number among tables
        public static final int PATH_FOR_ID_TOKEN = PATH_TOKEN + 1; //Unique number tables
        public static final int LOADER_ID = PATH_FOR_ID_TOKEN + 1; //Unique number tables
        public static final String NAME = PATH;
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH).build();
        public static final Uri CONTENT_FOR_ID_URI = BASE_URI.buildUpon().appendPath(PATH_FOR_ID).build();
        public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/" + PATH;
        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/" + PATH;

        public static class Cols {
            public static final String _ID = "_id";
            public static final String DESCRIPTION = "description";
            public static final String ACTIVE = "active";
            public static final String ADDRESS = "address";
            public static final String PHONE = "phone";
            public static final String SINCE = "since";
            public static final String LASTUPDATE = "lastUpdate";
            public static final String EMAIL = "email";
        }

        public static String[] PROJECTION;

        public static void init() {
            PROJECTION = new String[]{Cols._ID, Cols.DESCRIPTION, Cols.ACTIVE, Cols.ADDRESS, Cols.PHONE, Cols.SINCE, Cols.LASTUPDATE, Cols.EMAIL};
        }
    }
}