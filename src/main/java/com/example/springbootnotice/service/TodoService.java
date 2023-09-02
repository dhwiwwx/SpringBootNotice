package com.example.springbootnotice.service;

import org.springframework.stereotype.Service;

@Service
//@Service 기능적으로 비지니스 로직을 수행하는 서비스 레이어를 알려줌
public class TodoService {

    public String testService(){
        return "Test Service";
    }
}
