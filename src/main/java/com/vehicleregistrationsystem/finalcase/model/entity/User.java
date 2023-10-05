package com.vehicleregistrationsystem.finalcase.model.entity;

import com.vehicleregistrationsystem.finalcase.model.enums.Cities;

import java.util.Date;

public class User {
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Cities city;
    private Date accountCreationDate;
}
