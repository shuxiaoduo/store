package com.example.store.common;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/7
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultStatus {

    SUCCESS(1200,"操作成功"),

    BAD_REQUEST(1400, "请求参数有误"),

    NOT_FOUND(1404,"找不到资源"),

    FORBIDDEN(1405,"请求被拒绝"),

    NOT_ALLOWED(1406,"方法不允许"),

    TOKEN_EXPIRED(1401,"登录状态过期"),

    UNAUTHORIZED(1205,"未登录"),

    INTERNAL_SERVER_ERROR(1500,"服务器错误");

    private final int code;

    private final String status;

    ResultStatus(int code, String status) {

        this.code = code;
        this.status = status;

    }

    //JsonUnwrapped需要这两个函数
    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}
