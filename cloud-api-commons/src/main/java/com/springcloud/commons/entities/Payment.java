package com.springcloud.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
// 下面两个注解, 为类提供无参, 全参构造函数
// 还有一个指定必须参数注解 <@RequiredArgsConstructor>
@AllArgsConstructor        //全参
@NoArgsConstructor         //无参
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
