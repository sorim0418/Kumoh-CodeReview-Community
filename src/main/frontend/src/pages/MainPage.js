// MainPage.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Board from '../components/Board';

function MainPage() {
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        // 백엔드에서 질문 목록을 가져오는 비동기 함수
        const fetchQuestions = async () => {
            try {
                const response = await axios.get('/question'); // 백엔드 API 엔드포인트에 맞게 수정
                setQuestions(response.data);
            } catch (error) {
                console.error('질문을 불러오는 도중 오류가 발생했습니다:', error);
            }
        };

        // 페이지가 로드될 때 질문 목록을 불러옴
        fetchQuestions();
    }, []);

    return (
        <div>
            <h1>질문 목록</h1>
            <Board questions={questions} />
        </div>
    );
}

export default MainPage;
