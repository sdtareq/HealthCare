package com.example.tareq.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TAREQ on 10/23/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DatabaseHelper";


    private static final int DATABASE_VERSION = 7;

    // Database Name
    private static final String DATABASE_NAME = "maternalHealthDB";


    //  ====================== All  Tables start ===========================
    //-------------------------  Login table start  ------------------------------

    private static final String TABLE_LOGIN = "table_login";
    private static final String USER_COLUMN_ID = "_id";
    private static final String USER_COLUMN_NAME ="user_name";
    private static final String USER_COLUMN_PASSWORD ="user_password";
    private static final String USER_COLUMN_TYPE ="user_type";
    //-------------------------  Login table  end ------------------------------

    //-------------------------  MOTHER table start  ------------------------------

    private static final String TABLE_MOTHER = "table_mother";
    private static final String MOTHER_COLUMN_ID = "_id";           //----------------------------0
    private static final String MOTHER_COLUMN_NAME ="mother_name"; //----------------------------1
    private static final String MOTHER_COLUMN_LAST_MENSTRUATION ="mother_last_menstruation"; // ---2
    private static final String MOTHER_COLUMN_AGE ="mother_age";    //----------------------------/3
    private static final String MOTHER_COLUMN_IS_PREGNANT ="mother_is_pregnant";    //----------------------------/4
    private static final String MOTHER_COLUMN_IS_MESSAGE_DELIVERED ="is_message_delivered";  // ----- 5
    private static final String MOTHER_COLUMN_IS_CHILD_BORN ="is_child_born";  //----- ------------- 6
    private static final String MOTHER_COLUMN_CHILD_BIRTHDAY ="child_birthday";  //------ ----- --- 7
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

        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN    //-------------------   Create Table for LOGIN
                + "("
                + USER_COLUMN_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + USER_COLUMN_NAME + " TEXT UNIQUE, "
                + USER_COLUMN_PASSWORD +  " TEXT, "
                + USER_COLUMN_TYPE +  " TEXT "
                + ")";

        String CREATE_MOTHER_TABLE = "CREATE TABLE " + TABLE_MOTHER    //-------------------   Create Table for MOTHER
                + "("
                + MOTHER_COLUMN_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + MOTHER_COLUMN_NAME + " TEXT , "
                + MOTHER_COLUMN_LAST_MENSTRUATION +  " TEXT , "
                + MOTHER_COLUMN_AGE + " TEXT , "
                + MOTHER_COLUMN_IS_PREGNANT + " TEXT , "
                + MOTHER_COLUMN_IS_MESSAGE_DELIVERED +  " TEXT , "
                + MOTHER_COLUMN_IS_CHILD_BORN + " TEXT , "
                + MOTHER_COLUMN_CHILD_BIRTHDAY +  " TEXT "
                + ")";

         //db.execSQL(CREATE_LOGIN_TABLE);     //-------------------------------------------------------- -----===== //
         db.execSQL(CREATE_MOTHER_TABLE);


    }
//  ===========================   Create Table  end ==================================


//===============================   Drop Table start ======================================

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);  //------------------------------------------  ----======//
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOTHER);

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

    public void addAdmin(){                                // -------------------================   add admin  start ---------------

        String userName = "admin", userPassword = "admin", userType = "test";

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_NAME, userName);
        values.put(USER_COLUMN_PASSWORD, userPassword);
        values.put(USER_COLUMN_TYPE, userType);


        long newRowId = 0;


        try{
            // Inserting Row
               db.insertOrThrow(TABLE_LOGIN, null, values);

        } catch(Exception e){


            Log.d(TAG,"Duplicate Primary key at addAdmin()");


        }
        db.close(); // Closing database connection
        Log.d(TAG,"Successfully inserted");
    }                                                   // -------------------=================   add admin end ---------------


    public void registerMother(Mother mother){     // ------------------- ======================= add mother  start ---------------


        String motherName = mother.getMotherName(), lastMenstruationDate= mother.getLastMenstruationDate(), isMessageDelivered, isChildBorn, childBirthday, isPregnant  ;
if (mother.isMessageDelivered != null){
  isMessageDelivered = mother.isMessageDelivered;
}else {
    isMessageDelivered = "false";
}

        if (mother.isChildBorn != null){
            isChildBorn = mother.isChildBorn;
        }else {
            isChildBorn = "false";
        }

        if (mother.childBirthday != null){
            childBirthday = mother.childBirthday;
        }else {
            childBirthday = "false";
        }

        if (mother.isPregnant != null){
            isPregnant = mother.isPregnant;
        }else {
            isPregnant = "false";
        }



        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOTHER_COLUMN_NAME, motherName);
        values.put(MOTHER_COLUMN_LAST_MENSTRUATION, lastMenstruationDate);
        values.put(MOTHER_COLUMN_IS_MESSAGE_DELIVERED, isMessageDelivered);
        values.put(MOTHER_COLUMN_IS_CHILD_BORN, isChildBorn);
        values.put(MOTHER_COLUMN_CHILD_BIRTHDAY, childBirthday);
        values.put(MOTHER_COLUMN_IS_PREGNANT, isPregnant);



        long newRowId = 0;


        try{
            // Inserting Row
            db.insertOrThrow(TABLE_MOTHER, null, values);

        } catch(Exception e){


            Log.d(TAG,"Duplicate Primary key at resisterMother() ");


        }
        db.close(); // Closing database connection
        Log.d(TAG,"Successfully inserted");
    }                                                   // -------------------   register mother end ---------------




    public boolean isValidUser(User user){              //  ------------------ Check Valid user or not start----------
        String userName = user.getUserName(),
                userPassword = user.getUserPassword(),
                userType = user.getUserType();

        String where = USER_COLUMN_NAME + " =? AND "+ USER_COLUMN_PASSWORD +" =? AND "+ USER_COLUMN_TYPE +" =? ";

        SQLiteDatabase  db = super.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LOGIN,null,where, new String[] {userName,userPassword,userType},null,null,null) ;

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }

        if (cursor.moveToFirst()){
            MainActivity.user_primary_key = String.valueOf(cursor.getInt(0));
            MainActivity.user_name = userName;
        }


        cursor.close();

        return  true;
    }                                                 //  ------------------ Check Valid user or not  end ----------


    public List<Mother> getAllMothers(){                 //  ------------------ get List of All Mothers  start ----------
        List<Mother> allMothers = new ArrayList<>();

        String selectAllMoters = "SELECT  * FROM " + TABLE_MOTHER    ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllMoters, null);

        if (cursor !=null && cursor.moveToFirst()) {

            do {
                String motherName, lastMenstruationDate, isMessageDelivered, isPregnant ,  isChildBorn, childBirthday  ;
                motherName = cursor.getString(1);
                lastMenstruationDate =cursor.getString(2);
                isPregnant = cursor.getString(4);
                isMessageDelivered =cursor.getString(5);
                isChildBorn = cursor.getString(6);
                childBirthday = cursor.getString(7);


                Mother item = new Mother(motherName,lastMenstruationDate,isPregnant,isChildBorn,childBirthday,isMessageDelivered);
                allMothers.add(item);

            } while (cursor.moveToNext());

            cursor.close();
        }



        return allMothers;
    }                                                  //  ------------------ get List of All Mothers  start ----------





}  //===========================  Class end   ==========================