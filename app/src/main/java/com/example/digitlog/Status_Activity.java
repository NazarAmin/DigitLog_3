package com.example.digitlog;

public class Status_Activity {

    String status, datetime, comment, user_2;

    public Status_Activity(String status, String user_2, String comment, String datetime) {
        this.status = status;
        this.user_2 = user_2;
        this.comment = comment;
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser_2() {
        return user_2;
    }

    public void setUser_2(String user_2) {
        this.user_2 = user_2;
    }

    public Status_Activity() {
    }
}

