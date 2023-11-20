package com.example.darkstore;

public class Globals1 {

    private static Globals1 instance = new Globals1();

    // Getter-Setters
    public static Globals1 getInstance() {
        return instance;
    }

    public static void setInstance(Globals1 instance) {
        Globals1.instance = instance;
    }

    private String notification_index;


    private Globals1() {

    }


    public String getValue() {
        return notification_index;
    }


    public void setValue(String notification_index) {
        this.notification_index = notification_index;
    }
}
