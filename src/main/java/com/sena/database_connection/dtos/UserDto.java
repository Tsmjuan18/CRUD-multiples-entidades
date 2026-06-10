package com.sena.database_connection.dtos;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {

    private String name;

    private String email;

    private int age;

    private String phone;

    private List<Long> roleIds;
}
