package com.rangga.tokokita.repository;

import com.rangga.tokokita.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

  @Query("{'username': ?0}")
  public User findByUsername(String address);

  @Override
  <S extends User> S save(S s);
}
