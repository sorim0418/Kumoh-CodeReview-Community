import React from 'react'
import styles from '../styles/Header.module.css'
import {Link} from "react-router-dom";
import { BsFillBellFill } from "react-icons/bs";
import { TbMinusVertical } from "react-icons/tb";
import kitLogo from "../image/kit_LOGO.png";

function Header() {
    return (
        <div className={styles.Header}>
            <div className={styles.header_wapper}>
                <div className={styles.logo_wapper}>
                    <Link to="/">
                        <img className={styles.kitLogo} src= {kitLogo} alt="kit_logo_img"></img>
                    </Link>
                </div>


                <div className={styles.QnA_wapper}>
                    <a className={styles.goToQnA}>
                        <span className={styles.QnA}>Q&A</span>
                    </a>
                </div>

                <TbMinusVertical />

                <div className={styles.Code_wapper}>
                    <a className={styles.goToCode}>
                        <span className={styles.Code}>Code Review</span>
                    </a>
                </div>

                <TbMinusVertical />

                <div className={styles.tag_wapper}>
                    <a className={styles.goToTag}>
                        <span className={styles.tag}>Tag</span>
                    </a>
                </div>


                <div className={styles.alarm_wapper}>
                    <div className={styles.alarm}>
                        <BsFillBellFill />
                    </div>
                </div>

                <div className={styles.serch_wapper}>
                    <div className={styles.login_wapper}>
                        <input className={styles.search_input} placeholder="내용을 입력해주세요 :)"></input>
                        <button className={styles.search_button}>검색</button>
                    </div>
                </div>

                <div className={styles.user_wapper}>
                    <button className={styles.login} href="">로그인</button>
                    <button className={styles.register} href="">회원가입</button>
                </div>
            </div>
        </div>
    )
}

export default Header