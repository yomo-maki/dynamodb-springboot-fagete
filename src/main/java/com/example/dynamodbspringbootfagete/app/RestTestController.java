package com.example.dynamodbspringbootfagete.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class RestTestController {
  @RequestMapping("/")
  @ResponseBody
  private String restTest() {
    return "Rest Test OK";
  }
}