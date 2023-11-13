import React from "react";
import styles from '../styles/WriteBoard.module.css'
import WriteTitle from "./WriteTitle";
import WriteContent from "./WriteContent";
import ImageButton from "./ImageButton";
import WriteButton from "./WriteButton";

function WriteBoard() {
  return(
    <div className={styles.container}>
    <div className={styles.WriteBoard}>
      <WriteTitle />
      <div className={styles.Line}></div>
      <div className={styles.Line2}></div>
      <WriteContent id={1}/>
    </div>
    <ImageButton/><WriteButton id={3}/>
    </div>
  )
}

export default WriteBoard