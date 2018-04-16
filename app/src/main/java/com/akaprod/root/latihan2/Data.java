package com.akaprod.root.latihan2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Data {
    public int id_fruit;
    public String image;
    public String title;
    public String description;
    public String created_at;
    public String updated_at;

    public Data() {
        this.id_fruit = id_fruit;
        this.image = image;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    //id_fruit
    public int getId_fruit() {
        return id_fruit;
    }

    public void setId_fruit(int id_fruit) {
        this.id_fruit = id_fruit;
    }

    //image

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //created
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    //updated
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}