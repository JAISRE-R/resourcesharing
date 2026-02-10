package com.example.resourcesharing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/resoucesharing/login")

public class ResourceSharingLoginController {

    @GetMapping("/{id}")
    int CreateUser(@PathVariable int id)
    {
        return id;
    }

}
