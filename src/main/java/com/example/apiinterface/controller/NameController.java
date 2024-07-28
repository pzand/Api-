package com.example.apiinterface.controller;

import com.pzand.clinesdk.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name, HttpServletRequest request) {
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        return "POST 用户名字是" + user.getUsername();
    }
}
