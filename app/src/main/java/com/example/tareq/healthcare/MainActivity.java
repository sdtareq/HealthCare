package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import au.com.bytecode.opencsv.CSVWriter;

public class MainActivity extends AppCompatActivity {
    protected static String user_primary_key;
    protected static String user_name;
    protected static final String TAG = "MainActivity";
    protected static final String PREFERENCE_KEY = "last_date";
    protected static final String PREFERENCE_LAST_EXPORT = "last_date";
    protected boolean login_flag;
    DatabaseHelper db;


    boolean doubleBackToExitPressedOnce = false;   // track the back pressed button

    ProgressDialog progressDialog;  // init dialog


    TextView tvMessageRemain,
            tvMessageDelivered,
            tvMessageTotal, tvLastExportDate;

    android.support.v7.widget.CardView card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (savedInstanceState != null) {
            login_flag = savedInstanceState.getBoolean(TAG);
        } else {
            login_flag = true;
        }


//============================================ s t a r t  1 ==========================================================
        //Log - in First
        //=================== First Go to Login Page
//         if (login_flag){
//             Intent intent = new Intent(this, LoginActivity.class);
//             startActivity(intent);
//         }


        db = new DatabaseHelper(this);

        // temp start




//        Gson gson = new Gson();
//         String table1 = gson.toJson(db.getAllTables());
//      //  String table1 = gson.toJson(db.getDbDef2());
//
//        Toast.makeText(this,table1,Toast.LENGTH_LONG).show();
//        exportDoc(table1);






        // temp end
//
        init();


        if (!db.isMotherTableEmpty()) {
            new HeavyTaskExecutor().execute();
        } else {
            Log.d(TAG, " ========= Mother Table is Empty");
        }

