package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ANC_PNC_List_Activity extends AppCompatActivity {
    final static String TAG = "ANC_PNC_List_Activity";
    final static String LIST_STATE_KEY = "recycler_state";
    final static String DESIRE_CALLING_TIME_MORNING = "সকাল";
    final static String DESIRE_CALLING_TIME_NOON = "দুপুর ";
    final static String DESIRE_CALLING_TIME_EVENING = "সন্ধ্যা ";
    static String ANC_PNC_STATE;
    TextView tvTitle;
    String healthServiceName = "";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Parcelable mListState; ////  recycler view state
    ProgressDialog progressDialog;  // init dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anc_pnc_list);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (savedInstanceState != null) {
            healthServiceName = savedInstanceState.getString(TAG);
            ANC_PNC_STATE= healthServiceName;
        }

        init();

        if (healthServiceName != null) {
            ANC_PNC_STATE = healthServiceName;
            new HeavyTaskExecutor().execute();
        }

    }

    private void init() {
        progressDialog =
                new ProgressDialog(ANC_PNC_List_Activity.this);
        healthServiceName = getIntent().getStringExtra(MessageActivity.HEALTH_SEARVICE_NAME); //========================== init service name;
        tvTitle = (TextView) findViewById(R.id.tvListTitle);

        if (healthServiceName.equals(GroupMother.ANC_1)){
            tvTitle.setText("এএনসি ১");
        }else if (healthServiceName.equals(GroupMother.ANC_2)){
            tvTitle.setText("এএনসি ২");
        }else if (healthServiceName.equals(GroupMother.ANC_3)){
            tvTitle.setText("এএনসি ৩");
        }else if (healthServiceName.equals(GroupMother.ANC_4)){
            tvTitle.setText("এএনসি ৪");
        }else if (healthServiceName.equals( MessageActivity.PRE_DELIVERY)) {

            tvTitle.setText("ডেলিভারি পূর্ববর্তী মেসেজ দিন");
        } else if (healthServiceName.equals( GroupMother.PNC)) {

            tvTitle.setText("পিএনসি");
        } else {
            tvTitle.setText("শিশুদের জন্য মেসেজ দিন");
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_mother);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TAG,healthServiceName);

        mListState = mLayoutManager.onSaveInstanceState();// Save list state
        outState.putParcelable(LIST_STATE_KEY,mListState);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){     // Retrieve list state and list/item positions
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(getApplicationContext(),"onResume"   ,Toast.LENGTH_SHORT).show();
        if (healthServiceName != null) {
            new HeavyTaskExecutor().execute();
        }

        if (mListState != null){
            mLayoutManager.onRestoreInstanceState(mListState);
        }

    }

    //    @Override
//    protected void onRestart() {
//        super.onRestart();
//        if (healthServiceName != null) {
//            new HeavyTaskExecutor().execute();
//        }
//
//        Toast.makeText(getApplicationContext(),"health service name: "+ healthServiceName,Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onPause() {
        super.onPause();

        if (progressDialog != null && progressDialog.isShowing())  // for handle view not attached to window manager exception
            progressDialog.dismiss();
        progressDialog = null;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
         // Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }

    public class HeavyTaskExecutor extends AsyncTask<String, Void, List<Mother>> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (progressDialog == null) {
                progressDialog =
                        new ProgressDialog(ANC_PNC_List_Activity.this);
            }
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected List<Mother> doInBackground(String... datas) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

            // Map<String, Integer> allStat = new HashMap<>();

            GroupMother groupMother = new GroupMother();



            List<Mother> motherList = new ArrayList<>();
            if (healthServiceName.equals(MessageActivity.PRE_DELIVERY) ) {
                groupMother.doGrouping(db.getAllMothers()); //------------------------ Grouping list of mothers
                List<Mother> anc3, anc4;
                anc3 = groupMother.allGroupMap.get(GroupMother.ANC_3);
                anc4 = groupMother.allGroupMap.get(GroupMother.ANC_4);
                if (anc3 !=null){
                    motherList.addAll(anc3);
                }
               if (anc4 != null){
                   motherList.addAll(anc4);
               }

            }else if (healthServiceName.equals(GroupMother.CHILD_CARE_MESSAGE_STATUS) ||  healthServiceName.equals(GroupMother.PNC)){
                groupMother.filterMothersHavingChild(db.getAllMotherswithChild());
                if (groupMother.allGroupMap.get(healthServiceName) != null) {

                    motherList = groupMother.allGroupMap.get(healthServiceName);
                }

            } else {
                groupMother.doGrouping(db.getAllMothers()); //------------------------ Grouping list of mothers
                if (groupMother.allGroupMap.get(healthServiceName) != null) {

                    motherList = groupMother.allGroupMap.get(healthServiceName);
                }

            }

            Log.d(TAG, "=============== mother list size  " + motherList.size());
            if (motherList.size() > 0) {
                Collections.sort(motherList, Mother.motherNameComparator);
            }


            return motherList;
        }


        @Override
        protected void onPostExecute(List<Mother> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            if (progressDialog != null && progressDialog.isShowing()) {  // for handle view not attached to window manager exception
                progressDialog.dismiss();
            }
            int size = result.size();
            Log.d(TAG, " size of list is : " + size);
            // specify  adapter
            try {
                if (healthServiceName.equals(GroupMother.CHILD_CARE_MESSAGE_STATUS)){
                    mAdapter = new Child_Message_List_Adapter(result,ANC_PNC_List_Activity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }else if (healthServiceName.equals(MessageActivity.PRE_DELIVERY)){
                    mAdapter = new Pre_Delivery_Message_Adapter(result,ANC_PNC_List_Activity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }else{
                mAdapter = new ANC_PNC_List_Adapter(result, ANC_PNC_List_Activity.this,healthServiceName);
                mRecyclerView.setAdapter(mAdapter);}
            } catch (Exception e) {
                Log.d(TAG, " =============   error");
            }

        }

    }
}
