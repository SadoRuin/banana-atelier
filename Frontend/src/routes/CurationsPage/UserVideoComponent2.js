import React, { Component } from 'react';
import OpenViduVideoComponent from './OvVideo';
import styled from 'styled-components';

export const Streamcomponent = styled.div`
    display: inline-block;
    overflow: hidden;
    border-radius: 15px;
    height: 100%;
    width: 100%;

    & video {
        width: 100%;
        height: 100%;
        border-radius: 15px;
    }
`;



export default class UserVideoComponent extends Component {

    getNicknameTag() {
        // Gets the nickName of the user
        return JSON.parse(this.props.streamManager.stream.connection.data).clientData;
    }

    render() {
        return (
            <div style={{width: "100px", height: "100px"}}>
                {this.props.streamManager !== undefined ? (
                    <Streamcomponent>
                        <OpenViduVideoComponent style={{width: "100%"}} streamManager={this.props.streamManager} />
                    </Streamcomponent>
                ) : null}
                {/* <div><p> 호스트 : {this.getNicknameTag()}</p></div> */}
            </div>
        );
    }
}
