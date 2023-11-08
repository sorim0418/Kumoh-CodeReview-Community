// QnADetailPage.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Content from '../components/Content';
import { useParams } from 'react-router-dom';

function QnADetailPage() {
    const [question, setQuestion] = useState(null);
    const { Id } = useParams(); // URL 파라미터에서 id를 추출

    useEffect(() => {
        axios.get(`/question/${Id}`)
            .then(response => {
                // 성공적으로 데이터를 받아옴
                setQuestion(response.data);
            })
            .catch(error => {
                // 오류 처리
                console.error('There was an error fetching the question details!', error);
            });
    }, [Id]);

    if (!question) {
        return <div>Loading...</div>;
    }

    return (
        <Content question={question}/>
    )
}

export default QnADetailPage;