package com.rangga.tokokita.repository;

import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

  @Override
  Optional<Product> findById(String s);

  @Override
  <S extends Product> S save(S s);

  Page<Product> findAllBy(TextCriteria criteria, Pageable pageable);


}
