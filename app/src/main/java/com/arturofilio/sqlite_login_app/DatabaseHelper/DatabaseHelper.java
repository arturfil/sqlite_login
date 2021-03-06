package com.arturofilio.sqlite_login_app.DatabaseHelper;

import com.arturofilio.sqlite_login_app.models.Account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static String DB_NAME = "budget.db";
    private static String TABLE_NAME = "accounts";
    private static String COL0 = "ID";
    private static String COL1 = "username";
    private static String COL2 = "password";
    private static String COL3 = "full_name";
    private static String COL4 = "budget";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                "CREATE TABLE " +
                TABLE_NAME + "(" +
                COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean create(Account account) {
        boolean result = true;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, account.getUsername());
            contentValues.put(COL2, account.getPassword());
            contentValues.put(COL3, account.getFullName());
            contentValues.put(COL4, account.getBudget());
            result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues) > 0;
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    public boolean update(Account account) {
        boolean result = true;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, account.getUsername());
            contentValues.put(COL2, account.getPassword());
            contentValues.put(COL3, account.getFullName());
            contentValues.put(COL4, account.getBudget());
            result = sqLiteDatabase.update(TABLE_NAME, contentValues, COL0 + " = ?",
                        new String[] { String.valueOf(account.getId()) }) > 0;
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    public Account login(String username, String password) {
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME +
                    " WHERE username = ? AND password = ?", new String[] {username, password});
            if(cursor.moveToFirst()) {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setFullName(cursor.getString(3));
                account.setBudget(cursor.getInt(4));
            }
        } catch (Exception e) {
            account = null;
        }
        return account;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE username = ?",
                new String[]{username}
        );
        if(cursor.getCount()>0) {
            return false;
        } else {
            return true;
        }
    }

    public Account findAccount(int id) {
        Account account = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(
                    "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?",
                    new String[] {String.valueOf(id)}
            );
            if(cursor.moveToFirst()) {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setFullName(cursor.getString(3));
                account.setBudget(cursor.getShort(4));
            }
        } catch (Exception e) {
            account = null;
        }
        return account;
    }

    public boolean checkPassword(String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE password = ?",
                new String[]{password}
        );
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }


}
