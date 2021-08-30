package com.example.digitlog;

public class Mid_Night_Class_ST {
    String gen, starts, gen_mv, dem_w;

    public Mid_Night_Class_ST(String gen, String starts, String gen_mv, String dem_w) {
        this.gen = gen;
        this.starts = starts;
        this.dem_w = dem_w;
        this.gen_mv = gen_mv;
    }

    public Mid_Night_Class_ST() {
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public String getGen_mv() {
        return gen_mv;
    }

    public void setGen_mv(String gen_mv) {
        this.gen_mv = gen_mv;
    }

    public String getDem_w() {
        return dem_w;
    }

    public void setDem_w(String dem_w) {
        this.dem_w = dem_w;
    }
}