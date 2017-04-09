package com.example.aaronpries.herds_social_app;

/**
 * Created by aaronpries on 4/8/17.
 */

public class ModelAddUser {


    public String name;
    public String image;
    public String groups;
    public String id;
    public String events;




//REQUIRED BLANK CONSTRUCTOR
//    public ModelAddEvent() {
//
//    }

    public ModelAddUser(String name, String image, String id) {
        this.name = name;
        this.image = image;
//        this.groups = groups;
        this.id = id;
//        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
//
//    public String getGroups() {
//        return groups;
//    }
//
//    public void setGroups(String groups) {
//        this.groups = groups;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getEvents() {
//        return events;
//    }
//
//    public void setEvents(String events) {
//        this.events = events;
//    }
}



