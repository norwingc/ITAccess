package com.example.norwinguerrero.itaccess.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.norwinguerrero.itaccess.pojo.Access;

import java.util.ArrayList;


/**
 * Created by Norwin Guerrero on 2/2/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "db_itaccess";

    private static final String TABLE_ACCESS = "access";

    // Access Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_NAME = "fullname";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_PURPOSE  = "purposeofvisit";
    private static final String KEY_SIGNATURE = "signature";
    private static final String KEY_TIMEIN = "timein";
    private static final String KEY_TIMEOUT = "timeout";
    private static final String KEY_ESCORT = "escort";
    private static final String KEY_PHOTO = "photo";

    private final ArrayList<Access> access_list = new ArrayList<Access>();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCESS_TABLE = "CREATE TABLE " + TABLE_ACCESS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE +" TEXT," + KEY_NAME + " TEXT,"
               + KEY_COMPANY + " TEXT," + KEY_PURPOSE + " TEXT,"
                + KEY_SIGNATURE + " TEXT," + KEY_TIMEIN + " TEXT," + KEY_TIMEOUT + " TEXT," + KEY_ESCORT + " TEXT," + KEY_PHOTO + " TEXT" + ")";
        db.execSQL(CREATE_ACCESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCESS);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Access access) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DATE, access.get_date());
        values.put(KEY_NAME, access.get_fullname());
        values.put(KEY_COMPANY, access.get_company());
        values.put(KEY_PURPOSE, access.get_purposeofvisit());
        values.put(KEY_SIGNATURE, access.get_signature());
        values.put(KEY_TIMEIN, access.get_timein());
        values.put(KEY_TIMEOUT, access.get_timeout());
        values.put(KEY_ESCORT, access.get_escort());
        values.put(KEY_PHOTO, access.get_photo());

        // Inserting Row
        db.insert(TABLE_ACCESS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Access getAccess(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ACCESS, new String[]{KEY_ID, KEY_DATE,
                        KEY_NAME, KEY_COMPANY, KEY_PURPOSE, KEY_SIGNATURE, KEY_TIMEIN, KEY_TIMEOUT, KEY_ESCORT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Access access = new Access();
        access.set_id(cursor.getInt(0));
        access.set_date(cursor.getString(1));
        access.set_fullname(cursor.getString(2));
        access.set_company(cursor.getString(3));
        access.set_purposeofvisit(cursor.getString(4));
        access.set_signature(cursor.getString(5));
        access.set_timein(cursor.getString(6));
        access.set_timeout(cursor.getString(7));
        access.set_escort(cursor.getString(8));
        access.set_photo(cursor.getBlob(9));

        // return contact
        cursor.close();
        db.close();

        return access;
    }

    // Getting All Contacts
    public ArrayList<Access> getAccess() {
        try {
            access_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_ACCESS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Access access = new Access();
                    access.set_id(Integer.parseInt(cursor.getString(0)));
                    access.set_date(String.valueOf(cursor.getString(1)));
                    access.set_fullname(String.valueOf(cursor.getString(2)));
                    access.set_company(String.valueOf(cursor.getString(3)));
                    access.set_purposeofvisit(String.valueOf(cursor.getString(4)));
                    access.set_signature(String.valueOf(cursor.getString(5)));
                    access.set_timein(String.valueOf(cursor.getString(6)));
                    access.set_timeout(String.valueOf(cursor.getString(7)));
                    access.set_escort(String.valueOf(cursor.getString(8)));
                   // access.set_escort(String.valueOf(cursor.getBlob(9)));
                    access_list.add(access);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            return access_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }

        return access_list;
    }

    public ArrayList<Access> getAccessDate(String date) {
        try {
            access_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_ACCESS + " WHERE " + KEY_DATE + " =  \"" + date + "\"";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Access access = new Access();
                    access.set_id(Integer.parseInt(cursor.getString(0)));
                    access.set_date(String.valueOf(cursor.getString(1)));
                    access.set_fullname(String.valueOf(cursor.getString(2)));
                    access.set_company(String.valueOf(cursor.getString(3)));
                    access.set_purposeofvisit(String.valueOf(cursor.getString(4)));
                    access.set_signature(String.valueOf(cursor.getString(5)));
                    access.set_timein(String.valueOf(cursor.getString(6)));
                    access.set_timeout(String.valueOf(cursor.getString(7)));
                    access.set_escort(String.valueOf(cursor.getString(8)));
                    //access.set_photo(Byte.valueOf(cursor.getString(8)));
                    access_list.add(access);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            return access_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }

        return access_list;
    }

    // Updating single contact
    public int updateAccess(Access access) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, access.get_date());
        values.put(KEY_NAME, access.get_fullname());
        values.put(KEY_COMPANY, access.get_company());
        values.put(KEY_PURPOSE, access.get_purposeofvisit());
        values.put(KEY_SIGNATURE, access.get_signature());
        values.put(KEY_TIMEIN, access.get_timein());
        values.put(KEY_TIMEOUT, access.get_timeout());
        values.put(KEY_ESCORT, access.get_escort());

        // updating row
        return db.update(TABLE_ACCESS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(access.get_id()) });
    }

    // Deleting single contact
    public void deleteAccess(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCESS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
