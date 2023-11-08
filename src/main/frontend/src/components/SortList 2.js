import React from 'react'
import styles from '../styles/SortList.module.css'

const options = [
    {
        "value" : 1,
        "name" : "최신순"
    },
    {
        "value" : 2,
        "name" : "공감순"
    }
]

function SortList() {
  return (
    <select className={styles.selectBox}>
      {options.map(option => (
        <option value={option.value} key={option.value} className={styles.optionStyle}>{option.name}</option>
      ))}
    </select>
  )
}

export default SortList
