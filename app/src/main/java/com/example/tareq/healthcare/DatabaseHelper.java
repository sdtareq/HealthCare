package com.example.tareq.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by TAREQ on 10/23/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DatabaseHelper";


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "userDB";


    //  ====================== All  Tables start ===========================
    //-------------------------  Login table start  ------------------------------

    private static final String TABLE_LOGIN = "table_login";
    private static final String COLUMN_USER_ID = "_id";
    private static final String COLUMN_USER_NAME ="user_name";
    private static final String COLUMN_USER_PASSWORD ="user_password";
    private static final String COLUMN_USER_TYPE ="user_type";
    //-------------------------  Login table  end ------------------------------
   //  ====================== All  Tables end ===========================


//=============================    Constructor  start ================================

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

//=============================    Constructor end  ================================



//  ===========================   Create Table  start ==================================

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN
                + "("
                + COLUMN_USER_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + COLUMN_USER_NAME + " TEXT UNIQUE, "
                + COLUMN_USER_PASSWORD +  " TEXT, "
                + COLUMN_USER_TYPE +  " TEXT "
                + ")";


         db.execSQL(CREATE_LOGIN_TABLE);


    }
//  ===========================   Create Table  end ==================================


//===============================   Drop Table start ======================================

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

        // Create tables again
        onCreate(db);
    }



//===============================   Drop Table end ======================================

//===============================   Downgrade Table start ======================================


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

 //===============================   Downgrade Table end ======================================

// ======================================    Methods   ======================================

    public void addAdmin(){                                // -------------------   add admin  end---------------

        String userName = "admin", userPassword = "admin", userType = "test";

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, userName);
        values.put(COLUMN_USER_PASSWORD, userPassword);
        values.put(COLUMN_USER_TYPE, userType);


        long newRowId = 0;


        try{
            // Inserting Row
               db.insertOrThrow(TABLE_LOGIN, null, values);

        } catch(Exception e){


            Log.d(TAG,"Duplicate Primary key at addAdmin()");


        }
        db.close(); // Closing database connection
        Log.d(TAG,"Successfully inserted");
    }                                                   // -------------------   add admin end ---------------



    public boolean isValidUser(User user){              //  ------------------ Check Valid user or not ----------
        String userName = user.getUserName(),
                userPassword = user.getUserPassword(),
                userType = user.getUserType();

        String where = COLUMN_USER_NAME + " =? AND "+COLUMN_USER_PASSWORD+" =? AND "+COLUMN_USER_TYPE+" =? ";

        SQLiteDatabase  db = super.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LOGIN,null,where, new String[] {userName,userPassword,userType},null,null,null) ;

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return  true;
    }


}  //===========================  Class end   ==========================