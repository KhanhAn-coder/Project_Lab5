package com.example.lab5_ex2;

import android.widget.Switch;

public class Event {
    private String name;
    private String place;
    private String date;
    private String time;
    private boolean check;

    public Event(String name, String place, String date, String time, boolean check){
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
        this.check = check;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public String getPlace(){
        return this.place;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }
    public void setCheck(boolean check){
        this.check = check;
    }
    public boolean isChecked(){
        return this.check;
    }
}
