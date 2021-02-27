package com.example.hostlerapp;

public class Student {
    String id;
    String name;
    String scholar_no;

    String address_line1;
    String address_line2;
    String city;
    String state;
    String country;

    String hostel_no;
    String room_no;
    String email;
    String mobile_no;
    String password;

    public Student(String id, String name, String scholar_no, String address_line1, String address_line2,
                   String city, String state, String country, String hostel_no, String room_no,
                   String email, String mobile_no, String password) {
        this.id=id;
        this.name = name;
        this.scholar_no = scholar_no;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.hostel_no = hostel_no;
        this.room_no = room_no;
        this.email = email;
        this.mobile_no = mobile_no;
        this.password = password;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}