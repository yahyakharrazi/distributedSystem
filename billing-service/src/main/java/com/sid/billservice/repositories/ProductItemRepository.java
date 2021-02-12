package com.sid.billservice.repositories;

import com.sid.billservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
//    Collection<ProductItem> findByBillId(Long id);
}
