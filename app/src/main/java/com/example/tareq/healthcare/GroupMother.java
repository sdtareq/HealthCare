package com.example.tareq.healthcare;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TAREQ on 10/25/2016.
 */
public class GroupMother {

    Map<String, List<Mother>> allGroupMap = new HashMap<>();
    List<Mother> anc1 = new ArrayList<>();
    List<Mother> anc2 = new ArrayList<>();
    List<Mother> anc3 = new ArrayList<>();
    List<Mother> anc4 = new ArrayList<>();

    List<Mother> pnc1 = new ArrayList<>();
    List<Mother> pnc2 = new ArrayList<>();
    List<Mother> pnc3 = new ArrayList<>();
    List<Mother> pnc4 = new ArrayList<>();

    String groupKey = "";


    public void calcDays(Mother mother) {

        Date oldDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {

            oldDate = dateFormat.parse(mother.getChildBirthday());
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



            if (Boolean.parseBoolean(a_mother.getIsChildBorn())) {
                calcAgeOfChild(a_mother);

                if (a_mother.getAgeOfChild() < 11 && a_mother.getAgeOfChild() > 5) {
                    groupKey = "PNC 1";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, pnc1);
                    }

                    allGroupMap.get(groupKey).add(a_mother);

                } else if (a_mother.getAgeOfChild() < 16 && a_mother.getAgeOfChild() > 10) {
                    groupKey = "PNC 2";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, pnc2);
                    }

                    allGroupMap.get(groupKey).add(a_mother);

                } else if (a_mother.getAgeOfChild() < 21 && a_mother.getAgeOfChild() > 15) {
                    groupKey = "PNC 3";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, pnc3);
                    }

                    allGroupMap.get(groupKey).add(a_mother);
                } else if (a_mother.getAgeOfChild() < 27 && a_mother.getAgeOfChild() > 20) {
                    groupKey = "PNC 4";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, pnc4);
                    }

                    allGroupMap.get(groupKey).add(a_mother);
                }




            }else if (Boolean.parseBoolean(a_mother.getIsPregnant())){
                calcDays(a_mother);

                if (a_mother.getDaysOnPregnancy() < 11 && a_mother.getDaysOnPregnancy() > 5) {
                    groupKey = "ANC 1";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, anc1);
                    }

                    allGroupMap.get(groupKey).add(a_mother);

                } else if (a_mother.getDaysOnPregnancy() < 16 && a_mother.getDaysOnPregnancy() > 10) {
                    groupKey = "ANC 2";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, anc2);
                    }

                    allGroupMap.get(groupKey).add(a_mother);

                } else if (a_mother.getDaysOnPregnancy() < 21 && a_mother.getDaysOnPregnancy() > 15) {
                    groupKey = "ANC 3";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, anc3);
                    }

                    allGroupMap.get(groupKey).add(a_mother);
                } else if (a_mother.getDaysOnPregnancy() < 27 && a_mother.getDaysOnPregnancy() > 20) {
                    groupKey = "ANC 4";
                    if (!allGroupMap.containsKey(groupKey)) {
                        allGroupMap.put(groupKey, anc4);
                    }

                    allGroupMap.get(groupKey).add(a_mother);
                }



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


}