        //============================================ e n d 1 ==========================================================

    }

    private void exportDoc(String jsonObject) {//================================== exportDoc start *******************************************

        if (isExternalStorageReadable() || isExternalStorageWritable()) {  //====================================
//                String tempDateString = new SimpleDateFormat("dd").format(new Date());
//                String tempMonthString = new SimpleDateFormat("MM").format(new Date());
//                int tempMonthInt = Integer.parseInt(tempMonthString);
//                String month = String.valueOf(tempMonthInt);
//                String year = new SimpleDateFormat("yyyy").format(new Date());
//                String month_Year = month + year;
            String dateMonthYear = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File(sdCard.getAbsolutePath() + "/Health Care");//=================== Name of the Folder
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!directory.mkdirs()) {
                Log.e(TAG, "======  Directory not created");
            }

//Now create the file in the above directory and write the contents into it
            File file = new File(directory, "HC " + dateMonthYear + ".txt");  // ========================= Name of the File

            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            // osw.write(strFileData);
            try {



                        osw.write(jsonObject);


                    Toast.makeText(getApplicationContext(),"Stored Successfully",Toast.LENGTH_SHORT).show();


                osw.flush();
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }//================================== exportDoc end *******************************************

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(TAG, false);

        super.onSaveInstanceState(outState);
    }

    private void init() {

        progressDialog =
                new ProgressDialog(MainActivity.this);

        SharedPreferences sharedPref = getSharedPreferences(
                PREFERENCE_KEY, Context.MODE_PRIVATE);                                                                    // ================  Shared Preference initialize


        tvMessageRemain = (TextView) findViewById(R.id.tvMessageRemain);
        tvMessageDelivered = (TextView) findViewById(R.id.tvMessageDelivered);
        tvMessageTotal = (TextView) findViewById(R.id.tvMessageTotal);
        tvLastExportDate = (TextView) findViewById(R.id.tvExportDate);

        String lastExportDate = sharedPref.getString(PREFERENCE_LAST_EXPORT, "Date");
        if (lastExportDate != null) {
            tvLastExportDate.setText(lastExportDate);
        }

        card = (CardView) findViewById(R.id.card_view_message);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                     //===================  Go to  Message Delivery Page
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.btLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {              //=========================================  Go to  LogOut Page
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.card_view_help_line).setOnClickListener(new View.OnClickListener() {  // =================  Call Help Line
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:345678888"));
                startActivity(intent);
            }
        });


        findViewById(R.id.card_view_All_Nutritions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            //=========================================  Go to  Audio Page
                Intent intent = new Intent(MainActivity.this, Nutrition_Messages_Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.card_view_Export).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                   //=========================================  Export file
                new HeavyTaskExecutorFor_Export().execute();
            }
        });

        findViewById(R.id.card_view_mother_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MotherRegistrationActivity.class);
                startActivity(intent);
            }
        });

        Log.d(TAG, "user primary key: " + user_primary_key + "  user Name  " + user_name);
    }


    // adding functionality for exit from the app
    @Override
    public void onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();


            return;
        }

        this.doubleBackToExitPressedOnce = true;
     Toast.makeText(this, "বাহির হওয়ার জন্য দুইবার ব্যাক বাটন ক্লিক করুন", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);


    }

    @Override
    protected void onPause() {
        super.onPause();

        if (progressDialog != null && progressDialog.isShowing())  // for handle view not attached to window manager exception
            progressDialog.dismiss();
        progressDialog = null;

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (progressDialog != null && progressDialog.isShowing()) { // for handle view not attached to window manager exception
//            progressDialog.dismiss();
//        progressDialog = null;}

        new HeavyTaskExecutor().execute();
    }

    public class HeavyTaskExecutor extends AsyncTask<String, Void, Map<String, Integer>> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (progressDialog == null) {
                progressDialog =
                        new ProgressDialog(MainActivity.this);
            }
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Map<String, Integer> doInBackground(String... datas) {


            Map<String, Integer> allStat = new HashMap<>();

            GroupMother groupMother = new GroupMother();
            groupMother.doGrouping(db.getAllMothers()); //------------------------ put list of mothers

            Log.d(TAG, String.valueOf(groupMother.anc1.size()));
            Log.d(TAG, String.valueOf(groupMother.pnc.size()));

            int anc1_remain = 0, anc1_delivered = 0, anc2_remain = 0, anc2_delivered = 0, anc3_remain = 0, anc3_delivered = 0, anc4_remain = 0, anc4_delivered = 0,
                    pnc_remain = 0, pnc_delivered = 0, child_message_remain = 0, child_message_delivered = 0, pre_delivery_message_remain = 0, pre_delivery_message_delivered = 0,
                    child_message_delivered_0_to_14_days_remain = 0, child_message_delivered_0_to_14_days_delivered = 0,

                    child_message_delivered_1_2_3_month_remain = 0, child_message_delivered_1_2_3_month_delivered = 0,

                    child_message_delivered_6_to_8_month_remain = 0, child_message_delivered_6_to_8_month_delivered = 0,

                    child_message_delivered_9_to_12_month_remain = 0,
                    child_message_delivered_9_to_12_month_delivered = 0, pnc3_remain = 0, pnc3_delivered = 0, pnc4_remain = 0, pnc4_delivered = 0;

            for (Mother theMother : groupMother.anc1) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsANC_1_Message_Delivered());

                if (isDelivered) {
                    anc1_delivered++;
                } else {
                    anc1_remain++;
                }
            }
            for (Mother theMother : groupMother.anc2) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsANC_2_Message_Delivered());

                if (isDelivered) {
                    anc2_delivered++;
                } else {
                    anc2_remain++;
                }
            }
            for (Mother theMother : groupMother.anc3) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsANC_3_Message_Delivered());
                boolean pre_delivery_message_status = Boolean.parseBoolean(theMother.getIsPreDelivery_Message_Delivered()); // pre delivery message status

                if (isDelivered) {
                    anc3_delivered++;
                } else {
                    anc3_remain++;
                }
                if (pre_delivery_message_status) {
                    pre_delivery_message_delivered++;
                } else {
                    pre_delivery_message_remain++;
                }
            }
            for (Mother theMother : groupMother.anc4) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsANC_4_Message_Delivered());
                boolean pre_delivery_message_status = Boolean.parseBoolean(theMother.getIsPreDelivery_Message_Delivered());

                if (isDelivered) {
                    anc4_delivered++;
                } else {
                    anc4_remain++;
                }

                if (pre_delivery_message_status) {
                    pre_delivery_message_delivered++;
                } else {
                    pre_delivery_message_remain++;
                }
            }


            for (Mother theMother : groupMother.pnc) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsPNC_Message_Delivered());

                if (isDelivered) {
                    pnc_delivered++;
                } else {
                    pnc_remain++;
                }
            }
            for (Mother theMother : groupMother.childCareMessageStatusList) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsChildMessageDelivered());
