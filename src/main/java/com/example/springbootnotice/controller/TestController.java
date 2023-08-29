package com.example.springbootnotice.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/testGetMapping")
    public String testControllerWithPath(){
        return "Hello World! testGetMapping";
    }

    @GetMapping("testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id){
        return "Hello World! ID" + id;
    }

//    @GetMapping("/{id}")
//    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
//        return "Hello World! ID" + id;
//    }
}
