package com.roumaysae.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import  lombok.Builder;
import  lombok.Data;
import  lombok.AllArgsConstructor;
import  lombok.NoArgsConstructor;


@Entity @AllArgsConstructor @NoArgsConstructor @Builder
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;

}