//
//                if (isDelivered) {
//                    child_message_delivered++;
//                } else {
//                    child_message_remain++;
//                }


                if (theMother.getAgeOfChild() > 0 && theMother.getAgeOfChild() < 31) { //==============  0 - 14 days
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_0_to_14_days());
                    if (isDelivered) {
                        child_message_delivered_0_to_14_days_delivered++;
                    } else {
                        child_message_delivered_0_to_14_days_remain++;
                    }

                } else if (theMother.getAgeOfChild() > 30 && theMother.getAgeOfChild() < 180) {//==============  30 - 90 days  === 1,2,3 month
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_1_2_3_month());

                    if (isDelivered) {
                        child_message_delivered_1_2_3_month_delivered++;
                    } else {
                        child_message_delivered_1_2_3_month_remain++;
                    }

                } else if (theMother.getAgeOfChild() >179 && theMother.getAgeOfChild() < 270) {//==============  180 - 240 days === 6-8 month
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_6_to_8_month());

                    if (isDelivered) {
                        child_message_delivered_6_to_8_month_delivered++;
                    } else {
                        child_message_delivered_6_to_8_month_remain++;
                    }


                } else if (theMother.getAgeOfChild() > 269 && theMother.getAgeOfChild() < 366) {//==============  270 - 365 days === 9-12 month
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_9_to_12_month());

                    if (isDelivered) {
                        child_message_delivered_9_to_12_month_delivered++;
                    } else {
                        child_message_delivered_9_to_12_month_remain++;
                    }
                }
            }
