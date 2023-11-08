import React, { useEffect, useState } from 'react';
import axios from 'axios';
import WriteBoard from '../components/WriteBoard';
import Title from '../components/Title';

function WritePage() {
    const [questionData, setQuestionData] = useState(null);

    useEffect(() => {
        // Define a function to fetch data from the backend API
        async function fetchData() {
            try {
                const response = await axios.get('/api/question'); // Replace with your actual API endpoint
                setQuestionData(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        }

        // Call the fetchData function when the component mounts
        fetchData();
    }, []);

    return (
        <>
            <Title />
            <WriteBoard />
            {questionData && (
                <div>
                    {/* Display the data received from the backend */}
                    {questionData.map((question) => (
                        <div key={question.id}>
                            <h3>{question.title}</h3>
                            <p>{question.content}</p>
                        </div>
                    ))}
                </div>
            )}
        </>
    );
}

export default WritePage;
