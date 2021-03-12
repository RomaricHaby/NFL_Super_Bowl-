package com.example.miniprojet.Model;

import android.content.Intent;

import java.io.Serializable;

public class TeamHelmet implements Serializable {
    private String name;
    private Integer helmet;
    private String color;

    public TeamHelmet(String name, Integer helmet, String color) {
        this.name = name;
        this.helmet = helmet;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHelmet() {
        return helmet;
    }

    public void setHelmet(Integer helmet) {
        this.helmet = helmet;
    }



    @Override
    public String toString() {
        return "TeamHelmet{" +
                "name='" + name + '\'' +
                ", helmet=" + helmet +
                '}';
    }
}
