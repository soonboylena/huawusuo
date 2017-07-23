package com.blackbatsoft.web.dao;

import org.springframework.http.ResponseEntity;

/**
 * Created by sunb on 2017/7/12.
 */
public interface WebErrorHandler {
    <T> T handle(ResponseEntity<T> forEntity);
}
