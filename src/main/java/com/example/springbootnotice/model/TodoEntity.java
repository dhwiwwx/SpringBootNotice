package com.example.springbootnotice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoEntity {
    private String id; // 이 오브젝트 아이디
    private String userId; // 이 오브젝트를 생성한 유저의 아이디
    private String title; // 타이틀 제목
    private boolean done; // true - todo를 완료한 경우(checked)
}
