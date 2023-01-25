import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

// 회원가입 혹은 로그인 페이지의 레이아웃을 담당하는 컴포넌트입니다.

const AuthTemplateBlock = styled.div``

const AuthTemplate = ({children}) => {
    return (
        <AuthTemplateBlock>
            <div>
                <Link to="/">
                    홈으로
                </Link>
            </div>
            {children}
        </AuthTemplateBlock>
    )
}

export default AuthTemplate