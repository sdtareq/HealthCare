package com.example.tareq.healthcare;

import java.io.Serializable;

/**
 * Created by TAREQ on 11/3/2016.
 */
public class Child implements Serializable {

    String
            childMotherName,
    childMotherTableId,
    childName,
    childDateOfBirth,
    sexOfChild,
    childBirthWeight,
    childId,
    idNumberOfChild,
    childAge;


    public Child(String childMotherName, String childMotherTableId, String childName, String childDateOfBirth, String sexOfChild, String childBirthWeight, String childId, String idNumberOfChild) {
        this.childMotherName = childMotherName;
        this.childMotherTableId = childMotherTableId;
        this.childName = childName;
        this.childDateOfBirth = childDateOfBirth;
        this.sexOfChild = sexOfChild;
        this.childBirthWeight = childBirthWeight;
        this.childId = childId;
        this.idNumberOfChild = idNumberOfChild;
    }

    public Child() {
    }

    public Child(String childName, String childDateOfBirth, String sexOfChild, String childBirthWeight, String childId, String idNumberOfChild, String childAge) {
        this.childName = childName;
        this.childDateOfBirth = childDateOfBirth;
        this.sexOfChild = sexOfChild;
        this.childBirthWeight = childBirthWeight;
        this.childId = childId;
        this.idNumberOfChild = idNumberOfChild;
        this.childAge = childAge;
    }

    public Child(String childName, String childDateOfBirth, String sexOfChild, String childBirthWeight,   String idNumberOfChild) {
        this.childName = childName;
        this.childDateOfBirth = childDateOfBirth;
        this.sexOfChild = sexOfChild;
        this.childBirthWeight = childBirthWeight;

        this.idNumberOfChild = idNumberOfChild;
    }

    public Child(String childMotherName, String childName, String childDateOfBirth, String sexOfChild, String childBirthWeight, String idNumberOfChild) {
        this.childMotherName = childMotherName;
        this.childName = childName;
        this.childDateOfBirth = childDateOfBirth;
        this.sexOfChild = sexOfChild;
        this.childBirthWeight = childBirthWeight;
        this.idNumberOfChild = idNumberOfChild;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildDateOfBirth() {
        return childDateOfBirth;
    }

    public void setChildDateOfBirth(String childDateOfBirth) {
        this.childDateOfBirth = childDateOfBirth;
    }

    public String getSexOfChild() {
        return sexOfChild;
    }

    public void setSexOfChild(String sexOfChild) {
        this.sexOfChild = sexOfChild;
    }

    public String getChildBirthWeight() {
        return childBirthWeight;
    }

    public void setChildBirthWeight(String childBirthWeight) {
        this.childBirthWeight = childBirthWeight;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getIdNumberOfChild() {
        return idNumberOfChild;
    }

    public void setIdNumberOfChild(String idNumberOfChild) {
        this.idNumberOfChild = idNumberOfChild;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public String getChildMotherName() {
        return childMotherName;
    }

    public void setChildMotherName(String childMotherName) {
        this.childMotherName = childMotherName;
    }

    public String getChildMotherTableId() {
        return childMotherTableId;
    }

    public void setChildMotherTableId(String childMotherTableId) {
        this.childMotherTableId = childMotherTableId;
    }
}
