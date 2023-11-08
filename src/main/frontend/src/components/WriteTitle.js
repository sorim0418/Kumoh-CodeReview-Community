import React from "react";
import styles from '../styles/WriteTitle.module.css'

const placeHolderText = '제목을 입력해주세요.';

function WriteTitle() {
  return(
    <div>
      <input type='text' placeholder={placeHolderText} className={styles.title}/>
    </div>
  )
}

export default WriteTitle