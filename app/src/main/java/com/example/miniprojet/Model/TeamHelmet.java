package com.example.miniprojet.Model;

public class TeamHelmet {
    private String name;
    private  Integer helmet;

    public TeamHelmet(String name, Integer helemt) {
        this.name = name;
        this.helmet = helemt;
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
}
