import React from 'react';
import styles from '../styles/Content.module.css' // 스타일링을 위한 CSS 모듈

// props에서 question 객체를 구조 분해 할당으로 추출
const Content = ({ question }) => {
    // question 객체가 존재하지 않을 때 처리할 로직
    if (!question) {
        return <p>Loading...</p>;
    }

    // 게시글의 상세 내용을 렌더링
    return (
        <div className={styles.content}>
            <h1 className={styles.title}>{question.title}</h1>
            <p className={styles.writer}>작성자: {question.writer}</p>
            <div className={styles.details}>
                <span>좋아요 수: {question.likes}</span>
                <span>조회 수: {question.view}</span>
            </div>
            <div className={styles.body}>
                {question.content}
            </div>
            {/* 여기에 댓글 섹션 등 추가 컴포넌트를 배치할 수 있습니다. */}
        </div>
    );
};

export default Content;

// import React from 'react'
// import styles from '../styles/Content.module.css'
// import QnAContentHeader from './QnAContentHeader'
// import QnAContentBox from './QnAContentBox'
// import WriteContent from './WriteContent'
// import Comments from './Comments'
// import WriteButton from './WriteButton'
//
// const contents = {
//     Id : 1,
//     title: "OOP 질문",
//     author: "코린이",
//     dated: "2023.09.02",
//     hit: 29,
//     content: "객체 지향에서는 왜 한 클래스 파일에는 한 클래스만 작성해야 한다는 것을 고수하나요?",
//     hashtags: [
//         {
//             tagId: 1,
//             tagName: "OOP"
//         },
//         {
//             tagId: 2,
//             tagName: "객체지향"
//         }
//     ],
//     likes: 128,
//     comments: [
//         {
//             commentId: 1,
//             commentAuthor: "챗봇",
//             commentDated: "2023.09.01 18:02:24",
//             commentContent: "객체 지향 프로그래밍에서는 한 클래스 파일에는 일반적으로 한 클래스만 작성하는 것은 코드의 구조화, 유지 보수성, 가독성, 디버깅, 재사용성 등 다양한 이유로 권장됩니다.",
//             commentLiked: 0,
//             commentParent: null
//         },
//         {
//             commentId: 2,
//             commentAuthor: "코등이",
//             commentDated: "2023.09.01 18:06:55",
//             commentContent: "결론만 말하자면 가장 큰 이뉴는 단일 책임 원칙을 기초로 하기 때문이에요",
//             commentLiked: 0,
//             commentParent: null
//         },
//         {
//             commentId: 3,
//             commentAuthor: "삼색이",
//             commentDated: "2023.09.01 18:11:35",
//             commentContent: "멋지다 코린아 ~ 코리낭 파이팅!",
//             commentLiked: 0,
//             commentParent: null
//         },
//         {
//             commentId: 4,
//             commentAuthor: "삼다수",
//             commentDated: "2023.09.01 18:29:01",
//             commentContent: "챗봇 신기행",
//             commentLiked: 0,
//             commentParent: null
//         },
//         {
//             commentId: 5,
//             commentAuthor: "에스파",
//             commentDated: "2023.09.01 17:02:59",
//             commentContent: "광 야 최 고",
//             commentLiked: 0,
//             commentParent: null
//         },
//         {
//             commentId: 6,
//             commentAuthor: "냠냠이",
//             commentDated: "2023.09.01 18:46:11",
//             commentContent: "참고로 단일 책임 원칙은 한 클래스가 하나의 역할만을 수행해야한다는 것인데 그것을 가장 큰 이유로 한 클래스 파일에는 하나의 클래스만 담도록 하는 것이에요",
//             commentLiked: 13,
//             commentParent: 2
//         },
//         {
//             commentId: 7,
//             commentAuthor: "냠이",
//             commentDated: "2023.10.31 18:46:11",
//             commentContent: "참고로 는 것인데 그것을 가장 큰 이유로 한 클래스 파일에는 하나의 클래스만 담도록 하는 것이에요",
//             commentLiked: 2,
//             commentParent: 4
//         },
//         {
//             commentId: 8,
//             commentAuthor: "장원영",
//             commentDated: "2023.12.25 17:02:59",
//             commentContent: "다이브 최 고",
//             commentLiked: 100000,
//             commentParent: null
//         }
//     ]
// }
//
// function Content() {
//   return (
//     <div className={styles.contentBox}>
//       <QnAContentHeader title={contents.title} author={contents.author} dated={contents.dated} hit={contents.hit}/>
//
//       <hr className={styles.hr}/>
//         <QnAContentBox content={contents.content} liked={contents.likes} hashtags={contents.hashtags}/>
//
//         <hr className={styles.hr}></hr>
//
//         <WriteContent id={2}/> <WriteButton id={1}/>
//
//         <hr className={styles.hr}></hr>
//         <Comments comments={contents.comments}/>
//     </div>
//   )
// }
//
// export default Content
