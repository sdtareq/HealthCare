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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ANC_PNC_List_Activity extends AppCompatActivity {
    final static String TAG = "ANC_PNC_List_Activity";

    String healthServiceName = "";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anc_pnc_list);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        init();

        if (healthServiceName != null){
            new HeavyTaskExecutor().execute();
        }

    }

    private void init() {
     healthServiceName = getIntent().getStringExtra(MessageActivity.HEALTH_SEARVICE_NAME); // init service name;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_mother);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }


    public class HeavyTaskExecutor extends AsyncTask<String, Void, List<Mother>> {
        ProgressDialog progressDialog =
                new ProgressDialog(ANC_PNC_List_Activity.this);

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


            List<Mother> motherList ;

             motherList = groupMother.allGroupMap.get(healthServiceName);

            Collections.sort(motherList, Mother.motherNameComparator);

            return motherList;
        }


        @Override
        protected void onPostExecute(List<Mother> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();

            int size = result.size();
            Log.d(TAG," size of list is : "+size);
            // specify  adapter
            try {
                mAdapter = new ANC_PNC_List_Adapter(result,ANC_PNC_List_Activity.this);
                mRecyclerView.setAdapter(mAdapter);
            }catch (Exception e){
                Log.d(TAG," =============   error");
            }

        }

    }
}
