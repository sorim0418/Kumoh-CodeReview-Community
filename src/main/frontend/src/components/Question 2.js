import React from 'react'
import styles from '../styles/Question.module.css'
import { useHistory } from 'react-router-dom'

function Question({Id, title, writer, dated, hit, numOfComments}) {
  const history = useHistory();

  return (
    <li key={Id} className={styles.li} onClick={() => history.push(`/question/${Id}`)}>
      <div className={styles.title}>{title} {numOfComments !== 0 ? `[${numOfComments}]` : ''}</div>
      <div className={styles.writer}>{writer}</div>
      <div className={styles.dated}>{dated}</div>
      <div className={styles.hit}>{hit}</div>
    </li>
  )
}

export default Question
