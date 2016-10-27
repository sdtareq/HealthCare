package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class All_Mother_List_Activity extends AppCompatActivity {
    private final static String TAG = "AllMotherListActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_mother);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_mother);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        new HeavyTaskExecutor().execute();

    }



    public class HeavyTaskExecutor extends AsyncTask<String, Void, List<Mother>> {
        ProgressDialog progressDialog =
                new ProgressDialog(All_Mother_List_Activity.this);

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected List<Mother> doInBackground(String... datas) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

           // Map<String, Integer> allStat = new HashMap<>();

            GroupMother groupMother = new GroupMother();
            groupMother.doGrouping(db.getAllMothers()); //------------------------ Grouping list of mothers

            //Log.d(TAG, String.valueOf(groupMother.anc1.size()));
            //Log.d(TAG, String.valueOf(groupMother.pnc1.size()));

//            int anc1_remain = 0, anc1_delivered = 0, anc2_remain = 0, anc2_delivered = 0, anc3_remain = 0, anc3_delivered = 0, anc4_remain = 0, anc4_delivered = 0,
//                    pnc1_remain = 0, pnc1_delivered = 0, pnc2_remain = 0, pnc2_delivered = 0, pnc3_remain = 0, pnc3_delivered = 0, pnc4_remain = 0, pnc4_delivered = 0;
//
//            for (Mother theMother : groupMother.anc1) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    anc1_delivered++;
//                } else {
//                    anc1_remain++;
//                }
//            }
//            for (Mother theMother : groupMother.anc2) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    anc2_delivered++;
//                } else {
//                    anc2_remain++;
//                }
//            }
//            for (Mother theMother : groupMother.anc3) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    anc3_delivered++;
//                } else {
//                    anc3_remain++;
//                }
//            }
//            for (Mother theMother : groupMother.anc4) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    anc4_delivered++;
//                } else {
//                    anc4_remain++;
//                }
//            }
//
//
//            for (Mother theMother : groupMother.pnc1) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    pnc1_delivered++;
//                } else {
//                    pnc1_remain++;
//                }
//            }
//            for (Mother theMother : groupMother.pnc2) {
//                boolean isDelivered = Boolean.parseBoolean(theMother.getIsMessageDelivered());
//
//                if (isDelivered) {
//                    pnc2_delivered++;
//                } else {
//                    pnc2_remain++;
//                }
//            }
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
//
//
//            allStat.put("totalAnc1", groupMother.anc1.size());
//            allStat.put("totalAnc2", groupMother.anc2.size());
//            allStat.put("totalAnc3", groupMother.anc3.size());
//            allStat.put("totalAnc4", groupMother.anc4.size());
//
//            allStat.put("totalPnc1", groupMother.pnc1.size());
//            allStat.put("totalPnc2", groupMother.pnc2.size());
//            allStat.put("totalPnc3", groupMother.pnc3.size());
//            allStat.put("totalPnc4", groupMother.pnc4.size());
//
//            allStat.put("anc1_remain", anc1_remain);
//            allStat.put("anc1_delivered", anc1_delivered);
//            allStat.put("anc2_remain", anc2_remain);
//            allStat.put("anc2_delivered", anc2_delivered);
//            allStat.put("anc3_remain", anc3_remain);
//            allStat.put("anc3_delivered", anc3_delivered);
//            allStat.put("anc4_remain", anc4_remain);
//            allStat.put("anc4_delivered", anc4_delivered);
//
//            allStat.put("pnc1_remain",    pnc1_remain);
//            allStat.put("pnc1_delivered", pnc1_delivered);
//            allStat.put("pnc2_remain",    pnc2_remain);
//            allStat.put("pnc2_delivered", pnc2_delivered);
//            allStat.put("pnc3_remain",    pnc3_remain);
//            allStat.put("pnc3_delivered", pnc3_delivered);
//            allStat.put("pnc4_remain",    pnc4_remain);
//            allStat.put("pnc4_delivered", pnc4_delivered);

            List<Mother> motherList = new ArrayList<>();

            for (Map.Entry<String, List<Mother>> entry : groupMother.allGroupMap.entrySet()) {
                String runningHealthService = entry.getKey();
            Log.d(TAG,"anc/pnc group loading");
                List<Mother> values = entry.getValue();

                for (Mother mother : values) {  // set running Health Service for each mother
                    mother.setRunningHealthService(runningHealthService);
                }

                motherList.addAll(values);   // marge all list into one list

            }


            Collections.sort(motherList, Mother.motherComparator);

            return motherList;
        }


        @Override
        protected void onPostExecute(List<Mother> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();
            Log.d(TAG,"anc/pnc group received");
            int size = result.size();
            Log.d(TAG," ======================== size of list is : "+size);
            // specify  adapter
            try {
                mAdapter = new All_Mother_List_Adapter(result);
                mRecyclerView.setAdapter(mAdapter);
            }catch (Exception e){
                Log.d(TAG," =============   All Mother List is empty");
            }

        }

    }
}
