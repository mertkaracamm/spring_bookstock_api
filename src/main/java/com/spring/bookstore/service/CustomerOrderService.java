package com.spring.bookstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.bookstore.exception.OrderNotFoundException;
import com.spring.bookstore.model.Book;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.model.CustomerOrder;
import com.spring.bookstore.repository.CustomerOrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerOrderService {

   
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    

    
    public void placeOrder(Customer customer, Book book) {
        
        
        // create the order and save it
        CustomerOrder newOrder = new CustomerOrder();
        newOrder.setCreatedDate(new Date());
        newOrder.setBook(book);
        newOrder.setCustomer(customer);
        newOrder.setTotalPrice(book.getPrice());
        customerOrderRepository.save(newOrder);

       
    }

    public List<CustomerOrder> listOrders(Customer customer) {
        return customerOrderRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
    }


    public CustomerOrder getOrder(Integer orderId) throws OrderNotFoundException {
        Optional<CustomerOrder> order = customerOrderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }
}


