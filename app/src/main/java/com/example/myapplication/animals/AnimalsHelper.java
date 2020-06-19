package com.example.myapplication.animals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AnimalsHelper extends SQLiteOpenHelper {

    public static final String database = "animals.db";
    public static final int version = 1;

    public AnimalsHelper(@Nullable Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        AnimalsTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        AnimalsTable.onUpdate(db, oldVersion, newVersion);
    }
}
