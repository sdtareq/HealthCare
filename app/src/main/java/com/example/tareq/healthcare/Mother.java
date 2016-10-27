package com.example.tareq.healthcare;

/**
 * Created by TAREQ on 10/25/2016.
 */
public class Mother {

    String  motherName,lastMenstruationDate ,isPregnant  ,isMessageDelivered  , isChildBorn  , childBirthday ;
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
}
