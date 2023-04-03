package com.example.product.web.svc;


import com.example.product.dao.Product;
import com.example.product.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;

  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  @Override
  public Optional<Product> findById(Long pid) {
    return productDAO.findById(pid);
  }

  @Override
  public int update(Long pid, Product product) {
    return productDAO.update(pid, product);
  }

  @Override
  public int delete(Long pid) {
    return productDAO.delete(pid);
  }

  public int deleteParts(List<Long> pids) {
    return productDAO.deleteParts(pids);
  }
  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }

  @Override
  public boolean isExist(Long pid) {
    return productDAO.isExist(pid);
  }
}
