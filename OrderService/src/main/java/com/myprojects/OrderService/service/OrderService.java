package com.myprojects.OrderService.service;

import com.myprojects.OrderService.model.OrderRequest;
import com.myprojects.OrderService.model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);


	OrderResponse getOrderDetails(long orderId);

}
