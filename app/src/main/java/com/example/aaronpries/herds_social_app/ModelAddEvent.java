package com.example.aaronpries.herds_social_app;

/**
 * Created by aaronpries on 3/6/17.
 */

public class ModelAddEvent {

    public String title;
    public String image;
    public String info;
    public String category;
    public String date;
    public String time;
    public String group;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String location;

//REQUIRED BLANK CONSTRUCTOR
//    public ModelAddEvent() {
//
//    }

    public ModelAddEvent(String title, String info, String category, String date, String image, String time, String location, String group) {
        this.title = title;
        this.image = image;
        this.info = info;
        this.category = category;
        this.date = date;
        this.time = time;
        this.location = location;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
