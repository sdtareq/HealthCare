package com.example.tareq.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by TAREQ on 10/23/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;

    private static final String TAG = "DatabaseHelper";
    String  syncStatusFalse = "false"; //======================================================================== set Sync Status ------
    String statusDead = "dead";

    private static final int DATABASE_VERSION = 40;


    // Database Name
    protected static final String DATABASE_NAME = "maternalHealthDB";


    //  ====================== All  Tables start ===========================
    //-------------------------  Login table start  ------------------------------

    private static final String TABLE_LOGIN = "table_login";
    private static final String USER_COLUMN_ID = "_id";
    private static final String USER_COLUMN_NAME = "user_name";
    private static final String USER_COLUMN_PASSWORD = "user_password";
    private static final String USER_COLUMN_TYPE = "user_type";
    //-------------------------  Login table  end ------------------------------

    //-------------------------  MOTHER table start  ------------------------------

    protected static final String TABLE_MOTHER = "table_mother";
    protected static final String MOTHER_COLUMN_ID = "_id";           //----------------------------0
    protected static final String MOTHER_COLUMN_NAME = "mother_name"; //----------------------------1
    protected static final String MOTHER_COLUMN_HUSBAND_NAME = "husband_name"; //----------------------------2
    protected static final String MOTHER_COLUMN_AGE = "mother_age";    //----------------------------3
    protected static final String MOTHER_COLUMN_PHONE_NUMBER = "phone_number";  //------ ----- --- 4
    protected static final String MOTHER_COLUMN_DESIRED_CALLING_TIME = "desired_calling_time";  //------ ----- --- 5
    protected static final String MOTHER_COLUMN_MOTHER_ADDRESS = "mother_address";  //------ ----- --- 6
    protected static final String MOTHER_COLUMN_GIS_LOCATION = "GIS_location";  //------ ----- --- 7
    protected static final String MOTHER_COLUMN_ALTERNATIVE_PHONE_NO = "alternative_phone";  //------ ----- --- 8
    protected static final String MOTHER_COLUMN_ALTERNATIVE_PHONE_OWNER_NAME = "alternative_phone_owner_name";  //------ ----- --- 9
    protected static final String MOTHER_COLUMN_DHIS_ID = "DHIS_ID";  //------ ----- --- 10
    protected static final String MOTHER_COLUMN_LAST_MENSTRUATION = "mother_LMP"; // ---11
    protected static final String MOTHER_COLUMN_PREGNANCY_STATE = "pregnancy_state";    //----------------------------/12
    protected static final String MOTHER_COLUMN_DELIVERY_DATE = "delivery_date";    //----------------------------/13
    protected static final String MOTHER_COLUMN_LOGIN_USER_NAME = "login_name";    //----------------------------/14
    protected static final String MOTHER_COLUMN_SYNC_STATUS = "sync_status";    //----------------------------/15
    protected static final String MOTHER_COLUMN_TIMESTAMP = "created_at";    //----------------------------/16

                     //-------------------------  MOTHER table  end ------------------------------

       //-------------------------  ANC PNC MESSAGE DELIVERY table for mother start  ------------------------------

    protected static final String TABLE_ANC_PNC_MSG    = "tbl_anc_pnc_message";
    protected static final String ANC_PNC_MSG_DELIVERY_COL_ID                            = "_id";           //----------------------------0
    protected static final String ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID              = "mother_id";           //----------------------------1
    protected static final String ANC_PNC_MSG_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED       = "is_ANC_1_message_delivered"; //----------------------------2
    protected static final String ANC_PNC_MSG_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED       = "is_ANC_2_message_delivered"; //----------------------------3
    protected static final String ANC_PNC_MSG_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED        = "is_ANC_3_message_delivered"; //----------------------------4
    protected static final String ANC_PNC_MSG_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED       = "is_ANC_4_message_delivered"; //----------------------------5
    protected static final String ANC_PNC_MSG_DELIVERY_COL_IS_PNC_MSG_DELIVERED         = "is_PNC_message_delivered"; //----------------------------6
    protected static final String ANC_PNC_MSG_DELIVERY_COL_IS_MOTHER_DEAD                = "is_mother_dead";               //----------------------------7
    protected static final String ANC_PNC_MSG_DELIVERY_COL_SYNC_STATUS                  = "sync_status"; //----------------------------8
    protected static final String ANC_PNC_MSG_DELIVERY_COL_TIMESTAMP                    = "created_at"; //----------------------------9



    //------------------------- ANC PNC MESSAGE DELIVERY table  for mother end ------------------------------


    //------------------------- DELIVERY AND CHILD MESSAGE DELIVERY table for child start  ------------------------------

    protected static final String TABLE_DELIVERY_AND_CHILD_MSG = "tbl_delivery_and_child_message";
    protected static final String DELIVERY_AND_CHILD_MSG_COL_ID                                      = "_id";                                                                          //----------------------------0
    protected static final String DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID                        = "mother_id";                                                      //----------------------------1
    protected static final String DELIVERY_AND_CHILD_MSG_COL_IS_PRE_DELIVERY_MSG_DELIVERED          = "is_pre_delivery_message_delivered";           //----------------------------2
    protected static final String DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED    = "is_child_0_to_14_days_message_delivered";     //----------------------------3
    protected static final String DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED     = "is_child_1_2_3_months_message_delivered";      //----------------------------4
    protected static final String DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED    = "is_child_6_to_8_months_message_delivered";    //----------------------------5
    protected static final String DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED   = "is_child_9_to_12_months_message_delivered";  //----------------------------6
    protected static final String DELIVERY_AND_CHILD_MSG_COL_SYNC_STATUS                             = "sync_status";                                                         //----------------------------7
    protected static final String DELIVERY_AND_CHILD_MSG_COL_TIMESTAMP                               = "created_at";                                  //----------------------------8




    //------------------------- DELIVERY AND CHILD MESSAGE DELIVERY table for child end ------------------------------



    //-------------------------  Child table start  ------------------------------

    protected static final String TABLE_CHILD = "table_child";
    protected static final String CHILD_COLUMN_ID = "_id";           //----------------------------0
    protected static final String CHILD_COLUMN_MOTHER_ID = "mother_id";           //----------------------------1
    protected static final String CHILD_COLUMN_MOTHER_NAME = "child_mother_name";           //----------------------------2
    protected static final String CHILD_COLUMN_NAME = "child_name"; //----------------------------3
    protected static final String CHILD_COLUMN_SEX = "sex_of_child"; //-------------------------------- ---4
    protected static final String CHILD_COLUMN_DATE_OF_BIRTH = "child_date_of_birth";    //----------------------------/5
    protected static final String CHILD_COLUMN_BIRTH_WEIGHT = "child_weight";    //----------------------------/6
    protected static final String CHILD_COLUMN_ID_NUMBER_OF_CHILD = "id_number_of_child";  // ----- 7

    //-------------------------  child table  end ------------------------------



    //-------------------------  Child Follow Up table start  ------------------------------

    protected static final String TABLE_CHILD_FOLLOW_UP = "table_child_follow_up";
    protected static final String CHILD_FOLLOW_UP_COL_ID            = "_id";           //----------------------------0
    protected static final String CHILD_FOLLOW_UP_COL_MOTHER_ID     = "mother_id";           //----------------------------1
    protected static final String CHILD_FOLLOW_UP_COL_MOTHER_NAME   = "child_mother_name";           //----------------------------2
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_NAME    = "child_name"; //----------------------------3
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_TABLE_ID = "child_table_id"; //-------------------------------- ---4
    protected static final String CHILD_FOLLOW_UP_COL_DATE_OF_VISIT = "date_of_visit";    //----------------------------/5
    protected static final String CHILD_FOLLOW_UP_COL_AGE_OF_CHILD_IN_MONTH = "age_of_child_in_month";    //----------------------------/6
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_WEIGHT  = "child_weight";    //----------------------------/7
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_HEIGHT   = "child_height";  // ----- 8
    protected static final String CHILD_FOLLOW_UP_COL_SYNC_STATUS   = "sync_status";  // ----- 9
    protected static final String CHILD_FOLLOW_UP_COL_TIMESTAMP ="created_at";  //----- -------------10

    //-------------------------  child follow up table  end ------------------------------
    //  ====================== All  Tables end ===========================


//=============================    Constructor  start ================================

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }



//=============================    Constructor end  ================================


