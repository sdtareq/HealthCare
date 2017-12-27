package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    final static String TAG = "MessageActivity";
    static final String HEALTH_SEARVICE_NAME = "healthService" ;
    static final String PRE_DELIVERY = "preDelivery" ;
        static final String POST_DELIVERY = "postDelivery" ;
        static final String POST_DELIVERY_DB = "post delivery" ;  // DATABASE COLUMN ENTRY USED TO GROUPING MOTHER'S HAVING CHILD

    static final String ANC1 = "ANC 1" ;
    static final String ANC2 = "ANC 2" ;
    static final String ANC3 = "ANC 3" ;
    static final String ANC4 = "ANC 4" ;
    static final String PNC = "PNC" ;

    ProgressDialog progressDialog;  // init dialog

    TextView tvMessageRemainANC1, tvMessageDeliveredANC1, tvMessageTotalANC1,
            tvMessageRemainANC2, tvMessageDeliveredANC2, tvMessageTotalANC2,
            tvMessageRemainANC3, tvMessageDeliveredANC3, tvMessageTotalANC3,
            tvMessageRemainANC4, tvMessageDeliveredANC4, tvMessageTotalANC4,
            tvMessageRemainPreDelivery, tvMessageDeliveredPreDelivery, tvMessageTotalPreDelivery,
            tvMessageRemainPNC, tvMessageDeliveredPNC, tvMessageTotalPNC,
           tvMessageRemainChild, tvMessageDeliveredChild, tvMessageTotalChild;
