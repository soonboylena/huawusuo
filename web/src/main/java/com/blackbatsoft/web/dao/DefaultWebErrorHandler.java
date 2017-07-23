package com.blackbatsoft.web.dao;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 * Created by sunb on 2017/7/12.
 */
@Component
public class DefaultWebErrorHandler implements WebErrorHandler {


    private static final Logger logger = LoggerFactory.getLogger(DefaultWebErrorHandler.class);

    @Override
    public <T> T handle(ResponseEntity<T> entity) {

        Validate.notNull(entity);

        HttpStatus statusCode = entity.getStatusCode();
        if (HttpStatus.OK.equals(statusCode)) {
            return entity.getBody();
        }

        logger.debug("访问服务失败。code：{}  reason:{}", statusCode.toString(), statusCode.getReasonPhrase());

        throw new RuntimeException("http接口异常。");
    }
}
