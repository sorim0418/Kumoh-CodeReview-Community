package com.kcr.service;

import com.kcr.domain.dto.chatGPT.QuestionRequest;
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


@Service
@RequiredArgsConstructor
public class QuestionCommentService implements CommentService{
    @Autowired
    private QuestionCommentRepository questionCommentRepository;
    private ChatService chatgptService;
    @Autowired
    private QuestionRepository questionRepository;

    private static RestTemplate restTemplate = new RestTemplate();



    //댓글등록
    public Long save(QuestionCommentRequestDTO questionCommentRequestDTO) {
        return questionCommentRepository.save(questionCommentRequestDTO.toSaveEntity()).getId();
    }
    //댓글삭제
    @Override
    public void delete(Long id) {
        questionCommentRepository.deleteById(id);
    }

    public QuestionComment view(Long id) {

        return questionCommentRepository.findById(id).get();
    }

    //댓글수정
    public void update(Long id, QuestionCommentRequestDTO requestQuestionCommentDTO) {
       QuestionComment questionComment = questionCommentRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패했습니다.");
        });
        questionComment.updateQuestionComment(requestQuestionCommentDTO.getContent());
        System.out.println(questionComment.getContent()+" 수정된 content Service");
    }
    @Transactional
    public void updateLikes(@Param("id") Long id) {
        questionCommentRepository.updateLikes(id);
    }

    @Transactional
    public void cancelLikes(@Param("id") Long id) {
        questionCommentRepository.cancelLikes(id);
    }

   /* public Page<QuestionComment> getCommentsByQuestionId(Long questionId, Pageable pageable) {
        return questionCommentRepository.findByQuestionId(questionId, pageable);
    }*/
    public List<QuestionCommentResponseDTO> findAll(Long questionId) {
       // Question question = questionRepository.findById(questionId).get();
        List<QuestionComment> questionCommentList = questionCommentRepository.findAllByQuestionId(questionId);
        /* Entity -> DTO */
        List<QuestionCommentResponseDTO> questionCommentDTOList = new ArrayList<>();
        for (QuestionComment questionComment: questionCommentList) {
            QuestionCommentResponseDTO questionCommentDTO = QuestionCommentResponseDTO.toCommentDTO( questionComment, questionId);
            questionCommentDTOList.add(questionCommentDTO);
        }
        return questionCommentDTOList ;
    }

    /*public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        *//* EntityList -> DTOList *//*
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }*/
    @Transactional
    public Long commentSave(Long id, QuestionCommentRequestDTO questionCommentRequestDTO) {

    Question question = questionRepository.findById(id).orElseThrow(() ->
    new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

    questionCommentRequestDTO.setQuestion(question);

    QuestionComment questionComment = questionCommentRequestDTO.toSaveEntity();
    questionCommentRepository.save(questionComment);

    return questionCommentRequestDTO.getId();
}

    //댓글&대댓글
    public void updateComment(Long questionId, Long id, QuestionRequestDTO questionRequestDTO) {
    }

    /*public Long commentSave(Long id, QuestionCommentRequestDTO questionCommentRequestDTO) {
    }*/
}
