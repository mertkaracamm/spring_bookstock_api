package com.spring.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.bookstore.model.Customer;
import com.spring.bookstore.model.CustomerOrder;

import java.util.List;

@Repository
public interface CustomerOrderRepository  extends JpaRepository<CustomerOrder, Integer> {
    List<CustomerOrder> findAllByCustomerOrderByCreatedDateDesc(Customer customer);

}