//  ===========================   Create Table  start ==================================

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN    //-------------------   Create Table for LOGIN
                + "("
                + USER_COLUMN_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + USER_COLUMN_NAME + " TEXT UNIQUE, "
                + USER_COLUMN_PASSWORD + " TEXT, "
                + USER_COLUMN_TYPE + " TEXT "
                + ")";

        String CREATE_MOTHER_TABLE = "CREATE TABLE " + TABLE_MOTHER    //-------------------   Create Table for MOTHER
                + "("
                + MOTHER_COLUMN_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + MOTHER_COLUMN_NAME + " TEXT , "
                + MOTHER_COLUMN_HUSBAND_NAME + " TEXT , "
                + MOTHER_COLUMN_AGE + " TEXT , "
                + MOTHER_COLUMN_PHONE_NUMBER + " TEXT , "
                + MOTHER_COLUMN_DESIRED_CALLING_TIME + " TEXT , "
                + MOTHER_COLUMN_MOTHER_ADDRESS + " TEXT , "
                + MOTHER_COLUMN_GIS_LOCATION + " TEXT , "
                + MOTHER_COLUMN_ALTERNATIVE_PHONE_NO + " TEXT , "
                + MOTHER_COLUMN_ALTERNATIVE_PHONE_OWNER_NAME + " TEXT , "
                + MOTHER_COLUMN_DHIS_ID + " TEXT , "
                + MOTHER_COLUMN_LAST_MENSTRUATION + " TEXT , "
                + MOTHER_COLUMN_PREGNANCY_STATE + " TEXT , "
                + MOTHER_COLUMN_DELIVERY_DATE + " TEXT ,"
                + MOTHER_COLUMN_LOGIN_USER_NAME + " TEXT ,"
                + MOTHER_COLUMN_SYNC_STATUS + " TEXT ,"
                + MOTHER_COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";

        String CREATE_ANC_PNC_MESSAGE_TABLE = "CREATE TABLE " + TABLE_ANC_PNC_MSG    //-------------------   Create Table for ANC PNC MSG DELIVERY
                + "("
                + ANC_PNC_MSG_DELIVERY_COL_ID                      + " INTEGER UNIQUE PRIMARY KEY, "
                + ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID          + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED     + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED     + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED     + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED    + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_IS_PNC_MSG_DELIVERED      + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_IS_MOTHER_DEAD           + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_SYNC_STATUS                + " TEXT , "
                + ANC_PNC_MSG_DELIVERY_COL_TIMESTAMP                 + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";

        String CREATE_DELIVERY_AND_CHILD_MESSAGE_TABLE = "CREATE TABLE " + TABLE_DELIVERY_AND_CHILD_MSG    //-------------------   Create Table for DELIVERY AND CHILD MESSAGE
                + "("
                + DELIVERY_AND_CHILD_MSG_COL_ID                                    + " INTEGER UNIQUE PRIMARY KEY, "
                + DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID                      + " TEXT , "
                + DELIVERY_AND_CHILD_MSG_COL_IS_PRE_DELIVERY_MSG_DELIVERED           + " TEXT , "
                + DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED     + " TEXT , "
                + DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED      + " TEXT , "
                + DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED    + " TEXT , "
                + DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED      + " TEXT , "
                + DELIVERY_AND_CHILD_MSG_COL_SYNC_STATUS                            + " TEXT , "
                + DELIVERY_AND_CHILD_MSG_COL_TIMESTAMP                              + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";

        String CREATE_CHILD_TABLE = "CREATE TABLE " + TABLE_CHILD    //-------------------   Create Table for Child
                + "("
                + CHILD_COLUMN_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + CHILD_COLUMN_MOTHER_ID + " TEXT , "
                + CHILD_COLUMN_MOTHER_NAME + " TEXT , "
                + CHILD_COLUMN_NAME + " TEXT , "
                + CHILD_COLUMN_SEX + " TEXT , "
                + CHILD_COLUMN_DATE_OF_BIRTH + " TEXT , "
                + CHILD_COLUMN_BIRTH_WEIGHT + " TEXT , "
                + CHILD_COLUMN_ID_NUMBER_OF_CHILD + " TEXT  "

                + ")";

        String CREATE_CHILD_FOLLOW_UP_TABLE = "CREATE TABLE " + TABLE_CHILD_FOLLOW_UP    //-------------------   Create Table for Child
                + "("
                + CHILD_FOLLOW_UP_COL_ID            + " INTEGER UNIQUE PRIMARY KEY, "
                + CHILD_FOLLOW_UP_COL_MOTHER_ID     +  " TEXT , "
                + CHILD_FOLLOW_UP_COL_MOTHER_NAME   +  " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_NAME    +  " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_TABLE_ID + " TEXT , "
                + CHILD_FOLLOW_UP_COL_DATE_OF_VISIT      + " TEXT , "
                + CHILD_FOLLOW_UP_COL_AGE_OF_CHILD_IN_MONTH     + " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_WEIGHT    + " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_HEIGHT       + " TEXT ,"
                + CHILD_FOLLOW_UP_COL_SYNC_STATUS       + " TEXT ,"
                + CHILD_FOLLOW_UP_COL_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"

                + ")";

        db.execSQL(CREATE_LOGIN_TABLE);     //-------------------------------------------------------- -----===== //
        db.execSQL(CREATE_MOTHER_TABLE);
        db.execSQL(CREATE_ANC_PNC_MESSAGE_TABLE);
        db.execSQL(CREATE_DELIVERY_AND_CHILD_MESSAGE_TABLE);
        db.execSQL(CREATE_CHILD_TABLE);
        db.execSQL(CREATE_CHILD_FOLLOW_UP_TABLE);


    }
//  ===========================   Create Table  end ==================================


