package com.atosah.falconery.indonesiamealplansfordiet.model;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Macros implements Parcelable {

    private Double fat;
    private Double protein;
    private Double carbs;
    public final static Parcelable.Creator<Macros> CREATOR = new Creator<Macros>() {
        public Macros createFromParcel(Parcel in) {
            return new Macros(in);
        }

        public Macros[] newArray(int size) {
            return (new Macros[size]);
        }

    };

    private Macros(Parcel in) {
        this.fat = in.readDouble();
        this.protein = in.readDouble();
        this.carbs = in.readDouble();
    }

    /**
     * No args constructor for use in serialization
     */
    public Macros() {
    }

    /**
     * @param protein
     * @param fat
     * @param carbs
     */
    public Macros(Double fat, Double protein, Double carbs) {
        super();
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Macros withFat(Double fat) {
        this.fat = fat;
        return this;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Macros withProtein(Double protein) {
        this.protein = protein;
        return this;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Macros withCarbs(Double carbs) {
        this.carbs = carbs;
        return this;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(fat);
        dest.writeDouble(protein);
        dest.writeDouble(carbs);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Macros{" +
                "fat=" + fat +
                ", protein=" + protein +
                ", carbs=" + carbs +
                '}';
    }
}
