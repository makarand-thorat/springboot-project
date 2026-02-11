package com.myprojects.Paymentservice.service;

import com.myprojects.Paymentservice.model.PaymentRequest;
import com.myprojects.Paymentservice.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymnetDetailsByorderId(String orderId);

}
