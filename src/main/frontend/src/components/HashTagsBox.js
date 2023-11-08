import React from 'react'
import styles from '../styles/HashTagsBox.module.css'

function HashTagsBox({likes, ...hashtags}) {
    const hashtag = Object.values(hashtags)[0].hashtags;
    
  return (
    <div className={styles.container}>
        <div className={styles.hashTagBox}>
            {hashtag.map(tag => (
                <p key={tag.tagId} className={styles.hashTag}>#{tag.tagName}</p>
            ))}
      </div>
      <p className={styles.likes}>{likes}</p>
    </div>
  )
}

export default HashTagsBox
