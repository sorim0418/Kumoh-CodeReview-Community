package com.kcr.service;

import com.kcr.domain.dto.mypage.MyPageResponseDTO;
import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.Member;
import com.kcr.domain.entity.Question;
import com.kcr.repository.CodeQuestionRepository;
import com.kcr.repository.MemberRepository;
import com.kcr.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final CodeQuestionRepository codeQuestionRepository;
    //내프로필 화면
        public MyPageResponseDTO findbyId(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        List<Question> question = questionRepository.findAllByMemberId(id);
        List<CodeQuestion> codeQuestions = codeQuestionRepository.findAllByMemberId(id);

        return  MyPageResponseDTO.builder()
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .stuNum(member.getStuNum())
                .activityScore(member.getActivityScore())
                .question(question)
                .codeQuestion(codeQuestions)
                .build();
    }

    //내가 쓴 게시글 보기
    public MyPageResponseDTO findAllPostbyId(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        List<Question> question = questionRepository.findAllByMemberId(id);
        List<CodeQuestion> codeQuestions = codeQuestionRepository.findAllByMemberId(id);

        return  MyPageResponseDTO.builder()
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .stuNum(member.getStuNum())
                .activityScore(member.getActivityScore())
                .question(question)
                .codeQuestion(codeQuestions)
                .build();
    }
}
