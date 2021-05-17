package com.example.digitlog;

public class Handover_c {

    String user, PIC, datetime, description;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Handover_c() {
    }

    public Handover_c(String user, String PIC, String datetime, String description) {
        this.user = user;
        this.PIC = PIC;
        this.datetime = datetime;
        this.description = description;
    }

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
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


}
