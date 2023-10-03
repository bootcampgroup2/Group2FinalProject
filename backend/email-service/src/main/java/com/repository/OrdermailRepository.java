package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Ordermail;

import java.util.List;

@Repository
public interface OrdermailRepository extends MongoRepository<Ordermail, String> {
    List<Ordermail> findByEmail(String email);
}
