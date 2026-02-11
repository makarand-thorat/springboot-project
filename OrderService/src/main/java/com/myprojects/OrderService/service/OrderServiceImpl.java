package com.myprojects.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myprojects.OrderService.entity.Order;
import com.myprojects.OrderService.exception.CustomException;
import com.myprojects.OrderService.external.client.PaymentService;
import com.myprojects.OrderService.external.client.ProductService;
import com.myprojects.OrderService.external.request.PaymentRequest;
import com.myprojects.OrderService.external.response.PaymentResponse;
import com.myprojects.OrderService.external.response.ProductResponse;
import com.myprojects.OrderService.model.OrderRequest;
import com.myprojects.OrderService.model.OrderResponse;
import com.myprojects.OrderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public long placeOrder(OrderRequest orderRequest) {
		//order Entity -> Save data woith order status order created
		//product Serivce -> (Reduce Quantity)
		//Payment Service -> Payments -> Success -> Complete else Cancelled
		log.info	("PLacing Order request: {}",orderRequest);
		
		//implementing reducequantity from product service here before placing order
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info	("Creating order with status CREATED");
		Order order=Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus("CREATED")
				.productId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity())
				.orderDate(Instant.now())
				.build();
		order = orderRepository.save(order);
		
		//Do payment service implementation below 
		log.info("Payment service is being called");
		PaymentRequest paymentRequest =
				PaymentRequest.builder()
				.orderId(order.getId())
				.amount(orderRequest.getTotalAmount())
				.paymentMode(orderRequest.getPaymentMode())
				.build();
		
		String orderStatus=null;
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Order placed payment success");
			orderStatus="PLACED";
		} catch (Exception e) {
			log.error("Error occured in payment");
			orderStatus="PAYMENT_FAILED";
		}
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		
		
		log.info("ORder placed Successfully with OrderID: {}",order.getId());
		

		return order.getId();
		
	}
	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get order details for :" + orderId);
		
		
		Order order= orderRepository.findById(orderId)
				.orElseThrow(()-> new CustomException("Order not found","NOT FOUND", 404));
		
		
		log.info("Invoking productService to fetch productID {}",order.getProductId());
		ProductResponse productResponse= 
				restTemplate.getForObject(
						"http://PRODUCT-SERVICE/product/"+ order.getProductId(), 
						ProductResponse.class
						);
		
		log.info("Invoking paymentService to fetch PaymentDetails ");
		PaymentResponse paymentResponse= 
				restTemplate.getForObject(
						"http://PAYMENT-SERVICE/payment/"+ order.getId(), 
						PaymentResponse.class
						);
		OrderResponse.ProductDetails productDetails
		=OrderResponse.ProductDetails
		.builder()
		.productName(productResponse.getProductName())
		.productId(productResponse.getProductId())
		.build();
		
		OrderResponse.PaymentDetails paymentDetails
		=OrderResponse.PaymentDetails 
		.builder()
		.paymentId(paymentResponse.getPaymentId())
		.paymentStatus(paymentResponse.getStatus())
		.paymentDate(paymentResponse.getPaymentDate())
		.paymentMode(paymentResponse.getPaymentMode())
		.build();
		OrderResponse orderResponse = OrderResponse.builder()
				.orderId(order.getId())
				.orderStatus(order.getOrderStatus())
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.productDetails(productDetails)
				.paymentDetails(paymentDetails)
				.build();
		
		
		return orderResponse;
	}

}
