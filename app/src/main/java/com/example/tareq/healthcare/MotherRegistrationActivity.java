package com.example.tareq.healthcare;

import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MotherRegistrationActivity extends AppCompatActivity {
    protected static final String TAG = "MotherRegistration";
    protected static final String DESIRE_CALLING_TIME_MORNING = "morning";
    protected static final String DESIRE_CALLING_TIME_NOON = "noon";
    protected static final String DESIRE_CALLING_TIME_EVENING = "evening";
    protected static final String PREGNANCY_STATE_PREGNANT = "pregnant";
    protected static final String PREGNANCY_STATE_POST_DELIVERY = "post delivery";
    protected static final String SEX_OF_CHILD_MALE = "male";
    protected static final String SEX_OF_CHILD_FEMALE = "female";

    String mLmpDateStr = "", mChildDateOfBirth = "";
    EditText etMotherName, etHusbandName, etMotherAge, etPhoneNumber, etAddress, et_GIS_location, etAlternativePhoneNumber, etAlternativePhoneOwnerName, etDHIS_ID, etLMP,etEDD;
    EditText etChildName, etChildDateOfBirth, etChildBirthWeight, etIdNumberOfChild;
    LinearLayout linearLayout_container_1, linearLayout_container_2, linearLayout_cotainer_3;
    TextInputLayout textInputLayout_LMP,textInputLayout_EDD;
    Button btn_cancel, btn_register;
    int mYear, mMonth, mDay;
    Mother mother;
    String pregnancyState = null, sexOfChild="", desiredCallingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        init();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // ===============================================    Back Button on Click
                finish();
                //   Toast.makeText(getApplicationContext(), "c1: "+etC1.getText().toString()+"  c2:  "+etC2.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // ===============================================    Register Button on Click
                Log.d(TAG, " ===========  lmp " + mLmpDateStr);
                Log.d(TAG, " ===========  phone number " + etPhoneNumber.getText().toString());
                Log.d(TAG, " ===========  address " + etAddress.getText().toString());

                if (!validate()) {

                    //Toast.makeText(getApplicationContext(), "Not Valid Registration", Toast.LENGTH_SHORT).show();
                    return;
                }
                btn_register.setEnabled(false);

                if (pregnancyState != null && pregnancyState.equals(PREGNANCY_STATE_PREGNANT)) {
                if (mLmpDateStr.isEmpty()){
                    Toast.makeText(getApplicationContext(),"মায়ের শেষ মাসিকের প্রথম দিনের তারিখ নির্বাচন করুন",Toast.LENGTH_SHORT).show();
                    btn_register.setEnabled(true);
                    return;
                }


                    mother = new Mother(etMotherName.getText().toString(),etHusbandName.getText().toString(),etPhoneNumber.getText().toString(),etMotherAge.getText().toString(),desiredCallingTime,etAddress.getText().toString(),
                            et_GIS_location.getText().toString(),etAlternativePhoneNumber.getText().toString(),etAlternativePhoneOwnerName.getText().toString(),etDHIS_ID.getText().toString(),mLmpDateStr,
                            pregnancyState);

                    DatabaseHelper db = new DatabaseHelper(MotherRegistrationActivity.this);   // store in db)
                    db.registerMother(mother);


                }
                if (pregnancyState != null && pregnancyState.equals(PREGNANCY_STATE_POST_DELIVERY)) {
if (!validateChild()){
    btn_register.setEnabled(true);
    return;
}

                    Child child = new Child(etMotherName.getText().toString(), etChildName.getText().toString(),
                            mChildDateOfBirth, sexOfChild, etChildBirthWeight.getText().toString(),
                            etIdNumberOfChild.getText().toString());

                    mother = new Mother(etMotherName.getText().toString(),etHusbandName.getText().toString(),etPhoneNumber.getText().toString(),etMotherAge.getText().toString(),desiredCallingTime,etAddress.getText().toString(),
                            et_GIS_location.getText().toString(),etAlternativePhoneNumber.getText().toString(),etAlternativePhoneOwnerName.getText().toString(),etDHIS_ID.getText().toString(),mLmpDateStr,
                            pregnancyState,child);
                    mother.setDeliveryDate(mChildDateOfBirth);


                    DatabaseHelper db = new DatabaseHelper(MotherRegistrationActivity.this);   // store in db)
                    db.registerMother(mother);

                }
                // if (pregnancyState != null && pregnancyState.equals("not known")){}


            }
        });


    }

    private boolean validate() {
        boolean valid = true;
        if (etMotherName.getText().toString().isEmpty() || pregnancyState == null) {

            Toast.makeText(this, "মায়ের নাম এবং মা প্রেগন্যান্ট অথবা শিশুর জন্ম হয়েছে নির্বাচন করুন", Toast.LENGTH_SHORT).show();
            valid = false;

        }

        return valid;
    }

    private boolean validateChild() {
        boolean valid = true;
        if (mChildDateOfBirth.isEmpty() || sexOfChild.isEmpty() || etIdNumberOfChild.getText().toString().isEmpty()) {

            Toast.makeText(this, "শিশুর জন্ম তারিখ এবং শিশুটি ছেলে না মেয়ে নির্বাচন করুন এবং শিশুর আইডি নাম্বার নির্বাচন করুন", Toast.LENGTH_SHORT).show();
            valid = false;

        }

        return valid;
    }

    private void init() {


        etMotherName = (EditText) findViewById(R.id.etMotherName);
        etHusbandName = (EditText) findViewById(R.id.etHusbandName);
        etMotherAge = (EditText) findViewById(R.id.etMotherAge);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etAddress = (EditText) findViewById(R.id.etAddress);
        et_GIS_location = (EditText) findViewById(R.id.et_GIS_location);
        etAlternativePhoneNumber = (EditText) findViewById(R.id.etAlternativePhoneNumber);
        etAlternativePhoneOwnerName = (EditText) findViewById(R.id.etAlternativePhoneOwnerName);
        etDHIS_ID = (EditText) findViewById(R.id.etDHIS_ID);
        etLMP = (EditText) findViewById(R.id.etLMP);
        etEDD = (EditText) findViewById(R.id.etEDD);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_register = (Button) findViewById(R.id.btn_register);

        linearLayout_container_1 = (LinearLayout) findViewById(R.id.LinearLayout_Container_1);
        linearLayout_container_2 = (LinearLayout) findViewById(R.id.LinearLayout_Container_2);
        textInputLayout_LMP  = (TextInputLayout) findViewById(R.id.textInputLayout_LMP);
        textInputLayout_EDD = (TextInputLayout) findViewById(R.id.textInputLayout_EDD);

        etChildName = (EditText) findViewById(R.id.etChildName);
        etChildDateOfBirth = (EditText) findViewById(R.id.etChildDateOfBirth);
        etChildBirthWeight = (EditText) findViewById(R.id.etChildBirthWeight);
        etIdNumberOfChild = (EditText) findViewById(R.id.etIdNumberOfChild);

        //temp
        //etC1 = (EditText) findViewById(R.id.etContainer_1);
        //etC2 = (EditText) findViewById(R.id.etContainer_2);

        etLMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MotherRegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        mLmpDateStr = String.valueOf(selectedday) + "/" + String.valueOf(selectedmonth + 1) + "/" + String.valueOf(selectedyear);
                        //Toast.makeText(getApplicationContext(),mLmpDateStr,Toast.LENGTH_SHORT).show();
                        etLMP.setText("LMP: " + mLmpDateStr);

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        etEDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MotherRegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                       // mLmpDateStr = String.valueOf(selectedday) + "/" + String.valueOf(selectedmonth + 1) + "/" + String.valueOf(selectedyear);
                        //Toast.makeText(getApplicationContext(),mLmpDateStr,Toast.LENGTH_SHORT).show();
                       // etLMP.setText("LMP: " + mLmpDateStr);
                    String futureDateString =  selectedday+"/"+(selectedmonth+1) + "/"+selectedyear;
                        Date futureDate = null;
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {

                            futureDate = dateFormat.parse(futureDateString);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//                        Date date = new Date();
//                        final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
//                        if (futureDate != null) {
//                            //int diffInDays = (int) ((date.getTime() - oldDate.getTime()) / DAY_IN_MILLIS);
//                            int diffInDays = (int) ((date.getTime() - oldDate.getTime()) / DAY_IN_MILLIS);
//                            //mother.setDaysOnPregnancy(diffInDays);
//                        }
                        Calendar calender = Calendar.getInstance();
                        calender.setTime(futureDate);
                        calender.add(Calendar.DATE, - 280);
                        Date probableLMP = calender.getTime();
                        String tempDate = dateFormat.format(probableLMP);
                        mLmpDateStr =tempDate;
                        etEDD.setText(futureDateString);
                        Toast.makeText(getApplicationContext(),mLmpDateStr,Toast.LENGTH_LONG).show();

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        etChildDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MotherRegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        mChildDateOfBirth = String.valueOf(selectedday) + "/" + String.valueOf(selectedmonth + 1) + "/" + String.valueOf(selectedyear);
                        //  Toast.makeText(getApplicationContext(),mChildDateOfBirth,Toast.LENGTH_SHORT).show();
                        etChildDateOfBirth.setHint("জন্মের তারিখ: " + mChildDateOfBirth);


                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbPregnant:
                if (checked)
                    pregnancyState =   PREGNANCY_STATE_PREGNANT;
                // Toast.makeText(this, "type 1", Toast.LENGTH_SHORT).show();
                linearLayout_container_1.setVisibility(View.VISIBLE);
                linearLayout_container_2.setVisibility(View.GONE);
                //linearLayout_container_3.setVisibility(View.GONE);

                break;
            case R.id.rbPostDelivery:
                if (checked)
                    // userType = "type 2";
                    pregnancyState = PREGNANCY_STATE_POST_DELIVERY;
                //Toast.makeText(this, "type 1", Toast.LENGTH_SHORT).show();
                linearLayout_container_1.setVisibility(View.GONE);
                linearLayout_container_2.setVisibility(View.VISIBLE);
                //   linearLayout_container_3.setVisibility(View.GONE);
                break;
//            case R.id.rbNotKnown:
//                if (checked)
//                   // userType = "type 3";
            //pregnancyState = "not known";
//                    //Toast.makeText(this, "type 1", Toast.LENGTH_SHORT).show();
//                linearLayout_container_1.setVisibility(View.GONE);
//                linearLayout_container_2.setVisibility(View.GONE);
//                linearLayout_container_3.setVisibility(View.VISIBLE);
//                break;
            case R.id.rbLMP:
                if (checked)
                    textInputLayout_LMP.setVisibility(View.VISIBLE);
                    textInputLayout_EDD.setVisibility(View.GONE);
                    break;
            case R.id.rbEDD:
                if (checked)
                    textInputLayout_LMP.setVisibility(View.GONE);
                    textInputLayout_EDD.setVisibility(View.VISIBLE);
                    break;
            case R.id.rbMaleChild:
                if (checked)

                    sexOfChild = SEX_OF_CHILD_MALE;

                break;
            case R.id.rbFemaleChild:
                if (checked)
                    // userType = "type 2";
                    sexOfChild = SEX_OF_CHILD_FEMALE;

                break;

            case R.id.rbDesireTime_Morning:
                if (checked)
                    desiredCallingTime = DESIRE_CALLING_TIME_MORNING;
                break;

            case R.id.rbDesireTime_Noon:
                if (checked)
                    desiredCallingTime = DESIRE_CALLING_TIME_NOON;
                break;

            case R.id.rbDesireTime_Evening:
                if (checked)
                    desiredCallingTime = DESIRE_CALLING_TIME_EVENING;
                break;
        }
    }

}
