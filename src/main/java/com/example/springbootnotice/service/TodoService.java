package com.example.springbootnotice.service;

import com.example.springbootnotice.model.TodoEntity;
import com.example.springbootnotice.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

 public List<TodoEntity> create(final TodoEntity entity){
//     Validations검사하는 부분
    validations(entity);

     repository.save(entity);

     log.info("Entity Id : {} is saved.",entity.getId());

     return repository.findByUserId(entity.getUserId());

 }
 public void validations (final TodoEntity entity){
     if (entity == null){
         log.warn("Entity cannot be null");
         throw  new RuntimeException("Entity cannot be null");
     }

     if (entity.getUserId() == null){
         log.warn("Unknown user");
         throw  new RuntimeException("Unknown user");
     }
 }
}
