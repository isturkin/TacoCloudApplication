package com.ivanturkin.cloud.app.taco.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Street is required")
    @Column(name = "delivery_street")
    private String street;

    @NotBlank(message = "City is required")
    @Column(name = "delivery_city")
    private String city;

    @NotBlank(message = "State is required")
    @Column(name = "delivery_state")
    private String state;

    @NotBlank(message = "Zip code is required")
    @Column(name = "delivery_zip")
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String cvv;

    private Date placedDate;

    @ManyToMany(targetEntity = Taco.class)
    @JoinTable(joinColumns = {@JoinColumn(name = "order_id")},
                inverseJoinColumns = {@JoinColumn(name = "taco_id")})
    private List<Taco> tacos = new ArrayList<>();

    @PrePersist
    void placedDate() {
        this.placedDate = new Date();
    }
}
