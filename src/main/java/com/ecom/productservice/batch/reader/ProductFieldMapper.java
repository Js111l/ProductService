package com.ecom.productservice.batch.reader;

import com.ecom.productservice.dao.entities.Product;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Configuring mapper from file reader to product entity
public class ProductFieldMapper implements FieldSetMapper<Product> {
    @Override
    public Product mapFieldSet(FieldSet fieldSet) {
        return new Product(
                fieldSet.readString(1),
                fieldSet.readString(2),
                LocalDate.parse(fieldSet.readString(3)),
                LocalDate.parse(fieldSet.readString(4))
        );
    }
}
