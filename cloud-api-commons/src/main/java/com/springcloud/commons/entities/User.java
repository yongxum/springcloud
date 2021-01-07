package com.springcloud.commons.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class User extends BaseClass implements Serializable {

    private String userName;
    private String passWord;

}
