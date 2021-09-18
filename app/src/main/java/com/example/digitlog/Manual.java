package com.example.digitlog;

public class Manual {
    String  Alarm, Signal, Cause, Action;
    int Drop;
    public Manual() {
    }

    public Manual(int drop, String alarm, String signal, String cause, String action) {
        Drop = drop;
        Alarm = alarm;
        Signal = signal;
        Cause = cause;
        Action = action;
    }

    public int getDrop() {
        return Drop;
    }

    public void setDrop(int drop) {
        Drop = drop;
    }

    public String getAlarm() {
        return Alarm;
    }

    public void setAlarm(String alarm) {
        Alarm = alarm;
    }

    public String getSignal() {
        return Signal;
    }

    public void setSignal(String signal) {
        Signal = signal;
    }

    public String getCause() {
        return Cause;
    }

    public void setCause(String cause) {
        Cause = cause;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}
