package com.ecom.productservice.batch.job;

import com.ecom.productservice.dao.entities.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.transaction.PlatformTransactionManager;

// Job configuring
@Configuration
public class JobConfig {
  private final ItemReader<Product> fileItemReader;
  private final ItemWriter<Product> itemWriter;

  public JobConfig(
          ItemReader<Product> fileItemReader,
          ItemWriter<Product> productEntityJdbcBatchItemWriter) {
    this.fileItemReader = fileItemReader;
    this.itemWriter = productEntityJdbcBatchItemWriter;
  }

  // Configure a reading products from file job.
  @Bean(name = "productsJob")
  public Job getJob(JobRepository jobRepository, Step productsStep) {
    return new JobBuilder("products_job", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(productsStep)
        .build();
  }

  // Configure steps
  @Bean
  public Step productsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("products_step", jobRepository)
        .<Product, Product>chunk(100, transactionManager)
        .reader(this.fileItemReader)
        .writer(this.itemWriter)
        .build();
  }
}