//            for (Mother theMother : groupMother.pnc3) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    pnc3_delivered++;
//                } else {
//                    pnc3_remain++;
//                }
//            }
//            for (Mother theMother : groupMother.pnc4) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    pnc4_delivered++;
//                } else {
//                    pnc4_remain++;
//                }
//            }


            // calculate total delivered mes, total needed to deliver and total message
            int totalMsgDelivered, totalMsgRemaining, totalMsg;
            totalMsgDelivered = anc1_delivered + anc2_delivered + anc3_delivered + anc4_delivered + pnc_delivered + pre_delivery_message_delivered
                    + child_message_delivered_0_to_14_days_delivered + child_message_delivered_1_2_3_month_delivered
                    + child_message_delivered_6_to_8_month_delivered + child_message_delivered_9_to_12_month_delivered;

            totalMsgRemaining = anc1_remain + anc2_remain + anc3_remain + anc4_remain + pnc_remain + pre_delivery_message_remain
                    + child_message_delivered_0_to_14_days_remain + child_message_delivered_1_2_3_month_remain
                    + child_message_delivered_6_to_8_month_remain + child_message_delivered_9_to_12_month_remain;
            totalMsg = totalMsgDelivered + totalMsgRemaining;


            allStat.put("totalMsgDelivered", totalMsgDelivered);
            allStat.put("totalMsgRemaining", totalMsgRemaining);
            allStat.put("totalMsg", totalMsg);


            return allStat;
        }


        @Override
        protected void onPostExecute(Map<String, Integer> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            if (progressDialog != null && progressDialog.isShowing()) {  // for handle view not attached to window manager exception
                progressDialog.dismiss();
            }

            String messageRemain    = String.valueOf(result.get("totalMsgRemaining")) + " জনকে মেসেজ দেয়া হয়নি "    ;
            String messageDelivered =  String.valueOf(result.get("totalMsgDelivered")) + " জনকে মেসেজ দেয়া হয়েছে "   ;
            String messageTotal     =    "মোট সংখ্যা " + String.valueOf(result.get("totalMsg")) + " জন" ;

            tvMessageRemain.setText(messageRemain);
            tvMessageDelivered.setText(messageDelivered);
            tvMessageTotal.setText(messageTotal);
        }

    }


    public class HeavyTaskExecutorFor_Export extends AsyncTask<String, Void, Boolean> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (progressDialog == null) {
                progressDialog =
                        new ProgressDialog(MainActivity.this);
            }
            progressDialog.setMessage("Exporting database....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... datas) {

            boolean result = false;

//            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
//
//            Map<String, Integer> allStat = new HashMap<>();
//
//            GroupMother groupMother = new GroupMother();
//            groupMother.doGrouping(db.getAllMothers()); //------------------------ put list of mothers


            if (isExternalStorageReadable() || isExternalStorageWritable()) {  //====================================
//                String tempDateString = new SimpleDateFormat("dd").format(new Date());
//                String tempMonthString = new SimpleDateFormat("MM").format(new Date());
//                int tempMonthInt = Integer.parseInt(tempMonthString);
//                String month = String.valueOf(tempMonthInt);
//                String year = new SimpleDateFormat("yyyy").format(new Date());
//                String month_Year = month + year;
                String dateMonthYear = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                File sdCard = Environment.getExternalStorageDirectory();
                File directory = new File(sdCard.getAbsolutePath() + "/Health Care");//=================== Name of the Folder
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                if (!directory.mkdirs()) {
                    Log.e(TAG, "======  Directory not created");
                }

//Now create the file in the above directory and write the contents into it
                File file = new File(directory, "HC " + dateMonthYear + ".csv");  // ========================= Name of the File
//                FileOutputStream fOut = null;
//                try {
//                    fOut = new FileOutputStream(file);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
                try {

                    if (file.createNewFile()) {
                        Log.d(TAG, " ======== File is created!");
                        Log.d(TAG, "csv =========   " + file.getAbsolutePath());
                    } else {
                        Log.d(TAG, "======= File already exists.");
                    }

                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));

                    DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
                    SQLiteDatabase db = helper.getWritableDatabase();

                  // Cursor curCSV = db.rawQuery("select * from table_child", null);  // query
//                    String selectAllMothersWithChild = "SELECT  * FROM " +  DatabaseHelper.TABLE_MOTHER
//                            + " INNER JOIN " + DatabaseHelper.TABLE_MESSAGE_DELIVERY + " ON " + DatabaseHelper.TABLE_MESSAGE_DELIVERY + "." + DatabaseHelper.MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID
//                            + " = " + DatabaseHelper.TABLE_MOTHER + "." + DatabaseHelper.MOTHER_COLUMN_ID + " INNER JOIN " + DatabaseHelper.TABLE_CHILD + " ON "
//                            + DatabaseHelper.TABLE_CHILD + "." + DatabaseHelper.CHILD_COLUMN_MOTHER_ID + " = " + DatabaseHelper.TABLE_MOTHER + "." + DatabaseHelper.MOTHER_COLUMN_ID+" WHERE "
//                            + DatabaseHelper.TABLE_MOTHER + "." + DatabaseHelper.MOTHER_COLUMN_PREGNANCY_STATE + "=?";
//
                    String selectAllMothersWithChild = "SELECT  * FROM " +  DatabaseHelper.TABLE_MOTHER
                            + " INNER JOIN " + DatabaseHelper.TABLE_MESSAGE_DELIVERY + " ON " + DatabaseHelper.TABLE_MESSAGE_DELIVERY + "." + DatabaseHelper.MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID
                            + " = " + DatabaseHelper.TABLE_MOTHER + "." + DatabaseHelper.MOTHER_COLUMN_ID + " INNER JOIN " + DatabaseHelper.TABLE_CHILD + " ON "
                            + DatabaseHelper.TABLE_CHILD + "." + DatabaseHelper.CHILD_COLUMN_MOTHER_ID + " = " + DatabaseHelper.TABLE_MOTHER + "." + DatabaseHelper.MOTHER_COLUMN_ID ;

                    //Cursor cursor = db.rawQuery(selectAllMothersWithChild, new String[]{"post delivery"});


                   // Cursor curCSV =  db.rawQuery(selectAllMothersWithChild, new String[]{"post delivery"});  // query
                    Cursor curCSV =  db.rawQuery(selectAllMothersWithChild,null);  // query
                    csvWrite.writeNext(curCSV.getColumnNames());

                    while (curCSV.moveToNext())

                    {

                        String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3),
                                curCSV.getString(4), curCSV.getString(5), curCSV.getString(6), curCSV.getString(7), curCSV.getString(8), curCSV.getString(9),
                        curCSV.getString(10),
                        curCSV.getString(11),
                        curCSV.getString(12),
                        curCSV.getString(13),
                        curCSV.getString(14),
                        curCSV.getString(15),
                        curCSV.getString(16),
                        curCSV.getString(17),
                        curCSV.getString(18),
                        curCSV.getString(19),
                        curCSV.getString(20),
                        curCSV.getString(21),
                        curCSV.getString(22),
                        curCSV.getString(23),
                        curCSV.getString(24),
                        curCSV.getString(25),
                        curCSV.getString(26),
                        curCSV.getString(27),
                        curCSV.getString(28),
                        curCSV.getString(29),
                        curCSV.getString(30),
                        curCSV.getString(31),
                        curCSV.getString(32),
                        curCSV.getString(33),
                        curCSV.getString(34),
                        curCSV.getString(35),
                        curCSV.getString(36)

                        };

                        csvWrite.writeNext(arrStr);

                    }




                    //===========================================================================================================================================

                    String selectAllMothers  = "SELECT  * FROM " +  DatabaseHelper.TABLE_MOTHER
                            + " INNER JOIN " + DatabaseHelper.TABLE_MESSAGE_DELIVERY + " ON " + DatabaseHelper.TABLE_MESSAGE_DELIVERY + "." + DatabaseHelper.MESSAGE_DELIVERY_COL_MOTHER_COLUMN_ID
                            + " = " + DatabaseHelper.TABLE_MOTHER + "." + DatabaseHelper.MOTHER_COLUMN_ID  ;

                    //Cursor cursor = db.rawQuery(selectAllMothersWithChild, new String[]{"post delivery"});


                    // Cursor curCSV =  db.rawQuery(selectAllMothersWithChild, new String[]{"post delivery"});  // query
                    Cursor curCSV2 =  db.rawQuery(selectAllMothers ,null);  // query
                     //csvWrite.writeNext(curCSV.getColumnNames());

                    while (curCSV2.moveToNext() && curCSV2 != null)

                    {

                        String arrStr[] = {curCSV2.getString(0), curCSV2.getString(1), curCSV2.getString(2), curCSV2.getString(3),
                                curCSV2.getString(4), curCSV2.getString(5), curCSV2.getString(6), curCSV2.getString(7), curCSV2.getString(8), curCSV2.getString(9),
                                curCSV2.getString(10),
                                curCSV2.getString(11),
                                curCSV2.getString(12),
                                curCSV2.getString(13),
                                curCSV2.getString(14),
                                curCSV2.getString(15),
                                curCSV2.getString(16),
                                curCSV2.getString(17),
                                curCSV2.getString(18),
                                curCSV2.getString(19),
                                curCSV2.getString(20),
                                curCSV2.getString(21),
                                curCSV2.getString(22),
                                curCSV2.getString(23),
                                curCSV2.getString(24),
                                curCSV2.getString(25),
                                curCSV2.getString(26),
                                curCSV2.getString(27),
                                curCSV2.getString(28)




                        };
                        csvWrite.writeNext(arrStr);

                    }









                    csvWrite.close();
                    curCSV.close();
                    curCSV2.close();
        /*String data="";
        data=readSavedData();
        data= data.replace(",", ";");
        writeData(data);*/

                    result = true;

                } catch (SQLException sqlEx) {
                    Log.e(TAG, "======= " + sqlEx.getMessage(), sqlEx);

                    result = false;

                } catch (IOException e) {
                    // e.printStackTrace();
                    Log.e(TAG, "====== " + e.getMessage(), e);

                    result = false;
                }
            }    //==========================================================================


            return result;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            if (progressDialog != null && progressDialog.isShowing()) {  // for handle view not attached to window manager exception
                progressDialog.dismiss();
            }


            if (result)

            {

                SharedPreferences sharedPref = getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);// =====================   store date in shared preferences
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(PREFERENCE_LAST_EXPORT, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                editor.commit();

                tvLastExportDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

                Toast.makeText(getApplicationContext(), "Export succeed", Toast.LENGTH_SHORT).show();

            } else

            {

                Toast.makeText(getApplicationContext(), "Export failed", Toast.LENGTH_SHORT).show();

            }

        }

    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
