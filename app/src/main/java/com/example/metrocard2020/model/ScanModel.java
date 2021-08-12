package com.example.metrocard2020.model;

public class ScanModel {
    private Long id;
    private String data;
    private Boolean entry;
    private Boolean exit;

    public ScanModel(String data, Boolean entry, Boolean exit) {
        this.data = data;
        this.entry = entry;
        this.exit = exit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getEntry() {
        return entry;
    }

    public void setEntry(Boolean entry) {
        this.entry = entry;
    }

    public Boolean getExit() {
        return exit;
    }

    public void setExit(Boolean exit) {
        this.exit = exit;
    }
}
