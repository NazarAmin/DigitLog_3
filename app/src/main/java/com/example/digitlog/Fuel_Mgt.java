package com.example.digitlog;

public class Fuel_Mgt {
    String krc, consum, stock, user;

    public Fuel_Mgt() {
    }

    public Fuel_Mgt(String krc, String consum, String stock, String user) {
        this.krc = krc;
        this.consum = consum;
        this.stock = stock;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKrc() {
        return krc;
    }

    public void setKrc(String krc) {
        this.krc = krc;
    }

    public String getConsum() {
        return consum;
    }

    public void setConsum(String consum) {
        this.consum = consum;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