//            tvMessageRemainPNC4, tvMessageDeliveredPNC4, tvMessageTotalPNC4;


    CardView cvAnc1, cvAnc2, cvAnc3, cvAnc4, cvPreDelivery, cvPnc  , cvChildMessageStatus;// cvPnc4;
    List<Mother> tempList;

    String anc1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        init();

        new HeavyTaskExecutor().execute();
    }

    private void init() {




        tempList = new ArrayList<>();


        // for inserting temp mothers
              DatabaseHelper db = new DatabaseHelper(this);

        if(db.isMotherTableEmpty()){
        for (Mother theMother: tempList) {
            db.registerMother(theMother);

        }
        }



//    =========================   Temp   end  -===============

        progressDialog =
                new ProgressDialog(MessageActivity.this);
        ///================================  init views start    =======================
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

        tvMessageRemainPreDelivery = (TextView) findViewById(R.id.tvMessageRemainPreDelivery);
        tvMessageDeliveredPreDelivery = (TextView) findViewById(R.id.tvMessageDeliveredPreDelivery);
        tvMessageTotalPreDelivery = (TextView) findViewById(R.id.tvMessageTotalPreDelivery);

        tvMessageRemainPNC = (TextView) findViewById(R.id.tvMessageRemainPNC);
        tvMessageDeliveredPNC = (TextView) findViewById(R.id.tvMessageDeliveredPNC);
        tvMessageTotalPNC = (TextView) findViewById(R.id.tvMessageTotalPNC);

        tvMessageRemainChild = (TextView) findViewById(      R.id.tvMessageRemainChild);
        tvMessageDeliveredChild = (TextView) findViewById(R.id.tvMessageDeliveredChild);
            tvMessageTotalChild = (TextView) findViewById(    R.id.tvMessageTotalChild);



        cvAnc1 = (CardView) findViewById(R.id.card_view_message_ANC1);
        cvAnc2 = (CardView) findViewById(R.id.card_view_message_ANC2);
        cvAnc3 = (CardView) findViewById(R.id.card_view_message_ANC3);
        cvAnc4 = (CardView) findViewById(R.id.card_view_message_ANC4);
        cvPreDelivery = (CardView) findViewById(R.id.card_view_messagePreDelivery  );
        cvPnc  = (CardView) findViewById(R.id.card_view_messagePNC );
        cvChildMessageStatus = (CardView) findViewById(R.id.card_view_messageChild  );
//        cvPnc4 = (CardView) findViewById(R.id.card_view_messagePNC4  );

        cvAnc1.setOnClickListener(this);
        cvAnc2.setOnClickListener(this);
        cvAnc3.setOnClickListener(this);
        cvAnc4.setOnClickListener(this);
        cvPreDelivery.setOnClickListener(this);
        cvPnc.setOnClickListener(this);
        cvChildMessageStatus.setOnClickListener(this);
//        cvPnc4.setOnClickListener(this);



        ///================================  init views end    =======================


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
        new HeavyTaskExecutor().execute();
    }

    // ==============================    handle Click Events for all ANC and  PNC  =======================
    @Override
    public void onClick(View v) {

        int id =  v.getId();

        switch (id){

            case R.id.card_view_message_ANC1: {
                Intent intent = new Intent(MessageActivity.this, ANC_PNC_List_Activity.class);
                intent.putExtra(HEALTH_SEARVICE_NAME,GroupMother.ANC_1);
                startActivity(intent);

                break;}
            case R.id.card_view_message_ANC2: {
                Intent intent = new Intent(MessageActivity.this, ANC_PNC_List_Activity.class);
                intent.putExtra(HEALTH_SEARVICE_NAME,GroupMother.ANC_2);
                startActivity(intent);

                break;}
            case R.id.card_view_message_ANC3: {
                Intent intent = new Intent(MessageActivity.this, ANC_PNC_List_Activity.class);
                intent.putExtra(HEALTH_SEARVICE_NAME,GroupMother.ANC_3 );
                startActivity(intent);

                break;}
            case R.id.card_view_message_ANC4: {
                Intent intent = new Intent(MessageActivity.this, ANC_PNC_List_Activity.class);
                intent.putExtra(HEALTH_SEARVICE_NAME,GroupMother.ANC_4);
                startActivity(intent);

                break;}
            case R.id.card_view_messagePreDelivery : {
                Intent intent = new Intent(MessageActivity.this, ANC_PNC_List_Activity.class);
                intent.putExtra(HEALTH_SEARVICE_NAME,GroupMother.PRE_DELIVERY);
                startActivity(intent);

                break;}
            case R.id.card_view_messagePNC : {
                Intent intent = new Intent(MessageActivity.this, ANC_PNC_List_Activity.class);
                intent.putExtra(HEALTH_SEARVICE_NAME,GroupMother.PNC);
                startActivity(intent);

                break;}
            case R.id.card_view_messageChild : {
                Intent intent = new Intent(MessageActivity.this, ANC_PNC_List_Activity.class);
                intent.putExtra(HEALTH_SEARVICE_NAME, GroupMother.CHILD_CARE_MESSAGE_STATUS);
                startActivity(intent);

                break;}


        }

    }


    public class HeavyTaskExecutor extends AsyncTask<String, Void, Map<String, Integer>> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (progressDialog == null){
                progressDialog =
                        new ProgressDialog(MessageActivity.this);
            }
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Map<String, Integer> doInBackground(String... datas) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

            Map<String, Integer> allStat = new HashMap<>();

            GroupMother groupMother = new GroupMother(MessageActivity.this);
            try {
                groupMother.doGrouping(db.getAllMothers()); //------------------------ put list of mothers inorder to group all mothers based on anc or pnc
            }catch (NullPointerException e){
                Log.d(TAG," Mother Table is Empty");
            }


            Log.d(TAG, String.valueOf(groupMother.anc1.size()));
            Log.d(TAG, String.valueOf(groupMother.pnc.size()));

            //  =================   calc message remain, delivered and total no of mother for specific item

            int anc1_remain = 0, anc1_delivered = 0, anc2_remain = 0, anc2_delivered = 0, anc3_remain = 0, anc3_delivered = 0, anc4_remain = 0, anc4_delivered = 0,
                    pnc_remain = 0, pnc_delivered = 0, child_message_remain = 0, child_message_delivered = 0, pre_delivery_message_remain = 0, pre_delivery_message_delivered = 0,
             child_message_delivered_0_to_14_days_remain   = 0 ,
             child_message_delivered_0_to_14_days_delivered= 0,
              child_message_delivered_1_2_3_month_remain   = 0 ,
              child_message_delivered_1_2_3_month_delivered= 0,
             child_message_delivered_6_to_8_month_remain   = 0 ,
             child_message_delivered_6_to_8_month_delivered= 0,
            child_message_delivered_9_to_12_month_remain   = 0 ,
            child_message_delivered_9_to_12_month_delivered= 0, pnc4_remain = 0, pnc4_delivered = 0;

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
                boolean isDelivered = Boolean.parseBoolean(theMother.getIsANC_3_Message_Delivered());                              //
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



                if (theMother.getAgeOfChild()>=0 && theMother.getAgeOfChild()<15){ //==============  0 - 14 days
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_0_to_14_days());
                    if (isDelivered) {
                        child_message_delivered_0_to_14_days_delivered++;
                    } else {
                        child_message_delivered_0_to_14_days_remain++;
                    }

                }else if(theMother.getAgeOfChild()>14 && theMother.getAgeOfChild()<180){//==============  15 - 90 days  === 1,2,3 month
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_1_2_3_month());

                    if (isDelivered) {
                        child_message_delivered_1_2_3_month_delivered++;
                    } else {
                        child_message_delivered_1_2_3_month_remain++;
                    }

                }else if(theMother.getAgeOfChild()>179 && theMother.getAgeOfChild()<270){//==============  180 - 240 days === 6-8 month
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_6_to_8_month());

                    if (isDelivered) {
                        child_message_delivered_6_to_8_month_delivered++;
                    } else {
                        child_message_delivered_6_to_8_month_remain++;
                    }


                }else if(theMother.getAgeOfChild()>269 && theMother.getAgeOfChild()<331){//==============  270 - 330 days === 9-11 month
                    boolean isDelivered = Boolean.parseBoolean(theMother.getIsChild_message_delivered_9_to_12_month());

                    if (isDelivered) {
                        child_message_delivered_9_to_12_month_delivered++;
                    } else {
                        child_message_delivered_9_to_12_month_remain++;
                    }


                }




            }


