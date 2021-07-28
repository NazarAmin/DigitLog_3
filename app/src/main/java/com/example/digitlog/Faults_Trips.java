package com.example.digitlog;

public class Faults_Trips {
    String category, datetime, urgency, user_2, comment, image_name;

    public Faults_Trips(String category, String urgency, String user_2, String comment,String datetime, String image_name) {
        this.urgency = urgency;
        this.user_2 = user_2;
        this.comment = comment;
        this.category = category;
        this.datetime = datetime;
        this.image_name = image_name;

    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Faults_Trips() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
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

}

