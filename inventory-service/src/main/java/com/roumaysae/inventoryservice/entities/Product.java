package com.roumaysae.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity @AllArgsConstructor @NoArgsConstructor @Getter
@Setter @ToString
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;

}