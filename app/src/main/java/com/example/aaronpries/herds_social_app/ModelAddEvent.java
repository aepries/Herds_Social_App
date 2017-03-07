package com.example.aaronpries.herds_social_app;

/**
 * Created by aaronpries on 3/6/17.
 */

public class ModelAddEvent {

    public String title;
//    public String image;
    public String info;
    public String category;
    public String date;

//REQUIRED BLANK CONSTRUCTOR
//    public ModelAddEvent() {
//
//    }

    public ModelAddEvent(String title, String info, String category, String date) {
        this.title = title;
//        this.image = image;
        this.info = info;
        this.category = category;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
