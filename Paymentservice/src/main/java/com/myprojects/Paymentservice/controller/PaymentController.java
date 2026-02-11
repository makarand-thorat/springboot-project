package com.myprojects.Paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.Paymentservice.model.PaymentRequest;
import com.myprojects.Paymentservice.model.PaymentResponse;
import com.myprojects.Paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
		return new ResponseEntity<>(
				paymentService.doPayment(paymentRequest),
				HttpStatus.OK);
		
	}
	@GetMapping("/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentdetailsByOrderId(@PathVariable String orderId){
		return new ResponseEntity<>(
				paymentService.getPaymnetDetailsByorderId(orderId),
				HttpStatus.OK);
	}

}
