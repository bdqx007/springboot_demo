package com.wg.demo.dingding.controller;

import com.alibaba.fastjson.JSONObject;
import com.wg.demo.dingding.common.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wanggang.io
 * @Date: 2019/6/18 11:22
 * @todo
 */
@Api(description = "swagger2测试接口")
@RestController
@RequestMapping("ding")
public class SwaggerController {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());


    @ApiOperation(value = "sayHi")
    @GetMapping("sayHi")
    public ResultMsg makeException( String name)
    {
        return  ResultMsg.getMsg("Hi " + name + " !");
    }


    @ApiOperation(value = "发送钉钉消息")
    @PostMapping("sendMsg")
    static public String setMsgToDingDing(String msg)
    {
        String dingToken="";
        JSONObject text = new JSONObject();
        text.put("content",msg);
        JSONObject content = new JSONObject();
        content.put("msgtype","text");
        content.put("text",text);
        String url ="https://oapi.dingtalk.com/robot/send?access_token=" + dingToken;
        RestTemplate restTemplate = new RestTemplate();
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //请求体
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("id", 1);
        //封装成一个请求对象
        HttpEntity entity = new HttpEntity(content.toJSONString(), headers);
        String result = restTemplate.postForObject(url, entity, String.class);
        return result;
    }
}
