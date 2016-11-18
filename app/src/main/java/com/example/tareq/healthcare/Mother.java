package com.example.tareq.healthcare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by TAREQ on 10/25/2016.
 */
public class Mother implements Serializable {
    String motherRowPrimaryKey;
//  MOTHER
    String motherName;
    String husbandName;
    String motherAge;
    String motherPhoneNumber;
    String desiredCallingTime;
    String motherAddress;
    String GIS_Location;
    String alternativePhoneNumber;
    String alternativePhoneOwnerName;
    String DHIS_ID;
    String lastMenstruationDate;
    String pregnancyState;
    String deliveryDate;
    String syncStatus;
    String timeStamp;




    String isANC_1_Message_Delivered;
    String isANC_2_Message_Delivered;
    String isANC_3_Message_Delivered;
    String isANC_4_Message_Delivered;
    String isPreDelivery_Message_Delivered;
    String isPNC_Message_Delivered;
    String isChild_message_delivered_0_to_14_days;
    String isChild_message_delivered_1_2_3_month;
    String isChild_message_delivered_6_to_8_month;
    String isChild_message_delivered_9_to_12_month;

    String isMotherDead;
    String motherEDD;







    String isMessageDelivered;
    String isChildMessageDelivered;
    String isChildBorn;







    Child child;


    String runningHealthService;
    List<String> numberOfHealthServicesReceived = new ArrayList<>();
    int daysOnPregnancy, ageOfChild;


    public Mother() {

    }


    //  motherRagister
    public Mother(String motherName, String husbandName, String motherPhoneNumber, String motherAge, String desiredCallingTime, String motherAddress,
                  String GIS_Location, String alternativePhoneNumber, String alternativePhoneOwnerName, String DHIS_ID, String lastMenstruationDate, String pregnancyState) {
        this.motherName = motherName;
        this.husbandName = husbandName;
        this.motherPhoneNumber = motherPhoneNumber;
        this.motherAge = motherAge;
        this.desiredCallingTime = desiredCallingTime;
        this.motherAddress = motherAddress;
        this.GIS_Location = GIS_Location;
        this.alternativePhoneNumber = alternativePhoneNumber;
        this.alternativePhoneOwnerName = alternativePhoneOwnerName;
        this.DHIS_ID = DHIS_ID;
        this.lastMenstruationDate = lastMenstruationDate;
        this.pregnancyState = pregnancyState;
    }


    //motherRegistration with child
    public Mother(String motherName, String husbandName, String motherPhoneNumber, String motherAge, String desiredCallingTime, String motherAddress,
                  String GIS_Location, String alternativePhoneNumber, String alternativePhoneOwnerName, String DHIS_ID, String lastMenstruationDate, String pregnancyState,Child child) {
        this(motherName,husbandName,motherPhoneNumber,motherAge,desiredCallingTime,motherAddress,GIS_Location,alternativePhoneNumber,alternativePhoneOwnerName,DHIS_ID,lastMenstruationDate,pregnancyState);
        this.child = child;
    }

    // getAllMother

    public Mother(String motherRowPrimaryKey, String motherName, String husbandName, String motherAge, String motherPhoneNumber, String desiredCallingTime, String motherAddress,
                  String GIS_Location, String alternativePhoneOwnerName, String alternativePhoneNumber, String DHIS_ID, String lastMenstruationDate, String pregnancyState,
                  String deliveryDate, String isANC_1_Message_Delivered, String isANC_2_Message_Delivered, String isANC_3_Message_Delivered, String isANC_4_Message_Delivered,
                  String isPreDelivery_Message_Delivered, String isPNC_Message_Delivered, String isChild_message_delivered_0_to_14_days, String isChild_message_delivered_1_2_3_month,
                  String isChild_message_delivered_6_to_8_month, String isChild_message_delivered_9_to_12_month, String isMotherDead) {
        this.motherRowPrimaryKey = motherRowPrimaryKey;
        this.motherName = motherName;
        this.husbandName = husbandName;
        this.motherAge = motherAge;
        this.motherPhoneNumber = motherPhoneNumber;
        this.desiredCallingTime = desiredCallingTime;
        this.motherAddress = motherAddress;
        this.GIS_Location = GIS_Location;
        this.alternativePhoneOwnerName = alternativePhoneOwnerName;
        this.alternativePhoneNumber = alternativePhoneNumber;
        this.DHIS_ID = DHIS_ID;
        this.lastMenstruationDate = lastMenstruationDate;
        this.pregnancyState = pregnancyState;
        this.deliveryDate = deliveryDate;
        this.isANC_1_Message_Delivered = isANC_1_Message_Delivered;
        this.isANC_2_Message_Delivered = isANC_2_Message_Delivered;
        this.isANC_3_Message_Delivered = isANC_3_Message_Delivered;
        this.isANC_4_Message_Delivered = isANC_4_Message_Delivered;
        this.isPreDelivery_Message_Delivered = isPreDelivery_Message_Delivered;
        this.isPNC_Message_Delivered = isPNC_Message_Delivered;
        this.isChild_message_delivered_0_to_14_days = isChild_message_delivered_0_to_14_days;
        this.isChild_message_delivered_1_2_3_month = isChild_message_delivered_1_2_3_month;
        this.isChild_message_delivered_6_to_8_month = isChild_message_delivered_6_to_8_month;
        this.isChild_message_delivered_9_to_12_month = isChild_message_delivered_9_to_12_month;
        this.isMotherDead = isMotherDead;
    }




