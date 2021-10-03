package com.demo.domain.models.service;

import java.time.LocalDate;
import java.util.Date;

public class EmployeeServiceModel {



    private String id;
    private String firstName;
    private String lastName;
    private Integer egn;
    private String position;
    private String email;
    private LocalDate startData;
    private String address;


    public EmployeeServiceModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getEgn() {
        return egn;
    }

    public void setEgn(Integer egn) {
        this.egn = egn;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartData() {
        return startData;
    }

    public void setStartData(LocalDate startData) {
        this.startData = startData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}