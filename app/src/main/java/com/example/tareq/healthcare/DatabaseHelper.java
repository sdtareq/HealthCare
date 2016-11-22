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

/**
 * Created by TAREQ on 10/23/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;

    private static final String TAG = "DatabaseHelper";


    private static final int DATABASE_VERSION = 28;


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
    protected static final String MOTHER_COLUMN_SYNC_STATUS = "sync_status";    //----------------------------/14
    protected static final String MOTHER_COLUMN_TIMESTAMP = "created_at";    //----------------------------/15

    //-------------------------  MOTHER table  end ------------------------------

    //-------------------------  MESSAGE DELIVERY table start  ------------------------------

    protected static final String TABLE_MESSAGE_DELIVERY = "table_message_delivery";
    protected static final String MESSAGE_DELIVERY_COL_ID = "_id";           //----------------------------0
    protected static final String MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID = "mother_id";           //----------------------------1
    protected static final String MESSAGE_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED = "is_ANC_1_message_delivered"; //----------------------------2
    protected static final String MESSAGE_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED = "is_ANC_2_message_delivered"; //----------------------------3
    protected static final String MESSAGE_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED = "is_ANC_3_message_delivered"; //----------------------------4
    protected static final String MESSAGE_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED = "is_ANC_4_message_delivered"; //----------------------------5
    protected static final String MESSAGE_DELIVERY_COL_IS_PRE_DELIVERY_MSG_DELIVERED = "is_pre_delivery_message_delivered"; //----------------------------6
    protected static final String MESSAGE_DELIVERY_COL_IS_PNC_MSG_DELIVERED = "is_PNC_message_delivered"; //----------------------------7
    protected static final String MESSAGE_DELIVERY_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED = "is_child_0_to_14_days_message_delivered"; //----------------------------8
    protected static final String MESSAGE_DELIVERY_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED = "is_child_1_2_3_months_message_delivered"; //----------------------------9
    protected static final String MESSAGE_DELIVERY_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED = "is_child_6_to_8_months_message_delivered"; //----------------------------10
    protected static final String MESSAGE_DELIVERY_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED = "is_child_9_to_12_months_message_delivered"; //----------------------------11
    protected static final String MESSAGE_DELIVERY_COL_IS_MOTHER_DEAD = "is_mother_dead";                                      //----------------------------12



    //-------------------------  MESSAGE DELIVERY table  end ------------------------------


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
//    private static final String CHILD_COLUMN_IS_CHILD_BORN ="is_child_born";  //----- -------------
//    private static final String CHILD_COLUMN_CHILD_BIRTHDAY ="child_birthday";  //------ ----- ---
//    private static final String CHILD_COLUMN_PHONE_NUMBER ="phone_number";  //------ ----- ---
//    private static final String CHILD_COLUMN_MOTHER_ADDRESS ="mother_address";  //------ ----- ---
    //-------------------------  child table  end ------------------------------



    //-------------------------  Child Follow Up table start  ------------------------------

    protected static final String TABLE_CHILD_FOLLOW_UP = "table_child_follow_up";
    protected static final String CHILD_FOLLOW_UP_COL_ID            = "_id";           //----------------------------0
    protected static final String CHILD_FOLLOW_UP_COL_MOTHER_ID     = "mother_id";           //----------------------------1
    protected static final String CHILD_FOLLOW_UP_COL_MOTHER_NAME   = "child_mother_name";           //----------------------------2
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_NAME    = "child_name"; //----------------------------3
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_TABLE_ID = "child_table_id"; //-------------------------------- ---4
    protected static final String CHILD_FOLLOW_UP_COL_DATE_OF_VISIT = "date_of_visit";    //----------------------------/5
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_WEIGHT  = "child_weight";    //----------------------------/6
    protected static final String CHILD_FOLLOW_UP_COL_CHILD_HEIGHT   = "child_height";  // ----- 7
//    private static final String CHILD_COLUMN_IS_CHILD_BORN ="is_child_born";  //----- -------------
//    private static final String CHILD_COLUMN_CHILD_BIRTHDAY ="child_birthday";  //------ ----- ---
//    private static final String CHILD_COLUMN_PHONE_NUMBER ="phone_number";  //------ ----- ---
//    private static final String CHILD_COLUMN_MOTHER_ADDRESS ="mother_address";  //------ ----- ---
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
                + MOTHER_COLUMN_SYNC_STATUS + " TEXT ,"
                + MOTHER_COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";

        String CREATE_MESSAGE_DELIVERY_TABLE = "CREATE TABLE " + TABLE_MESSAGE_DELIVERY    //-------------------   Create Table for MOTHER
                + "("
                + MESSAGE_DELIVERY_COL_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED  + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_PRE_DELIVERY_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_PNC_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED   + " TEXT , "
                + MESSAGE_DELIVERY_COL_IS_MOTHER_DEAD   + " TEXT "
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
                + CHILD_COLUMN_ID_NUMBER_OF_CHILD + " TEXT UNIQUE  "

                + ")";

        String CREATE_CHILD_FOLLOW_UP_TABLE = "CREATE TABLE " + TABLE_CHILD_FOLLOW_UP    //-------------------   Create Table for Child
                + "("
                + CHILD_FOLLOW_UP_COL_ID            + " INTEGER UNIQUE PRIMARY KEY, "
                + CHILD_FOLLOW_UP_COL_MOTHER_ID     +  " TEXT , "
                + CHILD_FOLLOW_UP_COL_MOTHER_NAME   +  " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_NAME    +  " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_TABLE_ID + " TEXT , "
                + CHILD_FOLLOW_UP_COL_DATE_OF_VISIT      + " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_WEIGHT    + " TEXT , "
                + CHILD_FOLLOW_UP_COL_CHILD_HEIGHT       + " TEXT UNIQUE  "

                + ")";

        db.execSQL(CREATE_LOGIN_TABLE);     //-------------------------------------------------------- -----===== //
        db.execSQL(CREATE_MOTHER_TABLE);
        db.execSQL(CREATE_MESSAGE_DELIVERY_TABLE);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE_DELIVERY);
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

        String userName = "admin", userPassword = "admin", userType = "test";

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_NAME, userName);
        values.put(USER_COLUMN_PASSWORD, userPassword);
        values.put(USER_COLUMN_TYPE, userType);


        long newRowId = 0;


        try {
            // Inserting Row
            db.insertOrThrow(TABLE_LOGIN, null, values);

        } catch (Exception e) {


            Log.d(TAG, "Duplicate Primary key at addAdmin()");


        }
        db.close(); // Closing database connection
        Log.d(TAG, "Successfully inserted");
    }                                                   // -------------------=================   add admin end ---------------


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

         String  childFollowUpTableId = generateUniqueId(),
                 childTableId           = child.getChildId(),
                 childMotherName        = child.getChildMotherName(),
                 childMotherId           = child.getChildMotherTableId(),
                 childName              = child.getChildName(),
                 childDateOfVisit           = child.getChildDateOfVisit(),
                 childWeight            = child.getChildWeight(),
                 childHeight            = child.getChildHeight();




         SQLiteDatabase db = super.getWritableDatabase();
         ContentValues values = new ContentValues();


         values.put(CHILD_FOLLOW_UP_COL_ID           , childFollowUpTableId);
         values.put(CHILD_FOLLOW_UP_COL_MOTHER_ID    , childMotherId      );
         values.put(CHILD_FOLLOW_UP_COL_MOTHER_NAME  , childMotherName     );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_NAME   , childName       );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_TABLE_ID, childTableId           );
         values.put(CHILD_FOLLOW_UP_COL_DATE_OF_VISIT, childDateOfVisit    );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_WEIGHT , childWeight         );
         values.put(CHILD_FOLLOW_UP_COL_CHILD_HEIGHT , childHeight         );


         try {
             // Inserting Row
             db.insertOrThrow(TABLE_CHILD_FOLLOW_UP, null, values);

         } catch (Exception e) {


             Log.d(TAG, "Duplicate Primary key at addChildFollowUp() ");


         }


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
        String deliveryDate               ;
        String syncStatus = "false";


//        String motherName = mother.getMotherName(), lastMenstruationDate,
//                isMessageDelivered, isChildMessageDelivered, isPreDeliveryMessageDelivered, isChildBorn, deliveryDate, pregnancyState, motherPhoneNumber = mother.getMotherPhoneNumber(),
//                motherAddress = mother.getMotherAddress(), motherAge = mother.getMotherAge();

//        if (mother.getIsMessageDelivered() != null) {
//            isMessageDelivered = mother.getIsMessageDelivered();
//        } else {
//            isMessageDelivered = "false";
//        }
//
//        if (mother.getIsChildMessageDelivered() != null) {
//            isChildMessageDelivered = mother.getIsChildMessageDelivered();
//        } else {
//            isChildMessageDelivered = "false";
//        }
//        if (mother.getIsPreDelivery_Message_Delivered() != null) {
//            isPreDeliveryMessageDelivered = mother.getIsPreDelivery_Message_Delivered();
//        } else {
//            isPreDeliveryMessageDelivered = "false";
//        }
//
//        if (mother.getIsChildBorn() != null) {
//            isChildBorn = mother.getIsChildBorn();
//        } else {
//            isChildBorn = "false";
//        }
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


//
//        if (mother.getPregnancyState() != null) {
//            pregnancyState = mother.getPregnancyState();
//
//        } else {
//            pregnancyState = "false";
//        }
//        if (mother.getLastMenstruationDate() != null) {
//            lastMenstruationDate = mother.getLastMenstruationDate();
//
//        } else {
//            lastMenstruationDate = "false";
//        }

        // Toast.makeText(context, " phone no "+motherPhoneNumber+"   address: "+motherAddress, Toast.LENGTH_SHORT).show();

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
    values.put(MOTHER_COLUMN_DELIVERY_DATE  ,deliveryDate  );
    values.put(MOTHER_COLUMN_SYNC_STATUS  ,syncStatus  );





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
        Log.d(TAG, "  =============  " + deliveryDate);
        Log.d(TAG, " ==============  " + syncStatus);


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
        add_mother_in_message_Delivery_table(mother);
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

    public String generateUniqueId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date now = new Date();
        String idString =  sdf.format(now);

        Log.d(TAG,"========== time id string: "+idString);
      //  long id = Long.parseLong(idString);
        //Log.d(TAG,"========== time id int: "+id+ "type of id is:  "+ id);

        return idString;
    }

public void add_mother_in_message_Delivery_table(Mother mother ){

    String mother_column_id = mother.getMotherRowPrimaryKey() ;
    String isANC_1_Message_Delivered                 = "false";
    String isANC_2_Message_Delivered                 = "false";
    String isANC_3_Message_Delivered                 = "false";
    String isANC_4_Message_Delivered                 = "false";
    String isPreDelivery_Message_Delivered           = "false";

    String isPNC_Message_Delivered                   = "false";
    String isChild_message_delivered_0_to_14_days    = "false";
    String isChild_message_delivered_1_2_3_month     = "false";
    String isChild_message_delivered_6_to_8_month    = "false";
    String isChild_message_delivered_9_to_12_month   = "false";
    String isMotherDead                              = "false";


    SQLiteDatabase db = super.getWritableDatabase();
    ContentValues values = new ContentValues();



    values.put(MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID, mother_column_id);
    values.put(MESSAGE_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED  ,isANC_1_Message_Delivered  );
    values.put(MESSAGE_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED  ,isANC_2_Message_Delivered  );
    values.put(MESSAGE_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED  ,isANC_3_Message_Delivered  );
    values.put(MESSAGE_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED  ,isANC_4_Message_Delivered  );
    values.put(MESSAGE_DELIVERY_COL_IS_PRE_DELIVERY_MSG_DELIVERED  ,isPreDelivery_Message_Delivered  );
    values.put(MESSAGE_DELIVERY_COL_IS_PNC_MSG_DELIVERED  ,isPNC_Message_Delivered  );
    values.put(MESSAGE_DELIVERY_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED  ,isChild_message_delivered_0_to_14_days  );
    values.put(MESSAGE_DELIVERY_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED  , isChild_message_delivered_1_2_3_month );
    values.put(MESSAGE_DELIVERY_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED  ,isChild_message_delivered_6_to_8_month  );
    values.put(MESSAGE_DELIVERY_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED ,isChild_message_delivered_9_to_12_month  );
    values.put(MESSAGE_DELIVERY_COL_IS_MOTHER_DEAD  ,isMotherDead  );


    try {
        // Inserting Row
        db.insertOrThrow(TABLE_MESSAGE_DELIVERY, null, values);

    } catch (Exception e) {


        Log.d(TAG, "Duplicate Primary key at add_mother_in_message_Delivery_table(Mother mother ) ");


    }


    db.close(); // Closing database connection
    Log.d(TAG, "id Successfully inserted in message delivery table");


}
    public void registerChild(Child child) {     // ------------------- ======================= add child  start ---------------


        String childMotherName = child.getChildMotherName(),
                childMotherId = child.getChildMotherTableId(),
                childName = child.getChildName(),
                childDateOfBirth = child.getChildDateOfBirth(),
                sexOfChild = child.getSexOfChild(),
                childBirthWeight = child.getChildBirthWeight(),

                idNumberOfChild = child.getIdNumberOfChild();


        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues values = new ContentValues();


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
        SQLiteDatabase database = this.getWritableDatabase();

        String motherRowId = mother.getMotherRowPrimaryKey();
        Log.d(TAG, "=======================  "+motherRowId);

        database.execSQL("DELETE FROM " + TABLE_MOTHER + " WHERE " + MOTHER_COLUMN_ID + " = '" + motherRowId + "'");
        database.execSQL("DELETE FROM " + TABLE_MESSAGE_DELIVERY + " WHERE " + MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " = '" + motherRowId + "'");
        if (mother.getChild() != null){
            database.execSQL("DELETE FROM " + TABLE_CHILD + " WHERE " + CHILD_COLUMN_MOTHER_ID + "= '" + motherRowId + "'");
        }
        //Close the database
        database.close();


    }
    // -------------------   update mother end ---------------

    public boolean isValidUser(User user) {              //  ------------------ Check Valid user or not start----------
        String userName = user.getUserName(),
                userPassword = user.getUserPassword(),
                userType = user.getUserType();

        String where = USER_COLUMN_NAME + " =? AND " + USER_COLUMN_PASSWORD + " =? AND " + USER_COLUMN_TYPE + " =? ";

        SQLiteDatabase db = super.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LOGIN, null, where, new String[]{userName, userPassword, userType}, null, null, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }

        if (cursor.moveToFirst()) {
            MainActivity.user_primary_key = String.valueOf(cursor.getInt(0));
            MainActivity.user_name = userName;
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
        String syncStatus                         ;
        String timeStamp                         ;


        String isANC_1_Message_Delivered                 ;
        String isANC_2_Message_Delivered                 ;
        String isANC_3_Message_Delivered                 ;
        String isANC_4_Message_Delivered                 ;
        String isPreDelivery_Message_Delivered           ;
        String isPNC_Message_Delivered                   ;
        String isChild_message_delivered_0_to_14_days    ;
        String isChild_message_delivered_1_2_3_month     ;
        String isChild_message_delivered_6_to_8_month    ;
        String isChild_message_delivered_9_to_12_month   ;
        String isMotherDead                              ;


        List<Mother> allMothers = new ArrayList<>();

        String selectAllMoters = "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_MESSAGE_DELIVERY + " ON " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " = " + TABLE_MESSAGE_DELIVERY
                + "." + MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID ;
        //String selectAllMoters = "SELECT  * FROM " + TABLE_MOTHER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllMoters, null);

        if (cursor != null && cursor.moveToFirst()) {

            do {
//                String motherName, lastMenstruationDate, motherAge, isMessageDelivered, isChildMessageDelivered, isPreDeliveryMessageDelivered, pregnancyState, isChildBorn, childBirthday,
//                        motherRowPrimaryKey, motherPhoneNumber, motherAddress;

                mother_column_id           = cursor.getString(0);
                motherName                 = cursor.getString(1);
                husbandName                = cursor.getString(2);
                motherAge                  = cursor.getString(3);
                motherPhoneNumber          = cursor.getString(4);
                desiredCallingTime         = cursor.getString(5);
                motherAddress              = cursor.getString(6);
                GIS_Location               = cursor.getString(7);
                alternativePhoneNumber     = cursor.getString(8);
                alternativePhoneOwnerName  = cursor.getString(9);
                DHIS_ID                    = cursor.getString(10);
                lastMenstruationDate       = cursor.getString(11);
                pregnancyState             = cursor.getString(12);
                deliveryDate               = cursor.getString(13);
                syncStatus                 = cursor.getString(14);
                timeStamp                  = cursor.getString(15);


                isANC_1_Message_Delivered               = cursor.getString(18);
                isANC_2_Message_Delivered               = cursor.getString(19);
                isANC_3_Message_Delivered               = cursor.getString(20);
                isANC_4_Message_Delivered               = cursor.getString(21);
                isPreDelivery_Message_Delivered         = cursor.getString(22);
                isPNC_Message_Delivered                 = cursor.getString(23);
                isChild_message_delivered_0_to_14_days  = cursor.getString(24);
                isChild_message_delivered_1_2_3_month   = cursor.getString(25);
                isChild_message_delivered_6_to_8_month  = cursor.getString(26);
                isChild_message_delivered_9_to_12_month = cursor.getString(27);
                isMotherDead                            = cursor.getString(28);


                Mother item = new Mother(mother_column_id,motherName,husbandName,motherAge,motherPhoneNumber,desiredCallingTime,motherAddress,GIS_Location,alternativePhoneOwnerName,alternativePhoneNumber,
                        DHIS_ID,lastMenstruationDate,pregnancyState,deliveryDate,isANC_1_Message_Delivered,isANC_2_Message_Delivered,isANC_3_Message_Delivered,isANC_4_Message_Delivered,
                        isPreDelivery_Message_Delivered,isPNC_Message_Delivered,isChild_message_delivered_0_to_14_days,isChild_message_delivered_1_2_3_month,isChild_message_delivered_6_to_8_month,
                        isChild_message_delivered_9_to_12_month,isMotherDead);
                item.setSyncStatus(syncStatus);
                item.setTimeStamp(timeStamp);

                allMothers.add(item);


            } while (cursor.moveToNext());

            cursor.close();
        }


        return allMothers;
    }                                                  //  ------------------ get List of All Mothers  end ----------

    public List<Mother> getAllMotherswithChild() {                 //  ------------------ get List of All Mothers  with Child start ----------
        List<Mother> allMothers = new ArrayList<>();

//        String selectAllMothersWithChild = "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_CHILD + " ON " + TABLE_CHILD + "." + CHILD_COLUMN_MOTHER_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID + " WHERE "
//                + TABLE_MOTHER + "." + MOTHER_COLUMN_PREGNANCY_STATE + "=?";

        String selectAllMothersWithChild = "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_CHILD + " ON " + TABLE_CHILD + "." + CHILD_COLUMN_MOTHER_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID
                + " INNER JOIN " + TABLE_MESSAGE_DELIVERY + " ON " + TABLE_MESSAGE_DELIVERY + "." + MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID +" WHERE "
                + TABLE_MOTHER + "." + MOTHER_COLUMN_PREGNANCY_STATE + "=?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllMothersWithChild, new String[]{"post delivery"});

        if (cursor != null && cursor.moveToFirst()) {

            do {
//                String motherName, lastMenstruationDate, motherAge, isMessageDelivered, isChildMessageDelivered, isPreDeliveryMessageDelivered, pregnancyState, isChildBorn, childBirthday,
//                        motherRowPrimaryKey, motherPhoneNumber, motherAddress, childColumnId, childMotherId, childMotherName, childName, sexOfChild, childDateOfBirh, childBirthWeight, idNumberOfChild;
                String motherRowPrimaryKey                          = cursor.getString(0)                                       ;
                String motherName                                   = cursor.getString(1)                                       ;
                String husbandName                                  = cursor.getString(2)                                          ;
                String motherAge                                    = cursor.getString(3)                                          ;
                String motherPhoneNumber                            = cursor.getString(4)                                                       ;
                String desiredCallingTime                           = cursor.getString(5)                                                 ;
                String motherAddress                                = cursor.getString(6)                                            ;
                String GIS_Location                                 = cursor.getString(7)                                           ;
                String alternativePhoneNumber                       = cursor.getString(8)                                                     ;
                String alternativePhoneOwnerName                    = cursor.getString(9)                                                        ;
                String DHIS_ID                                      = cursor.getString(10)                                      ;
                String lastMenstruationDate                         = cursor.getString(11)                                                   ;
                String pregnancyState                               = cursor.getString(12)                                             ;
                String deliveryDate                                 = cursor.getString(13)                                           ;
                String syncStatus                                   = cursor.getString(14)                                           ;
                String timeStamp                                    = cursor.getString(15)                                           ;


                String  childColumnId                               = cursor.getString(16);
                String  childName                                   = cursor.getString(19)      ;
                String  sexOfChild                                  = cursor.getString(20)        ;
                String  childDateOfBirth                            = cursor.getString(21)             ;
                String  childBirthWeight                            = cursor.getString(22)             ;
                String  idNumberOfChild                             = cursor.getString(23)            ;


                String isANC_1_Message_Delivered                    = cursor.getString(26)                                                        ;
                String isANC_2_Message_Delivered                    = cursor.getString(27)                                                        ;
                String isANC_3_Message_Delivered                    = cursor.getString(28)                                                        ;
                String isANC_4_Message_Delivered                    = cursor.getString(29)                                                        ;
                String isPreDelivery_Message_Delivered              = cursor.getString(30)                                                              ;
                String isPNC_Message_Delivered                      = cursor.getString(31)                                                      ;
                String isChild_message_delivered_0_to_14_days       = cursor.getString(32)                                                                     ;
                String isChild_message_delivered_1_2_3_month        = cursor.getString(33)                                                                    ;
                String isChild_message_delivered_6_to_8_month       = cursor.getString(34)                                                                     ;
                String isChild_message_delivered_9_to_12_month      = cursor.getString(35)                                                                      ;
                String isMotherDead                                 = cursor.getString(36)                                           ;





                Child child = new Child(motherName, motherRowPrimaryKey, childName,childDateOfBirth, sexOfChild, childBirthWeight, childColumnId, idNumberOfChild);

                Mother item = new Mother(motherRowPrimaryKey,motherName,husbandName,motherAge,motherPhoneNumber,desiredCallingTime,motherAddress,GIS_Location,alternativePhoneOwnerName,
                        alternativePhoneNumber,DHIS_ID,lastMenstruationDate,pregnancyState,deliveryDate,isANC_1_Message_Delivered,isANC_2_Message_Delivered,isANC_3_Message_Delivered,isANC_4_Message_Delivered,
                        isPreDelivery_Message_Delivered,isPNC_Message_Delivered,isChild_message_delivered_0_to_14_days,isChild_message_delivered_1_2_3_month,isChild_message_delivered_6_to_8_month,
                        isChild_message_delivered_9_to_12_month,isMotherDead);
item.setSyncStatus(syncStatus);
                item.setTimeStamp(timeStamp);
                item.setChild(child);

                allMothers.add(item);


            } while (cursor.moveToNext());

            cursor.close();
        }


        return allMothers;
    }                                                  //  ------------------ get List of All Mothers with Child  end ----------

    //  ------------------ set message status for a Mothers  start ----------
    String syncStatusFalse = "false"; //======================================================================== set Sync Status ------


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
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_CHILD_0_TO_14_DAYS_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }
    //  ------------------ set  CHILD 1,2,3 MONTH Message Delivery Status  start ----------
    public void setChild_1_2_3_month_message_delivery_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_CHILD_1_2_3_MONTH_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }
    //  ------------------ set  CHILD 6 TO 8 MONTH Message Delivery Status  start ----------
    public void setChild_6_To_8_month_message_delivery_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_CHILD_6_TO_8_MONTH_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }
    //  ------------------ set  CHILD 9 TO 12 MONTH Message Delivery Status  start ----------
    public void setChild_9_To_12_month_message_delivery_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_CHILD_9_T0_12_MONTH_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ set  Pre Delivery Message Delivery Status  start ----------
    public void setPreDeliveryMessageStatus(String primaryKey, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_PRE_DELIVERY_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
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
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();

    }

    //  ------------------ SET  ANC 1 MESSAGE STATUS  start ----------
    public void set_ANC_1_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_ANC_1_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  ANC 2 MESSAGE STATUS  start ----------
    public void set_ANC_2_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_ANC_2_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  ANC 3 MESSAGE STATUS  start ----------
    public void set_ANC_3_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_ANC_3_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  ANC 4 MESSAGE STATUS  start ----------
    public void set_ANC_4_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_ANC_4_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }

    //  ------------------ SET  PNC MESSAGE STATUS  start ----------
    public void set_PNC_message_status(String primaryKey, String status){
        SQLiteDatabase db = super.getWritableDatabase();
        String where = MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " =? ";
        ContentValues values = new ContentValues();
        values.put(MESSAGE_DELIVERY_COL_IS_PNC_MSG_DELIVERED, status);
        db.update(TABLE_MESSAGE_DELIVERY, values, where, new String[]{primaryKey});

//========================= set sync status false in mother Table
        String whereMother = MOTHER_COLUMN_ID + " =? ";
        ContentValues valuesMother = new ContentValues();
        valuesMother.put(MOTHER_COLUMN_SYNC_STATUS, syncStatusFalse );
        db.update(TABLE_MOTHER, valuesMother, whereMother, new String[]{primaryKey});
        db.close();
    }




//    public    HashMap<String ,List<HashMap<String ,String>>> getAllTables(){// =========================== get all tables
//       // List<HashMap<String,HashMap<String ,String >>> allTableList = new ArrayList<>();
//         List<HashMap<String, List<HashMap<String,String>>>> allTableList = new ArrayList<>();
//        //HashMap<String ,List<HashMap<String ,String>>> tableMap = new HashMap<>();
//        HashMap<String ,List<HashMap<String ,String>>> all_table_map = new LinkedHashMap<>();
//        List<String> tableName = new ArrayList<>();
//        SQLiteDatabase database = super.getWritableDatabase();
//        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
//
//
//        if (c.moveToFirst()) {
//            while ( !c.isAfterLast() ) {
//
//                tableName.add(c.getString(0)); // table names
//
//                c.moveToNext();
//            }
//        }
////============================================================================================================================================
//        Cursor cursorMotherTable = database.rawQuery("SELECT * FROM "+tableName.get(2)+" WHERE "
//                + MOTHER_COLUMN_SYNC_STATUS + "=?", new String[] {"false"});  // mother table
//        String[] motherColumnNamesArray = cursorMotherTable.getColumnNames();
//
//        HashMap<String ,List<HashMap<String ,String>>> table_map = new HashMap<>();
//        List<HashMap<String ,String>> tableRow = new ArrayList<>();
//         if (cursorMotherTable.moveToFirst()) {
//            while ( !cursorMotherTable.isAfterLast() ) {
//                HashMap<String ,String> colValue = new LinkedHashMap<>();
//
//
//                colValue.put(motherColumnNamesArray[0], cursorMotherTable.getString(0));
//                colValue.put(motherColumnNamesArray[1], cursorMotherTable.getString(1));
//                colValue.put(motherColumnNamesArray[2], cursorMotherTable.getString(2));
//                colValue.put(motherColumnNamesArray[3], cursorMotherTable.getString(3));
//                colValue.put(motherColumnNamesArray[4], cursorMotherTable.getString(4));
//                colValue.put(motherColumnNamesArray[5], cursorMotherTable.getString(5));
//                colValue.put(motherColumnNamesArray[6], cursorMotherTable.getString(6));
//                colValue.put(motherColumnNamesArray[7], cursorMotherTable.getString(7));
//                colValue.put(motherColumnNamesArray[8], cursorMotherTable.getString(8));
//                colValue.put(motherColumnNamesArray[9], cursorMotherTable.getString(9));
//                colValue.put(motherColumnNamesArray[10],cursorMotherTable.getString(10));
//                colValue.put(motherColumnNamesArray[11],cursorMotherTable.getString(11));
//                colValue.put(motherColumnNamesArray[12],cursorMotherTable.getString(12));
//                colValue.put(motherColumnNamesArray[13],cursorMotherTable.getString(13));
//                colValue.put(motherColumnNamesArray[14],cursorMotherTable.getString(14));
//                colValue.put(motherColumnNamesArray[15],cursorMotherTable.getString(15));
//
//
//
//                tableRow.add(colValue);
//                cursorMotherTable.moveToNext();
//            }
//        }
//        cursorMotherTable.close();
//        //table_map.put(tableName.get(2),tableRow);
//       all_table_map.put(tableName.get(2),tableRow);
//
//             //allTableList.add(table_map);////============================================
//
////===================================================================================================================================================================
////============================================================================================================================================
////        "SELECT  * FROM " + TABLE_MOTHER + " INNER JOIN " + TABLE_CHILD + " ON " + TABLE_CHILD + "." + CHILD_COLUMN_MOTHER_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID
////                + " INNER JOIN " + TABLE_MESSAGE_DELIVERY + " ON " + TABLE_MESSAGE_DELIVERY + "." + MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID + " = " + TABLE_MOTHER + "." + MOTHER_COLUMN_ID +" WHERE "
////                + TABLE_MOTHER + "." + MOTHER_COLUMN_PREGNANCY_STATE + "=?";
//        String queryMessageTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(3)+ " ON " +tableName.get(3)+"."+ MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID
//                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";
//
//        Cursor cursorMessageTable = database.rawQuery(queryMessageTable, new String[]{"false"});  // message table
//        String[] messageColumnNamesArray = cursorMessageTable.getColumnNames();
//
//         HashMap<String ,List<HashMap<String ,String>>> table_message_map = new HashMap<>();
//         List<HashMap<String ,String>> tableMessageRow = new ArrayList<>();
//        if (cursorMessageTable.moveToFirst()) {
//            while ( !cursorMessageTable.isAfterLast() ) {
//                HashMap<String ,String> colValue = new LinkedHashMap<>();
//
//
//                colValue.put(messageColumnNamesArray[16], cursorMessageTable.getString(16));
//                colValue.put(messageColumnNamesArray[17], cursorMessageTable.getString(17));
//                colValue.put(messageColumnNamesArray[18], cursorMessageTable.getString(18));
//                colValue.put(messageColumnNamesArray[19], cursorMessageTable.getString(19));
//                colValue.put(messageColumnNamesArray[20], cursorMessageTable.getString(20));
//                colValue.put(messageColumnNamesArray[21], cursorMessageTable.getString(21));
//                colValue.put(messageColumnNamesArray[22], cursorMessageTable.getString(22));
//                colValue.put(messageColumnNamesArray[23], cursorMessageTable.getString(23));
//                colValue.put(messageColumnNamesArray[24], cursorMessageTable.getString(24));
//                colValue.put(messageColumnNamesArray[25], cursorMessageTable.getString(25));
//                colValue.put(messageColumnNamesArray[26], cursorMessageTable.getString(26));
//                colValue.put(messageColumnNamesArray[27], cursorMessageTable.getString(27));
//                colValue.put(messageColumnNamesArray[28], cursorMessageTable.getString(28));
//
//
//
//
//                tableMessageRow.add(colValue);
//                cursorMessageTable.moveToNext();
//            }
//        }
//        cursorMessageTable.close();
//       // table_message_map.put(tableName.get(3),tableMessageRow);
//        all_table_map.put(tableName.get(3),tableMessageRow);
//        //allTableList.add(table_message_map);////============================================
//
////===================================================================================================================================================================
//        //============================================================================================================================================
//        String queryChildTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(4)+ " ON " +tableName.get(4)+"."+ CHILD_COLUMN_MOTHER_ID
//                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";
//        Cursor cursorChildTable = database.rawQuery(queryChildTable, new String[] {"false"});  // Child table
//        String[] childColumnNamesArray = cursorChildTable.getColumnNames();
//
//        HashMap<String ,List<HashMap<String ,String>>> table_child_map = new HashMap<>();
//        List<HashMap<String ,String>> tableChildRow = new ArrayList<>();
//        if (cursorChildTable.moveToFirst()) {
//            while (!cursorChildTable.isAfterLast()) {
//                HashMap<String, String> colValue = new LinkedHashMap<>();
//
//
//                colValue.put(childColumnNamesArray[16], cursorChildTable.getString(16));
//                colValue.put(childColumnNamesArray[17], cursorChildTable.getString(17));
//                colValue.put(childColumnNamesArray[18], cursorChildTable.getString(18));
//                colValue.put(childColumnNamesArray[19], cursorChildTable.getString(19));
//                colValue.put(childColumnNamesArray[20], cursorChildTable.getString(20));
//                colValue.put(childColumnNamesArray[21], cursorChildTable.getString(21));
//                colValue.put(childColumnNamesArray[22], cursorChildTable.getString(22));
//               // colValue.put(childColumnNamesArray[23], cursorChildTable.getString(23));
//
//
//                tableChildRow.add(colValue);
//                cursorChildTable.moveToNext();
//
//            }
//        }
//        cursorChildTable.close();
//
//       // table_child_map.put(tableName.get(4),tableChildRow);
//       all_table_map.put(tableName.get(4),tableChildRow);
//        //allTableList.add(table_child_map);////============================================
//
////===================================================================================================================================================================
//
//        database.close();
//
//        return all_table_map;
//    }
//





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


//                HashMap<String ,String> colValue = new LinkedHashMap<>();
//
//
//                colValue.put(motherColumnNamesArray[0], cursorMotherTable.getString(0));
//                colValue.put(motherColumnNamesArray[1], cursorMotherTable.getString(1));
//                colValue.put(motherColumnNamesArray[2], cursorMotherTable.getString(2));
//                colValue.put(motherColumnNamesArray[3], cursorMotherTable.getString(3));
//                colValue.put(motherColumnNamesArray[4], cursorMotherTable.getString(4));
//                colValue.put(motherColumnNamesArray[5], cursorMotherTable.getString(5));
//                colValue.put(motherColumnNamesArray[6], cursorMotherTable.getString(6));
//                colValue.put(motherColumnNamesArray[7], cursorMotherTable.getString(7));
//                colValue.put(motherColumnNamesArray[8], cursorMotherTable.getString(8));
//                colValue.put(motherColumnNamesArray[9], cursorMotherTable.getString(9));
//                colValue.put(motherColumnNamesArray[10],cursorMotherTable.getString(10));
//                colValue.put(motherColumnNamesArray[11],cursorMotherTable.getString(11));
//                colValue.put(motherColumnNamesArray[12],cursorMotherTable.getString(12));
//                colValue.put(motherColumnNamesArray[13],cursorMotherTable.getString(13));
//                colValue.put(motherColumnNamesArray[14],cursorMotherTable.getString(14));
//                colValue.put(motherColumnNamesArray[15],cursorMotherTable.getString(15));



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

        String queryMessageTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(3)+ " ON " +tableName.get(3)+"."+ MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID
                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";

        Cursor cursorMessageTable = database.rawQuery(queryMessageTable, new String[]{"false"});  // message table
        String[] messageColumnNamesArray = cursorMessageTable.getColumnNames();

        String messageAllRows = "" ;
        boolean flagMessage = false;

        if (cursorMessageTable.moveToFirst()) {
            String n = "", t ="\t";

            while ( !cursorMessageTable.isAfterLast() ) {

                if (flagMessage){
                    n= "\n" ;
                }

                messageAllRows += n+cursorMessageTable.getString(16);
                for (int i=17;i< messageColumnNamesArray.length;i++){
                    messageAllRows += t + cursorMessageTable.getString(i);
                }



                flagMessage = true;

                cursorMessageTable.moveToNext();
            }
        }
        cursorMessageTable.close();

        tableNameAndRows_map.put(tableName.get(3),messageAllRows);

//                colValue.put(messageColumnNamesArray[16], cursorMessageTable.getString(16));
//                colValue.put(messageColumnNamesArray[17], cursorMessageTable.getString(17));
//                colValue.put(messageColumnNamesArray[18], cursorMessageTable.getString(18));
//                colValue.put(messageColumnNamesArray[19], cursorMessageTable.getString(19));
//                colValue.put(messageColumnNamesArray[20], cursorMessageTable.getString(20));
//                colValue.put(messageColumnNamesArray[21], cursorMessageTable.getString(21));
//                colValue.put(messageColumnNamesArray[22], cursorMessageTable.getString(22));
//                colValue.put(messageColumnNamesArray[23], cursorMessageTable.getString(23));
//                colValue.put(messageColumnNamesArray[24], cursorMessageTable.getString(24));
//                colValue.put(messageColumnNamesArray[25], cursorMessageTable.getString(25));
//                colValue.put(messageColumnNamesArray[26], cursorMessageTable.getString(26));
//                colValue.put(messageColumnNamesArray[27], cursorMessageTable.getString(27));
//                colValue.put(messageColumnNamesArray[28], cursorMessageTable.getString(28));

////===================================================================================================================================================================
//        //============================================================================================================================================
        String queryChildTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(4)+ " ON " +tableName.get(4)+"."+ CHILD_COLUMN_MOTHER_ID
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

                ChildAllRows += n+cursorChildTable.getString(16);
                for (int i=17;i< childColumnNamesArray.length;i++){
                    ChildAllRows += t + cursorChildTable.getString(i);
                }



                flagChild = true;

                cursorChildTable.moveToNext();
            }
        }
        cursorChildTable.close();

        tableNameAndRows_map.put(tableName.get(4),ChildAllRows);

//                colValue.put(childColumnNamesArray[16], cursorChildTable.getString(16));
//                colValue.put(childColumnNamesArray[17], cursorChildTable.getString(17));
//                colValue.put(childColumnNamesArray[18], cursorChildTable.getString(18));
//                colValue.put(childColumnNamesArray[19], cursorChildTable.getString(19));
//                colValue.put(childColumnNamesArray[20], cursorChildTable.getString(20));
//                colValue.put(childColumnNamesArray[21], cursorChildTable.getString(21));
//                colValue.put(childColumnNamesArray[22], cursorChildTable.getString(22));
//                colValue.put(childColumnNamesArray[23], cursorChildTable.getString(23));
//
//

////===================================================================================================================================================================
//        //============================================================================================================================================
        String queryChildFollowUpTable = "SELECT * FROM "+tableName.get(2)+" INNER JOIN "+ tableName.get(5)+ " ON " +tableName.get(5)+"."+ CHILD_FOLLOW_UP_COL_MOTHER_ID
                +" = "+tableName.get(2)+"."+MOTHER_COLUMN_ID+" WHERE "+ tableName.get(2)+"."+MOTHER_COLUMN_SYNC_STATUS+"=?";
        Cursor cursorChildFollowUpTable = database.rawQuery(queryChildFollowUpTable, new String[] {"false"});  // Child Follow Up table
        String[] childFollowUpColumnNamesArray = cursorChildTable.getColumnNames();


        String ChildFollowUpAllRows = "" ;
        boolean flagChildFollowUp = false;

        if (cursorChildFollowUpTable.moveToFirst()) {
            String n = "", t ="\t";

            while ( !cursorChildFollowUpTable.isAfterLast() ) {

                if (flagChildFollowUp){
                    n= "\n" ;
                }

                ChildFollowUpAllRows += n+cursorChildFollowUpTable.getString(16);
                for (int i=17;i< childFollowUpColumnNamesArray.length;i++){
                    ChildFollowUpAllRows += t + cursorChildFollowUpTable.getString(i);
                }



                flagChildFollowUp = true;

                cursorChildFollowUpTable.moveToNext();
            }
        }
        cursorChildFollowUpTable.close();

        tableNameAndRows_map.put(tableName.get(5),ChildFollowUpAllRows);



////===================================================================================================================================================================


        database.close();

        rowsAll_map.put("rowsAll",tableNameAndRows_map);

        return rowsAll_map;
    }










    public      HashMap<String,   HashMap<String , HashMap<String ,String>>> getDbDef2(){   //////////============================================
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


            colValue.put(motherColumnNamesArray[0],   (cursorMotherTable.getType(0 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[1],   (cursorMotherTable.getType(1 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[2],   (cursorMotherTable.getType(2 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[3],   (cursorMotherTable.getType(3 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[4],   (cursorMotherTable.getType(4 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[5],   (cursorMotherTable.getType(5 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[6],   (cursorMotherTable.getType(6 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[7],   (cursorMotherTable.getType(7 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[8],   (cursorMotherTable.getType(8 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[9],   (cursorMotherTable.getType(9 )==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[10],  (cursorMotherTable.getType(10)==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[11],  (cursorMotherTable.getType(11)==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[12],  (cursorMotherTable.getType(12)==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[13],  (cursorMotherTable.getType(13)==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[14],  (cursorMotherTable.getType(14)==1) ? "INTEGER":"TEXT");
            colValue.put(motherColumnNamesArray[15],  (cursorMotherTable.getType(15)==1) ? "INTEGER":"TEXT");



           // tableMotherRow.add(colValue);
            //   cursorMotherTable.moveToNext();
            // }
            //table_mother_map.put(tableName.get(2),colValue);
            allTablesMap.put(tableName.get(2),colValue);
        }


        //allTables.add(table_mother_map);////============================================

//===================================================================================================================================================================

        //============================================================================================================================================
        Cursor cursorMessageTable = database.rawQuery("SELECT * FROM "+tableName.get(3), null);  // MESSAGE table
        String[] messageColumnNamesArray = cursorMessageTable.getColumnNames();

        HashMap<String , HashMap<String ,String>>  table_message_map = new HashMap<>();
        List<HashMap<String ,String>> tableMessageRow = new ArrayList<>();
        if (cursorMessageTable.moveToFirst()) {

            HashMap<String ,String> colValue = new LinkedHashMap<>();


            colValue.put(messageColumnNamesArray[0],  (cursorMessageTable.getType(0 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[1],  (cursorMessageTable.getType(1 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[2],  (cursorMessageTable.getType(2 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[3],  (cursorMessageTable.getType(3 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[4],  (cursorMessageTable.getType(4 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[5],  (cursorMessageTable.getType(5 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[6],  (cursorMessageTable.getType(6 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[7],  (cursorMessageTable.getType(7 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[8],  (cursorMessageTable.getType(8 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[9],  (cursorMessageTable.getType(9 )==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[10], (cursorMessageTable.getType(10)==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[11], (cursorMessageTable.getType(11)==1) ? "INTEGER": "TEXT");
            colValue.put(messageColumnNamesArray[12], (cursorMessageTable.getType(12)==1) ? "INTEGER": "TEXT");




           // tableMessageRow.add(colValue);
            //table_message_map.put(tableName.get(3),colValue);
            allTablesMap.put(tableName.get(3),colValue);
        }


        //allTables.add(table_message_map);////============================================

//===================================================================================================================================================================



        //============================================================================================================================================
        Cursor cursorChildTable = database.rawQuery("SELECT * FROM "+tableName.get(4), null);  // Child table
        String[] childColumnNamesArray = cursorChildTable.getColumnNames();

        HashMap<String , HashMap<String ,String>>  table_child_map = new HashMap<>();
        List<HashMap<String ,String>> tableChildRow = new ArrayList<>();
        if (cursorChildTable.moveToFirst()) {

            HashMap<String ,String> colValue = new LinkedHashMap<>();


            colValue.put(childColumnNamesArray[0],  (cursorChildTable.getType(0 )==1) ? "INTEGER": "TEXT");
            colValue.put(childColumnNamesArray[1],  (cursorChildTable.getType(1 )==1) ? "INTEGER": "TEXT");
            colValue.put(childColumnNamesArray[2],  (cursorChildTable.getType(2 )==1) ? "INTEGER": "TEXT");
            colValue.put(childColumnNamesArray[3],  (cursorChildTable.getType(3 )==1) ? "INTEGER": "TEXT");
            colValue.put(childColumnNamesArray[4],  (cursorChildTable.getType(4 )==1) ? "INTEGER": "TEXT");
            colValue.put(childColumnNamesArray[5],  (cursorChildTable.getType(5 )==1) ? "INTEGER": "TEXT");
            colValue.put(childColumnNamesArray[6],  (cursorChildTable.getType(6 )==1) ? "INTEGER": "TEXT");
            colValue.put(childColumnNamesArray[7],  (cursorChildTable.getType(7 )==1) ? "INTEGER": "TEXT");






            allTablesMap.put(tableName.get(4),colValue);
        }


//===================================================================================================================================================================
        //============================================================================================================================================
        Cursor cursorChildFollowUpTable = database.rawQuery("SELECT * FROM "+tableName.get(5), null);  // Child FollowUp table
        String[] childFollowUpColumnNamesArray = cursorChildFollowUpTable.getColumnNames();

//        HashMap<String , HashMap<String ,String>>  table_child_map = new HashMap<>();
//        List<HashMap<String ,String>> tableChildRow = new ArrayList<>();
        if (cursorChildFollowUpTable.moveToFirst()) {

            HashMap<String ,String> colValue = new LinkedHashMap<>();


            for (int i = 0; i<childFollowUpColumnNamesArray.length;i++){
                colValue.put(childFollowUpColumnNamesArray[i],  (cursorChildFollowUpTable.getType(i)==1) ? "INTEGER": "TEXT");
            }

//            colValue.put(childColumnNamesArray[0],  (cursorChildTable.getType(0 )==1) ? "INTEGER": "TEXT");
//            colValue.put(childColumnNamesArray[1],  (cursorChildTable.getType(1 )==1) ? "INTEGER": "TEXT");
//            colValue.put(childColumnNamesArray[2],  (cursorChildTable.getType(2 )==1) ? "INTEGER": "TEXT");
//            colValue.put(childColumnNamesArray[3],  (cursorChildTable.getType(3 )==1) ? "INTEGER": "TEXT");
//            colValue.put(childColumnNamesArray[4],  (cursorChildTable.getType(4 )==1) ? "INTEGER": "TEXT");
//            colValue.put(childColumnNamesArray[5],  (cursorChildTable.getType(5 )==1) ? "INTEGER": "TEXT");
//            colValue.put(childColumnNamesArray[6],  (cursorChildTable.getType(6 )==1) ? "INTEGER": "TEXT");
//            colValue.put(childColumnNamesArray[7],  (cursorChildTable.getType(7 )==1) ? "INTEGER": "TEXT");






            allTablesMap.put(tableName.get(5),colValue);
        }


//===================================================================================================================================================================

        dbDef_hashMap.put("dbDef",allTablesMap);
        //dbDef_List.add(dbDef_hashMap);


        return dbDef_hashMap;
    }

}  //===========================  Class end   ==========================