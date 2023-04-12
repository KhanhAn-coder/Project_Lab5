package com.example.a51900475_lab5;

public class Phones {
    private int icon;
    private String name;
    private boolean check;



    public Phones(int icon, String name, boolean check){
        this.icon = icon;
        this.name = name;
        this.check = check;
    }

    public int getIcon(){
        return this.icon;
    }
    public void setIcon(int icon){
        this.icon = icon;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean isChecked(){
        return this.check;
    }
    public void setCheck(boolean check){
        this.check = check;
    }
}
