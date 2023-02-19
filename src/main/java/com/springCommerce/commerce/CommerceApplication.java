package com.springCommerce.commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CommerceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommerceApplication.class, args);
  }
}
