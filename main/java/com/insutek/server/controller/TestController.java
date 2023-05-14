package com.insutek.server.controller;

import com.insutek.server.utils.HostMachineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/user/greet")
    String greetEveryone(){
        return "hello";
    }

    @GetMapping("/admin/greet")
    String greetAdmin(){
        return "hello admin";
    }
}
