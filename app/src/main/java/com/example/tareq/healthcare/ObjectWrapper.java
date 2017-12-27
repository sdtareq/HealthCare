package com.example.tareq.healthcare;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TAREQ on 1/24/2017.
 */
public final class ObjectWrapper {
        private static final String TAG = "ObjectWrapper";

    private ObjectWrapper(){}

public static List<Mother> wrapMothers(List<String[]> rowColumn){
    List<Mother> motherList = new ArrayList<>();


    for (String[] columns  :  rowColumn ) {
        Mother mother = new Mother();

        mother.setMotherRowPrimaryKey(columns[0]);
        mother.setMotherName(columns[1]);
        mother.setHusbandName(columns[2]);
        mother.setMotherAge(columns[3]);
        mother.setMotherPhoneNumber(columns[4]);
        mother.setDesiredCallingTime(columns[5]);
        mother.setMotherAddress(columns[6]);
        mother.setGIS_Location(columns[7]);
        mother.setAlternativePhoneNumber(columns[8]);
        mother.setAlternativePhoneOwnerName(columns[9]);
        mother.setDHIS_ID(columns[10]);
        mother.setLastMenstruationDate(columns[11]);
        mother.setPregnancyState(columns[12]);
        mother.setDeliveryDate(columns[13]);
        mother.setLoginUserName(columns[14]);
        mother.setSyncStatus("true");

        motherList.add(mother);
    }



    return motherList;
}
    public static List<Mother> wrapDeliveryAndChildMessages(List<String[]> rowColumn){
        List<Mother> motherList = new ArrayList<>();


        for (String[] columns  :  rowColumn ) {
            Mother mother = new Mother();

            mother.setDelivery_and_child_msg_column_id(columns[0]);
            mother.setMotherRowPrimaryKey(columns[1]);
            mother.setIsPreDelivery_Message_Delivered(columns[2]);
            mother.setIsChild_message_delivered_0_to_14_days(columns[3]);
            mother.setIsChild_message_delivered_1_2_3_month(columns[4]);
            mother.setIsChild_message_delivered_6_to_8_month(columns[5]);
            mother.setIsChild_message_delivered_9_to_12_month(columns[6]);
            mother.setDelivery_and_child_msg_sync_status("true");

            motherList.add(mother);
        }



        return motherList;
    }
    public static List<Mother> wrapAncPncMessages(List<String[]> rowColumn){
        List<Mother> motherList = new ArrayList<>();


        for (String[] columns  :  rowColumn ) {
            Mother mother = new Mother();

            mother.setAnc_pnc_msg_column_id(columns[0]);
            mother.setMotherRowPrimaryKey(columns[1]);
            mother.setIsANC_1_Message_Delivered(columns[2]);
            mother.setIsANC_2_Message_Delivered(columns[3]);
            mother.setIsANC_3_Message_Delivered(columns[4]);
            mother.setIsANC_4_Message_Delivered(columns[5]);
            mother.setIsPNC_Message_Delivered(columns[6]);
            mother.setIsMotherDead(columns[7]);
            mother.setAnc_pnc_msg_sync_status("true");

            motherList.add(mother);
        }



        return motherList;
    }
    public static List<Child> wrapChildren(List<String[]> rowColumn){
        List<Child> childList = new ArrayList<>();
        for (String[] columns: rowColumn  ) {
            Child child = new Child();

            child.setChildId(columns[0]);
            child.setChildMotherTableId(columns[1]);
            child.setChildMotherName(columns[2]);
            child.setChildName(columns[3]);
            child.setSexOfChild(columns[4]);
            child.setChildDateOfBirth(columns[5]);
            child.setChildBirthWeight(columns[6]);
            child.setIdNumberOfChild(columns[7]);

            childList.add(child);


            Log.d(TAG,"child ===  "+ columns[0]);
            Log.d(TAG,"child ===  "+ columns[1]);
            Log.d(TAG,"child ===  "+ columns[2]);
            Log.d(TAG,"child ===  "+ columns[3]);
            Log.d(TAG,"child ===  "+ columns[4]);
            Log.d(TAG,"child ===  "+ columns[5]);
            Log.d(TAG,"child ===  "+ columns[6]);
            Log.d(TAG,"child ===  "+ columns[7]);

        }

        return childList;
    }
    public static List<Child> wrapChildFollowUp(List<String[]> rowColumn){
        List<Child> childList = new ArrayList<>();
        for (String[] columns: rowColumn             ) {
            Child child = new Child();

            child.setChildId(columns[0]);
            child.setChildMotherTableId(columns[1]);
            child.setChildMotherName(columns[2]);
            child.setChildName(columns[3]);
            child.setChildId(columns[4]);
            child.setChildDateOfVisit(columns[5]);
            child.setChildWeight(columns[6]);
            child.setChildHeight(columns[7]);
            child.setChildFollowUpSyncStatus("true");

            childList.add(child);

            Log.d(TAG,"child follow up ==" + columns[0]);
            Log.d(TAG,"child follow up ==" + columns[1]);
            Log.d(TAG,"child follow up ==" + columns[2]);
            Log.d(TAG,"child follow up ==" + columns[3]);
            Log.d(TAG,"child follow up ==" + columns[4]);
            Log.d(TAG,"child follow up ==" + columns[5]);
            Log.d(TAG,"child follow up ==" + columns[6]);
            Log.d(TAG,"child follow up ==" + columns[7]);
        }

        return childList;
    }

}
