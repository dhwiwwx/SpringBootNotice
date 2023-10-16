package com.example.springbootnotice.service;

import com.example.springbootnotice.model.TodoEntity;
import com.example.springbootnotice.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

 public List<TodoEntity> retrieve(final String userId){
     return repository.findByUserId(userId);
 }

 public List<TodoEntity> update (final TodoEntity entity){
     //저장할 엔티티가 유효한지 확인한다. 이메서드는 2.3.1 create Todo에서 구현했다.
     validations(entity);

     // 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트를 할수 없기 떄문에
     final Optional<TodoEntity> original = repository.findById(entity.getId());

     original.ifPresent(todo ->{
         // 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덥어 씌운다.
         todo.setTitle(entity.getTitle());
         todo.setDone(entity.isDone());
         // 데이터베이스에 새 값을 저장
         repository.save(todo);
     });

     // 2.3.2 Retrieve Todo에서 만든 메서드를 이용해 유저의 모든 Todo 리스트를 리턴한다.
     return retrieve(entity.getUserId());
 }

 public List<TodoEntity> delete(final TodoEntity entity){
     validations(entity);

     try{
         // 엔티티를 삭제한다.
         repository.delete(entity);
     }catch (Exception e){
         // exception 발생 시 id와 exception울 로강한다.
         log.error("error deleting entity", entity.getId(), e);

         // 컨트롤러로 exception을 날린다. 데이터베이스 내부 로직을 캡슐화하기 위해 e를 리턴허자 않고 새exception 오브젝트를 리턴한다.
         throw new RuntimeException("error deleting entity" + entity.getId());
     }
     return retrieve(entity.getUserId());
 }

}
