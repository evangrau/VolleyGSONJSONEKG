package com.example.volleygsonjsonekg;

import com.google.gson.annotations.SerializedName;

public class GameCompanyModel {
    // model items
    @SerializedName("name")
    private String mName;
    @SerializedName("year")
    private int mYear;
    @SerializedName("recentConsole")
    private String mRecentConsole;

    // initializer
    public GameCompanyModel(final String name, final int year, final String recentConsole) {
        setName(name);
        setYear(year);
        setRecentConsole(recentConsole);
    }

    // getters
    public String getName() {
        return mName;
    }

    public int getYear() {
        return mYear;
    }

    public String getRecentConsole() {
        return mRecentConsole;
    }

    // setters
    public void setName(final String name) {
        this.mName = name;
    }

    public void setYear(final int year) {
        this.mYear = year;
    }

    public void setRecentConsole(final String recentConsole) {
        this.mRecentConsole = recentConsole;
    }
}
