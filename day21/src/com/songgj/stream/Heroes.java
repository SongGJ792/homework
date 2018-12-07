package com.songgj.stream;

public class Heroes {
    private int id;
    private String name;
    private String location;
    private String sex;
    private int birth;
    private int death;
    private int power;

    public Heroes(int id, String name, String location, String sex, int birth, int death, int power) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.sex = sex;
        this.birth = birth;
        this.death = death;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
