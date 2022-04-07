package com.spring.bookstore.dto.customerorder;

import javax.validation.constraints.NotNull;

import com.spring.bookstore.model.CustomerOrder;


public class CustomerOrderDto {
    private Integer id;
    private @NotNull Integer userId;

    public CustomerOrderDto() {
    }

    public CustomerOrderDto(CustomerOrder order) {
        this.setId(order.getId());
        //this.setUserId(order.getUserId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
