package com.example.tareq.healthcare;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TAREQ on 10/25/2016.
 */
public class GroupMother   {
    Context context;
    protected static final String ANC_1 = "ANC 1";
    protected static final String ANC_2 = "ANC 2";
    protected static final String ANC_3 = "ANC 3";
    protected static final String ANC_4 = "ANC 4";
    static final String PRE_DELIVERY = "preDelivery" ;
    protected static final String PNC = "PNC";
    protected static final String CHILD_CARE_MESSAGE_STATUS = "child care message";
    static final String POST_DELIVERY_DB = "post delivery" ;  // DATABASE COLUMN ENTRY USED TO GROUPING MOTHER'S HAVING CHILD
    Map<String, List<Mother>> allGroupMap = new HashMap<>();
    List<Mother> anc1 = new ArrayList<>();
    List<Mother> anc2 = new ArrayList<>();
    List<Mother> anc3 = new ArrayList<>();
    List<Mother> anc4 = new ArrayList<>();

    List<Mother> pnc = new ArrayList<>();
    List<Mother> childCareMessageStatusList = new ArrayList<>();
//    List<Mother> pnc3 = new ArrayList<>();
//    List<Mother> pnc4 = new ArrayList<>();

    String groupKey = "";

    DatabaseHelper db  ;


    public GroupMother(Context context){
        this.context = context;
        db = new DatabaseHelper(context);
    }
    public void calcDays(Mother mother) {

        Date oldDate = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {

            oldDate = dateFormat.parse(mother.getLastMenstruationDate());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date date = new Date();
        final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
        if (oldDate != null) {
            int diffInDays = (int) ((date.getTime() - oldDate.getTime()) / DAY_IN_MILLIS);
            mother.setDaysOnPregnancy(diffInDays);
        }
    }

    public void calcAgeOfChild(Mother mother) {

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
            int diffInDays = (int) ((date.getTime() - oldDate.getTime()) / DAY_IN_MILLIS);
            mother.setAgeOfChild(diffInDays);
        }
    }


    public void doGrouping(List<Mother> mothers) {


        for (Mother a_mother : mothers) {



            if (a_mother.getPregnancyState()!= null && a_mother.getPregnancyState().equals(MotherRegistrationActivity.PREGNANCY_STATE_POST_DELIVERY)) { ///"post delivery" ============ Post Delivery
                calcAgeOfChild(a_mother);

                if (a_mother.getAgeOfChild() < 46 && a_mother.getAgeOfChild() >= 0) { //// ========== 1 to 45 days

                    if (!allGroupMap.containsKey(PNC)) {  //
                        allGroupMap.put(PNC, pnc); //
                    }
                    // if message delivery status null set initial value "false" as message status for both the object and update it inside database
                    if (a_mother.getIsPNC_Message_Delivered()==null){
                        a_mother.setIsPNC_Message_Delivered("false");
                        db.set_PNC_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                    }
                    allGroupMap.get(PNC).add(a_mother); //


                }

                if (a_mother.getAgeOfChild() < 331 && a_mother.getAgeOfChild() >= 0){  /// ============  1 to 330 days
                    if (!allGroupMap.containsKey(CHILD_CARE_MESSAGE_STATUS)) {  //
                        allGroupMap.put(CHILD_CARE_MESSAGE_STATUS, childCareMessageStatusList); //
                    }

                    // if message delivery status null set initial value "false" as message status for both the object and update it inside database

                    allGroupMap.get(CHILD_CARE_MESSAGE_STATUS).add(a_mother); //
                }





            }else if (a_mother.getPregnancyState()!= null && a_mother.getPregnancyState().equals(MotherRegistrationActivity.PREGNANCY_STATE_PREGNANT)){  // =================== if Mother is Pregnant
                calcDays(a_mother);
                setEDD(a_mother);


                if (a_mother.getDaysOnPregnancy() < 168 && a_mother.getDaysOnPregnancy() > 55) {
                    //groupKey = "ANC 1";
                    if (!allGroupMap.containsKey(ANC_1)) {
                        allGroupMap.put(ANC_1, anc1);
                    }

                    // if message delivery status null set initial value "false" as message status for both the object and update it inside database
                    if (a_mother.getIsANC_1_Message_Delivered()==null){
                        a_mother.setIsANC_1_Message_Delivered("false");
                        db.set_ANC_1_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                    }
                    allGroupMap.get(ANC_1).add(a_mother);

                } else if (a_mother.getDaysOnPregnancy() < 224 && a_mother.getDaysOnPregnancy() > 167) {
                    //groupKey = "ANC 2";
                    if (!allGroupMap.containsKey(ANC_2)) {
                        allGroupMap.put(ANC_2, anc2);
                    }
                    // if message delivery status null set initial value "false" as message status for both the object and update it inside database
                    if (a_mother.getIsANC_2_Message_Delivered()==null){
                        a_mother.setIsANC_2_Message_Delivered("false");
                        db.set_ANC_2_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                    }

                    allGroupMap.get(ANC_2).add(a_mother);

                } else if (a_mother.getDaysOnPregnancy() < 245 && a_mother.getDaysOnPregnancy() > 223) {
                   // groupKey = "ANC 3";
                    if (!allGroupMap.containsKey(ANC_3)) {
                        allGroupMap.put(ANC_3, anc3);
                    }
                    // if message delivery status null set initial value "false" as message status for both the object and update it inside database
                    if (a_mother.getIsANC_3_Message_Delivered()==null){
                        a_mother.setIsANC_3_Message_Delivered("false");
                        db.set_ANC_3_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                    }

                    allGroupMap.get(ANC_3).add(a_mother);
                } else if (a_mother.getDaysOnPregnancy() < 291 && a_mother.getDaysOnPregnancy() > 244) {
                   // groupKey = "ANC 4";
                    if (!allGroupMap.containsKey(ANC_4)) {
                        allGroupMap.put(ANC_4, anc4);
                    }
                    // if message delivery status null set initial value "false" as message status for both the object and update it inside database
                    if (a_mother.getIsANC_4_Message_Delivered()==null){
                        a_mother.setIsANC_4_Message_Delivered("false");
                        db.set_ANC_4_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                    }

                    allGroupMap.get(ANC_4).add(a_mother);
                }



            }


        }
    }



