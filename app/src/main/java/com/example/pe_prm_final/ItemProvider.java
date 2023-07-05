package com.example.pe_prm_final;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "com.example.pe_prm_final/ItemProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/my_library";
    //    URL ="content://com.example.pe_prm_test/ItemProvider/my_library";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    //Copy ben MydatabaseHelper
    public static final String TABLE_NAME = "my_library";
    public static final String ID = "_id";
    public static final String TITLE = "book_title";
    public static final String AUTHOR = "book_author";
    public static final String PAGES = "book_pages";

    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        MyDatabaseHelper helper = new MyDatabaseHelper(getContext());
        db = helper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] cols, @Nullable String condition, @Nullable String[] condition_Value, @Nullable String sortOrder) {
        return db.query(TABLE_NAME, cols, condition, condition_Value, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.dir/vnd.example.ItemProvider";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues cv) {
        db.insert(TABLE_NAME, null, cv);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String condition, @Nullable String[] condition_Val) {
        int resultValue = db.delete(TABLE_NAME, condition, condition_Val);
        getContext().getContentResolver().notifyChange(uri, null);
        return resultValue;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues cv, @Nullable String condition, @Nullable String[] condition_Val) {
        int resultValue = db.update(TABLE_NAME, cv, condition, condition_Val);
        getContext().getContentResolver().notifyChange(uri, null);
        return resultValue;
    }
}
