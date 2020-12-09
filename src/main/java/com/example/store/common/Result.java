package com.example.store.common;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/7
 **/
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    @JsonUnwrapped
    private ResultStatus resultStatus;

    private String message;

    private Object data;

    public Result setStatus(ResultStatus resultStatus){
        this.resultStatus = resultStatus;
        return this;
    }

    public Result success(){
        return new Result().setStatus(ResultStatus.SUCCESS);
    }

    public Result success(Object data){
        return new Result().setStatus(ResultStatus.SUCCESS).setData(data);
    }

    public Result badRequest(){
        return new Result().setStatus(ResultStatus.BAD_REQUEST);
    }

    public Result badRequest(Object data){
        return new Result().setStatus(ResultStatus.BAD_REQUEST).setData(data);
    }

    @Override
    @SneakyThrows
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(this);
    }
}
