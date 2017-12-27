package com.example.tareq.healthcare;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityAdmin extends AppCompatActivity {
    private static final String TAG = "ActivityAdmin";
    DatabaseHelper db;
    Button btnExportJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_admin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        db = new DatabaseHelper(this);
        init();

        btnExportJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();

                String table1 = gson.toJson(db.getDbDef2());
                 exportDoc(table1);
            }
        });



    }

    private void init() {
        btnExportJson = (Button) findViewById(R.id.buttonExportJSON);
    }


    private void exportDoc(String jsonObject) {//================================== exportDoc start *******************************************

        if (isExternalStorageReadable() || isExternalStorageWritable()) {  //====================================

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
            File file = new File(directory, "Admin_rowsAll_json_" + dateMonthYear + ".txt");  // ========================= Name of the File
            // File file = new File(directory, "HC_" + "IMEI" + ".txt");  // ========================= Name of the File


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
