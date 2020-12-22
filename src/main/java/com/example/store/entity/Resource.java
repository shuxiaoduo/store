package com.example.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    private int id;
    private String name;
    private int pid;
    private String path;
    private String component;
    private String icon;
}
