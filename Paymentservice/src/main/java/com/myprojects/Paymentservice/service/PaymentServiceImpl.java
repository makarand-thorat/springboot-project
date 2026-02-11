package com.myprojects.Paymentservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.Paymentservice.entity.TransactionDetails;
import com.myprojects.Paymentservice.model.PaymentMode;
import com.myprojects.Paymentservice.model.PaymentRequest;
import com.myprojects.Paymentservice.model.PaymentResponse;
import com.myprojects.Paymentservice.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;
	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("here problem occured");
		log.info("Recording payment details {}",paymentRequest);
		
		TransactionDetails transactionDetails = 
				TransactionDetails.builder()
				.paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId())
				.amount(paymentRequest.getAmount())
				.build();
		
		transactionDetailsRepository.save(transactionDetails);
		log.info("Transaction Completed with Id {}",transactionDetails.getId());		
		return transactionDetails.getId();
	}
	@Override
	public PaymentResponse getPaymnetDetailsByorderId(String orderId) {
		log.info("Getting payment details for order id : {}",orderId);
		
	TransactionDetails transactionDetails = 
			transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));
	PaymentResponse paymentResponse=
			PaymentResponse.builder()
			.paymentId(transactionDetails.getId())
			.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
			.paymentDate(transactionDetails.getPaymentDate())
			.orderId(transactionDetails.getOrderId())
			.status(transactionDetails.getPaymentStatus())
			.amount(transactionDetails.getAmount())
			
			.build();
		return paymentResponse;
	}

}
