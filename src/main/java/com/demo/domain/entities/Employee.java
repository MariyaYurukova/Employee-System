package com.demo.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name= "employees")
public class Employee extends BaseEntity{

    private String firstName;
    private String lastName;
    private Integer egn;
    private String position;
    private String email;
    private LocalDate startData;
    private String address;


    public Employee() {
    }


    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EGN")
    public Integer getEgn() {
        return egn;
    }

    public void setEgn(Integer egn) {
        this.egn = egn;
    }


    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "start_work_date")
    public LocalDate getStartData() {

        return startData;
    }

    public void setStartData(LocalDate startData) {
        this.startData = startData;
    }





    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
