package com.example.product.dao;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

  Long save(Product product);


  Optional<Product> findById(Long pid);


  int update(Long pid,Product product);


  int delete(Long pid);

  int deleteParts(List<Long> pids);

  int deleteAll();


  List<Product> findAll();


  boolean isExist(Long pid);

  int countOfRecord();
}
