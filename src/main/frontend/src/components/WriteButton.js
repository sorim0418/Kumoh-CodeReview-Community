import React from "react";
import styles from '../styles/WriteButton.module.css'
import { useHistory } from "react-router-dom";

function WriteButton({id}) {
  const history = useHistory();

  const locate = () => {
    if (id === 2) {
      history.push(`/question/add`)
    }
    else if (id === 3) {
      alert('글 작성이 완료되었습니다.')
      history.push(`/`)
    }
  }

  return (
    <div className={styles.WriteButtonDiv}>
      <button type='submit' className={id !== 1 ? styles.WriteButton : styles.EnrollButton} onClick={() => locate()}>{id !== 1 ? '글쓰기' : '댓글 등록'}</button>
    </div>
  )
}

export default WriteButton