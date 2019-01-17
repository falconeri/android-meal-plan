package com.atosah.falconery.indonesiamealplansfordiet.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Plan implements Parcelable {
    private String id;
    private String title;
    private List<Menu> menus = new ArrayList<>();

    public final static Parcelable.Creator<Plan> CREATOR = new Creator<Plan>() {
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        public Plan[] newArray(int size) {
            return (new Plan[size]);
        }
    };

    private Plan(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.menus = new ArrayList<>();
        in.readList(this.menus, (Plan.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Plan() {
    }

    /**
     * @param id
     * @param menus
     * @param title
     */
    public Plan(String id, String title, List<Menu> menus) {
        super();
        this.id = id;
        this.title = title;
        this.menus = menus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Plan withId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Plan withTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Plan withMenus(List<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeList(menus);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", menus=" + menus +
                '}';
    }
}
