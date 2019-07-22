package com.example.eigo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TestOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "TestDB.db";
    private static final String TABLE_NAME = "testdb";
    private static final String _ID = "_id";
    private static final String SYMBOLS = "company";
    private static final String PHONETIC_SYMBOLS_GROUP = "stockprice";
    private static final String PHONETIC_SYMBOLS_GROUP2 = "stockprice2";
    private static final String PHONETIC_SYMBOLS_GROUP3 = "stockprice3";
    private static final String PHONETIC_SYMBOLS_GROUP4 = "stockprice4";
    private static final String PHONETIC_SYMBOLS_GROUP5 = "stockprice5";
    private static final String PHONETIC_SYMBOLS_GROUP6 = "stockprice6";
    private static final String PHONETIC_SYMBOLS_GROUP7 = "stockprice7";
    private static final String PHONETIC_SYMBOLS_GROUP8 = "stockprice8";
    private static final String PHONETIC_SYMBOLS_GROUP9 = "stockprice9";
    private static final String PHONETIC_SYMBOLS_GROUP10 = "stockprice10";
    private static final String PHONETIC_SYMBOLS_GROUP11 = "stockprice11";
    private static final String PHONETIC_SYMBOLS_GROUP12 = "stockprice12";
    private static final String PHONETIC_SYMBOLS_GROUP13 = "stockprice13";
    private static final String PHONETIC_SYMBOLS_GROUP14 = "stockprice14";
    private static final String PHONETIC_SYMBOLS_GROUP15 = "stockprice15";
    private static final String PHONETIC_SYMBOLS_GROUP16 = "stockprice16";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    SYMBOLS + " TEXT," +
                    PHONETIC_SYMBOLS_GROUP + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP2 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP3 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP4 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP5 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP6 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP7 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP8 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP9 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP10 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP11 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP12 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP13 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP14 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP15 + " INTEGER," +
                    PHONETIC_SYMBOLS_GROUP16 + " INTEGER)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    TestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        Log.d("debug", "onCreate(SQLiteDatabase db)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}