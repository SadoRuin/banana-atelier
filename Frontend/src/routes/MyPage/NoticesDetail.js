import React from 'react';
import {RedBtn, YellowBtn} from "../../components/commons/buttons";
import styles from "./Notice.module.css";

export function loader({params}) {
    // const notice = getNotice(+params.notice_id)
    // return notice;
}

function NoticesDetail({noticeContent}) {
    // const noticeData = useLoaderData();
    // const navigate = useNavigate();
    return (
        <div>
            <div className={styles["notice-content"]}>{noticeContent}</div>
            <div className={styles["buttons"]}>
                <YellowBtn className={styles["button"]}>수정하기</YellowBtn>
                <RedBtn className={styles["button"]}>삭제하기</RedBtn>
            </div>
        </div>
    );
}

export default NoticesDetail;