    public Mother(String motherName, String lastMenstruationDate) {
        this.motherName = motherName;
        this.lastMenstruationDate = lastMenstruationDate;

    }

    public Mother(String motherName, String deliveryDate, String motherPhoneNumber, String motherAge, String pregnancyState, Child child, String motherAddress) {
        this.motherName = motherName;
        this.deliveryDate = deliveryDate;
        this.motherPhoneNumber = motherPhoneNumber;
        this.motherAge = motherAge;
        this.pregnancyState = pregnancyState;
        this.child = child;
        this.motherAddress = motherAddress;
    }

    public Mother(String motherName, String lastMenstruationDate, String isMessageDelivered, String deliveryDate, String motherRowPrimaryKey, String motherPhoneNumber, String motherAge, String pregnancyState, Child child, String motherAddress) {
        this.motherName = motherName;
        this.lastMenstruationDate = lastMenstruationDate;
        this.isMessageDelivered = isMessageDelivered;
        this.deliveryDate = deliveryDate;
        this.motherRowPrimaryKey = motherRowPrimaryKey;
        this.motherPhoneNumber = motherPhoneNumber;
        this.motherAge = motherAge;
        this.pregnancyState = pregnancyState;
        this.child = child;
        this.motherAddress = motherAddress;
    }


    public Mother(String motherName, String lastMenstruationDate, String pregnancyState, String isChildBorn, String deliveryDate, String isMessageDelivered) {
        this.motherName = motherName;
        this.lastMenstruationDate = lastMenstruationDate;
        this.isChildBorn = isChildBorn;
        this.deliveryDate = deliveryDate;
        this.isMessageDelivered = isMessageDelivered;
        this.pregnancyState = pregnancyState;
    }


//    public Mother(String motherName, String lastMenstruationDate, String motherAge, String motherPhoneNumber, String motherAddress, String pregnancyState) {
//        this.motherName = motherName;
//        this.lastMenstruationDate = lastMenstruationDate;
//        this.motherAge = motherAge;
//        this.motherPhoneNumber = motherPhoneNumber;
//        this.motherAddress = motherAddress;
//        this.pregnancyState = pregnancyState;
//    }

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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPregnancyState() {
        return pregnancyState;
    }

