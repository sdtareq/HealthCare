package com.example.tareq.healthcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

public class AddChildActivity extends AppCompatActivity {
    protected final static String TAG = "AddChildActivity";
    protected final static String MOTHER_NAME = "mother name";
    protected final static String MOTHER_ROW_ID = "mother row id";
    int mYear,mMonth,mDay;

    String mMotherName, mMotherRowId , mSexOfChild,mChildDateOfBirth="";

    EditText etChildName, etChildDateOfBirth, etChildBirthWeight, etIdNumberOfChild;
    AppCompatButton btn_register,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        mMotherName = getIntent().getStringExtra(MOTHER_NAME);
        mMotherRowId = getIntent().getStringExtra(MOTHER_ROW_ID);


        ((EditText) findViewById(R.id.etMotherName)).setHint(mMotherName);
        ((EditText) findViewById(R.id.etMotherRowId)).setHint(mMotherRowId);


        init();

        try {

            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn_register.setEnabled(false);
                    Child  child = new Child(mMotherName,etChildName.getText().toString(),
                            mChildDateOfBirth,mSexOfChild,etChildBirthWeight.getText().toString(),
                            etIdNumberOfChild.getText().toString());
                    child.setChildMotherTableId(mMotherRowId);

                    DatabaseHelper db = new DatabaseHelper(AddChildActivity.this);   // store in db)
                    db.registerChild(child);
                    db.setPregnancyState(mMotherRowId,MessageActivity.POST_DELIVERY_DB,mChildDateOfBirth);

            }
            });




        }catch (Exception e){
            Log.d(TAG," data insertion problem "+e.toString());
        }

    }

    private void init() {


        etChildName = (EditText) findViewById(R.id.etChildName);
        etChildDateOfBirth = (EditText) findViewById(R.id.etChildDateOfBirth);
        etChildBirthWeight = (EditText) findViewById(R.id.etChildBirthWeight);
        etIdNumberOfChild = (EditText) findViewById(R.id.etIdNumberOfChild);

        btn_register = (AppCompatButton) findViewById(R.id.btn_register);
        btn_back = (AppCompatButton) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etChildDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(AddChildActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        mChildDateOfBirth = String.valueOf(selectedday)+ "/" +String.valueOf(selectedmonth+1)+ "/" +String.valueOf(selectedyear);
                        //  Toast.makeText(getApplicationContext(),mChildDateOfBirth,Toast.LENGTH_SHORT).show();
                        etChildDateOfBirth.setHint("জন্মের তারিখ: "+mChildDateOfBirth);


                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });




    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()){
            case R.id.rbMaleChild:
                if (checked)

                    mSexOfChild = "male";

                break;
            case R.id.rbFemaleChild:
                if (checked)
                    // userType = "type 2";
                    mSexOfChild = "female";

                break;
        }
    }

}