//=============  store in map
            allStat.put("totalAnc1", groupMother.anc1.size());   /// ============== total size
            allStat.put("totalAnc2", groupMother.anc2.size());
            allStat.put("totalAnc3", groupMother.anc3.size());
            allStat.put("totalAnc4", groupMother.anc4.size());

            allStat.put("totalPreDelivery", groupMother.anc3.size()+groupMother.anc4.size());
           allStat.put("totalPnc", groupMother.pnc.size());

            int totalChildMessage =   child_message_delivered_0_to_14_days_remain     + child_message_delivered_0_to_14_days_delivered +

                                       child_message_delivered_1_2_3_month_remain     + child_message_delivered_1_2_3_month_delivered  +

                                       child_message_delivered_6_to_8_month_remain    + child_message_delivered_6_to_8_month_delivered +

                                      child_message_delivered_9_to_12_month_remain    + child_message_delivered_9_to_12_month_delivered ;

            allStat.put("total_child_message", totalChildMessage);
//            allStat.put("totalPnc4", groupMother.pnc4.size());

            allStat.put("anc1_remain", anc1_remain);            //  =================== remains and delivered message
            allStat.put("anc1_delivered", anc1_delivered);
            allStat.put("anc2_remain", anc2_remain);
            allStat.put("anc2_delivered", anc2_delivered);
            allStat.put("anc3_remain", anc3_remain);
            allStat.put("anc3_delivered", anc3_delivered);
            allStat.put("anc4_remain", anc4_remain);
            allStat.put("anc4_delivered", anc4_delivered);

            allStat.put("PreDelivery_remain", pre_delivery_message_remain );
            allStat.put("PreDelivery_delivered", pre_delivery_message_delivered);
            allStat.put("pnc_remain", pnc_remain);
            allStat.put("pnc_delivered", pnc_delivered);


            int childMessageRemain =  child_message_delivered_0_to_14_days_remain     + child_message_delivered_1_2_3_month_remain     +
                                      child_message_delivered_6_to_8_month_remain    +  child_message_delivered_9_to_12_month_remain  ;

            int childMessageDelivered = child_message_delivered_0_to_14_days_delivered +  child_message_delivered_1_2_3_month_delivered  +
                                        child_message_delivered_6_to_8_month_delivered + child_message_delivered_9_to_12_month_delivered ;

            allStat.put("child_message_remain", childMessageRemain);
            allStat.put("child_message_delivered", childMessageDelivered);
//            allStat.put("pnc4_remain", pnc4_remain);
//            allStat.put("pnc4_delivered", pnc4_delivered);


            return allStat;
        }


        @Override
        protected void onPostExecute(Map<String, Integer> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (progressDialog != null && progressDialog.isShowing()) {  // for handle view not attached to window manager exception
                progressDialog.dismiss();
            }

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



            tvMessageRemainPreDelivery.setText(String.valueOf(result.get("PreDelivery_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredPreDelivery.setText(String.valueOf(result.get("PreDelivery_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotalPreDelivery.setText("মোট সংখ্যা " + String.valueOf(result.get("totalPreDelivery")) + " জন");

            tvMessageRemainPNC.setText(String.valueOf(result.get("pnc_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredPNC.setText(String.valueOf(result.get("pnc_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotalPNC.setText("মোট সংখ্যা " + String.valueOf(result.get("totalPnc")) + " জন");

            tvMessageRemainChild.setText(String.valueOf(result.get("child_message_remain")) + " জনকে মেসেজ দেয়া হয়নি ");
            tvMessageDeliveredChild.setText(String.valueOf(result.get("child_message_delivered")) + " জনকে মেসেজ দেয়া হয়েছে ");
            tvMessageTotalChild.setText("মোট সংখ্যা " + String.valueOf(result.get("total_child_message")) + " জন");

        }

    }
}
