package com.example.aaronpries.herds_social_app;

/**
 * Created by aaronpries on 2/6/17.
 */

public class ModelClass {
    public String title;
    public String image;
    public String name;
    public String bio;
    public String info;
    //public String shortbio;
    public String category;
    public String date;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String time;
    public String location;
    public String group;


//CONSTRUCTOR
    public ModelClass(String image, String title, String name, String bio, String category, String date, String time, String location, String group, String info) {
        this.image = image;
        this.title = title;
        this.name = name;
        this.bio = bio;
        this.info = info;
        this.category = category;
        this.date = date;
        this.time = time;
        this.location = location;
        this.group = group;
    }


//BLANK CONSTRUCTOR
    public ModelClass() {
    }


//GETTERS
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public String getName() {return name;}
    public String getInfo() {return info;}
    public String getBio() {return bio;}
    //public String getShortBio() {return shortbio;}
    public String getCategory() {return category;}
    public String getDate() {return date;}

//SETTERS
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setInfo(String info) {this.info = info;}
    //public void setShortBio(String shortbio) {this.shortbio = shortbio;}
    public void setCategory(String category) {this.category = category;}
    public void setDate(String date) {this.date = date;}
}