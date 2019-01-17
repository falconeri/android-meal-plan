package com.atosah.falconery.indonesiamealplansfordiet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;


public class Menu implements Parcelable {

    private String name;
    private String shortDesc;
    private String type;
    private String calorie;
    private Macros macros;
    private List<String> images = new ArrayList<>();
    private List<String> ingredients = new ArrayList<>();
    private List<String> steps = new ArrayList<>();
    public final static Parcelable.Creator<Menu> CREATOR = new Creator<Menu>() {
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        public Menu[] newArray(int size) {
            return (new Menu[size]);
        }

    };

    protected Menu(Parcel in) {
        this.name = in.readString();
        this.shortDesc = in.readString();
        this.type = in.readString();
        this.calorie = in.readString();
        this.macros = ((Macros) in.readValue((Macros.class.getClassLoader())));
        in.readList(this.images, (java.lang.String.class.getClassLoader()));
        in.readList(this.ingredients, (java.lang.String.class.getClassLoader()));
        in.readList(this.steps, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Menu() {
    }

    public Menu(String name, String shortDesc, String type, String calorie, Macros macros, List<String> images, List<String> ingredients, List<String> steps) {
        super();
        this.name = name;
        this.shortDesc = shortDesc;
        this.type = type;
        this.calorie = calorie;
        this.macros = macros;
        this.images = images;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu withName(String name) {
        this.name = name;
        return this;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Menu withShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Menu withType(String type) {
        this.type = type;
        return this;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public Menu withCalorie(String calorie) {
        this.calorie = calorie;
        return this;
    }

    public Macros getMacros() {
        return macros;
    }

    public void setMacros(Macros macros) {
        this.macros = macros;
    }

    public Menu withMacros(Macros macros) {
        this.macros = macros;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Menu withImages(List<String> images) {
        this.images = images;
        return this;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Menu withIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public Menu withSteps(List<String> steps) {
        this.steps = steps;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(shortDesc);
        dest.writeString(type);
        dest.writeString(calorie);
        dest.writeValue(macros);
        dest.writeList(images);
        dest.writeList(ingredients);
        dest.writeList(steps);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", type='" + type + '\'' +
                ", calorie='" + calorie + '\'' +
                ", macros=" + macros +
                ", images=" + images +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
