package com.example.tareq.healthcare;

import android.content.Context;
import android.content.res.AssetManager;
import android.nfc.Tag;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tareq.volleymultipart.AppHelper;
import com.example.tareq.volleymultipart.VolleyMultipartRequest;
import com.example.tareq.volleymultipart.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

public class Activity_Sync extends AppCompatActivity {
    final static String TAG  = "Activity_Sync";
    Button btnSync,btnShow,btn_parse_csv,btn_send_file;
    TextView tvSync;
    RequestQueue requestQueue;
    //String insertUrl = "http://10.0.3.2/sync/hello.php";
    // String insertUrl = "http://10.0.3.2/sync/show.php";
     String insertUrl = "http://10.0.3.2/sync/insertStudent.php";
     String uploadFileUrl = "http://10.0.3.2/sync/upload.php";

    //String showUrl = "http://localhost/sync/insertStudent.php/";
    String showUrl = "http://10.0.3.2/sync/log_data.txt";
  //  String showUrl = "http://10.0.3.2/HealthCareServerSide/log_data.txt";
    String log_dataString ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

//        //second
//        JSONObject jsonBody = new JSONObject();
//        try {
//            jsonBody.put("data", "Android Volley Demo");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        final String mRequestBody = jsonBody.toString();

        init();


        requestQueue = Volley.newRequestQueue(this);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//=================================================================== show
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, showUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                tvSync.setText( response);
                                log_dataString = response;
                                if (!log_dataString.isEmpty()){
                                    try {
                                        process_logData(log_dataString);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvSync.setText("That didn't work!");
                    }
                });
// Add the request to the RequestQueue.
                requestQueue.add(stringRequest);
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, "========   error:  "+ response);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG, "========   error:  "+ error.toString());
//                    }
//                }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String ,String> parameters = new HashMap<String, String>();
//
//                        parameters.put("firstname", "Tareq");
//                     //   parameters.put("data", "Tareq");
//                        parameters.put("lastname", "Tareq");
//                        //parameters.put("age", "33");
//                        Log.d(TAG, "========   parameters ");
//                        return parameters;
//
//                    }
////                    @Override
////                    public Map<String, String> getHeaders() throws AuthFailureError {
////                        Map<String,String> headers = new HashMap<String, String>();
////                         headers.put("Content-Type","application/x-www-form-urlencoded");
////                       //  headers.put("Content-Type","application/json");
////                        headers.put("abc", "value");
////                        return headers;
////                    }
//                };
//
//                requestQueue.add(request);



                // =======second
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.i("VOLLEY", "================" + response);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("VOLLEY", "=============== "+error.toString());
//                    }
//                }){
//                    @Override
//                    public String getBodyContentType() {
//                        return "application/json; charset=utf-8";  /////////////////////////////////////////////
//                    }
//
//                    @Override
//                    public byte[] getBody() throws AuthFailureError {
//                        try {
//                            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
//                        } catch (UnsupportedEncodingException e) {
//                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
//                            return null;
//                        }
//                    }
//
//                    @Override
//                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                        String responseString = "";
//                        if (response != null) {
//                            responseString = String.valueOf(response.statusCode);
//                            // can get more details such as response.headers
//                        }
//                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                    }
//                };
//                requestQueue.add(stringRequest);



//

