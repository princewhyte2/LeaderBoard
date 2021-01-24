package com.example.leaderboard;

import com.google.gson.annotations.SerializedName;

public class SkillClass {
    // Member variables representing the title and information about the sport.
    @SerializedName("name")
    private String name;

    @SerializedName("score")
    private int score;

    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String imageResource;


    public SkillClass(String name, int score, String country, String imageResource) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
}