    public void setPregnancyState(String pregnancyState) {
        this.pregnancyState = pregnancyState;
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

    public String getMotherAddress() {
        return motherAddress;
    }

    public void setMotherAddress(String motherAddress) {
        this.motherAddress = motherAddress;
    }

    public String getMotherPhoneNumber() {
        return motherPhoneNumber;
    }

    public void setMotherPhoneNumber(String motherPhoneNumber) {
        this.motherPhoneNumber = motherPhoneNumber;
    }

    public String getMotherAge() {
        return motherAge;
    }

    public void setMotherAge(String motherAge) {
        this.motherAge = motherAge;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public String getIsChildMessageDelivered() {
        return isChildMessageDelivered;
    }

    public void setIsChildMessageDelivered(String isChildMessageDelivered) {
        this.isChildMessageDelivered = isChildMessageDelivered;
    }

    public String getIsPreDelivery_Message_Delivered() {
        return isPreDelivery_Message_Delivered;
    }

    public void setIsPreDelivery_Message_Delivered(String isPreDeliveryMessageDelivered) {
        this.isPreDelivery_Message_Delivered = isPreDeliveryMessageDelivered;
    }


    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    public String getDesiredCallingTime() {
        return desiredCallingTime;
    }

    public void setDesiredCallingTime(String desiredCallingTime) {
        this.desiredCallingTime = desiredCallingTime;
    }

    public String getGIS_Location() {
        return GIS_Location;
    }

    public void setGIS_Location(String GIS_Location) {
        this.GIS_Location = GIS_Location;
    }

    public String getAlternativePhoneNumber() {
        return alternativePhoneNumber;
    }

    public void setAlternativePhoneNumber(String alternativePhoneNumber) {
        this.alternativePhoneNumber = alternativePhoneNumber;
    }

    public String getAlternativePhoneOwnerName() {
        return alternativePhoneOwnerName;
    }

    public void setAlternativePhoneOwnerName(String alternativePhoneOwnerName) {
        this.alternativePhoneOwnerName = alternativePhoneOwnerName;
    }

    public String getDHIS_ID() {
        return DHIS_ID;
    }

    public void setDHIS_ID(String DHIS_ID) {
        this.DHIS_ID = DHIS_ID;
    }

    public String getIsANC_2_Message_Delivered() {
        return isANC_2_Message_Delivered;
    }

    public void setIsANC_2_Message_Delivered(String isANC_2_Message_Delivered) {
        this.isANC_2_Message_Delivered = isANC_2_Message_Delivered;
    }

    public String getIsANC_1_Message_Delivered() {
        return isANC_1_Message_Delivered;
    }

    public void setIsANC_1_Message_Delivered(String isANC_1_Message_Delivered) {
        this.isANC_1_Message_Delivered = isANC_1_Message_Delivered;
    }

    public String getIsANC_3_Message_Delivered() {
        return isANC_3_Message_Delivered;
    }

    public void setIsANC_3_Message_Delivered(String isANC_3_Message_Delivered) {
        this.isANC_3_Message_Delivered = isANC_3_Message_Delivered;
    }

    public String getIsANC_4_Message_Delivered() {
        return isANC_4_Message_Delivered;
    }

    public void setIsANC_4_Message_Delivered(String isANC_4_Message_Delivered) {
        this.isANC_4_Message_Delivered = isANC_4_Message_Delivered;
    }



    public String getIsPNC_Message_Delivered() {
        return isPNC_Message_Delivered;
    }

    public void setIsPNC_Message_Delivered(String isPNC_Message_Delivered) {
        this.isPNC_Message_Delivered = isPNC_Message_Delivered;
    }

    public String getIsChild_message_delivered_0_to_14_days() {
        return isChild_message_delivered_0_to_14_days;
    }

    public void setIsChild_message_delivered_0_to_14_days(String isChild_message_delivered_0_to_14_days) {
        this.isChild_message_delivered_0_to_14_days = isChild_message_delivered_0_to_14_days;
    }

    public String getIsChild_message_delivered_1_2_3_month() {
        return isChild_message_delivered_1_2_3_month;
    }

    public void setIsChild_message_delivered_1_2_3_month(String isChild_message_delivered_1_2_3_month) {
        this.isChild_message_delivered_1_2_3_month = isChild_message_delivered_1_2_3_month;
    }

    public String getIsChild_message_delivered_6_to_8_month() {
        return isChild_message_delivered_6_to_8_month;
    }

    public void setIsChild_message_delivered_6_to_8_month(String isChild_message_delivered_6_to_8_month) {
        this.isChild_message_delivered_6_to_8_month = isChild_message_delivered_6_to_8_month;
    }

    public String getIsChild_message_delivered_9_to_12_month() {
        return isChild_message_delivered_9_to_12_month;
    }

    public void setIsChild_message_delivered_9_to_12_month(String isChild_message_delivered_9_to_12_month) {
        this.isChild_message_delivered_9_to_12_month = isChild_message_delivered_9_to_12_month;
    }
    public String getIsMotherDead() {
        return isMotherDead;
    }

    public void setIsMotherDead(String isMotherDead) {
        this.isMotherDead = isMotherDead;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMotherEDD() {
        return motherEDD;
    }

    public void setMotherEDD(String motherEDD) {
        this.motherEDD = motherEDD;
    }
}
