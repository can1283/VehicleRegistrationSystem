package com.vehicleregistrationsystem.finalcase.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vehicleregistrationsystem.finalcase.enums.Cities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountCreationDate;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String mail;

    private String password;

    @Enumerated(EnumType.STRING)
    private Cities city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehicle> vehicles = new ArrayList<>();

    public User(String userName, String mail, String password, String firstName, String lastName, Cities city, String accountCreationDate, List<Vehicle> vehicles) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.accountCreationDate = accountCreationDate;
        this.vehicles = vehicles;
    }
}
