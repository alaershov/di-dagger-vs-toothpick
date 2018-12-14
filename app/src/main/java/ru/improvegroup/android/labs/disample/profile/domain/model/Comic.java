package ru.improvegroup.android.labs.disample.profile.domain.model;

public final class Comic {

    private final int number;
    private final String imageUrl;
    private final String title;
    private final String comment;

    public Comic(int number, String imageUrl, String title, String comment) {
        this.number = number;
        this.imageUrl = imageUrl;
        this.title = title;
        this.comment = comment;
    }

    public int getNumber() {
        return number;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "number=" + number +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
