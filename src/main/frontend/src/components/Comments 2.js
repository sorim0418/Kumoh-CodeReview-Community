import React from 'react'
import styles from '../styles/Comments.module.css'

function Comments({...comments}) {
    const arr = Object.values(comments)[0];

    const len = arr.length;
    
    const renderComments = commentId => (arr)
    .filter(comment => comment.commentParent === commentId)
    .map(comment => (
        <div key={comment.commentId} className={comment.commentParent === null ? styles.commentBox : styles.commentBox2}>
            <p className={styles.pTag1}>{comment.commentAuthor}</p>  <p className={styles.pTag2}>{comment.commentDated}</p> <p className={styles.liked}>{comment.commentLiked}</p>
            <p className={styles.pTag3}>{comment.commentContent}</p>
            {renderComments(comment.commentId)}
        </div>
    ))


  return (
    <div>
      <p className={styles.lenTag}>댓글 [{len}]</p>
      {renderComments(null)}
    </div>
  )
}

export default Comments
