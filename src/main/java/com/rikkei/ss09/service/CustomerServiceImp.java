package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Customer;
import com.rikkei.ss09.repository.CustomerDao;
import com.rikkei.ss09.repository.CustomerDaoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService{
    @Autowired
    private CustomerDao customerDao;
    @Override
    public Customer login(String username, String password) {
        return customerDao.login(username, password);
    }
}