//===============================   Drop Table start ======================================

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);  //------------------------------------------  ----======//
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOTHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANC_PNC_MSG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELIVERY_AND_CHILD_MSG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHILD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHILD_FOLLOW_UP);

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

    public void addAdmin() {                                // -------------------================   add admin  start ---------------

        //String userName = "admin", userPassword = "admin", userType = "test";
        List<User> userList = new ArrayList<>();

        userList.add(new User("admin","admin","test"));

        userList.add(new User("1CHCP","1CHCP","CHCP"));
        userList.add(new User("2CHCP","2CHCP","CHCP"));

        userList.add(new User("1FWA","1FWA","FWA"));
        userList.add(new User("2FWA","2FWA","FWA"));

        userList.add(new User("1HA","1HA","HA"));
        userList.add(new User("2HA","2HA","HA"));

        SQLiteDatabase db = super.getWritableDatabase();

        for (User user:userList
             ) {

            ContentValues values = new ContentValues();
            values.put(USER_COLUMN_NAME, user.getUserName());
            values.put(USER_COLUMN_PASSWORD, user.getUserPassword());
            values.put(USER_COLUMN_TYPE, user.getUserType());


            long newRowId = 0;


            try {
                // Inserting Row
                db.insertOrThrow(TABLE_LOGIN, null, values);

            } catch (Exception e) {


                Log.d(TAG, "Duplicate Primary key at addAdmin()");


            }
        }

        db.close(); // Closing database connection
        Log.d(TAG, "Successfully inserted");
    }                                                   // -------------------=================   add admin end ---------------


                                                // -------------------=================   get Last Follow Up of a child end ---------------

    public Child getLastFollowUp(String motherPrimaryKey){
        Child child = new Child();
       String query = "SELECT * FROM "+ TABLE_CHILD_FOLLOW_UP + " WHERE "+ CHILD_FOLLOW_UP_COL_MOTHER_ID+" = ? "+" ORDER BY "+CHILD_FOLLOW_UP_COL_TIMESTAMP+" DESC";
       // String query = "SELECT * FROM "+ TABLE_CHILD_FOLLOW_UP +" ORDER BY "+CHILD_FOLLOW_UP_COL_TIMESTAMP+" DESC";

        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,new String[]{motherPrimaryKey});

        if (cursor != null && cursor.moveToFirst()){

            child.setChildDateOfVisit(cursor.getString(5));
            child.setChildWeight(cursor.getString(6));
            child.setChildHeight(cursor.getString(7));
//           do {
//               Log.d(TAG, "======  "+cursor.getString(5));
//
//           }while (cursor.moveToNext());
        }

        db.close();
        return child;
    }

    public boolean isLoginUserTableEmpty(){
        SQLiteDatabase db = super.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_LOGIN;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor != null && cursor.moveToFirst()){
            db.close();
            Log.d(TAG,"Login Table contains Values");
            return false;
        }

        Log.d(TAG, "============= LoginTable Is Empty");
        db.close();
        return true;
    }
    public boolean isMotherTableEmpty() {     // ------------------- ======================= Check mother table is empty or not start ---------------


        SQLiteDatabase db = super.getWritableDatabase();


        String selectAllMothers = "SELECT  * FROM " + TABLE_MOTHER;  // used for checking
        Cursor cursor = db.rawQuery(selectAllMothers, null);            // used for checking

        if (cursor != null && cursor.moveToFirst()) {   // Check if Mother table is not null

            db.close(); // Closing database connection
            Log.d(TAG, "Mother table contain values");
            return false;
        } // if end
        Log.d(TAG, "Mother table is empty");
        db.close(); // Closing database connection
        return true;
    }                                                   // -------------------   Check mother table is empty or not end ---------------

    // -------------------   addChildFollowUp start ---------------

     public void addChildFollowUp(Child child){

         String  childFollowUpTableId,
                 childTableId           = child.getChildId(),
                 childMotherName        = child.getChildMotherName(),
                 childMotherId           = child.getChildMotherTableId(),
                 childName              = child.getChildName(),
                 childDateOfVisit           = child.getChildDateOfVisit(),
                 childAge                = child.getChildAge(),
                 childWeight            = child.getChildWeight(),
                 childHeight            = child.getChildHeight();

if(child.getChildFollowUpColumnId() != null){
    childFollowUpTableId = child.getChildFollowUpColumnId();
}else {
    childFollowUpTableId = generateUniqueId();
}


         SQLiteDatabase db = super.getWritableDatabase();
         ContentValues values = new ContentValues();


         values.put(CHILD_FOLLOW_UP_COL_ID           , childFollowUpTableId);
         values.put(CHILD_FOLLOW_UP_COL_MOTHER_ID    , childMotherId      );
         values.put(CHILD_FOLLOW_UP_COL_MOTHER_NAME  , childMotherName     );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_NAME   , childName       );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_TABLE_ID, childTableId           );
         values.put(CHILD_FOLLOW_UP_COL_DATE_OF_VISIT, childDateOfVisit    );
         values.put(CHILD_FOLLOW_UP_COL_AGE_OF_CHILD_IN_MONTH, childAge  );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_WEIGHT , childWeight         );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_HEIGHT , childHeight         );


         try {
             // Inserting Row
             db.insertOrThrow(TABLE_CHILD_FOLLOW_UP, null, values);

         } catch (Exception e) {


             Log.d(TAG, "Duplicate Primary key at addChildFollowUp() ");


         }

         String whereMother = MOTHER_COLUMN_ID + " =? ";
         ContentValues valuesMother = new ContentValues();
         valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
         valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
         db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{childMotherId});



         db.close(); // Closing database connection
         Log.d(TAG, "Child Follow Up Successfully inserted");

     }

    // -------------------   addChildFollowUp end ---------------


    public void registerMother(Mother mother) {     // ------------------- ======================= add mother  start ---------------

        String motherRowId = "";
        String motherName                = mother.getMotherName();
        String husbandName               = mother.getHusbandName();
        String motherAge                 = mother.getMotherAge();
        String motherPhoneNumber         = mother.getMotherPhoneNumber();
        String desiredCallingTime        = mother.getDesiredCallingTime();
        String motherAddress             = mother.getMotherAddress();
        String GIS_Location              = mother.getGIS_Location();
        String alternativePhoneNumber    = mother.getAlternativePhoneNumber();
        String alternativePhoneOwnerName = mother.getAlternativePhoneOwnerName();
        String DHIS_ID                   = mother.getDHIS_ID();
        String lastMenstruationDate      = mother.getLastMenstruationDate();
        String pregnancyState            = mother.getPregnancyState();
        String loginUserName            = ((MyApplication)context.getApplicationContext()).getLoginUserName();
        String deliveryDate               ;


        String syncStatus;
        if(mother.getSyncStatus() != null){
            syncStatus = mother.getSyncStatus();
        }else{
            syncStatus = "false";
        }






//
        if (mother.getDeliveryDate() != null) {
            deliveryDate = mother.getDeliveryDate();
        } else {
            deliveryDate = "false";
            // Toast.makeText(context, " Delivery date set to false", Toast.LENGTH_SHORT).show();

        }

        if (mother.getMotherRowPrimaryKey() != null){
            motherRowId = mother.getMotherRowPrimaryKey();
        }else {
            motherRowId = generateUniqueId();
        }
        if (mother.getLoginUserName()== null){//=========================================================================
            loginUserName = "Empty";
        }


//


        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
if (!motherRowId.isEmpty()){
    // Toast.makeText(context,"db mother row:  "+ mother.getMotherRowPrimaryKey(),Toast.LENGTH_LONG).show();
    values.put(MOTHER_COLUMN_ID, motherRowId);

}


    values.put(MOTHER_COLUMN_NAME  , motherName);
    values.put(MOTHER_COLUMN_HUSBAND_NAME  , husbandName);
    values.put(MOTHER_COLUMN_AGE  , motherAge);
    values.put(MOTHER_COLUMN_PHONE_NUMBER  , motherPhoneNumber);
    values.put(MOTHER_COLUMN_DESIRED_CALLING_TIME  , desiredCallingTime );
    values.put(MOTHER_COLUMN_MOTHER_ADDRESS  ,motherAddress  );
    values.put(MOTHER_COLUMN_GIS_LOCATION  , GIS_Location  );
    values.put(MOTHER_COLUMN_ALTERNATIVE_PHONE_NO  ,alternativePhoneNumber  );
    values.put(MOTHER_COLUMN_ALTERNATIVE_PHONE_OWNER_NAME  ,alternativePhoneOwnerName  );
    values.put(MOTHER_COLUMN_DHIS_ID  ,DHIS_ID  );
    values.put(MOTHER_COLUMN_LAST_MENSTRUATION  ,lastMenstruationDate  );
    values.put(MOTHER_COLUMN_PREGNANCY_STATE  ,pregnancyState  );
    values.put(MOTHER_COLUMN_LOGIN_USER_NAME  ,loginUserName  );
    values.put(MOTHER_COLUMN_DELIVERY_DATE  ,deliveryDate  );
    values.put(MOTHER_COLUMN_SYNC_STATUS  ,syncStatus  );
    values.put(MOTHER_COLUMN_TIMESTAMP  ,getTimeStamp()  );






        Log.d(TAG, " ==============  " + motherRowId);
        Log.d(TAG, "  =============  " + motherName);
        Log.d(TAG, " ==============  " + husbandName);
        Log.d(TAG, " ==============  " + motherAge);
        Log.d(TAG, "  =============  " + motherPhoneNumber);
        Log.d(TAG, " ==============  " + desiredCallingTime);
        Log.d(TAG, "  =============  " + motherAddress);

        Log.d(TAG, "  =============  " + GIS_Location);
        Log.d(TAG, " ==============  " + alternativePhoneNumber);
        Log.d(TAG, "  =============  " + alternativePhoneOwnerName);
        Log.d(TAG, " ==============  " + DHIS_ID);
        Log.d(TAG, "  =============  " + lastMenstruationDate);
        Log.d(TAG, " ==============  " + pregnancyState);
        Log.d(TAG, " ==============  " + loginUserName);
        Log.d(TAG, "  =============  " + deliveryDate);
        Log.d(TAG, " ==============  " + syncStatus);
        Log.d(TAG, " ==============  " + getTimeStamp());


        long newRowId = 0;


        try {
            // Inserting Row
            newRowId = db.insertOrThrow(TABLE_MOTHER, null, values);

        } catch (Exception e) {


            Log.d(TAG, "Duplicate Primary key at resisterMother() ");


        }


        db.close(); // Closing database connection

        Log.d(TAG, "Successfully inserted");

if (mother.getMotherRowPrimaryKey() == null){
        mother.setMotherRowPrimaryKey(String.valueOf(newRowId));
        //add_mother_in_message_Delivery_table(mother);
    add_mother_in_anc_pnc_msg_table(mother);
    add_mother_in_delivery_and_child_msg_table(mother);
    }
//else {
//    add_mother_in_message_Delivery_table(mother);
//}

        if (mother.getChild() != null) {           //  call register child
            Log.d(TAG, " ================ Child not null ");


            mother.getChild().setChildMotherTableId(String.valueOf(newRowId));

            registerChild(mother.getChild());


        }


    }                                                   // -------------------   register mother end ---------------

                                                // -------------------  generate Unique Id start ---------------

    public String generateUniqueId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date now = new Date();
        String idString =  sdf.format(now);

        Log.d(TAG,"========== time id string: "+idString);
      //  long id = Long.parseLong(idString);
        //Log.d(TAG,"========== time id int: "+id+ "type of id is:  "+ id);

        return idString;
    }
    // -------------------  generate Unique Id end ---------------

    private String getTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
    // -------------------  add motehr in anc pnc message table start ---------------
    public void add_mother_in_anc_pnc_msg_table(Mother mother){
        String anc_pnc_msg_delivery_col_id;
        String mother_column_id  = mother.getMotherRowPrimaryKey() ;
        String isANC_1_Message_Delivered  ;
        String isANC_2_Message_Delivered  ;
        String isANC_3_Message_Delivered  ;
        String isANC_4_Message_Delivered  ;
        String isPNC_Message_Delivered    ;
        String isMotherDead               ;
        String sync_status                ;

        if (mother.getIsANC_1_Message_Delivered()  == null ){ isANC_1_Message_Delivered   = "";}else {isANC_1_Message_Delivered   = mother.getIsANC_1_Message_Delivered() ;}
        if (mother.getIsANC_2_Message_Delivered()  == null ){ isANC_2_Message_Delivered   = "";}else {isANC_2_Message_Delivered   = mother.getIsANC_2_Message_Delivered() ;}
        if (mother.getIsANC_3_Message_Delivered()  == null ){ isANC_3_Message_Delivered   = "";}else {isANC_3_Message_Delivered   = mother.getIsANC_3_Message_Delivered() ;}
        if (mother.getIsANC_4_Message_Delivered()  == null ){ isANC_4_Message_Delivered   = "";}else {isANC_4_Message_Delivered   = mother.getIsANC_4_Message_Delivered() ;}
        if (mother.getIsPNC_Message_Delivered()    == null ){ isPNC_Message_Delivered     = "";}else {isPNC_Message_Delivered     = mother.getIsPNC_Message_Delivered() ;}
        if (mother.getIsMotherDead()               == null ){ isMotherDead                = "";}else {isMotherDead                = mother.getIsMotherDead() ;}
        if (mother.getAnc_pnc_msg_sync_status()    == null ){ sync_status                 = "";}else {sync_status                 = mother.getAnc_pnc_msg_sync_status() ;}


        if (mother.getAnc_pnc_msg_column_id() == null){
            anc_pnc_msg_delivery_col_id = generateUniqueId();
        }else {
            anc_pnc_msg_delivery_col_id = mother.getAnc_pnc_msg_column_id();
        }


        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(ANC_PNC_MSG_DELIVERY_COL_ID                     ,anc_pnc_msg_delivery_col_id);
        values.put(ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID       ,mother_column_id );
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED ,isANC_1_Message_Delivered );
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED ,isANC_2_Message_Delivered  );
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED ,isANC_3_Message_Delivered  );
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED ,isANC_4_Message_Delivered    );
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_PNC_MSG_DELIVERED   ,isPNC_Message_Delivered  );
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_MOTHER_DEAD         ,isMotherDead  );
        values.put(ANC_PNC_MSG_DELIVERY_COL_SYNC_STATUS            ,sync_status );

        try {
            // Inserting Row
            db.insertOrThrow(TABLE_ANC_PNC_MSG, null, values);

        } catch (Exception e) {
            Log.d(TAG, "Duplicate Primary key at anc pnc message delivery table at add_mother_in_message_Delivery_table(Mother mother ) ");
        }

        db.close();




    }


    // -------------------  add motehr in anc pnc message table end ---------------

    // -------------------  add motehr in delivery and child message table start ---------------
    public void add_mother_in_delivery_and_child_msg_table(Mother mother){

        String delivery_and_child_msg_col_id;
        String mother_column_id  = mother.getMotherRowPrimaryKey() ;
        String isPreDelivery_Message_Delivered          ;
        String isChild_message_delivered_0_to_14_days   ;
        String isChild_message_delivered_1_2_3_month    ;
        String isChild_message_delivered_6_to_8_month   ;
        String isChild_message_delivered_9_to_12_month  ;
        String sync_status                              ;

        if (mother.getIsPreDelivery_Message_Delivered() == null){isPreDelivery_Message_Delivered                  = ""; }else { isPreDelivery_Message_Delivered       =mother.getIsPreDelivery_Message_Delivered() ;}
        if (mother.getIsChild_message_delivered_0_to_14_days() == null){isChild_message_delivered_0_to_14_days    = ""; }else { isChild_message_delivered_0_to_14_days       =mother.getIsChild_message_delivered_0_to_14_days() ;}
        if (mother.getIsChild_message_delivered_1_2_3_month() == null){isChild_message_delivered_1_2_3_month      = ""; }else { isChild_message_delivered_1_2_3_month        =mother.getIsChild_message_delivered_1_2_3_month() ;}
        if (mother.getIsChild_message_delivered_6_to_8_month() == null){isChild_message_delivered_6_to_8_month    = ""; }else { isChild_message_delivered_6_to_8_month       =mother.getIsChild_message_delivered_6_to_8_month() ;}
        if (mother.getIsChild_message_delivered_9_to_12_month() == null){isChild_message_delivered_9_to_12_month  = ""; }else { isChild_message_delivered_9_to_12_month      =mother.getIsChild_message_delivered_9_to_12_month() ;}
        if (mother.getDelivery_and_child_msg_sync_status() == null){sync_status                                   = ""; }else { sync_status                                  =mother.getDelivery_and_child_msg_sync_status() ;}

        if (mother.getDelivery_and_child_msg_column_id() == null){
            delivery_and_child_msg_col_id = generateUniqueId();
        }else {
            delivery_and_child_msg_col_id = mother.getDelivery_and_child_msg_column_id();
        }

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(DELIVERY_AND_CHILD_MSG_COL_ID                                   ,delivery_and_child_msg_col_id);
        values.put(DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID                     ,mother_column_id );
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_PRE_DELIVERY_MSG_DELIVERED        ,isPreDelivery_Message_Delivered );
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED  ,isChild_message_delivered_0_to_14_days  );
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED   ,isChild_message_delivered_1_2_3_month  );
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED  ,isChild_message_delivered_6_to_8_month    );
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED ,isChild_message_delivered_9_to_12_month);
        values.put(DELIVERY_AND_CHILD_MSG_COL_SYNC_STATUS                          ,sync_status );


        try {
            // Inserting Row
            db.insertOrThrow(TABLE_DELIVERY_AND_CHILD_MSG, null, values);

        } catch (Exception e) {
            Log.d(TAG, "Duplicate Primary key at delivery and child message delivery table at add_mother_in_message_Delivery_table(Mother mother ) ");
        }






        db.close(); // Closing database connection

    }
    // -------------------  add motehr in delivery and child message table end ---------------


    public void registerChild(Child child) {     // ------------------- ======================= add child  start ---------------


        String  childColumnId,
                childMotherName = child.getChildMotherName(),
                childMotherId = child.getChildMotherTableId(),
                childName = child.getChildName(),
                childDateOfBirth = child.getChildDateOfBirth(),
                sexOfChild = child.getSexOfChild(),
                childBirthWeight = child.getChildBirthWeight(),

                idNumberOfChild = child.getIdNumberOfChild();

        if (child.getChildId() != null){
        childColumnId = child.getChildId();
    }else {
            childColumnId = generateUniqueId();
        }

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CHILD_COLUMN_ID, childColumnId);
        values.put(CHILD_COLUMN_MOTHER_NAME, childMotherName);
        values.put(CHILD_COLUMN_MOTHER_ID, childMotherId);
        values.put(CHILD_COLUMN_NAME, childName);
        values.put(CHILD_COLUMN_SEX, sexOfChild);
        values.put(CHILD_COLUMN_DATE_OF_BIRTH, childDateOfBirth);
        values.put(CHILD_COLUMN_BIRTH_WEIGHT, childBirthWeight);
        values.put(CHILD_COLUMN_ID_NUMBER_OF_CHILD, idNumberOfChild);


        try {
            // Inserting Row
            db.insertOrThrow(TABLE_CHILD, null, values);

        } catch (Exception e) {


            Log.d(TAG, "Duplicate Primary key at resisterChild() ");


        }

        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{childMotherId});
        Log.d(TAG, "========== Child mother table id = "+childMotherId);

        db.close(); // Closing database connection
        Log.d(TAG, "Child Successfully inserted");


    }                                                   // -------------------   register child end ---------------

                                                         // -------------------   update mother start ---------------

    public void updateMother(Mother mother){
        SQLiteDatabase database = this.getWritableDatabase();

        String motherRowId = mother.getMotherRowPrimaryKey();
        Log.d(TAG, "=======================  "+motherRowId);

        database.execSQL("DELETE FROM " + TABLE_MOTHER + " WHERE " + MOTHER_COLUMN_ID + " = '" + motherRowId + "'");
        //database.execSQL("DELETE FROM " + TABLE_MESSAGE_DELIVERY + " WHERE " + MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " = '" + motherRowId + "'");

        if (mother.getChild() != null){
            database.execSQL("DELETE FROM " + TABLE_CHILD + " WHERE " + CHILD_COLUMN_MOTHER_ID + "= '" + motherRowId + "'");
        }
        //Close the database
        database.close();

        registerMother(mother);
    }
                                                          // -------------------   update mother end ---------------


    public void deleteMother(Mother mother){
//        SQLiteDatabase database = this.getWritableDatabase();
//
         String motherRowId = mother.getMotherRowPrimaryKey();
//        Log.d(TAG, "=======================  "+motherRowId);
//



        SQLiteDatabase db = super.getWritableDatabase();

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_PREGNANCY_STATE, statusDead );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{motherRowId});
        db.close();


    }
    // -------------------   update mother end ---------------
    public void clearChildFollowUpTable(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_CHILD_FOLLOW_UP  );
        //Close the database
        database.close();
    }
    public void clearDeliveryAndChildMsgTable(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_DELIVERY_AND_CHILD_MSG  );
        //Close the database
        database.close();
    }
    public void clearAncPncMsgTable(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_ANC_PNC_MSG );
        //Close the database
        database.close();
    }
    public void clearChildTable(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_CHILD  );
        //Close the database
        database.close();
    }
    public void clearMotherTable(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_MOTHER  );
        //Close the database
        database.close();
    }


    public boolean isValidUser(User user) {              //  ------------------ Check Valid user or not start----------
        String userName = user.getUserName(),
                userPassword = user.getUserPassword(),
                userType = user.getUserType();
        Log.d(TAG,"============ user name:  "+userName);
        Log.d(TAG,"============ password:  "+userPassword);
        Log.d(TAG,"============ user type:  "+userType);

        String where = USER_COLUMN_NAME + " =? AND " + USER_COLUMN_PASSWORD + " =? AND " + USER_COLUMN_TYPE + " =? ";

        SQLiteDatabase db = super.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LOGIN, null, where, new String[]{userName, userPassword, userType}, null, null, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }



        cursor.close();

        return true;
    }                                                 //  ------------------ Check Valid user or not  end ----------


                                                                                                                                                                                                                                                 public List<Mother> getAllMothers() {                 //  ------------------ get List of All Mothers  start ----------
        String mother_column_id                     ;
        String motherName                           ;
        String husbandName                          ;
        String motherAge                            ;
        String motherPhoneNumber                    ;
        String desiredCallingTime                   ;
        String motherAddress                        ;
        String GIS_Location                         ;
        String alternativePhoneNumber               ;
        String alternativePhoneOwnerName            ;
        String DHIS_ID                              ;
        String lastMenstruationDate                 ;
        String pregnancyState                       ;
        String deliveryDate                         ;
        String loginUserName                        ;
        String syncStatus                           ;
        String timeStamp                            ;


        String anc_pnc_msg_column_id                     ;
        String isANC_1_Message_Delivered                 ;
        String isANC_2_Message_Delivered                 ;
        String isANC_3_Message_Delivered                 ;
        String isANC_4_Message_Delivered                 ;
        String isPNC_Message_Delivered                   ;
        String isMotherDead                              ;
        String anc_pnc_msg_sync_status                   ;
        String anc_pnc_msg_timeStamp                     ;



        String delivery_and_child_msg_column_id          ;
        String isPreDelivery_Message_Delivered           ;
        String isChild_message_delivered_0_to_14_days    ;
        String isChild_message_delivered_1_2_3_month     ;
        String isChild_message_delivered_6_to_8_month    ;
        String isChild_message_delivered_9_to_12_month   ;
        String delivery_and_child_msg_sync_status        ;
        String delivery_and_child_msg_timeStamp          ;


        List<Mother> allMothers = new ArrayList<>();

        String selectAllMoters = "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_ANC_PNC_MSG + " ON " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " = " + TABLE_ANC_PNC_MSG
                + "." + ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " INNER JOIN " + TABLE_DELIVERY_AND_CHILD_MSG + " ON " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " = " + TABLE_DELIVERY_AND_CHILD_MSG
                + "." + DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID;
        //String selectAllMoters = "SELECT  * FROM " + TABLE_MOTHER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllMoters, null);

        if (cursor != null && cursor.moveToFirst()) {

            do {
//                String motherName, lastMenstruationDate, motherAge, isMessageDelivered, isChildMessageDelivered, isPreDeliveryMessageDelivered, pregnancyState, isChildBorn, childBirthday,
//                        motherRowPrimaryKey, motherPhoneNumber, motherAddress;

                mother_column_id           = cursor.getString(0 );
                motherName                 = cursor.getString(1 );
                husbandName                = cursor.getString(2 );
                motherAge                  = cursor.getString(3 );
                motherPhoneNumber          = cursor.getString(4 );
                desiredCallingTime         = cursor.getString(5 );
                motherAddress              = cursor.getString(6 );
                GIS_Location               = cursor.getString(7 );
                alternativePhoneNumber     = cursor.getString(8 );
                alternativePhoneOwnerName  = cursor.getString(9 );
                DHIS_ID                    = cursor.getString(10);
                lastMenstruationDate       = cursor.getString(11);
                pregnancyState             = cursor.getString(12);
                deliveryDate               = cursor.getString(13);
                loginUserName              = cursor.getString(14);
                syncStatus                 = cursor.getString(15);
                timeStamp                  = cursor.getString(16);

                anc_pnc_msg_column_id      = cursor.getString(17) ;
                isANC_1_Message_Delivered  = cursor.getString(19) ;
                isANC_2_Message_Delivered  = cursor.getString(20) ;
                isANC_3_Message_Delivered  = cursor.getString(21) ;
                isANC_4_Message_Delivered  = cursor.getString(22) ;
                isPNC_Message_Delivered    = cursor.getString(23) ;
                isMotherDead               = cursor.getString(24) ;
                anc_pnc_msg_sync_status    = cursor.getString(25) ;
                anc_pnc_msg_timeStamp      = cursor.getString(26) ;

                delivery_and_child_msg_column_id        = cursor.getString(27);
                isPreDelivery_Message_Delivered         = cursor.getString(29);
                isChild_message_delivered_0_to_14_days  = cursor.getString(30);
                isChild_message_delivered_1_2_3_month   = cursor.getString(31);
                isChild_message_delivered_6_to_8_month  = cursor.getString(32);
                isChild_message_delivered_9_to_12_month = cursor.getString(33);
                delivery_and_child_msg_sync_status      = cursor.getString(34);
                delivery_and_child_msg_timeStamp        = cursor.getString(35);



                Mother item = new Mother(mother_column_id,motherName,husbandName,motherAge,motherPhoneNumber,desiredCallingTime,motherAddress,GIS_Location,alternativePhoneOwnerName,alternativePhoneNumber,
                        DHIS_ID,lastMenstruationDate,pregnancyState,deliveryDate,isANC_1_Message_Delivered,isANC_2_Message_Delivered,isANC_3_Message_Delivered,isANC_4_Message_Delivered,
                        isPreDelivery_Message_Delivered,isPNC_Message_Delivered,isChild_message_delivered_0_to_14_days,isChild_message_delivered_1_2_3_month,isChild_message_delivered_6_to_8_month,
                        isChild_message_delivered_9_to_12_month,isMotherDead);
                item.setSyncStatus(syncStatus);
                item.setTimeStamp(timeStamp);
                item.setLoginUserName(loginUserName)    ;
                item.setAnc_pnc_msg_column_id(anc_pnc_msg_column_id);
                item.setAnc_pnc_msg_sync_status(anc_pnc_msg_sync_status);
                item.setAnc_pnc_msg_timeStamp(anc_pnc_msg_timeStamp);
                item.setDelivery_and_child_msg_column_id(delivery_and_child_msg_column_id);
                item.setDelivery_and_child_msg_sync_status(delivery_and_child_msg_sync_status);
                item.setDelivery_and_child_msg_timeStamp(delivery_and_child_msg_timeStamp);


                allMothers.add(item);


            } while (cursor.moveToNext());

            cursor.close();
        }


        return allMothers;
    }                                                  //  ------------------ get List of All Mothers  end ----------

    public List<Mother> getAllMotherswithChild() {                 //  ------------------ get List of All Mothers  with Child start ----------
        String mother_column_id                     ;
        String motherName                           ;
        String husbandName                          ;
        String motherAge                            ;
        String motherPhoneNumber                    ;
        String desiredCallingTime                   ;
        String motherAddress                        ;
        String GIS_Location                         ;
        String alternativePhoneNumber               ;
        String alternativePhoneOwnerName            ;
        String DHIS_ID                              ;
        String lastMenstruationDate                 ;
        String pregnancyState                       ;
        String deliveryDate                         ;
        String loginUserName                        ;
        String syncStatus                           ;
        String timeStamp                            ;


        String anc_pnc_msg_column_id                     ;
        String isANC_1_Message_Delivered                 ;
        String isANC_2_Message_Delivered                 ;
        String isANC_3_Message_Delivered                 ;
        String isANC_4_Message_Delivered                 ;
        String isPNC_Message_Delivered                   ;
        String isMotherDead                              ;
        String anc_pnc_msg_sync_status                   ;
        String anc_pnc_msg_timeStamp                     ;



        String delivery_and_child_msg_column_id          ;
        String isPreDelivery_Message_Delivered           ;
        String isChild_message_delivered_0_to_14_days    ;
        String isChild_message_delivered_1_2_3_month     ;
        String isChild_message_delivered_6_to_8_month    ;
        String isChild_message_delivered_9_to_12_month   ;
        String delivery_and_child_msg_sync_status        ;
        String delivery_and_child_msg_timeStamp          ;



        String  childColumnId                            ;
        String  childName                                ;
        String  sexOfChild                               ;
        String  childDateOfBirth                         ;
        String  childBirthWeight                         ;
        String  idNumberOfChild                          ;


        List<Mother> allMothers = new ArrayList<>();

//        String selectAllMothersWithChild = "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_CHILD + " ON " + TABLE_CHILD + "." + CHILD_COLUMN_MOTHER_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " WHERE "
//                + TABLE_MOTHER + "." + MOTHER_COLUMN_PREGNANCY_STATE + "=?";

        String selectAllMothersWithChild = "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_ANC_PNC_MSG + " ON " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " = " + TABLE_ANC_PNC_MSG
                + "." + ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " INNER JOIN " + TABLE_DELIVERY_AND_CHILD_MSG + " ON " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " = " + TABLE_DELIVERY_AND_CHILD_MSG
                + "." + DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID+ " INNER JOIN " + TABLE_CHILD + " ON " + TABLE_CHILD + "." + CHILD_COLUMN_MOTHER_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID
                +" WHERE " + TABLE_MOTHER + "." + MOTHER_COLUMN_PREGNANCY_STATE + "=?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllMothersWithChild, new String[]{"post delivery"});

        if (cursor != null && cursor.moveToFirst()) {

            do {
//                String motherName, lastMenstruationDate, motherAge, isMessageDelivered, isChildMessageDelivered, isPreDeliveryMessageDelivered, pregnancyState, isChildBorn, childBirthday,
//                        motherRowPrimaryKey, motherPhoneNumber, motherAddress, childColumnId, childMotherId, childMotherName, childName, sexOfChild, childDateOfBirh, childBirthWeight, idNumberOfChild;

                mother_column_id           = cursor.getString(0 );
                motherName                 = cursor.getString(1 );
                husbandName                = cursor.getString(2 );
                motherAge                  = cursor.getString(3 );
                motherPhoneNumber          = cursor.getString(4 );
                desiredCallingTime         = cursor.getString(5 );
                motherAddress              = cursor.getString(6 );
                GIS_Location               = cursor.getString(7 );
                alternativePhoneNumber     = cursor.getString(8 );
                alternativePhoneOwnerName  = cursor.getString(9 );
                DHIS_ID                    = cursor.getString(10);
                lastMenstruationDate       = cursor.getString(11);
                pregnancyState             = cursor.getString(12);
                deliveryDate               = cursor.getString(13);
                loginUserName              = cursor.getString(14);
                syncStatus                 = cursor.getString(15);
                timeStamp                  = cursor.getString(16);

                anc_pnc_msg_column_id      = cursor.getString(17) ;
                isANC_1_Message_Delivered  = cursor.getString(19) ;
                isANC_2_Message_Delivered  = cursor.getString(20) ;
                isANC_3_Message_Delivered  = cursor.getString(21) ;
                isANC_4_Message_Delivered  = cursor.getString(22) ;
                isPNC_Message_Delivered    = cursor.getString(23) ;
                isMotherDead               = cursor.getString(24) ;
                anc_pnc_msg_sync_status    = cursor.getString(25) ;
                anc_pnc_msg_timeStamp      = cursor.getString(26) ;

                delivery_and_child_msg_column_id        = cursor.getString(27);
                isPreDelivery_Message_Delivered         = cursor.getString(29);
                isChild_message_delivered_0_to_14_days  = cursor.getString(30);
                isChild_message_delivered_1_2_3_month   = cursor.getString(31);
                isChild_message_delivered_6_to_8_month  = cursor.getString(32);
                isChild_message_delivered_9_to_12_month = cursor.getString(33);
                delivery_and_child_msg_sync_status      = cursor.getString(34);
                delivery_and_child_msg_timeStamp        = cursor.getString(35);


               childColumnId                               = cursor.getString(36);
               childName                                   = cursor.getString(39);
               sexOfChild                                  = cursor.getString(40);
               childDateOfBirth                            = cursor.getString(41);
               childBirthWeight                            = cursor.getString(42);
               idNumberOfChild                             = cursor.getString(43);





                Child child = new Child(motherName, mother_column_id, childName,childDateOfBirth, sexOfChild, childBirthWeight, childColumnId, idNumberOfChild);

                Mother item = new Mother(mother_column_id,motherName,husbandName,motherAge,motherPhoneNumber,desiredCallingTime,motherAddress,GIS_Location,alternativePhoneOwnerName,
                        alternativePhoneNumber,DHIS_ID,lastMenstruationDate,pregnancyState,deliveryDate,isANC_1_Message_Delivered,isANC_2_Message_Delivered,isANC_3_Message_Delivered,isANC_4_Message_Delivered,
                        isPreDelivery_Message_Delivered,isPNC_Message_Delivered,isChild_message_delivered_0_to_14_days,isChild_message_delivered_1_2_3_month,isChild_message_delivered_6_to_8_month,
                        isChild_message_delivered_9_to_12_month,isMotherDead);
                item.setSyncStatus(syncStatus);
                item.setTimeStamp(timeStamp);
                item.setChild(child);
                item.setLoginUserName(loginUserName)    ;
                item.setAnc_pnc_msg_column_id(anc_pnc_msg_column_id);
                item.setAnc_pnc_msg_sync_status(anc_pnc_msg_sync_status);
                item.setAnc_pnc_msg_timeStamp(anc_pnc_msg_timeStamp);
                item.setDelivery_and_child_msg_column_id(delivery_and_child_msg_column_id);
                item.setDelivery_and_child_msg_sync_status(delivery_and_child_msg_sync_status);
                item.setDelivery_and_child_msg_timeStamp(delivery_and_child_msg_timeStamp);



                allMothers.add(item);


            } while (cursor.moveToNext());

            cursor.close();
        }


        return allMothers;
    }                                                  //  ------------------ get List of All Mothers with Child  end ----------




    public List<Mother> getAllMothersWithOrWithoutChild() {                 //  ------------------ get List of All Mothers  with or without Child start ----------
        String mother_column_id                     ;
        String motherName                           ;
        String husbandName                          ;
        String motherAge                            ;
        String motherPhoneNumber                    ;
        String desiredCallingTime                   ;
        String motherAddress                        ;
        String GIS_Location                         ;
        String alternativePhoneNumber               ;
        String alternativePhoneOwnerName            ;
        String DHIS_ID                              ;
        String lastMenstruationDate                 ;
        String pregnancyState                       ;
        String deliveryDate                         ;
        String loginUserName                        ;
        String syncStatus                           ;
        String timeStamp                            ;


        String anc_pnc_msg_column_id                     ;
        String isANC_1_Message_Delivered                 ;
        String isANC_2_Message_Delivered                 ;
        String isANC_3_Message_Delivered                 ;
        String isANC_4_Message_Delivered                 ;
        String isPNC_Message_Delivered                   ;
        String isMotherDead                              ;
        String anc_pnc_msg_sync_status                   ;
        String anc_pnc_msg_timeStamp                     ;



        String delivery_and_child_msg_column_id          ;
        String isPreDelivery_Message_Delivered           ;
        String isChild_message_delivered_0_to_14_days    ;
        String isChild_message_delivered_1_2_3_month     ;
        String isChild_message_delivered_6_to_8_month    ;
        String isChild_message_delivered_9_to_12_month   ;
        String delivery_and_child_msg_sync_status        ;
        String delivery_and_child_msg_timeStamp          ;



        String  childColumnId                            ;
        String  childName                                ;
        String  sexOfChild                               ;
        String  childDateOfBirth                         ;
        String  childBirthWeight                         ;
        String  idNumberOfChild                          ;


        List<Mother> allMothers = new ArrayList<>();

//        String selectAllMothersWithChild = "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_CHILD + " ON " + TABLE_CHILD + "." + CHILD_COLUMN_MOTHER_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " WHERE "
//                + TABLE_MOTHER + "." + MOTHER_COLUMN_PREGNANCY_STATE + "=?";

        String selectAllMothersWithChild = "SELECT  * FROM " + TABLE_MOTHER + " LEFT JOIN " + TABLE_ANC_PNC_MSG + " ON " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " = " + TABLE_ANC_PNC_MSG
                + "." + ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " LEFT JOIN " + TABLE_DELIVERY_AND_CHILD_MSG + " ON " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " = " + TABLE_DELIVERY_AND_CHILD_MSG
                + "." + DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID+ " LEFT JOIN " + TABLE_CHILD + " ON " + TABLE_CHILD + "." + CHILD_COLUMN_MOTHER_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID;
                //+" WHERE " + TABLE_MOTHER + "." + MOTHER_COLUMN_PREGNANCY_STATE + "=?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllMothersWithChild, null);

        if (cursor != null && cursor.moveToFirst()) {

            do {
//                String motherName, lastMenstruationDate, motherAge, isMessageDelivered, isChildMessageDelivered, isPreDeliveryMessageDelivered, pregnancyState, isChildBorn, childBirthday,
//                        motherRowPrimaryKey, motherPhoneNumber, motherAddress, childColumnId, childMotherId, childMotherName, childName, sexOfChild, childDateOfBirh, childBirthWeight, idNumberOfChild;

                mother_column_id           = cursor.getString(0 );
                motherName                 = cursor.getString(1 );
                husbandName                = cursor.getString(2 );
                motherAge                  = cursor.getString(3 );
                motherPhoneNumber          = cursor.getString(4 );
                desiredCallingTime         = cursor.getString(5 );
                motherAddress              = cursor.getString(6 );
                GIS_Location               = cursor.getString(7 );
                alternativePhoneNumber     = cursor.getString(8 );
                alternativePhoneOwnerName  = cursor.getString(9 );
                DHIS_ID                    = cursor.getString(10);
                lastMenstruationDate       = cursor.getString(11);
                pregnancyState             = cursor.getString(12);
                deliveryDate               = cursor.getString(13);
                loginUserName              = cursor.getString(14);
                syncStatus                 = cursor.getString(15);
                timeStamp                  = cursor.getString(16);

                anc_pnc_msg_column_id      = cursor.getString(17) ;
                isANC_1_Message_Delivered  = cursor.getString(19) ;
                isANC_2_Message_Delivered  = cursor.getString(20) ;
                isANC_3_Message_Delivered  = cursor.getString(21) ;
                isANC_4_Message_Delivered  = cursor.getString(22) ;
                isPNC_Message_Delivered    = cursor.getString(23) ;
                isMotherDead               = cursor.getString(24) ;
                anc_pnc_msg_sync_status    = cursor.getString(25) ;
                anc_pnc_msg_timeStamp      = cursor.getString(26) ;

                delivery_and_child_msg_column_id        = cursor.getString(27);
                isPreDelivery_Message_Delivered         = cursor.getString(29);
                isChild_message_delivered_0_to_14_days  = cursor.getString(30);
                isChild_message_delivered_1_2_3_month   = cursor.getString(31);
                isChild_message_delivered_6_to_8_month  = cursor.getString(32);
                isChild_message_delivered_9_to_12_month = cursor.getString(33);
                delivery_and_child_msg_sync_status      = cursor.getString(34);
                delivery_and_child_msg_timeStamp        = cursor.getString(35);


                childColumnId                               = cursor.getString(36);
                childName                                   = cursor.getString(39);
                sexOfChild                                  = cursor.getString(40);
                childDateOfBirth                            = cursor.getString(41);
                childBirthWeight                            = cursor.getString(42);
                idNumberOfChild                             = cursor.getString(43);





                Child child = new Child(motherName, mother_column_id, childName,childDateOfBirth, sexOfChild, childBirthWeight, childColumnId, idNumberOfChild);

                Mother item = new Mother(mother_column_id,motherName,husbandName,motherAge,motherPhoneNumber,desiredCallingTime,motherAddress,GIS_Location,alternativePhoneOwnerName,
                        alternativePhoneNumber,DHIS_ID,lastMenstruationDate,pregnancyState,deliveryDate,isANC_1_Message_Delivered,isANC_2_Message_Delivered,isANC_3_Message_Delivered,isANC_4_Message_Delivered,
                        isPreDelivery_Message_Delivered,isPNC_Message_Delivered,isChild_message_delivered_0_to_14_days,isChild_message_delivered_1_2_3_month,isChild_message_delivered_6_to_8_month,
                        isChild_message_delivered_9_to_12_month,isMotherDead);
                item.setSyncStatus(syncStatus);
                item.setTimeStamp(timeStamp);
                item.setChild(child);
                item.setLoginUserName(loginUserName)    ;
                item.setAnc_pnc_msg_column_id(anc_pnc_msg_column_id);
                item.setAnc_pnc_msg_sync_status(anc_pnc_msg_sync_status);
                item.setAnc_pnc_msg_timeStamp(anc_pnc_msg_timeStamp);
                item.setDelivery_and_child_msg_column_id(delivery_and_child_msg_column_id);
                item.setDelivery_and_child_msg_sync_status(delivery_and_child_msg_sync_status);
                item.setDelivery_and_child_msg_timeStamp(delivery_and_child_msg_timeStamp);



                allMothers.add(item);


            } while (cursor.moveToNext());

            cursor.close();
        }


        return allMothers;
    }                                                  //  ------------------ get List of All Mothers with or without Child  end ----------










    //  ------------------ set message status for a Mothers  start ----------





    public void setMessageStatus(String primaryKey, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
      //  values.put(MOTHER_COLUMN_IS_MESSAGE_DELIVERED, status);
        db.update(TABLE_MOTHER, values, where, new String[]{primaryKey});
        db.close();

    }


    //  ------------------ set  Child Message Delivery Status  start ----------
    public void setChildMessageStatus(String primaryKey, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
      //  values.put(MOTHER_COLUMN_IS_CHILD_MESSAGE_DELIVERED, status);
        db.update(TABLE_MOTHER, values, where, new String[]{primaryKey});
        db.close();

    }
    //  ------------------ set  CHILD 0 TO 14 DAYS Message Delivery Status  start ----------
    public void setChild_0_To_14_Days_message_delivery_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED, status);
        db.update(TABLE_DELIVERY_AND_CHILD_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }
    //  ------------------ set  CHILD 1,2,3 MONTH Message Delivery Status  start ----------
    public void setChild_1_2_3_month_message_delivery_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED, status);
        db.update(TABLE_DELIVERY_AND_CHILD_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }
    //  ------------------ set  CHILD 6 TO 8 MONTH Message Delivery Status  start ----------
    public void setChild_6_To_8_month_message_delivery_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED, status);
        db.update(TABLE_DELIVERY_AND_CHILD_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }
    //  ------------------ set  CHILD 9 TO 12 MONTH Message Delivery Status  start ----------
    public void setChild_9_To_12_month_message_delivery_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED, status);
        db.update(TABLE_DELIVERY_AND_CHILD_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ set  Pre Delivery Message Delivery Status  start ----------
    public void setPreDeliveryMessageStatus(String primaryKey, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        String where = DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(DELIVERY_AND_CHILD_MSG_COL_IS_PRE_DELIVERY_MSG_DELIVERED, status);
        db.update(TABLE_DELIVERY_AND_CHILD_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();

    }

    //  ------------------ set    Delivery state  start ----------
    public void setPregnancyState(String primaryKey, String pregnancyState, String deliveryDate) {


        SQLiteDatabase db = super.getWritableDatabase();
        String where = MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MOTHER_COLUMN_PREGNANCY_STATE, pregnancyState);
        values.put(MOTHER_COLUMN_DELIVERY_DATE, deliveryDate);
        db.update(TABLE_MOTHER, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();

    }

    //  ------------------ SET  ANC 1 MESSAGE STATUS  start ----------
    public void set_ANC_1_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED, status);
        db.update(TABLE_ANC_PNC_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  ANC 2 MESSAGE STATUS  start ----------
    public void set_ANC_2_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED, status);
        db.update(TABLE_ANC_PNC_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  ANC 3 MESSAGE STATUS  start ----------
    public void set_ANC_3_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED, status);
        db.update(TABLE_ANC_PNC_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  ANC 4 MESSAGE STATUS  start ----------
    public void set_ANC_4_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED, status);
        db.update(TABLE_ANC_PNC_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  PNC MESSAGE STATUS  start ----------
    public void set_PNC_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(ANC_PNC_MSG_DELIVERY_COL_IS_PNC_MSG_DELIVERED, status);
        db.update(TABLE_ANC_PNC_MSG, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        valuesMother.put(MOTHER_COLUMN_TIMESTAMP, getTimeStamp() );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }








//=================================================================================================================================================================================================================================================

    public    HashMap<String ,HashMap<String ,String>> getAllTables2(){// =========================== get all tables

        HashMap<String , HashMap<String ,String>> rowsAll_map = new LinkedHashMap<>();////////////////////////////////////////////
        HashMap<String,String > tableNameAndRows_map = new LinkedHashMap<>();   /////////////////////////////////////////////
        List<String> tableName = new ArrayList<>();
        SQLiteDatabase database = super.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);


        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {

                tableName.add(c.getString(0)); // table names

                c.moveToNext();
            }
        }
//============================================================================================================================================
        Cursor cursorMotherTable = database.rawQuery("SELECT * FROM "+tableName.get(2)+" WHERE "
                + MOTHER_COLUMN_SYNC_STATUS + "=?", new String[] {"false"});  // mother table
        String[] motherColumnNamesArray = cursorMotherTable.getColumnNames();

        String motherAllRows = "" ;
        boolean flag = false;

        if (cursorMotherTable.moveToFirst()) {
            String n = "", t ="\t";
            while ( !cursorMotherTable.isAfterLast() ) {

                if (flag){
                   n= "\n" ;
                }

                motherAllRows += n+cursorMotherTable.getString(0);
                for (int i=1;i< motherColumnNamesArray.length;i++){
                    motherAllRows += t + cursorMotherTable.getString(i);
                }




              //  tableRow.add(colValue);
                flag = true;

                cursorMotherTable.moveToNext();
            }
        }
        cursorMotherTable.close();

        tableNameAndRows_map.put(tableName.get(2),motherAllRows);
        ///////////////////////////////////////////////////////////////////////////////////===============================================================

       //===================================================================================================================================================================
////============================================================================================================================================

        String queryAncPncMessageTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(3)+ " ON " +tableName.get(3)+"."+ ANC_PNC_MSG_DELIVERY_COL_MOTHER_COLUMN_ID
                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";

        Cursor cursorAncPncMessageTable = database.rawQuery(queryAncPncMessageTable, new String[]{"false"});  //anc pnc message table
        String[] ancPncMessageColumnNamesArray = cursorAncPncMessageTable.getColumnNames();

        String ancPncMessageAllRows = "" ;
        boolean flagAncPncMessage = false;

        if (cursorAncPncMessageTable.moveToFirst()) {
            String n = "", t ="\t";

            while ( !cursorAncPncMessageTable.isAfterLast() ) {

                if (flagAncPncMessage){
                    n= "\n" ;
                }

                ancPncMessageAllRows += n+cursorAncPncMessageTable.getString(17);
                for (int i=18;i< ancPncMessageColumnNamesArray.length;i++){
                    ancPncMessageAllRows += t + cursorAncPncMessageTable.getString(i);
                }



                flagAncPncMessage = true;

                cursorAncPncMessageTable.moveToNext();
            }
        }
        cursorAncPncMessageTable.close();

        tableNameAndRows_map.put(tableName.get(3),ancPncMessageAllRows);

////===================================================================================================================================================================



        ////============================================================================================================================================

        String queryDeliveryAndChildMessageTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(4)+ " ON " +tableName.get(4)+"."+DELIVERY_AND_CHILD_MSG_COL_MOTHER_COLUMN_ID
                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";

        Cursor cursorDeliveryAndChildMessageTable = database.rawQuery(queryDeliveryAndChildMessageTable, new String[]{"false"});  //DeliveryAndChild message table
        String[] messageColumnNamesArray = cursorDeliveryAndChildMessageTable.getColumnNames();

        String deliveryAndChildMessageAllRows = "" ;
        boolean flagDeliveryAndChildMessage = false;

        if (cursorDeliveryAndChildMessageTable.moveToFirst()) {
            String n = "", t ="\t";

            while ( !cursorDeliveryAndChildMessageTable.isAfterLast() ) {

                if (flagDeliveryAndChildMessage){
                    n= "\n" ;
                }

                deliveryAndChildMessageAllRows += n+cursorDeliveryAndChildMessageTable.getString(17);
                for (int i=18;i< messageColumnNamesArray.length;i++){
                    deliveryAndChildMessageAllRows += t + cursorDeliveryAndChildMessageTable.getString(i);
                }



                flagDeliveryAndChildMessage = true;

                cursorDeliveryAndChildMessageTable.moveToNext();
            }
        }
        cursorDeliveryAndChildMessageTable.close();

        tableNameAndRows_map.put(tableName.get(4),deliveryAndChildMessageAllRows);


////===================================================================================================================================================================
//        //============================================================================================================================================
        String queryChildTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(5)+ " ON " +tableName.get(5)+"."+ CHILD_COLUMN_MOTHER_ID
                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";
        Cursor cursorChildTable = database.rawQuery(queryChildTable, new String[] {"false"});  // Child table
        String[] childColumnNamesArray = cursorChildTable.getColumnNames();


        String ChildAllRows = "" ;
        boolean flagChild = false;

        if (cursorChildTable.moveToFirst()) {
            String n = "", t ="\t";

            while ( !cursorChildTable.isAfterLast() ) {

                if (flagChild){
                    n= "\n" ;
                }

                ChildAllRows += n+cursorChildTable.getString(17);
                for (int i=18;i< childColumnNamesArray.length;i++){
                    ChildAllRows += t + cursorChildTable.getString(i);
                }



                flagChild = true;

                cursorChildTable.moveToNext();
            }
        }
        cursorChildTable.close();

        tableNameAndRows_map.put(tableName.get(5),ChildAllRows);



////===================================================================================================================================================================
//        //============================================================================================================================================
        String queryChildFollowUpTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(6)+ " ON " +tableName.get(6)+"."+ CHILD_FOLLOW_UP_COL_MOTHER_ID
                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";
        Cursor cursorChildFollowUpTable = database.rawQuery(queryChildFollowUpTable, new String[] {"false"});  // Child Follow Up table
        String[] childFollowUpColumnNamesArray = cursorChildFollowUpTable.getColumnNames();


        String ChildFollowUpAllRows = "" ;
        boolean flagChildFollowUp = false;

        if (cursorChildFollowUpTable.moveToFirst()) {
            String n = "", t ="\t";

            while ( !cursorChildFollowUpTable.isAfterLast() ) {

                if (flagChildFollowUp){
                    n= "\n" ;
                }

                ChildFollowUpAllRows += n+cursorChildFollowUpTable.getString(17);
                for (int i=18;i< childFollowUpColumnNamesArray.length;i++){
                    ChildFollowUpAllRows += t + cursorChildFollowUpTable.getString(i);
                }



                flagChildFollowUp = true;

                cursorChildFollowUpTable.moveToNext();
            }
        }
        cursorChildFollowUpTable.close();

        tableNameAndRows_map.put(tableName.get(6),ChildFollowUpAllRows);



////===================================================================================================================================================================


        database.close();

        rowsAll_map.put("rowsAll",tableNameAndRows_map);

        return rowsAll_map;
    }





