package com.example.digitlog;

public class Trip_Class {

    String load, datetime, fuel, user_2, comment, alarms;

    public Trip_Class(String load, String fuel, String user_2, String comment,String datetime, String alarms) {
        this.load = load;
        this.user_2 = user_2;
        this.comment = comment;
        this.fuel = fuel;
        this.datetime = datetime;
        this.alarms = alarms;
    }

    public Trip_Class() {
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getUser_2() {
        return user_2;
    }

    public void setUser_2(String user_2) {
        this.user_2 = user_2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAlarms() {
        return alarms;
    }

    public void setAlarms(String alarms) {
        this.alarms = alarms;
    }
}

