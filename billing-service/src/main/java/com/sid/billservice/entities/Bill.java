package com.sid.billservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill  {

    @Id
    private Long id;

    private Date date;

    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;

    private long customerId;

//    @Transient
//    private  Customer customer;
}
