package com.kcr.service;

import com.kcr.domain.dto.codequestion.CodeQuestionRequestDTO;
import com.kcr.domain.dto.codequestion.CodeQuestionResponseDTO;
import com.kcr.domain.entity.CodeQuestion;
import com.kcr.repository.CodeQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 생성자 의존관계 주입
public class CodeQuestionService {

    private final CodeQuestionRepository codeQuestionRepository;

    /* 게시글 등록 */
    @Transactional // 메소드 실행 시 트랜잭션 시작 -> 정상 종료되면 커밋 / 에러 시 롤백
    public Long save(CodeQuestionRequestDTO requestDTO) {
        return codeQuestionRepository.save(requestDTO.toSaveEntity()).getId();
    }

    /* 게시글 상세 조회 */
    @Transactional
    public CodeQuestionResponseDTO findById(Long id) {
        CodeQuestion codeQuestion = codeQuestionRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return CodeQuestionResponseDTO.builder()
                .id(codeQuestion.getId())
                .title(codeQuestion.getTitle())
                .writer(codeQuestion.getWriter())
                .content(codeQuestion.getContent())
                .createDate(codeQuestion.getCreateDate())
                .likes(codeQuestion.getLikes())
                .views(codeQuestion.getViews())
                .build();
    }

    /* 게시글 수정 */
    @Transactional
    public Long update(Long id, CodeQuestionRequestDTO codeQuestionRequestDTO) {
        CodeQuestion codeQuestion = codeQuestionRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        codeQuestion.updateQuestion(codeQuestionRequestDTO.getTitle(), codeQuestionRequestDTO.getContent(), codeQuestionRequestDTO.getCodeContent());
        return id;
    }

    /* 게시글 삭제 */
    @Transactional
    public void delete(Long id) {
        CodeQuestion codeQuestion = codeQuestionRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        codeQuestionRepository.delete(codeQuestion);
    }

    /* 게시글 조회수 업데이트 */
    @Transactional
    public void updateViews(@Param("id") Long id) {
        codeQuestionRepository.updateViews(id);
    }

    /* 게시글 공감수 업데이트 */
    @Transactional
    public void updateLikes(@Param("id") Long id) {
        codeQuestionRepository.updateLikes(id);
    }
}
