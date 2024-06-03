package com.rgonza14.crud_products.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rgonza14.crud_products.models.ProductModel;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Long> {

}
