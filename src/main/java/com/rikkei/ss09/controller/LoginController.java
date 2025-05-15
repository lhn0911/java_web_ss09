package com.rikkei.ss09.controller;

import com.rikkei.ss09.model.Customer;
import com.rikkei.ss09.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("customer", new Customer());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("customer") Customer customer, Model model) {
        Customer loggedIn = customerService.login(customer.getUsername(), customer.getPassword());
        if (loggedIn != null) {
            model.addAttribute("customer", loggedIn);
            return "home";
        } else {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
            return "login";
        }
    }
}
