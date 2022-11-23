package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.Category;
import com.josemeurer.DeliveryKing.entities.Product;

import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters")
    @NotBlank(message = "Required field")
    private String name;

    @Positive(message = "Price must be a positive value")
    @NotNull
    private Double price;
    private String imgUrl;

    @NotBlank(message = "Required field")
    private String description;

    @NotEmpty(message = "Product needs at least one category")
    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Double price, String imgUrl, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public ProductDTO(Product entity) {
        this(entity.getId(), entity.getName(), entity.getPrice(), entity.getImgUrl(), entity.getDescription());
    }

    public ProductDTO(Product entity, Set<Category> list) {
        this(entity);
        list.forEach(x -> categories.add(new CategoryDTO(x)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }
}
