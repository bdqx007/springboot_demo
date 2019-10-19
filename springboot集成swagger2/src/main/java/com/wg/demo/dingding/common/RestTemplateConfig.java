package com.wg.demo.dingding.common;

import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @Author: wanggang
 * @Date: 2018/9/30 17:54
 * @todo
 */

@Configuration
public class RestTemplateConfig {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        });
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(15000);//单位为ms
        factory.setConnectTimeout(10000);//单位为ms
        return factory;

    }
//为了支持https 改为下面的factory
//    @Bean(name = "httpsFactory")
//    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory()
//              {
//                  try {
//                      CloseableHttpClient httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
//                      HttpComponentsClientHttpRequestFactory httpsFactory =
//                              new HttpComponentsClientHttpRequestFactory(httpClient);
//                      httpsFactory.setReadTimeout(40000);
//                      httpsFactory.setConnectTimeout(40000);
//                      return httpsFactory;
//                  }
//                  catch (Exception e ){
//                      logger.info(e.getMessage());
//                      return  null;
//                  }
//
//    }



}
