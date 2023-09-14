package com.example.springbootnotice.controller;

import com.example.springbootnotice.dto.ResponseDTO;
import com.example.springbootnotice.dto.TodoDTO;
import com.example.springbootnotice.model.TodoEntity;
import com.example.springbootnotice.service.TodoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        try {
            String temporaryUserId = "temporary-user"; // temporary user id

            // TodoEntity로 변환
            TodoEntity entity = TodoDTO.todoEntity(dto);

            // id를 null로 초기화한다. 생성 당시에는 id가 없어야 하기 때문에
            entity.setId(null);

            // 임시 유저 아이디를 생성해준다.
            entity.setUserId(temporaryUserId);

            // 서비스를 이용해 todo 엔티티를 생성한다.
            List<TodoEntity> entities = todoService.create(entity);

            // 자바 스트림을 이용해 리턴된 엔티티 리스트를 Todo리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            //반환되 TodoDTO리스트를 이용해 ResponseDTO를 초기화한다
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            // ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            // 혹시 예외가 나는 경우 dto대신 error에 메세지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}

