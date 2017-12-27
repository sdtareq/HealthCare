package com.example.tareq.healthcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChildFollowUpActivity extends AppCompatActivity {
    final static String TAG = "ChildFollowUpActivity";
    boolean isEdited = false;

    EditText etMotherName,
            etChildAge   ,
            etDateOfVisit,
            etChildWeight,
            etChildHeight;

    AppCompatButton btn_back,
                     btn_add;
    int mYear,mMonth,mDay;

    String  mChildDateOfVisit = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_follow_up);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final Mother mother = (Mother) getIntent().getSerializableExtra(TAG);

        init( );
        etMotherName.setText(mother.getMotherName());
       // int ageOfChildInMonths = Math.abs(calcAgeOfChild(mother)/30);
        int ageOfChildInMonths = Math.abs(mother.getAgeOfChild()/30);

        etChildAge.setText(String.valueOf(ageOfChildInMonths) );
//etChildAge.setVisibility(View.GONE);




        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               exitFromActivity();
            }
        });

        etDateOfVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(ChildFollowUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        mChildDateOfVisit = String.valueOf(selectedday)+ "/" +String.valueOf(selectedmonth+1)+ "/" +String.valueOf(selectedyear);
                        //  Toast.makeText(getApplicationContext(),mChildDateOfBirth,Toast.LENGTH_SHORT).show();
                        etDateOfVisit.setText(mChildDateOfVisit);


                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        try {

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn_add.setEnabled(false);

                    if (!validateChild()){
                        btn_add.setEnabled(true);
                        return;
                    }
                    int ageOfChildInMonths = Math.abs(mother.getAgeOfChild()/30);
                    Child  child = new Child(mother.getChild().getChildName(),mother.getMotherRowPrimaryKey(),mother.getMotherName(),mother.getChild().getChildId(),
                            etChildWeight.getText().toString(),etChildHeight.getText().toString(),mChildDateOfVisit);
                    child.setChildAge(String.valueOf(ageOfChildInMonths));

                    DatabaseHelper db = new DatabaseHelper(ChildFollowUpActivity.this);   // store in db)
                    db.addChildFollowUp(child);


                    isEdited = true;/////////////////

                   exitFromActivity();
                }
            });




        }catch (Exception e){
            Log.d(TAG," data insertion problem "+e.toString());
        }


    }
    @Override
    public void onBackPressed() {

       exitFromActivity();

    }

    public void exitFromActivity(){
        Intent intent = new Intent();//////////////
        intent.putExtra(TAG, isEdited );///////
        setResult(RESULT_OK, intent);//////

        finish();
    }

    private boolean validateChild() {
        boolean valid = true;
        if (mChildDateOfVisit.isEmpty() || etChildWeight.getText().toString().isEmpty() || etChildHeight.getText().toString().isEmpty()) {
            showCustomToast("সব তথ্য পূরণ করুন");
            //Toast.makeText(this, "সব তথ্য পূরণ করুন", Toast.LENGTH_SHORT).show();
            valid = false;

        }

        return valid;
    }


    private void init() {
        etMotherName = (EditText) findViewById(R.id.etMotherName );
                etChildAge = (EditText) findViewById(R.id.etChildAge);
        etDateOfVisit = (EditText) findViewById(R.id.etDateOfVisit);
                etChildWeight = (EditText) findViewById(R.id.etChildWeight);
        etChildHeight = (EditText) findViewById(R.id.etChildHeight);

        btn_back = (AppCompatButton) findViewById(R.id.btn_back );
                btn_add = (AppCompatButton) findViewById(R.id.btn_add );
    }



    public int calcAgeOfChild(Mother mother) {
        int diffInDays = 0;
        Date oldDate = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {

            oldDate = dateFormat.parse(mother.getDeliveryDate());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date date = new Date();
        final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
        if (oldDate != null) {
              diffInDays = (int) ((date.getTime() - oldDate.getTime()) / DAY_IN_MILLIS);
           // mother.setAgeOfChild(diffInDays);
        }
        return diffInDays;
    }
    public void showCustomToast(String text){
        LayoutInflater inflater = getLayoutInflater();
        View view= inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView textView = (TextView) view.findViewById(R.id.tvCustomToastText);
        textView.setText(text);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
