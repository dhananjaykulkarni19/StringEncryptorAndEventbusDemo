package com.eventbusdemo;

/**
 * Created by admin on 12/13/2015.
 */
public class User {

    String name;
    String company;

    public User(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }
}
