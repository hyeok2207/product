package com.example.product;

import com.example.product.dao.Product;
import com.example.product.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class ProductDAOImplTest {

  @Autowired
  ProductDAO productDAO;

  //등록
  @Test
  @DisplayName("상품등록")
  void save(){
    Product product = new Product();
    product.setPname("복사기");
    product.setQuantity(10L);
    product.setPrice(1000000L);

    Long pid = productDAO.save(product);
    log.info("pid={}",pid);
    Assertions.assertThat(pid).isGreaterThan(0L);
  }

  //조회
  @Test
  @DisplayName("상품조회")
  void findById(){
    Long pid = 1L;
    Optional<Product> product = productDAO.findById(pid);

    Product findedProduct = product.orElseThrow();// 없으면 java.util.NoSuchElementException
    Assertions.assertThat(findedProduct.getPname()).isEqualTo("복사기");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(10L);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(1000000L);
  }

  //수정
  @Test
  @DisplayName("상품수정")
  void update() {
    Long pid = 1L;
    Product product = new Product();
    product.setPname("복사기_수정");
    product.setQuantity(20L);
    product.setPrice(2000000L);
    int updatedRowCount = productDAO.update(pid, product);
    Optional<Product> findedProduct = productDAO.findById(pid);

    Assertions.assertThat(updatedRowCount).isEqualTo(1);
    Assertions.assertThat(findedProduct.get().getPname()).isEqualTo(product.getPname());
    Assertions.assertThat(findedProduct.get().getQuantity()).isEqualTo(product.getQuantity());
    Assertions.assertThat(findedProduct.get().getPrice()).isEqualTo(product.getPrice());
  }

  //삭제
  @Test
  @DisplayName("상품삭제")
  void delete() {
    Long pid = 1L;
    int deletedRowCount = productDAO.delete(pid);
    Optional<Product> findedProduct = productDAO.findById(pid);

    Assertions.assertThatThrownBy(()->findedProduct.orElseThrow())
        .isInstanceOf(NoSuchElementException.class);
  }

  //목록
  @Test
  @DisplayName("상품목록")
  void findAll() {
    List<Product> list = productDAO.findAll();

    Assertions.assertThat(list.size()).isGreaterThan(0);
  }
}
