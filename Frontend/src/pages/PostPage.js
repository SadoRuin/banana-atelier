import React from "react";
import { Link } from "react-router-dom";

const PostPage = () => {
    return <div>
        포스트 읽기
        <p><Link to='/login'>로그인페이지로</Link></p>
    </div>
}

export default PostPage