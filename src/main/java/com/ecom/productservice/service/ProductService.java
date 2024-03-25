package com.ecom.productservice.service;

import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.dao.repository.ProductRepository;
import com.ecom.productservice.exception.ErrorKey;
import com.ecom.productservice.exception.LogicalException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@Service
public class ProductService implements BaseService<Product, Long> {

  private final ProductRepository productRepository;
  private final JobService jobService;
  public ProductService(ProductRepository productRepository, JobService jobService) {
    this.productRepository = productRepository;
    this.jobService = jobService;
  }

  public List<Product> getActiveProducts() {
    return this.productRepository.getActiveProducts();
  }

  @Override
  public List<Product> getAll() {
    return null;
  }

  @Override
  public Product getById(Long id) {
    return this.productRepository
        .findById(id)
            .orElseThrow(() -> new LogicalException(ErrorKey.NOT_FOUND));
  }

  @Override
  public Product deleteById(Long id) {
    var productToDelete = getById(id);
    this.productRepository.deleteById(productToDelete.getId());
    return productToDelete;
  }

  @Override
  public Product delete(Product productEntity) {
    this.productRepository.delete(productEntity);
    return productEntity; //// TODO: 19.09.2023
  }

  @Override
  public Product create(Product productEntity) {
    return this.productRepository.save(productEntity);
  }

  @Override
  public Product update(Product productEntity) {
    var productToUpdate = getById(productEntity.getId());

    productToUpdate.setId(productToUpdate.getId());
    //productToUpdate.setProductAttributes(productEntity.getProductAttributes());
    //productToUpdate.setCategory(productEntity.getCategory());
    productToUpdate.setName(productEntity.getName());
    productToUpdate.setDescription(productEntity.getDescription());
    productToUpdate.setCreatedAt(productEntity.getCreatedAt());
    productToUpdate.setEndDateOfSale(productEntity.getEndDateOfSale());
    productToUpdate.setStartDateOfSale(productEntity.getStartDateOfSale());

    return this.productRepository.save(productToUpdate);
  }

  public ResponseEntity processCsv(MultipartFile productsCsv) {
//    try(Reader reader = new BufferedReader(new InputStreamReader(productsCsv.getInputStream()))) {
//      final var strategy =
//              new HeaderColumnNameMappingStrategy<ProductCsvModel>();
//      strategy.setType(ProductCsvModel.class);
//      final var csvToBean =
//              new CsvToBeanBuilder<ProductCsvModel>(reader)
//                      .withMappingStrategy(strategy)
//                      .withIgnoreEmptyLine(true)
//                      .withIgnoreLeadingWhiteSpace(true)
//                      .build();
//      return csvToBean.parse()
//              .stream()
//              .map(csvLine -> new Prod
//              )
//              .collect(Collectors.toSet());
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
      return null;
  }
}
