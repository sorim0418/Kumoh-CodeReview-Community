// Board.js

import React from 'react';

function Board({ questions }) {
    return (
        <div>
            <h1>질문 목록</h1>
            {questions.length > 0 ? (
                <ul>
                    {questions.map((question) => (
                        <li key={question.id}>
                            <h2>{question.title}</h2>
                            <p>{question.content}</p>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>질문이 없습니다.</p>
            )}
        </div>
    );
}

export default Board;
