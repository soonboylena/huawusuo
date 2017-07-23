package com.blackbatsoft.web.dao;


import com.blackbatsoft.web.configuration.GatewayConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * Created by sunb on 2017/7/9.
 */
public abstract class WebBaseDao {

    private static final Logger logger = LoggerFactory.getLogger(WebBaseDao.class);


    @Autowired
    private RestTemplate template;

    @Autowired
    private GatewayConfiguration gatewayConfigration;

    @Autowired
    private WebErrorHandler errorHandler;

    abstract String getServiceKey();

    /**
     * @param path          请求的路径 可以用大括号指定参数 user/{id}
     * @param uriParamMap   对应path，指定url的参数，  id："001"
     * @param queryParamMap uri里边？以后部分
     * @return uri
     */
    URI url(String path, Map<String, Object> uriParamMap, Map<String, Object> queryParamMap) {

        Validate.notBlank(gatewayConfigration.getHost());

        if (StringUtils.isBlank(path)) {
            logger.warn("请求的rest接口path没有指定。请求的服务key为：{}", getServiceKey());
        }

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(gatewayConfigration.getScheme())
                .host(gatewayConfigration.getHost())
                .port(gatewayConfigration.getPort())
                .path(StringUtils.removeEndIgnoreCase(getServiceKey(), "/"));

        if (StringUtils.isNotBlank(path)) {
            builder = builder.path(StringUtils.prependIfMissing(path, "/"));
        }

        if (queryParamMap != null) {
            for (Map.Entry<String, Object> stringObjectEntry : queryParamMap.entrySet()) {
                builder = builder.queryParam(stringObjectEntry.getKey(), stringObjectEntry.getValue());
            }
        }

        URI uri;
        if (uriParamMap != null) {
            uri = builder.buildAndExpand(uriParamMap).toUri();
        } else {
            uri = builder.build(true).toUri();
        }

        logger.debug(uri.toString());

        return uri;
    }

    URI url(String path) {
        return url(path, null, null);
    }

    URI url(String path, Map<String, Object> uriParamMap) {
        return url(path, uriParamMap, null);
    }

    <T> T getEntity(String path, Map<String, Object> param, Class<T> responseType) {

        URI url = url(path, param);
        ResponseEntity<T> forEntity = template.getForEntity(url, responseType);
        return errorHandler.handle(forEntity);
    }


}
