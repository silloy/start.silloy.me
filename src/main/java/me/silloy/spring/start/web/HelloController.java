package me.silloy.spring.start.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaohuasu
 * @since 1.8
 */
@RestController
public class HelloController {

  @GetMapping("/abc")
  public String abc() {
    return "SUCCESS";
  }
}
