package com.tb.flp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "TriangleAreas.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "triangles";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_A = "a";
    private static final String COLUMN_B = "b";
    private static final String COLUMN_C = "c";
    private static final String COLUMN_AREA = "area";
    private static final String COLUMN_TIME = "time";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_A + " INTEGER, " +
                COLUMN_B + " INTEGER, " +
                COLUMN_C + " INTEGER, " +
                COLUMN_AREA + " DOUBLE, " +
                COLUMN_TIME + " LONG);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTriangle(String a, String b, String c, double area){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_A, a);
        cv.put(COLUMN_B, b);
        cv.put(COLUMN_C, c);
        cv.put(COLUMN_AREA, area);
        cv.put(COLUMN_TIME, System.currentTimeMillis());

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
