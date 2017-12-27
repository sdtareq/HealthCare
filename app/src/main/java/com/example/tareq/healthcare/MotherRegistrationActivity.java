package com.example.tareq.healthcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
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
    boolean isEdited = false;///////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        init();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // ===============================================    Back Button on Click
               exitFromActivity();


            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // ===============================================    Register Button on Click
                Log.d(TAG, " ===========  lmp " + mLmpDateStr);
                Log.d(TAG, " ===========  phone number " + etPhoneNumber.getText().toString());
                Log.d(TAG, " ===========  address " + etAddress.getText().toString());

                if (!validate()) {


                    return;
                }
                btn_register.setEnabled(false);

                if (pregnancyState != null && pregnancyState.equals(PREGNANCY_STATE_PREGNANT)) {
                if (mLmpDateStr.isEmpty()){
                    showCustomToast("মায়ের শেষ মাসিকের প্রথম দিনের তারিখ নির্বাচন করুন");

                    btn_register.setEnabled(true);
                    return;
                }


                    mother = new Mother(etMotherName.getText().toString(),etHusbandName.getText().toString(),etPhoneNumber.getText().toString(),etMotherAge.getText().toString(),
                            desiredCallingTime,etAddress.getText().toString(), et_GIS_location.getText().toString(),etAlternativePhoneNumber.getText().toString(),
                            etAlternativePhoneOwnerName.getText().toString(),etDHIS_ID.getText().toString(),mLmpDateStr,pregnancyState);
                    mother.setLoginUserName(MainActivity.user_name);

                    DatabaseHelper db = new DatabaseHelper(MotherRegistrationActivity.this);   // store in db)
                    db.registerMother(mother);
                    isEdited = true;    /////////////////

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
                    mother.setLoginUserName(MainActivity.user_name);


                    DatabaseHelper db = new DatabaseHelper(MotherRegistrationActivity.this);   // store in db)
                    db.registerMother(mother);
                    isEdited = true;    /////////////////
                }
                // if (pregnancyState != null && pregnancyState.equals("not known")){}

exitFromActivity();
            }
        });


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
    private boolean validate() {
        boolean valid = true;
        if (etMotherName.getText().toString().isEmpty() || pregnancyState == null) {

            if (etMotherName.getText().toString().isEmpty()){
                showCustomToast("মায়ের নাম নির্বাচন করুন");
                etMotherName.setError("");
            }else if (pregnancyState == null){
                showCustomToast("মা  র্গভবতী অথবা শিশুর জন্ম হয়েছে নির্বাচন করুন");

            }

            valid = false;

        }

        return valid;
    }

    private boolean validateChild() {
        boolean valid = true;
        if (mChildDateOfBirth.isEmpty() || sexOfChild.isEmpty()){ //|| etIdNumberOfChild.getText().toString().isEmpty()) {

            if (mChildDateOfBirth.isEmpty() ){
                showCustomToast("শিশুর জন্ম তারিখ নির্বাচন করুন ");
            }else if (sexOfChild.isEmpty()){
                showCustomToast("শিশুটি ছেলে না মেয়ে নির্বাচন করুন ");
            }

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

                    String futureDateString =  selectedday+"/"+(selectedmonth+1) + "/"+selectedyear;
                        Date futureDate = null;
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {

                            futureDate = dateFormat.parse(futureDateString);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//
                        Calendar calender = Calendar.getInstance();
                        calender.setTime(futureDate);
                        calender.add(Calendar.DATE, - 280);
                        Date probableLMP = calender.getTime();
                        String tempDate = dateFormat.format(probableLMP);
                        mLmpDateStr =tempDate;
                        etEDD.setText(futureDateString);

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
