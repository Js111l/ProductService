package com.ecom.productservice.batch.writer;

import com.ecom.productservice.dao.entities.Product;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class WriterConfig {
  private final DataSource dataSource;

  public WriterConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public ItemWriter<Product> getDbProductWriter() {
    var jdbcBatchItemWriter = new JdbcBatchItemWriter<Product>();
    jdbcBatchItemWriter.setDataSource(dataSource);
    jdbcBatchItemWriter.setSql(
            //TODO dat nie chce czytac
        "INSERT INTO Product (name, description, start_date_of_sale, end_date_of_sale) " +
                "VALUES (:name, :description, :startDateOfSale, :endDateOfSale);");
    jdbcBatchItemWriter.setItemSqlParameterSourceProvider(
        new BeanPropertyItemSqlParameterSourceProvider<>());
    return jdbcBatchItemWriter;
  }
}
