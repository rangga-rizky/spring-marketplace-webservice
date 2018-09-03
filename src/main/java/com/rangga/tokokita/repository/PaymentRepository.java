package com.rangga.tokokita.repository;

import com.rangga.tokokita.model.Order;
import com.rangga.tokokita.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
