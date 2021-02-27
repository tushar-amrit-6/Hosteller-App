package com.example.hostlerapp;

public class BookingsRvModel {
    private String token;
    private String name;
    private String hostel_no;
    private String text_date;
    private int slot_id;
    private Boolean checked_in;

    public BookingsRvModel(String token, String name, String hostel_no, String text_date,int slot_id) {
        this.token = token;
        this.name = name;
        this.hostel_no = hostel_no;
        this.text_date = text_date;
        this.slot_id=slot_id;
        checked_in=false;
    }

    public BookingsRvModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostel_no() {
        return hostel_no;
    }

    public void setHostel_no(String hostel_no) {
        this.hostel_no = hostel_no;
    }

    public String getText_date() {
        return text_date;
    }

    public void setText_date(String text_date) {
        this.text_date = text_date;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public Boolean getChecked_in() {
        return checked_in;
    }

    public void setChecked_in(Boolean checked_in) {
        this.checked_in = checked_in;
    }
}
