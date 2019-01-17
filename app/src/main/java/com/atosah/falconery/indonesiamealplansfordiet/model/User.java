package com.atosah.falconery.indonesiamealplansfordiet.model;

public class User {
    private int targetId;
    private double targetWeight;
    private int gender;
    private int targetLevel;
    private int currentWeight;
    private int currentHeight;
    private int age;
    private int targetPlan;
    private int bmr;

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(int targetLevel) {
        this.targetLevel = targetLevel;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTargetPlan() {
        return targetPlan;
    }

    public void setTargetPlan(int targetPlan) {
        this.targetPlan = targetPlan;
    }

    public int getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    @Override
    public String toString() {
        return "User{" +
                "targetId=" + targetId +
                ", targetWeight=" + targetWeight +
                ", gender=" + gender +
                ", targetLevel=" + targetLevel +
                ", currentWeight=" + currentWeight +
                ", currentHeight=" + currentHeight +
                ", age=" + age +
                ", targetPlan=" + targetPlan +
                ", bmr=" + bmr +
                '}';
    }
}
