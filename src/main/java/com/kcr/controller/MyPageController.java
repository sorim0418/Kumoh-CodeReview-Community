package com.kcr.controller;

import com.kcr.domain.dto.mypage.MyPageResponseDTO;
import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.domain.entity.Member;
import com.kcr.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    //내프로필 전체
    @GetMapping("/mypage/member/{id}")
    public ResponseEntity<MyPageResponseDTO> showMyPage(@PathVariable("id") Long id) {
        MyPageResponseDTO myPageResponseDTO = myPageService.findbyId(id);

        MyPageResponseDTO.builder()
                .loginId(myPageResponseDTO.getLoginId())
                .nickname(myPageResponseDTO.getNickname())
                .stuNum(myPageResponseDTO.getStuNum())
                .activityScore(myPageResponseDTO.getActivityScore())
                .question(myPageResponseDTO.getQuestions())
                .codeQuestion(myPageResponseDTO.getCodeQuestions())
                .build();
        return new ResponseEntity<>(myPageResponseDTO, HttpStatus.OK);
    }

    //내 Q&A게시글 조회
    @GetMapping("/mypage/member/{id}/showquestion")
    public ResponseEntity<Member> showMyQuestion(@PathVariable("id") Long id) {
        return null;
    }
    //내 code게시글 조회
    @GetMapping("/mypage/member/{id}/showcodequestion")
    public ResponseEntity<Member> showMyCodeQuestion(@PathVariable("id") Long id) {
        return null;
    }
    //내 Q&A댓글 조회

    //내 code댓글 조회
}
