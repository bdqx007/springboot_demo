package com.wg.demo.dingding.common.exception;

import com.wg.demo.dingding.common.ResultMsg;
import com.wg.demo.dingding.controller.DingMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: wanggang
 * @Date: 2018/9/25 11:43
 * @todo
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass().getName());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMsg jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ResultMsg r = new ResultMsg();
        logger.info(e.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        DingMsg.setMsgToDingDing("发生异常：\n URL:" + req.getRequestURL().toString() + "\n" + sw.toString());
        r.setResultMsg(e.getMessage());
        r.setResult("FAILED");
        r.setResultCode(500);
        r.setData(sw.toString());
        e.printStackTrace();
        return r;
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultMsg httpRequestMethodHandler() {
        ResultMsg r = new ResultMsg();
        r.setResultMsg("请求方式有误,请检查 GET/POST");
        r.setResult("FAILED");
        r.setResultCode(500);
        return r;
    }

}
