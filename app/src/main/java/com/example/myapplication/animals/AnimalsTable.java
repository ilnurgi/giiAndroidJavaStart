package com.example.myapplication.animals;

import android.database.sqlite.SQLiteDatabase;

public class AnimalsTable {

    public static final String TABLE_ANIMALS = "animals";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANIMAL = "animal";

    public static final String CREATE = " " +
            " create table " + TABLE_ANIMALS +
            " ( " + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_ANIMAL + " text not null ); ";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
        populate(db);
    }

    private static void populate(SQLiteDatabase db) {
        db.execSQL(" insert into " + TABLE_ANIMALS + " ( " + COLUMN_ANIMAL + " ) values ('crow')");
        db.execSQL(" insert into " + TABLE_ANIMALS + " ( " + COLUMN_ANIMAL + " ) values ('dingo')");
        db.execSQL(" insert into " + TABLE_ANIMALS + " ( " + COLUMN_ANIMAL + " ) values ('owl')");
        db.execSQL(" insert into " + TABLE_ANIMALS + " ( " + COLUMN_ANIMAL + " ) values ('eagle')");
        db.execSQL(" insert into " + TABLE_ANIMALS + " ( " + COLUMN_ANIMAL + " ) values ('doggy')");
        db.execSQL(" insert into " + TABLE_ANIMALS + " ( " + COLUMN_ANIMAL + " ) values ('kitty')");
        db.execSQL(" insert into " + TABLE_ANIMALS + " ( " + COLUMN_ANIMAL + " ) values ('elephant')");
    }

    public static void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
