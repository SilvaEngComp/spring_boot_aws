package com.eliabsilva.desafioanotai.domain.product;

import com.eliabsilva.desafioanotai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String categoryId;

    public Product(ProductDTO productData){
        this.title = productData.title();
        this.description = productData.description();
        this.ownerId = productData.ownerId();
        this.price = productData.price();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();

        json.put("id",this.id);
        json.put("title",this.title);
        json.put("description",this.description);
        json.put("ownerId",this.ownerId);
        json.put("price",this.price);
        json.put("categoryId",this.categoryId);
        json.put("type","product");

        return json.toString();

    }
}
