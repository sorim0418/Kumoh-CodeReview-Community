import React from 'react'
import styles from '../styles/Question.module.css'
import { useHistory } from 'react-router-dom'

function Question({id, title, writer, createDate, likes, views}) {
    const history = useHistory();

    return (
        <li key={id} className={styles.li} onClick={() => history.push(`/question/${id}`)}>
            <div className={styles.title}>{title} {likes !== 0 ? `[${likes}]` : ''}</div>
            <div className={styles.writer}>{writer}</div>
            <div className={styles.dated}>{createDate}</div>
            <div className={styles.hit}>{views}</div>
        </li>
    )
}

export default Question