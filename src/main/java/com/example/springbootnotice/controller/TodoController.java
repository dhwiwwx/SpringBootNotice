package com.example.springbootnotice.controller;

import com.example.springbootnotice.dto.ResponseDTO;
import com.example.springbootnotice.dto.TodoDTO;
import com.example.springbootnotice.service.TodoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {
    @Autowired // 빈을 찾아서 그빈을 이 인스턴스 멤버변수에 연결하라는 뜻
    private TodoService todoService;
    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = todoService.testService(); // 테스트 서비스 사용
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){

    }
}

