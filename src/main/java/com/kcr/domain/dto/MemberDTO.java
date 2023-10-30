package com.kcr.domain.dto;

import com.kcr.domain.entity.Member;
import com.kcr.domain.type.RoleType;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter
public class MemberDTO {

    private Long id;
    private String loginId;
    private String loginPw;
    private String nickname;
    private String stuNum;
    private RoleType roleType = RoleType.ROLE_NOT_PERMITTED;
    private Long activityScore;

    /* 회원 서비스 요청 클래스 */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class Request {

        private Long id;

        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Column(nullable = false, unique = true)
        private String loginId;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        private String loginPw;

        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        @Column(nullable = false, unique = true)
        private String nickname;

        @NotBlank(message = "학번은 필수 입력 값입니다.")
        @Column(unique = true)
        private String stuNum;

        private RoleType roleType = RoleType.ROLE_NOT_PERMITTED;
        private Long activityScore;

        /* DTO -> Entity */
        public Member toEntity() {
            Member member = Member.builder()
                    .id(id)
                    .loginId(loginId)
                    .loginPw(loginPw)
                    .nickname(nickname)
                    .roleType(roleType)
                    .activityScore(activityScore)
                    .build();
            return member;
        }
    }

    /*
     * 인증된 사용자 정보를 세션에 저장하기 위한 클래스
     * 세션을 저장하기 위해 User 엔티티 클래스를 직접 사용하게 되면 직렬화를 해야 하는데,
     * 엔티티 클래스에 직렬화를 넣어주면 추후에 다른 엔티티와 연관관계를 맺을시
     * 직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈 우려가 있기 때문에
     * 세션 저장용 Dto 클래스 생성
     */
    @Getter
    public static class Response implements Serializable {

        private final Long id;
        private final String loginId;
        private final String loginPw;
        private final String nickname;
        private final String stuNum;
        private final RoleType roleType;
        private final Long activityScore;

        /* Entity -> DTO */
        public Response(Member member) {
            this.id = member.getId();
            this.loginId = member.getLoginId();
            this.loginPw = member.getLoginPw();
            this.nickname = member.getNickname();
            this.stuNum = member.getStuNum();
            this.roleType = member.getRoleType();
            this.activityScore = member.getActivityScore();
        }
    }
}
