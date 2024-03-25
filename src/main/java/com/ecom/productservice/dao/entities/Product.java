package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_date_of_sale")
    private LocalDate startDateOfSale;

    @Column(name = "end_date_of_sale")
    private LocalDate endDateOfSale;
    
    @ManyToMany
    @JoinTable(name = "CATEGORY_PRODUCT_MAP",
            joinColumns =
            @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    )
    // TODO: 17.03.2024 fix it abd create category map sql table
    private List<Category> category;
    @PrePersist
    void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

//    @Convert(converter = HashMapConverter.class)
//    private Map<String, String> productAttributes;

    public Product(
            String name,
            String description,
            LocalDate startDateOfSale,
            LocalDate endDateOfSale) {
        this.name = name;
        this.description = description;
        this.startDateOfSale = startDateOfSale;
        this.endDateOfSale =endDateOfSale;
    }
}
