package com.example.cpm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String itemType;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties(value = { "firstName", "lastName", "emailOffice", "emailPersonal" }, allowSetters = true)
    private Customer owner;
}
