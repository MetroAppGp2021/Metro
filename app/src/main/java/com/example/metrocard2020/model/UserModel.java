package com.example.metrocard2020.model;


public class UserModel {
    private Long id;
    private String name;
    private String phone;
    private String password;
    private String national_id;
    private Double account;
    private String from_station;
    private String to_station;

    public UserModel(String name, String phone, String password, String national_id, Double account, String from_station, String to_station) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.national_id = national_id;
        this.account = account;
        this.from_station = from_station;
        this.to_station = to_station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public String getFrom_station() {
        return from_station;
    }

    public void setFrom_station(String from_station) {
        this.from_station = from_station;
    }

    public String getTo_station() {
        return to_station;
    }

    public void setTo_station(String to_station) {
        this.to_station = to_station;
    }
}
