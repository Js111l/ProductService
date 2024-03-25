package com.ecom.productservice.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class SellerModel {
    String name;
    String nip;
    LocationModel locationModel;
    LocalDate createdAt;
    private String bdo;
    private Map<String, String> additionalSellerAttributes;
    List<RatingModel> sellerRatings;
}
