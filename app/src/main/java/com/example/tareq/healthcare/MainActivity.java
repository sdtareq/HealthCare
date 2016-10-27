package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    protected static String user_primary_key;
    protected static String user_name;
    protected static final String TAG = "MainActivity";

    boolean doubleBackToExitPressedOnce = false;   // track the back pressed button


    TextView tvMessageRemain,
            tvMessageDelivered,
            tvMessageTotal;

    android.support.v7.widget.CardView card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Log - in First
        Intent intent = new Intent(this, All_Mother_List_Activity.class);              //=================== First Go to Login Page
        //Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);

        init();
    }

    private void init() {
        tvMessageRemain = (TextView) findViewById(R.id.tvMessageRemain);
        tvMessageDelivered = (TextView) findViewById(R.id.tvMessageDelivered);
        tvMessageTotal = (TextView) findViewById(R.id.tvMessageTotal);
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
            public void onClick(View v) {              //===================  Go to  LogOut Page
                Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.card_view_AllMotherList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, All_Mother_List_Activity.class);
                startActivity(intent);
            }
        });


        new HeavyTaskExecutor().execute();
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

    public class HeavyTaskExecutor extends AsyncTask<String, Void, Map<String, Integer>> {
        ProgressDialog progressDialog =
                new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Map<String, Integer> doInBackground(String... datas) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

            Map<String, Integer> allStat = new HashMap<>();

            GroupMother groupMother = new GroupMother();
            groupMother.doGrouping(db.getAllMothers()); //------------------------ put list of mothers

            Log.d(TAG, String.valueOf(groupMother.anc1.size()));
            Log.d(TAG, String.valueOf(groupMother.pnc1.size()));

            int anc1_remain = 0, anc1_delivered = 0, anc2_remain = 0, anc2_delivered = 0, anc3_remain = 0, anc3_delivered = 0, anc4_remain = 0, anc4_delivered = 0,
                    pnc1_remain = 0, pnc1_delivered = 0, pnc2_remain = 0, pnc2_delivered = 0, pnc3_remain = 0, pnc3_delivered = 0, pnc4_remain = 0, pnc4_delivered = 0;

            for (Mother theMother : groupMother.anc1) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    anc1_delivered++;
                } else {
                    anc1_remain++;
                }
            }
            for (Mother theMother : groupMother.anc2) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    anc2_delivered++;
                } else {
                    anc2_remain++;
                }
            }
            for (Mother theMother : groupMother.anc3) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    anc3_delivered++;
                } else {
                    anc3_remain++;
                }
            }
            for (Mother theMother : groupMother.anc4) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    anc4_delivered++;
                } else {
                    anc4_remain++;
                }
            }


            for (Mother theMother : groupMother.pnc1) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    pnc1_delivered++;
                } else {
                    pnc1_remain++;
                }
            }
            for (Mother theMother : groupMother.pnc2) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    pnc2_delivered++;
                } else {
                    pnc2_remain++;
                }
            }
            for (Mother theMother : groupMother.pnc3) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    pnc3_delivered++;
                } else {
                    pnc3_remain++;
                }
            }
            for (Mother theMother : groupMother.pnc4) {
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());

                if (isDelivered) {
                    pnc4_delivered++;
                } else {
                    pnc4_remain++;
                }
            }


            // calculate total delivered mes, total needed to deliver and total message
            int totalMsgDelivered, totalMsgRemaining, totalMsg;
            totalMsgDelivered = anc1_delivered + anc2_delivered + anc3_delivered + anc4_delivered + pnc1_delivered + pnc2_delivered + pnc3_delivered + pnc4_delivered;
            totalMsgRemaining = anc1_remain + anc2_remain + anc3_remain + anc4_remain + pnc1_remain + pnc2_remain + pnc3_remain + pnc4_remain;
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
            progressDialog.dismiss();


            tvMessageRemain.setText(String.valueOf(result.get("totalMsgRemaining")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDelivered.setText(String.valueOf(result.get("totalMsgDelivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotal.setText("মোট সংখ্যা " + String.valueOf(result.get("totalMsg")) + " জন");
        }

    }
}
