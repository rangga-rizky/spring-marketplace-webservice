package com.rangga.tokokita.service;

import com.rangga.tokokita.exception.ResourceNotFoundExceptions;
import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.model.Product;
import com.rangga.tokokita.model.User;
import com.rangga.tokokita.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> index(Pageable pageable){
        return  productRepository.findAll(pageable);
    }

    public Page<Product> searchByName(String q,Pageable pageable){
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(q);
        return  productRepository.findAllBy(criteria,pageable);
    }

    public Product findById(String id){
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new ResourceNotFoundExceptions("Produk Tidak ditemukan");
        }
        return product.get();
    }

    public Product store(Product product){
        product.setCreated_at(new Date());
        product.setUpdated_at(new Date());
        return productRepository.save(product);
    }

    public Product save(Product product){
        product.setUpdated_at(new Date());
        return productRepository.save(product);
    }

}