//    public      HashMap<String,   HashMap<String , HashMap<String ,String>>> getDbDef() {   //////////============================================//////////////////////////////////////
//        List<HashMap<String, List<HashMap<String, HashMap<String, String>>>>> dbDef_List = new ArrayList<>();
//        HashMap<String, HashMap<String, HashMap<String, String>>> dbDef_hashMap = new HashMap<>();
//        HashMap<String, HashMap<String, String>> allTablesMap = new LinkedHashMap<>();
//
//        List<String> tableName = new ArrayList<>();
//        SQLiteDatabase database = super.getWritableDatabase();
//        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
//
//
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//
//                tableName.add(c.getString(0)); // table names
//
//                c.moveToNext();
//            }
//        }
//
//        Cursor cursor;
//        ////////////////
//        for (int i = 1  ; i<tableName.size();i++){
//            cursor = database.rawQuery("SELECT * FROM "+tableName.get(i),null);
//            String[] columnNames = cursor.getColumnNames();
//            if (cursor.moveToFirst()){
//                HashMap<String ,String > colValue = new LinkedHashMap<>();
//
//                for (int j = 0; j<columnNames.length;j++){
//                    colValue.put(columnNames[j],   (cursor.getType(j )==1) ? "INTEGER":"TEXT");
//                }
//                allTablesMap.put(tableName.get(i),colValue);
//            }
//        }
//
//        //////////////
//
//        dbDef_hashMap.put("dbDef",allTablesMap);
//        //dbDef_List.add(dbDef_hashMap);
//
//      return dbDef_hashMap;
//
//    }





        public      HashMap<String,   HashMap<String , HashMap<String ,String>>> getDbDef2(){   //////////============================================//////////////////////////////////////
        List<HashMap<String,  List<HashMap<String , HashMap<String ,String>>>>> dbDef_List = new ArrayList<>();
        HashMap<String,   HashMap<String , HashMap<String ,String>>> dbDef_hashMap = new HashMap<>();
        HashMap<String , HashMap<String ,String>> allTablesMap = new LinkedHashMap<>();

        List<String> tableName = new ArrayList<>();
        SQLiteDatabase database = super.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);


        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {

                tableName.add(c.getString(0)); // table names

                c.moveToNext();
            }
        }


        //============================================================================================================================================
        Cursor cursorMotherTable = database.rawQuery("SELECT * FROM "+tableName.get(2), null);  // mother table
        String[] motherColumnNamesArray = cursorMotherTable.getColumnNames();

        HashMap<String , HashMap<String ,String>>  table_mother_map = new HashMap<>();
        List<HashMap<String ,String>> tableMotherRow = new ArrayList<>();
        if (cursorMotherTable.moveToFirst()) {
            //while ( !cursorMotherTable.isAfterLast() ) {
            HashMap<String ,String> colValue = new LinkedHashMap<>();


            for (int i = 0; i<motherColumnNamesArray.length;i++){
                if (i== motherColumnNamesArray.length-1){
                    colValue.put(motherColumnNamesArray[i],   "DATETIME");
                    continue;
                }
                colValue.put(motherColumnNamesArray[i],   (cursorMotherTable.getType(i )==1) ? "INTEGER":"TEXT");
            }



            allTablesMap.put(tableName.get(2),colValue);
        }


        //allTables.add(table_mother_map);////============================================

