package com.example.gestionbinsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SGLiteBib extends SQLiteOpenHelper {
    public SGLiteBib(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String script="create table livre (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "titre text NOT NULL, nbrePages INTEGER);";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
