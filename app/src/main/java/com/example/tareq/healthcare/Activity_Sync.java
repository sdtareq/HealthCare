package com.example.tareq.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_Sync extends AppCompatActivity {
    final static String TAG = "Activity_Sync";
    Button btnSync, btnAdmin, btnShow, btn_parse_csv, btn_send_file;
    TextView tvSync;
    RequestQueue requestQueue;

    String insertUrl = "http://10.0.3.2/HealthCareSync/controller.php";


    DatabaseHelper dbHelper;
    String syncDate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        dbHelper = new DatabaseHelper(this);
        init();

        String user_name = ((MyApplication) this.getApplication()).getLoginUserName();
        Log.d(TAG, "===== user name" + user_name);
        if ((user_name).equals(MainActivity.ADMIN_STRING)) {
            btnAdmin.setVisibility(View.VISIBLE);
        } else {
            btnAdmin.setVisibility(View.GONE);
        }

        requestQueue = Volley.newRequestQueue(this);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Sync.this, ActivityAdmin.class);
                startActivity(intent);
            }
        });


        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sync


                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                final String imei = telephonyManager.getDeviceId();  /// GET IMEI NO
                Gson gson = new Gson();
                final String allTableData = gson.toJson(dbHelper.getAllTables2());

                Log.d(TAG, allTableData);

                StringRequest strRequest = new StringRequest(Request.Method.POST, insertUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                boolean isValid = JSONUtils.isJSONValid(response);
                                if (isValid) {
                                    try {
                                        process_logData(response);
                                        tvSync.setText("সফলভাবে সিঙ্ক করা হয়েছে");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    tvSync.setText("সিঙ্ক ব্যর্থ হয়েছে! আবার চেষ্টা করুন");
                                }


                                // tvSync.setText(response+"\n"+String.valueOf(isValid));
                                //tvSync.setText(response+"\n"+String.valueOf(isValid));

                                syncDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                // tvSync.setText("No Internet Connection"error.toString());

                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    tvSync.setText("ইন্টারনেট সংযোগ নেই অথবা সার্ভারের সাথে সংযোগ পাচ্ছে না। একটু পরে আবার চেষ্টা করুন।");

                                } else if (error instanceof AuthFailureError) {
                                    tvSync.setText("Auth Failure Error");

                                } else if (error instanceof ServerError) {
                                    tvSync.setText("Server Error");

                                } else if (error instanceof NetworkError) {
                                    tvSync.setText("Network Error");

                                } else if (error instanceof ParseError) {
                                    tvSync.setText("Parse Error");

                                }
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("rowsAll", allTableData);
                        params.put("imei", imei);
                        return params;
                    }
                };

                requestQueue.add(strRequest);

            }////////
        });


    }


    private void process_logData(String log_dataString) throws JSONException {


        JSONObject jsonObject = new JSONObject(log_dataString);
        JSONObject jsonObjectRowsAll = jsonObject.getJSONObject("rowsAll");

        String motherTable = jsonObjectRowsAll.getString(DatabaseHelper.TABLE_MOTHER); ////================================= TABLE_MOTHER
        if (motherTable != null) {
            List<Mother> motherList = ObjectWrapper.wrapMothers(separateColumn(motherTable));
            dbHelper.clearMotherTable();
            for (Mother mother : motherList
                    ) {

                dbHelper.registerMother(mother);
            }

        }

        String ancPncMessageTable = jsonObjectRowsAll.getString(DatabaseHelper.TABLE_ANC_PNC_MSG);////=================================TABLE_ANC_PNC_MSG
        if (ancPncMessageTable != null) {
            List<Mother> motherList = ObjectWrapper.wrapAncPncMessages(separateColumn(ancPncMessageTable));
            dbHelper.clearAncPncMsgTable();
            for (Mother mother : motherList
                    ) {

                dbHelper.add_mother_in_anc_pnc_msg_table(mother);
            }

        }

        String deliveryAndChildMessageTable = jsonObjectRowsAll.getString(DatabaseHelper.TABLE_DELIVERY_AND_CHILD_MSG);////=================================TABLE_DELIVERY_AND_CHILD_MSG
        if (!deliveryAndChildMessageTable.isEmpty()) {
            List<Mother> motherList = ObjectWrapper.wrapDeliveryAndChildMessages(separateColumn(deliveryAndChildMessageTable));
            dbHelper.clearDeliveryAndChildMsgTable();
            for (Mother mother : motherList
                    ) {

                dbHelper.add_mother_in_delivery_and_child_msg_table(mother);
            }

        }


        String childTable = jsonObjectRowsAll.getString(DatabaseHelper.TABLE_CHILD);////=================================TABLE_CHILD
        if (!childTable.isEmpty()) {
            List<Child> childList = ObjectWrapper.wrapChildren(separateColumn(childTable));

            Log.d(TAG, "========= child list=====" + childList);
            dbHelper.clearChildTable();
            for (Child child : childList
                    ) {

                dbHelper.registerChild(child);
            }

        }

        String childFollowUpTable = jsonObjectRowsAll.getString(DatabaseHelper.TABLE_CHILD_FOLLOW_UP);////=================================TABLE_CHILD_FOLLOW_UP
        if (!childFollowUpTable.isEmpty()) {
            List<Child> childList = ObjectWrapper.wrapChildFollowUp(separateColumn(childFollowUpTable));
            dbHelper.clearChildFollowUpTable();
            for (Child child : childList
                    ) {

                dbHelper.addChildFollowUp(child);
            }

        }
        //String childTable =   jsonObjectRowsAll.getString(DatabaseHelper.TABLE_CHILD);

        Log.d(TAG, "============= mother  " + motherTable);
        Log.d(TAG, "============= message  " + ancPncMessageTable);
        Log.d(TAG, "============= message  " + deliveryAndChildMessageTable);
        Log.d(TAG, "============= child  " + childTable);


        // log
        logData(separateColumn(childTable));

    }

    private void logData(List<String[]> rowColumn) {
        for (String[] a_row : rowColumn
                ) {
            Log.d(TAG, "======== row size  " + String.valueOf(a_row.length));

            for (String a_column : a_row
                    ) {
                Log.d(TAG, "======== column " + a_column);
            }
        }
    }


    private List<String[]> separateColumn(String rows_String) {
        String[] rowsArray = rows_String.split("\n");
        List<String> rowsList = new ArrayList<>(Arrays.asList(rowsArray));

        List<String[]> rowsColumnList = new ArrayList<>();

        for (String a_row : rowsList) {
            String[] columnArray = a_row.split("\t");

            rowsColumnList.add(columnArray);

        }

        return rowsColumnList;

    }


    private void init() {
        btnSync = (Button) findViewById(R.id.btnSync);
        btnShow = (Button) findViewById(R.id.btnShow);
        tvSync = (TextView) findViewById(R.id.tvSync);
        btn_parse_csv = (Button) findViewById(R.id.btn_parse_csv);
        btn_send_file = (Button) findViewById(R.id.btn_send_file);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);

        btnShow.setVisibility(View.GONE);
        btn_send_file.setVisibility(View.GONE);
        btn_parse_csv.setVisibility(View.GONE);


    }

    @Override
    public void onBackPressed() {

        exitFromActivity();

    }

    public void exitFromActivity() {
        Intent intent = new Intent();//////////////
        intent.putExtra(TAG, syncDate);///////
        setResult(RESULT_OK, intent);//////

        finish();
    }

}




