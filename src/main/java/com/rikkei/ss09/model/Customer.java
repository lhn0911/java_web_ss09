package com.rikkei.ss09.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    private Long id;
    @NotEmpty(message = "Tên không được để trống")
    private String username;
    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;
    private String email;
    private String phone;
    private String address;
    private String gender;
}
