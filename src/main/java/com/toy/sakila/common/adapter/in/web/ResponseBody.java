package com.toy.sakila.common.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseBody<T> {
    private int status;
    private T data;
    private String message;
}