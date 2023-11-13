package com.kcr.service;

import com.kcr.domain.dto.question.QuestionResponseDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentRequestDTO;
import com.kcr.domain.dto.question.QuestionRequestDTO;
import com.kcr.domain.dto.questioncomment.QuestionCommentResponseDTO;
import com.kcr.domain.entity.Question;
import com.kcr.domain.entity.QuestionComment;
import com.kcr.repository.QuestionCommentRepository;
//import io.github.flashvayne.chatgpt.service.ChatgptService;
import com.kcr.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionCommentService implements CommentService {
    @Autowired
    private QuestionCommentRepository questionCommentRepository;
    @Autowired
    private QuestionRepository questionRepository;

    //댓글삭제
    @Override
    public void delete(Long id) {
        questionCommentRepository.deleteById(id);
    }
   //댓글 상세보기(하나)
    public QuestionComment view(Long id) {
        return questionCommentRepository.findById(id).get();
    }
    //댓글수정
    @Transactional
    public void updateComment(Long questionId, Long commentId, QuestionCommentRequestDTO requestDTO) {
        // 댓글 엔티티 조회
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + commentId));
        // 게시글 ID 일치 여부 검증
        if (!comment.getQuestion().getId().equals(questionId)) {
            throw new IllegalArgumentException("댓글이 해당 게시글에 속하지 않습니다: " + questionId);
        }
        // 댓글 업데이트
        comment.updateQuestionComment(requestDTO.getContent());
    }

    //댓글리스트 전체
    public List<QuestionCommentResponseDTO> findAll(Long questionId) {
        List<QuestionComment> questionCommentList = questionCommentRepository.findAllByQuestionId(questionId);
        /* Entity -> DTO */
        List<QuestionCommentResponseDTO> questionCommentDTOList = new ArrayList<>();
        for (QuestionComment questionComment : questionCommentList) {
            QuestionCommentResponseDTO questionCommentDTO = QuestionCommentResponseDTO.toCommentDTO2(questionComment);
            questionCommentDTOList.add(questionCommentDTO);
        }
        return questionCommentDTOList;
    }

    //댓글 등록
    @Transactional
    public Long commentSave(Long id, QuestionCommentRequestDTO questionCommentRequestDTO) {

        Question question = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        questionCommentRequestDTO.setQuestion(question);
        QuestionComment questionComment = questionCommentRequestDTO.toSaveEntity();
        questionComment.updateParent(questionComment);
        questionCommentRepository.save(questionComment);

        return questionCommentRequestDTO.getId();
    }

    //대댓글 찾기
    public List<QuestionCommentResponseDTO> findAllChildComments(Long commentId) {
        return questionCommentRepository.findChildCommentsByParentId(commentId).stream()
                .map(QuestionCommentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<QuestionCommentResponseDTO> findAllWithChild(Long questionId) {
        List<QuestionComment> questionCommentList = questionCommentRepository.findAllWithRepliesByQuestionId(questionId);
        List<QuestionCommentResponseDTO> questionCommentDTOList = new ArrayList<>();

        for (QuestionComment questionComment : questionCommentList) {
            QuestionCommentResponseDTO questionCommentDTO = QuestionCommentResponseDTO.toCommentDTO2(questionComment);
            questionCommentDTOList.add(questionCommentDTO);
        }

        return questionCommentDTOList;
    }
    //대댓글 등록
    @Transactional
    public Long saveChildComment(Long parentId, Long questionID, QuestionCommentRequestDTO requestDTO) {
        QuestionComment parent = questionCommentRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다: " + parentId));
        Question question = questionRepository.findById(questionID)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + questionID));
        requestDTO.setQuestion(question);
//        requestDTO.setParentComment(parent);
        QuestionComment child = requestDTO.toSaveEntity();
        child.updateParent(parent); // 대댓글에 부모 댓글 설정
        //  parent.setChild((List<QuestionComment>) child);
        questionCommentRepository.save(child); //DB에 저장

        return child.getId();
    }

    //대댓글까지 조회할때 최상위 댓글인지 확인(부모)
    public List<QuestionCommentResponseDTO> findAllTopLevelComments(Long questionId) {
        List<QuestionComment> topLevelComments = questionCommentRepository.findByQuestionIdAndParentIsNull(questionId);
        List<QuestionCommentResponseDTO> commentDTOList = topLevelComments.stream()
                .map(QuestionCommentResponseDTO::toCommentDTO2)
                .collect(Collectors.toList());

        return commentDTOList;
    }
}
