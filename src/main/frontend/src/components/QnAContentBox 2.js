import React from 'react'
import styles from '../styles/QnAContentBox.module.css'
import HashTagsBox from './HashTagsBox'

function QnAContentBox({content, liked, ...hastags}) {
  return (
    <div className={styles.container}>
      <p className={styles.contentBox}>{content}</p>

      <HashTagsBox likes={liked} hashtags={hastags}/>
    </div>
  )
}

export default QnAContentBox
