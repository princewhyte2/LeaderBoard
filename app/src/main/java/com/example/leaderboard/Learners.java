package com.example.leaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Learners {

    // Member variables representing the title and information about the sport.
    @SerializedName("name")
    private String name;

    @SerializedName("hours")
    private int hours;

    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String imageResource;


    public Learners(String name, int hours, String country, String imageResource) {
        this.name = name;
        this.hours = hours;

        this.country = country;
        this.imageResource = imageResource;
    }

    String getName() { return name;}

    int getHours() {return hours;}



    String getCountry() {return country;}

    public String getImageResource() {
        return imageResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
}
