package com.example.ngothaolinh.repository;


import com.example.ngothaolinh.model.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
}