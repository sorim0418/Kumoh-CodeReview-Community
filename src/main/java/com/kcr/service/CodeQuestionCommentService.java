package com.kcr.service;

import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentRequestDTO;
import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentResponseDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.domain.entity.CodeQuestionComment;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import com.kcr.repository.CodeQuestionCommentRepository;
import com.kcr.repository.CodeQuestionRepository;
import com.kcr.repository.QuestionCommentRepository;
import com.kcr.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeQuestionCommentService implements CommentService{
    @Autowired
    private CodeQuestionCommentRepository codeQuestionCommentRepository;
    @Autowired
    private CodeQuestionRepository codeQuestionRepository;
    //댓글 수정
    @Transactional
    public void updateComment(Long codequestionid, Long id, CodeQuestionCommentRequestDTO requestDTO) {
        CodeQuestionComment comment = codeQuestionCommentRepository.findById(codequestionid)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + codequestionid));

        // 게시글 ID 일치 여부 검증
        if (!comment.getCodeQuestion().getId().equals(codequestionid)) {
            throw new IllegalArgumentException("댓글이 해당 게시글에 속하지 않습니다: " + codequestionid);
        }

        // 댓글 업데이트
        comment.updateCodeQuestionComment(requestDTO.getContent());
    }
    //댓글삭제
    @Override
    public void delete(Long id) {
        codeQuestionCommentRepository.deleteById(id);
    }

    //댓글 등록
    @Transactional
    public Long commentSave(Long id, CodeQuestionCommentRequestDTO codeQuestionCommentRequestDTO) {
        CodeQuestion codeQuestion = codeQuestionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        codeQuestionCommentRequestDTO.setCodeQuestion(codeQuestion);
        CodeQuestionComment codeQuestionComment = codeQuestionCommentRequestDTO.toSaveEntity();
        codeQuestionComment.updateParent(codeQuestionComment);
        codeQuestionCommentRepository.save(codeQuestionComment);

        return codeQuestionCommentRequestDTO.getId();
    }

    //대댓글 등록
    public Long saveChildComment(Long parentId, Long codeQuestionID, CodeQuestionCommentRequestDTO requestDTO) {
        CodeQuestionComment parent = codeQuestionCommentRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다: " + parentId));
        CodeQuestion codeQuestion = codeQuestionRepository.findById(codeQuestionID)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " +codeQuestionID));
        requestDTO.setCodeQuestion(codeQuestion);
//        requestDTO.setParentComment(parent);
        CodeQuestionComment child = requestDTO.toSaveEntity();
        child.updateParent(parent); // 대댓글에 부모 댓글 설정
        //  parent.setChild((List<QuestionComment>) child);
        codeQuestionCommentRepository.save(child); //DB에 저장

        return child.getId();
    }

    //댓글 조회(대댓글과 같이)
    public List<CodeQuestionCommentResponseDTO> findAllChildComments(Long commentId) {
        return codeQuestionCommentRepository.findChildCommentsByParentId(commentId).stream()
                .map(CodeQuestionCommentResponseDTO::new)
                .collect(Collectors.toList());
    }
}
