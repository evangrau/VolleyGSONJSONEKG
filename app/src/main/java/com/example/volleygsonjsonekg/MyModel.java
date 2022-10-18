package com.example.volleygsonjsonekg;

import com.google.gson.annotations.SerializedName;

public class MyModel {
    // model items
    @SerializedName("name")
    private String mName;
    @SerializedName("year")
    private int mYear;
    @SerializedName("description")
    private String mDescription;

    // initializer
    public MyModel(final String name, final int year, final String description) {
        setName(name);
        setYear(year);
        setDescription(description);
    }

    // getters
    public String getName() {
        return mName;
    }

    public int getYear() {
        return mYear;
    }

    public String getDescription() {
        return mDescription;
    }

    // setters
    public void setName(final String name) {
        this.mName = name;
    }

    public void setYear (final int year) {
        this.mYear = year;
    }

    public void setDescription(final String description) {
        this.mDescription = description;
    }
}
