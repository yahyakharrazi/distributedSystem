package com.sid.billservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductItem {

    @Id
    private Long id;

    private int quantity;

    private long productId;

//    @Transient
//    private Product product;

    private double price;

    @ManyToOne
    private Bill bill;

}
