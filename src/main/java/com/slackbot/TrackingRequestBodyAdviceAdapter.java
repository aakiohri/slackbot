package com.slackbot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
public class TrackingRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    private final Logger logger = LoggerFactory.getLogger(TrackingRequestBodyAdviceAdapter.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {

        try {
            logger.info("RequestBody : {}", mapper.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            logger.info("RequestBody in error state.");
            e.printStackTrace();
        }

        return body;
    }
}