//===================================================================================================================================================================

        //============================================================================================================================================
        Cursor cursorAncPncMessageTable = database.rawQuery("SELECT * FROM "+tableName.get(3), null);  // Anc Pnc MESSAGE table
        String[] ancPncMessageColumnNamesArray = cursorAncPncMessageTable.getColumnNames();



        if (cursorAncPncMessageTable.moveToFirst()) {

            HashMap<String ,String> colValue = new LinkedHashMap<>();
for (int i = 0;i <ancPncMessageColumnNamesArray.length;i++){
    if (i == ancPncMessageColumnNamesArray.length-1){
        colValue.put(ancPncMessageColumnNamesArray[i],   "DATETIME");
        continue;
    }
    colValue.put(ancPncMessageColumnNamesArray[i],  (cursorAncPncMessageTable.getType(i )==1) ? "INTEGER": "TEXT");
}


            allTablesMap.put(tableName.get(3),colValue);
        }


        //allTables.add(table_message_map);////============================================

//===================================================================================================================================================================



        //============================================================================================================================================
        Cursor cursorDeliveryAndChildMessageTable = database.rawQuery("SELECT * FROM "+tableName.get(4), null);  // DeliveryAndChild MESSAGE table
        String[] deliveryAndChildMessageColumnNamesArray = cursorDeliveryAndChildMessageTable.getColumnNames();


        if (cursorDeliveryAndChildMessageTable.moveToFirst()) {

            HashMap<String ,String> colValue = new LinkedHashMap<>();
            for (int i = 0;i < deliveryAndChildMessageColumnNamesArray.length;i++){
                if (i==deliveryAndChildMessageColumnNamesArray.length-1){
                    colValue.put(deliveryAndChildMessageColumnNamesArray[i],   "DATETIME");
                    continue;
                }
                colValue.put(deliveryAndChildMessageColumnNamesArray[i],  (cursorDeliveryAndChildMessageTable.getType(i )==1) ? "INTEGER": "TEXT");
            }


            allTablesMap.put(tableName.get(4),colValue);
        }


        //allTables.add(table_message_map);////============================================

