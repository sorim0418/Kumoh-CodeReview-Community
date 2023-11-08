import React from 'react'
import Content from '../components/Content'

const contents = {
    qnaId : 1,
    title: "OOP 질문",
    author: "코린이",
    dated: "2023.09.02",
    hit: 29,
    content: "객체 지향에서는 왜 한 클래스 파일에는 한 클래스만 작성해야 한다는 것을 고수하나요?",
    hashtags: [
        {
            tagId: 1,
            tagName: "OOP"
        },
        {
            tagId: 2,
            tagName: "객체지향"
        }
    ],
    likes: 128,
    comments: [
        {
            commentId: 1,
            commentAuthor: "챗봇",
            commentDated: "2023.09.01 18:02:24",
            commentContent: "객체 지향 프로그래밍에서는 한 클래스 파일에는 일반적으로 한 클래스만 작성하는 것은 코드의 구조화, 유지 보수성, 가독성, 디버깅, 재사용성 등 다양한 이유로 권장됩니다.",
            commentLiked: 0,
            commentParent: null
        },
        {
            commentId: 2,
            commentAuthor: "코등이",
            commentDated: "2023.09.01 18:06:55",
            commentContent: "결론만 말하자면 가장 큰 이유는 단일 책임 원칙을 기초로 하기 때문이에요",
            commentLiked: 0,
            commentParent: null
        },
        {
            commentId: 3,
            commentAuthor: "삼색이",
            commentDated: "2023.09.01 18:11:35",
            commentContent: "멋지다 코린아 ~ 코린이 파이팅!",
            commentLiked: 0,
            commentParent: null
        },
        {
            commentId: 4,
            commentAuthor: "삼다수",
            commentDated: "2023.09.01 18:29:01",
            commentContent: "챗봇 신기행",
            commentLiked: 0,
            commentParent: null
        },
        {
            commentId: 5,
            commentAuthor: "에스파",
            commentDated: "2023.09.01 17:02:59",
            commentContent: "광 야 최 고",
            commentLiked: 0,
            commentParent: null
        },
        {
            commentId: 6,
            commentAuthor: "냠냠이",
            commentDated: "2023.09.01 18:46:11",
            commentContent: "참고로 단일 책임 원칙은 한 클래스가 하나의 역할만을 수행해야한다는 것인데 그것을 가장 큰 이유로 한 클래스 파일에는 하나의 클래스만 담도록 하는 것이에요",
            commentLiked: 13,
            commentParent: 2
        }
    ]
}

function QnADetailPage() {
  return (
    <>
      <Content />
    </>
  )
}

export default QnADetailPage
