package com.rangga.tokokita.service;

import com.rangga.tokokita.model.Payment;
import com.rangga.tokokita.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public void create(Payment payment){
        payment.setCreated_at(LocalDateTime.now());
        paymentRepository.save(payment);
    }
}
