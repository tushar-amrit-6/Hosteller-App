package com.example.hostlerapp;

public class Infected {
    String name;
    String scholar_no;
    String hostel_no;
    String room_no;
    String mobile_no;
    String date_of_infection;
    String type;

    public Infected(String name, String scholar_no, String hostel_no, String room_no, String mobile_no, String date_of_infection, String type) {
        this.name = name;
        this.scholar_no = scholar_no;
        this.hostel_no = hostel_no;
        this.room_no = room_no;
        this.mobile_no = mobile_no;
        this.date_of_infection = date_of_infection;
        this.type = type;
    }

    public Infected(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScholar_no() {
        return scholar_no;
    }

    public void setScholar_no(String scholar_no) {
        this.scholar_no = scholar_no;
    }

    public String getHostel_no() {
        return hostel_no;
    }

    public void setHostel_no(String hostel_no) {
        this.hostel_no = hostel_no;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getDate_of_infection() {
        return date_of_infection;
    }

    public void setDate_of_infection(String date_of_infection) {
        this.date_of_infection = date_of_infection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
