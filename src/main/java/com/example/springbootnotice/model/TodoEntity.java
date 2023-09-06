package com.example.springbootnotice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid") // id를 자동생성 하겠다.
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; // 이 오브젝트 아이디
    private String userId; // 이 오브젝트를 생성한 유저의 아이디
    private String title; // 타이틀 제목
    private boolean done; // true - todo를 완료한 경우(checked)
}
