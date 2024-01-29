package com.eliabsilva.desafioanotai.repositories;

import com.eliabsilva.desafioanotai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