//===================================================================================================================================================================
        //============================================================================================================================================
        Cursor cursorChildTable = database.rawQuery("SELECT * FROM "+tableName.get(5), null);  // Child table
        String[] childColumnNamesArray = cursorChildTable.getColumnNames();

        HashMap<String , HashMap<String ,String>>  table_child_map = new HashMap<>();
        List<HashMap<String ,String>> tableChildRow = new ArrayList<>();
        if (cursorChildTable.moveToFirst()) {

            HashMap<String ,String> colValue = new LinkedHashMap<>();


            for (int i = 0; i<childColumnNamesArray.length;i++){

                colValue.put(childColumnNamesArray[i],  (cursorChildTable.getType(i)==1) ? "INTEGER": "TEXT");
            }







            allTablesMap.put(tableName.get(5),colValue);
        }


//===================================================================================================================================================================
        //============================================================================================================================================
        Cursor cursorChildFollowUpTable = database.rawQuery("SELECT * FROM "+tableName.get(6), null);  // Child FollowUp table
        String[] childFollowUpColumnNamesArray = cursorChildFollowUpTable.getColumnNames();

//        HashMap<String , HashMap<String ,String>>  table_child_map = new HashMap<>();
//        List<HashMap<String ,String>> tableChildRow = new ArrayList<>();
        if (cursorChildFollowUpTable.moveToFirst()) {

            HashMap<String ,String> colValue = new LinkedHashMap<>();


            for (int i = 0; i<childFollowUpColumnNamesArray.length;i++){
                if (i == childFollowUpColumnNamesArray.length-1){
                    colValue.put(childFollowUpColumnNamesArray[i],   "DATETIME");
                    continue;
                }
                colValue.put(childFollowUpColumnNamesArray[i],  (cursorChildFollowUpTable.getType(i)==1) ? "INTEGER": "TEXT");
            }


            allTablesMap.put(tableName.get(6),colValue);
        }


//===================================================================================================================================================================

        dbDef_hashMap.put("dbDef",allTablesMap);
        //dbDef_List.add(dbDef_hashMap);


        return dbDef_hashMap;
    }

}  //===========================  Class end   ==========================