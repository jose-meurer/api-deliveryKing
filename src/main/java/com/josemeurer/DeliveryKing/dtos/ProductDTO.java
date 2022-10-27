package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.Category;
import com.josemeurer.DeliveryKing.entities.Product;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;
    private String description;

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

    public ProductDTO(Long id, String name, Double price, String imgUrl, String description, Set<CategoryDTO> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.categories = categories;
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
