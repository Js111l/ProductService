package com.ecom.productservice.controller;

import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final JobLauncher jobLauncher;
    private final Job productsJob;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return this.productService.getById(id);
    }

    @GetMapping
    public List<Product> getActiveProducts() {
        return this.productService.getActiveProducts();
    }

    @PostMapping("/upload")
    public ResponseEntity uploadCsvFileWithProducts(@RequestPart("file") MultipartFile productsCsv) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, IOException, JobRestartException {
        var jobParameters = new JobParametersBuilder()
                .addString("savedAt", String.valueOf(System.currentTimeMillis()))
                .addString("fileContent", new String(productsCsv.getBytes()))
                .toJobParameters();

        this.jobLauncher.run(productsJob, jobParameters);

        return ResponseEntity.ok().build();
    }
}
