package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Customer;

public interface CustomerDao {
    Customer login(String username, String password);
}
