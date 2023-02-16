import React, {useState} from 'react';
import {RedBtn, WhiteBtn, YellowBtn} from "../../components/commons/buttons";
import styles from "./Notice.module.css";
import {Form} from 'react-router-dom'

export function loader({params}) {
    // const notice = getNotice(+params.notice_id);
    // return notice;
}

function NoticesDetail({userSeq, noticeSeq, notice_title, notice_content}) {
    const [noticeTitle, setNoticeTitle] = useState(notice_title);
    const [noticeContent, setNoticeContent] = useState(notice_content);
    const [isUpdate, setIsUpdate] = useState(false);

    const handleTitleChange = e => {
        console.log(e.target.value)
        setNoticeTitle(e.target.value)
    }
    const handleContentChange = e => {
        console.log(e.target.value)
        setNoticeContent(e.target.value)
    }

    return (
        <div>
            {isUpdate ?
                <Form method="put" className={styles["form"]} onSubmit={() => {
                    setIsUpdate(prev => !prev)
                }}>
                    <input type="hidden" name="noticeSeq" defaultValue={`${noticeSeq}`}/>
                    <div className={styles["label-input"]}>
                        <label className={styles["label-of-form"]}> TITLE </label>
                        <input className={styles["input-of-form"]} type="text" name="noticeTitle"
                               defaultValue={`${noticeTitle}`}
                               onChange={handleTitleChange}
                        />
                    </div>
                    <div className={styles["label-input"]}>
                        <label className={styles["label-of-form"]}> CONTENT </label>
                        <textarea name="noticeContent" id="notice_content" cols="30"
                                  rows="10" defaultValue={`${noticeContent}`} onChange={handleContentChange}></textarea>
                    </div>
                    <div className={styles["buttons"]} onSubmit={() => {
                        setIsUpdate(prev => !prev)
                    }}>
                        <YellowBtn className={styles["button"]} type="submit" onSubmit={() => {
                            setIsUpdate(prev => !prev)
                        }}> 수 정 </YellowBtn>
                        <WhiteBtn className={styles["button"]}
                                  type="button" onClick={() => {
                            setIsUpdate(prev => !prev)
                        }}> 취 소 </WhiteBtn>
                    </div>
                </Form>
                :
                <div>
                    <div className={styles["notice-content"]}>
                        {noticeContent}
                    </div>
                    <div className={styles["buttons"]}>
                        <YellowBtn className={styles["button"]} type="button" onClick={() => {
                            setIsUpdate(prev => !prev)
                        }}> 수 정 </YellowBtn>

                        <Form method="delete" onSubmit={() => {
                            setIsUpdate(prev => !prev)
                        }}>
                            <input type="hidden" name="noticeSeq" defaultValue={`${noticeSeq}`}/>
                            <RedBtn className={styles["button"]} type="submit" onClick={() => {
                                setIsUpdate(prev => !prev)
                            }}> 삭 제 </RedBtn>
                        </Form>
                    </div>
                </div>
            }
        </div>
    );
}

export default NoticesDetail;