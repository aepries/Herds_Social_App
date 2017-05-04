package com.example.aaronpries.herds_social_app;

/**
 * Created by aaronpries on 5/4/17.
 */

public class ModelLogged {

    public String image;
    public String name;
    public String bio;
    public String age;

    public ModelLogged() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ModelLogged(String image, String name, String bio, String age) {

        this.image = image;
        this.name = name;
        this.bio = bio;
        this.age = age;
    }
}





