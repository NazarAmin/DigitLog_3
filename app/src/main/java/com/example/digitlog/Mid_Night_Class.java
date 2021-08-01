package com.example.digitlog;

public class Mid_Night_Class {
    String gen, fuel, hp, lp, df, ds;

    public Mid_Night_Class(String gen, String fuel, String hp, String lp, String df, String ds) {
        this.gen = gen;
        this.fuel = fuel;
        this.hp = hp;
        this.lp = lp;
        this.df = df;
        this.ds = ds;
    }

    public Mid_Night_Class() {
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getLp() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }
}
