package com.vehicleregistrationsystem.finalcase.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vehicleregistrationsystem.finalcase.model.enums.Cities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Cities city;

    @Temporal(value = TemporalType.DATE)
    private Date accountCreationDate;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Vehicle> vehicles;

    public User(String userName, String password, String firstName, String lastName, Cities city, Date accountCreationDate, List<Vehicle> vehicles) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.accountCreationDate = accountCreationDate;
        this.vehicles = vehicles;
    }
}
