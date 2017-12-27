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
    DatabaseHelper db;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ProgressDialog progressDialog;  // init dialog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_mother);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      db = new DatabaseHelper(getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_mother);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressDialog =
                new ProgressDialog(All_Mother_List_Activity.this);

        new HeavyTaskExecutor().execute();

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

    public class HeavyTaskExecutor extends AsyncTask<String, Void, List<Mother>> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (progressDialog == null){
                progressDialog =
                        new ProgressDialog(All_Mother_List_Activity.this);
            }
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected List<Mother> doInBackground(String... datas) {


           // Map<String, Integer> allStat = new HashMap<>();

            GroupMother groupMother = new GroupMother(All_Mother_List_Activity.this);
            groupMother.doGrouping(db.getAllMothersWithOrWithoutChild()); //------------------------ Grouping list of mothers

            //Log.d(TAG, String.valueOf(groupMother.anc1.size()));
            //Log.d(TAG, String.valueOf(groupMother.pnc1.size()));


            List<Mother> motherList = new ArrayList<>();

            for (Map.Entry<String, List<Mother>> entry : groupMother.allGroupMap.entrySet()) {
                String runningHealthService = entry.getKey();
            Log.d(TAG,"anc/pnc group loading");
                List<Mother> values = entry.getValue();
                if (runningHealthService.equals(GroupMother.PNC)){
                    continue;
                }
                for (Mother mother : values) {  // set running Health Service for each mother
                    mother.setRunningHealthService(runningHealthService);
                }

                motherList.addAll(values);   // marge all list into one list

            }


            Collections.sort(motherList, Mother.motherNameComparator);

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
            if (size>0){
                try {
                    mAdapter = new All_Mother_List_Adapter(result,All_Mother_List_Activity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }catch (Exception e){
                    Log.d(TAG," =============   All Mother List is empty");
                }
            }


        }

    }
}
