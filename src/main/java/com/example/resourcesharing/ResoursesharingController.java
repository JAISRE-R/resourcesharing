package com.example.resourcesharing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")

public class ResoursesharingController {
    @GetMapping("/hello")
    String sayhelloworld(){
        return "helloworld!";
    }

}