    public void filterMothersHavingChild(List<Mother> mothers) {


        for (Mother a_mother : mothers) {


               /// =============================== Post Delivery
                calcAgeOfChild(a_mother);
            if (a_mother.getAgeOfChild() < 46 && a_mother.getAgeOfChild() >= 0) { //// ========== 1 to 45 days

                if (!allGroupMap.containsKey(PNC)) {  //
                    allGroupMap.put(PNC, pnc); //
                }
                // if message delivery status null set initial value "false" as message status for both the object and update it inside database
                if (a_mother.getIsPNC_Message_Delivered().isEmpty()){
                    a_mother.setIsPNC_Message_Delivered("false");
                    db.set_PNC_message_status(a_mother.getMotherRowPrimaryKey(),"false");
                }
                allGroupMap.get(PNC).add(a_mother); //


            }
                if (a_mother.getAgeOfChild() < 331 && a_mother.getAgeOfChild() >= 0) {/// ============  1 to 330 days
                    if (!allGroupMap.containsKey(CHILD_CARE_MESSAGE_STATUS)) {  //
                        allGroupMap.put(CHILD_CARE_MESSAGE_STATUS, childCareMessageStatusList); //
                    }

                    allGroupMap.get(CHILD_CARE_MESSAGE_STATUS).add(a_mother); //


                }

        }
    }



    // ------------------- getting each ob from allGroupMap map
    public String allString() {
        String all = "", temp = "";


        for (Map.Entry<String, List<Mother>> entry : allGroupMap.entrySet()) {
            String key = entry.getKey();
            List<Mother> values = entry.getValue();
            for (Mother theMother : values) {
                temp = " name: " + theMother.getMotherName() + "  Date " + theMother.getLastMenstruationDate() + "  days  " + theMother.getDaysOnPregnancy();
            }
            all += "Key = " + key + "  Values = " + temp + "\n";
        }


        return all;
    }
    public void setEDD( Mother mother) {
        String lmp = mother.getLastMenstruationDate();//=====================
        DateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calender = Calendar.getInstance();

        Date lmpDate = null;
        try {
            lmpDate = formate.parse(lmp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calender.setTime(lmpDate);
        calender.add(Calendar.DATE, + 280);
        Date probableEDD = calender.getTime();
        //===========================


      mother.setMotherEDD(formate.format(probableEDD));
    }






}
