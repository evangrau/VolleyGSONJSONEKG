package com.example.volleygsonjsonekg;

public class GameCompanyModel {
    // model items
    private String name;
    private int year;
    private String recentConsole;

    // initializer
    public GameCompanyModel(final String name, final int year, final String recentConsole) {
        setName(name);
        setYear(year);
        setRecentConsole(recentConsole);
    }

    // getters
    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getRecentConsole() {
        return recentConsole;
    }

    // setters
    public void setName(final String name) {
        this.name = name;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public void setRecentConsole(final String recentConsole) {
        this.recentConsole = recentConsole;
    }
}
