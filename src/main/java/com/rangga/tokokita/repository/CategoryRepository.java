package com.rangga.tokokita.repository;

import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

  @Override
  <S extends Category> List<S> findAll(Example<S> example);
}