//                        // third
//                StringRequest sr = new StringRequest(Request.Method.POST, insertUrl , new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, response.toString());
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        VolleyLog.d(TAG, "Error: " + error.getMessage());
//                        Log.d(TAG, ""+error.getMessage()+","+error.toString());
//                        tvSync.setText("error");
//                    }
//                }){
//                    @Override
//                    protected Map<String,String> getParams(){
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("firstname", "dfdfff");
//                        params.put("lastname", "zzzzzz");
//                        //params.put("age", "23");
//
//                        return params;
//                    }
//
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String,String> headers = new HashMap<String, String>();
//                         headers.put("Content-Type","application/x-www-form-urlencoded");
//                       //  headers.put("Content-Type","application/json");
//                        headers.put("abc", "value");
//                        return headers;
//                    }
//                };
//
//              requestQueue.add(sr);


                //fourth

                StringRequest strRequest = new StringRequest(Request.Method.POST, insertUrl,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                tvSync.setText(response);
                                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                               // Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                tvSync.setText(error.toString());
                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("firstname", "rohim");
                        params.put("lastname", "sadek");
                        params.put("age", "20");
                        return params;
                    }
                };

                requestQueue.add(strRequest);

            }////////
        });


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btn_parse_csv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String[]>  csvList ;
                    csvList = readCsv(Activity_Sync.this);
                for (String[] row: csvList
                     ) {
                    for (int i = 0; i<row.length;i++){
                    Log.d(TAG, "=========" + row[i]+"   ");}
                }

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_send_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, uploadFileUrl, new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        String resultResponse = new String(response.data);
                    tvSync.setText(resultResponse);
                        // parse success output
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("api_token", "gh659gjhvdyudo973823tt9gvjf7i6ric75r76");
                        params.put("name", "Angga");
                        params.put("location", "Indonesia");
                        params.put("about", "UI/UX Designer");
                        params.put("contact", "angga@email.com");
                        return params;
                    }

                    @Override
                    protected Map<String, DataPart> getByteData() throws FileNotFoundException {
                        Map<String, DataPart> params = new HashMap<>();
                        // file name could found file base or direct access from real path
                        // for now just get bitmap data from ImageView

                        File data = Environment.getDataDirectory();
                        String currentDBPath =  "/data/"+ "com.example.tareq.healthcare" +"/databases/"+DatabaseHelper.DATABASE_NAME;
                        File currentDB = new File(data, currentDBPath);

                        byte[] byteArray = new byte[(int)currentDB.length()];
                        FileInputStream inputStream = new FileInputStream(currentDB);
                        try {
                            inputStream.read(byteArray);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        params.put("uploaded_file", new DataPart(DatabaseHelper.DATABASE_NAME,byteArray,"application/x-sqlite3") );
                       // params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));

                        return params;
                    }
                };

                VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);







            }
        });



    }

    public final List<String[]> readCsv(Context context) {
        List<String[]> questionList = new ArrayList<String[]>();
        AssetManager assetManager = context.getAssets();
        File sd = Environment.getExternalStorageDirectory();
        File directory = new File(sd.getAbsolutePath() + "/Health Care/HC_Follow_Up_21-11-2016.csv");//=================== Name of the Folder
        String path = directory.getAbsolutePath() ;
        //File myfile =  new File("/sdcard/Health Care/HC_Follow_Up_21-11-2016.csv");

Log.d(TAG,"========== path "+path);
        try {
            FileInputStream csvStream = new FileInputStream(directory); //assetManager.open(path);
            BufferedReader csvStreamReader = new BufferedReader(new InputStreamReader(csvStream));
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                questionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }






    private void process_logData(String log_dataString) throws JSONException {

       // Gson gson = new Gson();
        //JSONObject jsonOb = gson.to
        //String jsonString = gson.toJson(log_dataString);
        //Log.d(TAG,"=============   "+ jsonString);

        JSONObject jsonObject = new JSONObject(log_dataString);
        JSONObject jsonObjectRowsAll = jsonObject.getJSONObject("rowsAll");

        String motherTable =  jsonObjectRowsAll.getString(DatabaseHelper.TABLE_MOTHER);
        String ancPncMessageTable = jsonObjectRowsAll.getString(DatabaseHelper.TABLE_ANC_PNC_MSG);
        String deliveryAndChildMessageTable = jsonObjectRowsAll.getString(DatabaseHelper.TABLE_DELIVERY_AND_CHILD_MSG);
        String childTable =   jsonObjectRowsAll.getString(DatabaseHelper.TABLE_CHILD);
        //String childTable =   jsonObjectRowsAll.getString(DatabaseHelper.TABLE_CHILD);

        Log.d(TAG,"============= mother  "+ motherTable);
        Log.d(TAG,"============= message  "+ ancPncMessageTable);
        Log.d(TAG,"============= message  "+ deliveryAndChildMessageTable);
        Log.d(TAG,"============= child  "+ childTable);


        String[] motherRowsArray = motherTable.split("\n");
        List<String> motherRowsList = new ArrayList<>(Arrays.asList(motherRowsArray));
        List<List<String>> motherRowColumnList = new ArrayList<>();

        for (String a_row: motherRowsList
             ) {
            String[] motherColumnArray = a_row.split("\t");
            List<String> motherColumnList = new ArrayList<>(Arrays.asList(motherColumnArray));
            motherRowColumnList.add(motherColumnList);
        }


        // log

        for (List<String> a_row:motherRowColumnList
             ) {
            Log.d(TAG,"======== row size  "+ String.valueOf(a_row.size()));

            for (String a_column: a_row
                 ) {
                Log.d(TAG,"======== column "+ a_column);
            }




        }





    }

    private void init() {
        btnSync= (Button) findViewById(R.id.btnSync);
        btnShow = (Button) findViewById(R.id.btnShow);
                tvSync= (TextView) findViewById(R.id.tvSync);
        btn_parse_csv = (Button) findViewById(R.id.btn_parse_csv);
        btn_send_file = (Button) findViewById(R.id.btn_send_file);


    }
}

