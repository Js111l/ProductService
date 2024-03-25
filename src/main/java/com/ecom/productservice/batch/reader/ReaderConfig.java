package com.ecom.productservice.batch.reader;

import com.ecom.productservice.dao.entities.Product;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

@Configuration
public class ReaderConfig {

    @Value("${products.read.resource.delimiter}")
    private String delimiter;

    @Bean
    @StepScope
    public FlatFileItemReader<Product> getProductItemReader(@Value("#{jobParameters['fileContent']}") String path) {
        final var reader = new FlatFileItemReader<Product>();
        final var lineMapper = new DefaultLineMapper<Product>();

        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(this.delimiter));
        lineMapper.setFieldSetMapper(new ProductFieldMapper());
        reader.setResource(new ByteArrayResource(path.getBytes()));
        reader.setLineMapper(lineMapper);
        return reader;
    }

}
