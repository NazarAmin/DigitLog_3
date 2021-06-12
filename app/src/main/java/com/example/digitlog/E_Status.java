package com.example.digitlog;

public class E_Status {
    String user, status, datetime, description;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public E_Status() {
    }

    public E_Status(String status, String description, String user, String datetime) {
        this.user = user;
        this.status = status;
        this.description = description;
        this.datetime = datetime;
    }
}
