package com.example.tareq.healthcare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by TAREQ on 10/25/2016.
 */
public class Mother {

    String  motherName,lastMenstruationDate ,isPregnant  ,isMessageDelivered  , isChildBorn  , childBirthday, motherRowPrimaryKey ;
    String runningHealthService;
    List<String> numberOfHealthServicesReceived  = new ArrayList<>();
    int daysOnPregnancy, ageOfChild;


    public Mother(String motherName, String lastMenstruationDate) {
        this.motherName = motherName;
        this.lastMenstruationDate = lastMenstruationDate;

    }

    public Mother(String motherName, String lastMenstruationDate, String isPregnant, String isChildBorn, String childBirthday, String isMessageDelivered) {
        this.motherName = motherName;
        this.lastMenstruationDate = lastMenstruationDate;
        this.isChildBorn = isChildBorn;
        this.childBirthday = childBirthday;
        this.isMessageDelivered = isMessageDelivered;
        this.isPregnant = isPregnant;
    }

    public Mother(String motherName, String lastMenstruationDate, String isMessageDelivered) {
        this.motherName = motherName;
        this.lastMenstruationDate = lastMenstruationDate;
        this.isMessageDelivered = isMessageDelivered;
    }




    /*Comparator for sorting the list by Mother running health service */
    public static Comparator<Mother> motherComparator = new Comparator<Mother>() {

        @Override
        public int compare(Mother lhs, Mother rhs) {

            String runningHealthService1 = lhs.getRunningHealthService().toUpperCase();
            String runningHealthService2 = rhs.getRunningHealthService().toUpperCase();

            //ascending order
            return runningHealthService1.compareTo(runningHealthService2);

            //descending order
            //return StudentName2.compareTo(StudentName1);

        }
      };

    /*Comparator for sorting the list by Mother name */
    public static Comparator<Mother> motherNameComparator = new Comparator<Mother>() {

        @Override
        public int compare(Mother lhs, Mother rhs) {

            String name1 = lhs.getMotherName().toUpperCase();
            String name2 = rhs.getMotherName().toUpperCase();

            //ascending order
            return name1.compareTo(name2);

            //descending order
            //return StudentName2.compareTo(StudentName1);

        }
    };


    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getLastMenstruationDate() {
        return lastMenstruationDate;
    }

    public void setLastMenstruationDate(String lastMenstruationDate) {
        this.lastMenstruationDate = lastMenstruationDate;
    }

    public int getDaysOnPregnancy() {
        return daysOnPregnancy;
    }

    public void setDaysOnPregnancy(int daysOnPregnancy) {
        this.daysOnPregnancy = daysOnPregnancy;
    }
    public String getIsMessageDelivered() {
        return isMessageDelivered;
    }

    public void setIsMessageDelivered(String isMessageDelivered) {
        this.isMessageDelivered = isMessageDelivered;
    }

    public String getIsChildBorn() {
        return isChildBorn;
    }

    public void setIsChildBorn(String isChildBorn) {
        this.isChildBorn = isChildBorn;
    }

    public String getChildBirthday() {
        return childBirthday;
    }

    public void setChildBirthday(String childBirthday) {
        this.childBirthday = childBirthday;
    }

    public String getIsPregnant() {
        return isPregnant;
    }

    public void setIsPregnant(String isPregnant) {
        this.isPregnant = isPregnant;
    }

    public int getAgeOfChild() {
        return ageOfChild;
    }

    public void setAgeOfChild(int ageOfChild) {
        this.ageOfChild = ageOfChild;
    }

    public String getRunningHealthService() {
        return runningHealthService;
    }

    public void setRunningHealthService(String runningHealthService) {
        this.runningHealthService = runningHealthService;
    }

    public List<String> getNumberOfHealthServicesReceived() {
        return numberOfHealthServicesReceived;
    }

    public void setNumberOfHealthServicesReceived(List<String> numberOfHealthServicesReceived) {
        this.numberOfHealthServicesReceived = numberOfHealthServicesReceived;
    }

    public String getMotherRowPrimaryKey() {
        return motherRowPrimaryKey;
    }

    public void setMotherRowPrimaryKey(String motherRowPrimaryKey) {
        this.motherRowPrimaryKey = motherRowPrimaryKey;
    }
}
