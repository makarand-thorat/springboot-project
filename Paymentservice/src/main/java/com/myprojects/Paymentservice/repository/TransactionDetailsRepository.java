package com.myprojects.Paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myprojects.Paymentservice.entity.TransactionDetails;
import java.util.List;



@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {
TransactionDetails findByOrderId(long orderId);
}
