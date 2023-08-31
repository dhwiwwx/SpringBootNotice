package com.example.springbootnotice.controller;
import com.example.springbootnotice.dto.TestRequestBodyDTO;
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
    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello World! Id" + testRequestBodyDTO.getId() +"Message :" + testRequestBodyDTO.getMessage();
    }
//    @GetMapping("/{id}")
//    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
//        return "Hello World! ID" + id;
//    }
}
