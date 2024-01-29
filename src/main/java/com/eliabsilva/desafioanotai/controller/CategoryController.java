package com.eliabsilva.desafioanotai.controller;

import com.eliabsilva.desafioanotai.domain.category.Category;
import com.eliabsilva.desafioanotai.domain.category.CategoryDTO;
import com.eliabsilva.desafioanotai.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @PostMapping
    public ResponseEntity<Category> store(@RequestBody CategoryDTO categoryData){
        Category result =  this.service.store(categoryData);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> result = this.service.getAll();
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData){

        Category result =  this.service.update(id,categoryData);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getById(@PathVariable("id") String id){

        Optional<Category> result =  this.service.getById(id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id){

        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
