package com.kcr.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


//Question과 식별관계
@Entity
@Getter
@Table(name = "chatgpt")
public class ChatGPT {
    @Id
    private Long id;

    @Column
    private String response;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    // ChatGPT 엔티티의 나머지 부분...
}

