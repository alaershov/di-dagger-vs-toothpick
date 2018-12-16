package ru.improvegroup.android.labs.disample.comic.data;

import com.google.gson.annotations.SerializedName;

public final class ComicApiModel {

    @SerializedName("num")
    private int num;

    @SerializedName("day")
    private String day;
    @SerializedName("month")
    private String month;
    @SerializedName("year")
    private String year;

    @SerializedName("safe_title")
    private String safe_title;
    @SerializedName("title")
    private String title;

    @SerializedName("alt")
    private String alt;
    @SerializedName("img")
    private String img;

    public int getNum() {
        return num;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getSafe_title() {
        return safe_title;
    }

    public String getTitle() {
        return title;
    }

    public String getAlt() {
        return alt;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "ComicApiModel{" +
                "num=" + num +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", safe_title='" + safe_title + '\'' +
                ", title='" + title + '\'' +
                ", alt='" + alt + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
