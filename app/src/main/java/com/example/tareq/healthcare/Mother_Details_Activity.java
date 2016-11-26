package com.example.tareq.healthcare;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class Mother_Details_Activity extends AppCompatActivity {
    protected static final String TAG = "MotherDetailsActivity";


    EditText etMotherName, etHusbandName, etMotherAge, etPhoneNumber, etAddress, et_GIS_location, etAlternativePhoneNumber, etAlternativePhoneOwnerName, etDHIS_ID, etLMP;
    EditText etChildName, etChildDateOfBirth, etChildBirthWeight, etIdNumberOfChild,etFollowUpLastDateOfVisit,etFollowUpLastChildWeight,etFollowUpLastChildHeight;
    LinearLayout linearLayout_container_1, linearLayout_container_2, linearLayout_container_3;
    Button btn_cancel ;
    RadioButton rbDesireTime_Morning,rbDesireTime_Noon,rbDesireTime_Evening,rbMaleChild,rbFemaleChild ;
    int mYear, mMonth, mDay;

    LinearLayout
            linearLayout_ANC_Messages    ,
            linearLayout_ANC_4_Messages   ,
            linearLayout_PNC_Messages     ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Mother mother = (Mother) getIntent().getSerializableExtra(TAG);
        //Toast.makeText(this,mother.getMotherName(),Toast.LENGTH_SHORT).show();
        init();

        if (mother.getChild() != null){
            DatabaseHelper db = new DatabaseHelper(this);
            Child child = db.getLastFollowUp(mother.getMotherRowPrimaryKey());

            if (child.getChildDateOfVisit() != null){
                linearLayout_container_3.setVisibility(View.VISIBLE);
                etFollowUpLastDateOfVisit.setText(child.getChildDateOfVisit());
                etFollowUpLastChildWeight.setText(child.getChildWeight());
                etFollowUpLastChildHeight.setText(child.getChildHeight());
            }



        }




if ( mother.getMotherName() != null){ etMotherName.setText(mother.getMotherName());}
if (mother.getHusbandName()  != null){  etHusbandName.setText(mother.getHusbandName());}
if ( mother.getMotherAge() != null){etMotherAge.setText(mother.getMotherAge());}
if ( mother.getMotherPhoneNumber() != null){ etPhoneNumber.setText(mother.getMotherPhoneNumber());}
if ( mother.getMotherAddress() != null){etAddress.setText(mother.getMotherAddress());}
if (mother.getGIS_Location()  != null){   et_GIS_location.setText(mother.getGIS_Location());}
if (mother.getAlternativePhoneNumber()  != null){etAlternativePhoneNumber.setText(mother.getAlternativePhoneNumber());}
if ( mother.getAlternativePhoneOwnerName() != null){etAlternativePhoneOwnerName.setText(mother.getAlternativePhoneOwnerName());}
if ( mother.getDHIS_ID() != null){ etDHIS_ID.setText(mother.getDHIS_ID());}


         if (ANC_PNC_List_Activity.ANC_PNC_STATE.equals(GroupMother.CHILD_CARE_MESSAGE_STATUS) ||ANC_PNC_List_Activity.ANC_PNC_STATE.equals(GroupMother.PNC) ){

             linearLayout_container_1.setVisibility(View.GONE);
             linearLayout_container_2.setVisibility(View.VISIBLE);
             if (mother.getChild() != null){

             if ( mother.getChild().getChildName() != null){ etChildName.setText(mother.getChild().getChildName());}
             if ( mother.getChild().getChildDateOfBirth() != null){etChildDateOfBirth.setText(mother.getChild().getChildDateOfBirth());}
             if ( mother.getChild().getChildBirthWeight() != null){ etChildBirthWeight.setText(mother.getChild().getChildBirthWeight());}
             if ( mother.getChild().getIdNumberOfChild() != null){} etIdNumberOfChild.setText(mother.getChild().getIdNumberOfChild());
             if(mother.getChild().sexOfChild != null && mother.getChild().sexOfChild.equals(MotherRegistrationActivity.SEX_OF_CHILD_MALE)){
                 rbMaleChild.setChecked(true);
             }
             if(mother.getChild().sexOfChild != null && mother.getChild().sexOfChild.equals(MotherRegistrationActivity.SEX_OF_CHILD_FEMALE)){
                 rbFemaleChild.setChecked(true);
             }

             }



        }else {
             linearLayout_container_1.setVisibility(View.VISIBLE);
             linearLayout_container_2.setVisibility(View.GONE);
            etLMP.setText(mother.getLastMenstruationDate());
        }

        if(mother.getDesiredCallingTime() != null && mother.getDesiredCallingTime().equals(MotherRegistrationActivity.DESIRE_CALLING_TIME_MORNING)){
            rbDesireTime_Morning.setChecked(true);
        }else if(mother.getDesiredCallingTime() != null && mother.getDesiredCallingTime().equals(MotherRegistrationActivity.DESIRE_CALLING_TIME_NOON)){
rbDesireTime_Noon.setChecked(true);
        }else if(mother.getDesiredCallingTime() != null && mother.getDesiredCallingTime().equals(MotherRegistrationActivity.DESIRE_CALLING_TIME_EVENING)){
rbDesireTime_Evening.setChecked(true);
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {

        rbDesireTime_Morning= (RadioButton) findViewById(R.id.rbDesireTime_Morning);
        rbDesireTime_Noon = (RadioButton) findViewById(R.id.rbDesireTime_Noon);
        rbDesireTime_Evening= (RadioButton) findViewById(R.id.rbDesireTime_Evening);
        rbMaleChild = (RadioButton) findViewById(R.id.rbMaleChild);
        rbFemaleChild = (RadioButton) findViewById(R.id.rbFemaleChild);

        etMotherName                = (EditText) findViewById(R.id.etMotherName);
        etHusbandName               = (EditText) findViewById(R.id.etHusbandName);
        etMotherAge                 = (EditText) findViewById(R.id.etMotherAge);
        etPhoneNumber               = (EditText) findViewById(R.id.etPhoneNumber);
        etAddress                   = (EditText) findViewById(R.id.etAddress);
        et_GIS_location             = (EditText) findViewById(R.id.et_GIS_location);
        etAlternativePhoneNumber    = (EditText) findViewById(R.id.etAlternativePhoneNumber);
        etAlternativePhoneOwnerName = (EditText) findViewById(R.id.etAlternativePhoneOwnerName);
        etDHIS_ID                   = (EditText) findViewById(R.id.etDHIS_ID);
        etLMP                        = (EditText) findViewById(R.id.etLMP);

        btn_cancel = (Button) findViewById(R.id.btn_cancel);


        linearLayout_container_1 = (LinearLayout) findViewById(R.id.LinearLayout_Container_1);
        linearLayout_container_2 = (LinearLayout) findViewById(R.id.LinearLayout_Container_2);
        linearLayout_container_3 = (LinearLayout) findViewById(R.id.LinearLayout_Container_3);

        etChildName        = (EditText) findViewById(R.id.etChildName);
        etChildDateOfBirth = (EditText) findViewById(R.id.etChildDateOfBirth);
        etChildBirthWeight = (EditText) findViewById(R.id.etChildBirthWeight);
        etIdNumberOfChild  = (EditText) findViewById(R.id.etIdNumberOfChild);
        etFollowUpLastDateOfVisit = (EditText) findViewById(R.id.etFollowUpLastDateOfVisit);
        etFollowUpLastChildHeight = (EditText) findViewById(R.id.etFollowUpLastChildHeight);
        etFollowUpLastChildWeight = (EditText) findViewById(R.id.etFollowUpLastChildWeight);


    }
}
