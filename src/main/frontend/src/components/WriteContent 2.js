import React from "react";
import styles from '../styles/WriteContent.module.css'

const placeHolderText = '내용을 입력해주세요.';

function WriteContent({id}) {
  const className = id === 1 ? styles.content : styles.WriteContent;

  return(
    <div>
      <textarea placeholder={placeHolderText} className={className} rows="10"/>
    </div>
  )
}

export default WriteContent