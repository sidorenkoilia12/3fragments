package com.entagesoft.a3fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ilia on 04-Oct-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactDB1";
    public static final String TABLE_NAME = "ContactTable1";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TELEPHONE = "telephone";
    public  final Context ctx;

    public DBHelper DBHelper;
    public SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (" + KEY_ID + " integer primary key, " + KEY_NAME + " text, "
            + KEY_SURNAME + " text, " + KEY_EMAIL + " text, " + KEY_TELEPHONE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void open(){

        DBHelper = new DBHelper(ctx);
        db = DBHelper.getWritableDatabase();
        //db = DBHelper.getReadableDatabase();
    }

    public void close(){

        if(DBHelper != null){

            DBHelper.close();
        }
    }

    public Cursor getAllData(){

        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public Cursor getOneRec(long id){

        String[] selectionArgs = new String[] {String.valueOf(id)};
        return db.query(TABLE_NAME, null, KEY_ID + " = ?", selectionArgs, null, null, null);
    }

    public String getTelephone(long id){

        String[] selectionArgs = new String[] {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", selectionArgs, null, null, null);

        cursor.moveToFirst();

        return cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TELEPHONE));
    }

    public String getEmail(long id){

        String[] selectionArgs = new String[] {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", selectionArgs, null, null, null);

        cursor.moveToFirst();

        return cursor.getString(cursor.getColumnIndex(DBHelper.KEY_EMAIL));
    }

    public boolean addItem(String name, String surname, String email, String telephone){

        ContentValues contentValues = new ContentValues();
        boolean succ = false;

        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_SURNAME, surname);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_TELEPHONE, telephone);

        if(db.insert(TABLE_NAME, null, contentValues) > 0){

            succ = true;
        }
        return succ;
    }

    public boolean updateItem(long id, String name, String surname, String email, String telephone){

        ContentValues contentValues = new ContentValues();
        boolean succ = false;

        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_SURNAME, surname);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_TELEPHONE, telephone);

        if(db.update(TABLE_NAME, contentValues, KEY_ID + " = " + id, null) > 0){

            succ = true;
        }
        return succ;
    }

    public boolean deleteRec(long id){

        boolean succ = false;

        if(db.delete(TABLE_NAME, KEY_ID + " = " + id, null) > 0){

            succ = true;
        }
        return succ;
    }
}
