package com.bozoslivehere.fumble.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DatabaseHelper  extends SDCardSQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.sqlite";
    private static final int DATABASE_VERSION = 0;
    Context mCtx;
    String PATH;

    public DatabaseHelper(Context ctx, int rawResource) {
        super(ctx.getExternalFilesDir(null) + "/db", DATABASE_NAME, null, DATABASE_VERSION);


        mCtx = ctx;
        PATH = getDirPath();
        if (new File(PATH, DATABASE_NAME).exists() == false) {
            copyDB(rawResource);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected boolean copyDB(int rawResource) {

        boolean res = true;

        FileOutputStream out = null;
        InputStream dis = null;

        try {
            if (PATH.compareTo("none") == 0) {
                return false;
            }

            File dir = new File(PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(PATH, DATABASE_NAME);

            out = new FileOutputStream(file.getAbsolutePath(), true);

            dis = mCtx.getResources().openRawResource(rawResource);
            byte[] buffer = new byte[4096];

            int length = 0;
            while ((length = dis.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            dis.close();
            dis = null;

            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("XS2TheWorld", "Problem decompressing a database, is there enough free space?", e);
            res = false;
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
            }
        }
        return res;
    }

}