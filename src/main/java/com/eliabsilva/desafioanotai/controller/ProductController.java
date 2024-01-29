package com.eliabsilva.desafioanotai.controller;


import com.eliabsilva.desafioanotai.domain.product.Product;
import com.eliabsilva.desafioanotai.domain.product.ProductDTO;
import com.eliabsilva.desafioanotai.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> store(@RequestBody ProductDTO productData) throws ClassNotFoundException {
        Product result =  this.service.store(productData);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> result = this.service.getAll();
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData){

        Product result =  this.service.update(id,productData);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") String id){

        Product result =  this.service.getById(id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id){

        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
