package com.eliabsilva.desafioanotai.services;

import com.eliabsilva.desafioanotai.domain.aws.MessageDTO;
import com.eliabsilva.desafioanotai.domain.category.Category;
import com.eliabsilva.desafioanotai.domain.category.exceptions.CategoryNotFoundException;
import com.eliabsilva.desafioanotai.domain.product.Product;
import com.eliabsilva.desafioanotai.domain.product.ProductDTO;
import com.eliabsilva.desafioanotai.domain.product.exceptions.ProductNotFoundException;
import com.eliabsilva.desafioanotai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

private final CategoryService categoryService;
private final AwsSnsService snsService;

    public ProductService(ProductRepository repository, CategoryService categoryService, AwsSnsService snsService){
        this.repository = repository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }

    public Product store(ProductDTO productData) throws ClassNotFoundException {
        this.categoryService.getById(productData.categoryId()).orElseThrow(ClassNotFoundException::new);
        Product newProduct = new Product(productData);
        this.repository.save(newProduct);
        this.snsService.publish(new MessageDTO(newProduct.toString()));
        return newProduct;
    }
    public List<Product> getAll(){
        return this.repository.findAll();
    }
    public Product update(String id, ProductDTO productData){


            Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
       if(productData.categoryId()!=null) {
            this.categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
           product.setCategoryId(productData.categoryId());
       }
        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());

        if(!(productData.price()==null)) product.setPrice(productData.price());
        this.repository.save(product);

        this.snsService.publish(new MessageDTO(product.toString()));
        return product;
    }

    public void delete(String id){
        Product Product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        this.repository.delete(Product);

    }

    public Product getById(String id){
        return this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
