package com.example.dreamsalonfinderapp;

import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.TABLENAME;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_EMAIL;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_FULLNAME;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_ID;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_PASSWORD;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "Users.db";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLENAME + " ("
            + USERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERS_COLUMN_FULLNAME + " TEXT,"
            + USERS_COLUMN_EMAIL + " TEXT,"  + USERS_COLUMN_PASSWORD + " TEXT " +")";


    private String SELECT_BY_ID = "SELECT id FROM" + TABLENAME + " WHERE " + USERS_COLUMN_EMAIL + " = ?";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLENAME;



    public DBHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 3);
        Log.d("Database Operations", "Database created");
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL(DROP_USER_TABLE);
        onCreate(database);
    }

    public Boolean deleteData(String keyName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[] {keyName});

        if(cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{keyName});

            if(result == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);

        return cursor;
    }

  public void addUser(String name, String email, String password, SQLiteDatabase database) {

        ContentValues values = new ContentValues();

        values.put(USERS_COLUMN_FULLNAME, name);
        values.put(USERS_COLUMN_EMAIL, email);
        values.put(USERS_COLUMN_PASSWORD, password);

        // Inserting Row
        database.insert(TABLENAME, null, values);
        Log.d("Database Operations", "One user row created");

    }


    // update user details
    public void updateUserData( String fullName, String email, String password, String confirmPassword, SQLiteDatabase database) {
        int id = Integer.parseInt(SELECT_BY_ID);

        ContentValues contentValues = new ContentValues();

        contentValues.put("fName", fullName);
        contentValues.put("email", email);
        contentValues.put("password", password);


        String selection = USERS_COLUMN_ID + " = " + id;

        database.update(TABLENAME, contentValues, selection, null);

       // database.close();

        }

    protected Cursor readData(SQLiteDatabase database) {
        String id = (USERS_COLUMN_ID);
        String[] columns = {USERS_COLUMN_FULLNAME, USERS_COLUMN_EMAIL, USERS_COLUMN_PASSWORD};
        Cursor cursor = database.query(TABLENAME, columns, " id = ? ", new String[]{id}, null, null, null);

        if(cursor.moveToFirst()) {

        }
        return cursor;
        }



    // delete
    public void deleteUser(User user) {
        SQLiteDatabase database = this.getWritableDatabase();
        // delete user record by id
        database.delete(TABLENAME, USERS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        database.close();
    }

    // checks if user exists using 2 params
    public boolean checkUser(String email, String password) {
        String [] columns = {
                USERS_COLUMN_ID
        };
        SQLiteDatabase database = this.getReadableDatabase();

        String select = USERS_COLUMN_EMAIL +" =?" + " AND " + USERS_COLUMN_PASSWORD +" =?";
        String [] selectionParams = {email, password};
        Cursor cursor = database.query(TABLENAME, columns,select,selectionParams,null,null, null);

        int count = cursor.getCount();

        cursor.close();
        database.close();
        if(count > 0) {
            return true;
        }
        return false;
    }


    // checks if user exists using one param
    public boolean checkUser(String email) {
        String[] columns = {
                USERS_COLUMN_ID
        };
        SQLiteDatabase database = this.getReadableDatabase();

        String select = USERS_COLUMN_EMAIL + " = ?";
        String[] selectionParams = {email};
        Cursor cursor = database.query(TABLENAME, columns, select, selectionParams, null, null, null);

        int count = cursor.getCount();

        cursor.close();
        database.close();
        if (count > 0) {
            return true;
        }
        return false;
    }
}
