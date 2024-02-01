package com.example.dynamodbspringbootfagete.domain.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.example.dynamodbspringbootfagete.domain.model.User;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
}
