package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Customer;

public interface CustomerService {
    Customer login(String username, String password);
}
