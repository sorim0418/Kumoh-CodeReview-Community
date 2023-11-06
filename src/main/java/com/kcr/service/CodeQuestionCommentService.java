package com.kcr.service;

import com.kcr.domain.dto.codequestion.CodeQuestionRequestDTO;
import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentRequestDTO;
import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentResponseDTO;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentRequestDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.CodeQuestionComment;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import com.kcr.repository.CodeQuestionCommentRepository;
import com.kcr.repository.CodeQuestionRepository;
import com.kcr.repository.QuestionCommentRepository;
import com.kcr.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeQuestionCommentService {
    @Autowired
    private CodeQuestionCommentRepository codeQuestionCommentRepository;
    @Autowired
    private CodeQuestionRepository codeQuestionRepository;

    //댓글 전체조회
    public List<CodeQuestionCommentResponseDTO> findAll(Long codeQuestionId) {
        List<CodeQuestionComment> codeQuestionCommentList = codeQuestionCommentRepository.findAllByCodeQuestionId(codeQuestionId);
        /* Entity -> DTO */
        List<CodeQuestionCommentResponseDTO> codeQuestionCommentDTOList = new ArrayList<>();
        for (CodeQuestionComment codeQuestionComment: codeQuestionCommentList) {
            CodeQuestionCommentResponseDTO codeQuestionCommentDTO = CodeQuestionCommentResponseDTO.toCommentDTO(codeQuestionComment, codeQuestionId);
            codeQuestionCommentDTOList.add(codeQuestionCommentDTO);
        }
        return codeQuestionCommentDTOList ;
    }

    //댓글 등록
    @Transactional
    public Long commentSave(Long id, CodeQuestionCommentRequestDTO codeQuestionCommentRequestDTO) {

        CodeQuestion codeQuestion = codeQuestionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        codeQuestionCommentRequestDTO.setCodeQuestion(codeQuestion);

        CodeQuestionComment codeQuestionComment = codeQuestionCommentRequestDTO.toSaveEntity();
        codeQuestionCommentRepository.save(codeQuestionComment);

        return codeQuestionCommentRequestDTO.getId();
    }

    //댓글 수정
    public void updateComment(Long codeQuestionId, Long id, CodeQuestionRequestDTO codeQuestionRequestDTO) {
    }

    //댓글삭제
    public void delete(Long id) {
        codeQuestionCommentRepository.deleteById(id);
    }
}
