package com.example.demo.controller;


import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.core.lookup.UpperLookup;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;

@ResponseBody
@RestController
public class TestClass {

    @PostMapping("/yso")
    public void URLDemo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ServletInputStream inputStream = request.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream.readObject();
    }

    private static final Logger logger = LoggerFactory.getLogger(TestClass.class);
    @GetMapping("/log")
    public String echo(@RequestHeader("X-Api-Version") String apiVersion) {

        ThreadContext.put("apiVersion", apiVersion);
        logger.info("Received a request");
        UpperLookup upperLookup = new UpperLookup();
        logger.info(upperLookup.lookup("i"));
        return "Hello, API Controller!";
    }
}