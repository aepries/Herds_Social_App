package com.example.aaronpries.herds_social_app;

/**
 * Created by aaronpries on 3/6/17.
 */

public class ModelAddGroup {

    public String name;
    public String image;
    public String bio;

    public ModelAddGroup(String name, String image, String bio) {
        this.name = name;
        this.image = image;
        this.bio = bio;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
