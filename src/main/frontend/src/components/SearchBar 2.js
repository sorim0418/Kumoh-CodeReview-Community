import React from 'react'
import styles from '../styles/SearchBar.module.css'

const placeHolderText = '검색어를 입력해주세요.';

function SearchBar() {
  return (
    <div className={styles.searchBar}>
      <input type='text' placeholder={placeHolderText} className={styles.textField}/>
      <button type='submit' className={styles.searchBtn}>검색</button>
    </div>
  )
}

export default SearchBar
