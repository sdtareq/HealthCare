package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageActivity extends AppCompatActivity {
    final static String TAG = "MessageActivity";

    TextView tvMessageRemainANC1, tvMessageDeliveredANC1, tvMessageTotalANC1,
            tvMessageRemainANC2, tvMessageDeliveredANC2, tvMessageTotalANC2,
            tvMessageRemainANC3, tvMessageDeliveredANC3, tvMessageTotalANC3,
            tvMessageRemainANC4, tvMessageDeliveredANC4, tvMessageTotalANC4,
            tvMessageRemainPNC1, tvMessageDeliveredPNC1, tvMessageTotalPNC1,
            tvMessageRemainPNC2, tvMessageDeliveredPNC2, tvMessageTotalPNC2,
            tvMessageRemainPNC3, tvMessageDeliveredPNC3, tvMessageTotalPNC3,
            tvMessageRemainPNC4, tvMessageDeliveredPNC4, tvMessageTotalPNC4;



    List<Mother> tempList;

    String anc1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        init();
    }

    private void init() {

        //  =======================    Temp   ===========
//          tempList = new ArrayList<>();
//        tempList.add(new Mother("aa","2016/10/7"));
//        tempList.add(new Mother("bb","2016/10/17"));
//        tempList.add(new Mother("cc","2016/10/21"));
//        tempList.add(new Mother("dd","2016/10/8"));
//        tempList.add(new Mother("ee","2016/10/18"));
//        tempList.add(new Mother("ff","2016/10/15"));
//        tempList.add(new Mother("gg","2016/10/13"));
//        tempList.add(new Mother("hh","2016/10/7"));
//        tempList.add(new Mother("ii","2016/10/7"));
//        tempList.add(new Mother("jj","2016/10/19"));
//        tempList.add(new Mother("kk","2016/10/6"));


        tempList = new ArrayList<>();
        tempList.add(new Mother("a", "2016/10/1", "true", "false", "false", "true"));
        tempList.add(new Mother("b", "2016/10/17", "true", "false", "false", "true"));
        tempList.add(new Mother("c", "2016/10/21", "true", "false", "false", "true"));
        tempList.add(new Mother("d", "2016/10/8", "true", "false", "false", "true"));
        tempList.add(new Mother("e", "2016/10/18", "true", "false", "false", "true"));
        tempList.add(new Mother("f", "2016/10/15", "true", "false", "false", "true"));
        tempList.add(new Mother("g", "2016/10/13", "true", "false", "false", "false"));
        tempList.add(new Mother("h", "2016/10/1", "true", "false", "false", "false"));
        tempList.add(new Mother("i", "2016/10/7", "true", "false", "false", "false"));
        tempList.add(new Mother("j", "2016/10/19", "true", "false", "false", "false"));
        tempList.add(new Mother("k", "2016/10/6", "true", "false", "false", "false"));


        //tempList = new ArrayList<>();
        tempList.add(new Mother("l", "2016/10/1", "false", "true", "2016/10/1", "false"));
        tempList.add(new Mother("m", "2016/10/17", "false", "true", "2016/10/17", "false"));
        tempList.add(new Mother("n", "2016/10/21", "false", "true", "2016/10/21", "false"));
        tempList.add(new Mother("o", "2016/10/8", "false", "true", "2016/10/8", "false"));
        tempList.add(new Mother("p", "2016/10/18", "false", "true", "2016/10/18", "false"));
        tempList.add(new Mother("q", "2016/10/15", "false", "true", "2016/10/15", "false"));
        tempList.add(new Mother("r", "2016/10/13", "false", "true", "2016/10/13", "false"));
        tempList.add(new Mother("s", "2016/10/7", "false", "true", "2016/10/1", "true"));
        tempList.add(new Mother("t", "2016/10/1", "false", "true", "2016/10/7", "true"));
        tempList.add(new Mother("u", "2016/10/19", "false", "true", "2016/10/19", "true"));
        tempList.add(new Mother("v", "2016/10/6", "false", "true", "2016/10/6", "true"));
        // for inserting temp mothers
//              DatabaseHelper db = new DatabaseHelper(this);
//        for (Mother theMother: tempList) {
//            db.registerMother(theMother);
//        }


        //tv.setText(  GroupMother.anc1.toString() +"   "+ String.valueOf(GroupMother.anc2.size())+"   "+ String.valueOf(GroupMother.anc3.size()));

        // Toast.makeText(this,GroupMother.allString(),Toast.LENGTH_LONG).show();

//    =========================   Temp   end  -===============


        ///================================      =======================
        tvMessageRemainANC1 = (TextView) findViewById(R.id.tvMessageRemainANC1);
        tvMessageDeliveredANC1 = (TextView) findViewById(R.id.tvMessageDeliveredANC1);
        tvMessageTotalANC1 = (TextView) findViewById(R.id.tvMessageTotalANC1);

        tvMessageRemainANC2 = (TextView) findViewById(R.id.tvMessageRemainANC2);
        tvMessageDeliveredANC2 = (TextView) findViewById(R.id.tvMessageDeliveredANC2);
        tvMessageTotalANC2 = (TextView) findViewById(R.id.tvMessageTotalANC2);

        tvMessageRemainANC3 = (TextView) findViewById(R.id.tvMessageRemainANC3);
        tvMessageDeliveredANC3 = (TextView) findViewById(R.id.tvMessageDeliveredANC3);
        tvMessageTotalANC3 = (TextView) findViewById(R.id.tvMessageTotalANC3);

        tvMessageRemainANC4 = (TextView) findViewById(R.id.tvMessageRemainANC4);
        tvMessageDeliveredANC4 = (TextView) findViewById(R.id.tvMessageDeliveredANC4);
        tvMessageTotalANC4 = (TextView) findViewById(R.id.tvMessageTotalANC4);

        tvMessageRemainPNC1 = (TextView) findViewById(R.id.tvMessageRemainPNC1);
        tvMessageDeliveredPNC1 = (TextView) findViewById(R.id.tvMessageDeliveredPNC1);
        tvMessageTotalPNC1 = (TextView) findViewById(R.id.tvMessageTotalPNC1);

        tvMessageRemainPNC2 = (TextView) findViewById(R.id.tvMessageRemainPNC2);
        tvMessageDeliveredPNC2 = (TextView) findViewById(R.id.tvMessageDeliveredPNC2);
        tvMessageTotalPNC2 = (TextView) findViewById(R.id.tvMessageTotalPNC2);

        tvMessageRemainPNC3 = (TextView) findViewById(R.id.tvMessageRemainPNC3);
        tvMessageDeliveredPNC3 = (TextView) findViewById(R.id.tvMessageDeliveredPNC3);
        tvMessageTotalPNC3 = (TextView) findViewById(R.id.tvMessageTotalPNC3);

        tvMessageRemainPNC4 = (TextView) findViewById(R.id.tvMessageRemainPNC4);
        tvMessageDeliveredPNC4 = (TextView) findViewById(R.id.tvMessageDeliveredPNC4);
        tvMessageTotalPNC4 = (TextView) findViewById(R.id.tvMessageTotalPNC4);



        new HeavyTaskExecutor().execute();
    }


    public class HeavyTaskExecutor extends AsyncTask<String, Void, Map<String, Integer>> {
        ProgressDialog progressDialog =
                new ProgressDialog(MessageActivity.this);

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


            allStat.put("totalAnc1", groupMother.anc1.size());
            allStat.put("totalAnc2", groupMother.anc2.size());
            allStat.put("totalAnc3", groupMother.anc3.size());
            allStat.put("totalAnc4", groupMother.anc4.size());

            allStat.put("totalPnc1", groupMother.pnc1.size());
            allStat.put("totalPnc2", groupMother.pnc2.size());
            allStat.put("totalPnc3", groupMother.pnc3.size());
            allStat.put("totalPnc4", groupMother.pnc4.size());

            allStat.put("anc1_remain", anc1_remain);
            allStat.put("anc1_delivered", anc1_delivered);
            allStat.put("anc2_remain", anc2_remain);
            allStat.put("anc2_delivered", anc2_delivered);
            allStat.put("anc3_remain", anc3_remain);
            allStat.put("anc3_delivered", anc3_delivered);
            allStat.put("anc4_remain", anc4_remain);
            allStat.put("anc4_delivered", anc4_delivered);

            allStat.put("pnc1_remain",    pnc1_remain);
            allStat.put("pnc1_delivered", pnc1_delivered);
            allStat.put("pnc2_remain",    pnc2_remain);
            allStat.put("pnc2_delivered", pnc2_delivered);
            allStat.put("pnc3_remain",    pnc3_remain);
            allStat.put("pnc3_delivered", pnc3_delivered);
            allStat.put("pnc4_remain",    pnc4_remain);
            allStat.put("pnc4_delivered", pnc4_delivered);


            return allStat;
        }


        @Override
        protected void onPostExecute(Map<String, Integer> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();

            tvMessageRemainANC1.setText(String.valueOf(result.get("anc1_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredANC1.setText(String.valueOf(result.get("anc1_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotalANC1.setText("মোট সংখ্যা " + String.valueOf(result.get("totalAnc1")) + " জন");

            tvMessageRemainANC2.setText(String.valueOf(result.get("anc2_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredANC2.setText(String.valueOf(result.get("anc2_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotalANC2.setText("মোট সংখ্যা " + String.valueOf(result.get("totalAnc2")) + " জন");

            tvMessageRemainANC3.setText(String.valueOf(result.get("anc3_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredANC3.setText(String.valueOf(result.get("anc3_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotalANC3.setText("মোট সংখ্যা " + String.valueOf(result.get("totalAnc3")) + " জন");

            tvMessageRemainANC4.setText(String.valueOf(result.get("anc4_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredANC4.setText(String.valueOf(result.get("anc4_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotalANC4.setText("মোট সংখ্যা " + String.valueOf(result.get("totalAnc4")) + " জন");


               tvMessageRemainPNC1.setText(String.valueOf(result.get("pnc1_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredPNC1.setText(String.valueOf(result.get("pnc1_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
                tvMessageTotalPNC1.setText("মোট সংখ্যা " + String.valueOf(result.get("totalPnc1")) + " জন");
              tvMessageRemainPNC2.setText(String.valueOf(result.get("pnc2_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredPNC2.setText(String.valueOf(result.get("pnc2_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
                tvMessageTotalPNC2.setText("মোট সংখ্যা " + String.valueOf(result.get("totalPnc2")) + " জন");
                tvMessageRemainPNC3.setText(String.valueOf(result.get("pnc3_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredPNC3.setText(String.valueOf(result.get("pnc3_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
                tvMessageTotalPNC3.setText("মোট সংখ্যা " + String.valueOf(result.get("totalPnc3")) + " জন");
             tvMessageRemainPNC4.setText(String.valueOf(result.get("pnc4_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredPNC4.setText(String.valueOf(result.get("pnc4_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
                tvMessageTotalPNC4.setText("মোট সংখ্যা " + String.valueOf(result.get("totalPnc4")) + " জন");
        }

    }
}
