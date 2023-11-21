package com.kcr.service;

import com.kcr.domain.dto.codequestioncomment.CodeQuestionCommentResponseDTO;
import com.kcr.domain.entity.Push;
import com.kcr.repository.PushRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PushService {

    @Autowired
    PushRepository pushRepository;

    //알림함 조회
    @Transactional
    public List<Push> showAll(Long pushId) {
        return pushRepository.findAllById(Collections.singleton(pushId));
    }

    //댓글공감알림
    public void notifyLikesByComment(Long commentID){

    }
    //대댓글공감알림
    public void notifyLikesByRecomment(Long recommentID){

    }
    //댓글알림
    public void notifyByComment(Long recommentID){

    }
    //대댓글알림
    public Long notifyByRecomment(Long recommentID){

        return recommentID;
    }
    //게시글공감알림
    public void notifyLikesByPost(Long postID){

    }
}
