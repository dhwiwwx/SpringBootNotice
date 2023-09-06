package com.example.springbootnotice.service;

import com.example.springbootnotice.model.TodoEntity;
import com.example.springbootnotice.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Service 기능적으로 비지니스 로직을 수행하는 서비스 레이어를 알려줌
public class TodoService {

 @Autowired
 private TodoRepository repository;

 public String testService(){
//     TodoEntity 생성
     TodoEntity entity = TodoEntity.builder().title("My First todo item").build();
//     TodoEntity 저장
     repository.save(entity);
//     TodoEntity 검색
     TodoEntity saveEntity = repository.findById(entity.getId()).get();
     return saveEntity.getTitle();
 }
}
