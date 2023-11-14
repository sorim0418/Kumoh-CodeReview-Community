package com.kcr.domain.dto.mypage;

import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.Member;
import com.kcr.domain.entity.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MyPageResponseDTO {
  //  private Long id;
    private String loginId;
    private String nickname;
    private String stuNum;
    private Long activityScore;
    private int boardCnt;
    private int commentCnt;
    private List<Question> question;
    private List<CodeQuestion> codeQuestions;

    @Builder
    public MyPageResponseDTO(String loginId, String nickname, String stuNum, Long activityScore,List<Question> question, List<CodeQuestion> codeQuestion) {
        this.loginId=loginId;
        this.nickname=nickname;
        this.stuNum=stuNum;
        this.activityScore = activityScore;
        this.question =question;
        this.codeQuestions =codeQuestion;
    }
    @Builder
    public MyPageResponseDTO(Member member) {
        this.loginId = member.getLoginId();
        this.nickname = member.getNickname();
        this.stuNum = member.getStuNum();
        this.activityScore= member.getActivityScore();
    }

    @Builder
    public MyPageResponseDTO(Long activityScore, int boardCnt, Question question, CodeQuestion codeQuestion) {
        this.activityScore = activityScore;
        this.question = (List<Question>) question;
        this.codeQuestions = (List<CodeQuestion>) codeQuestion;
    }


}
