import React, { Component } from 'react';
import OpenViduVideoComponent from './OvVideo';
import styled from 'styled-components';

export const Streamcomponent = styled.div`
    display: inline-block;
    overflow: hidden;
    border-radius: 15px;
    height: 80px;
    width: 180px;
  `;



export default class UserVideoComponent extends Component {

    getNicknameTag() {
        // Gets the nickName of the user
        return JSON.parse(this.props.streamManager.stream.connection.data).clientData;
    }

    render() {
        return (
            <div>
                {this.props.streamManager !== undefined ? (
                    <Streamcomponent>
                        <OpenViduVideoComponent streamManager={this.props.streamManager} />
                    </Streamcomponent>
                ) : null}
                <div><p> 호스트 : {this.getNicknameTag()}</p></div>
            </div>
        );
    }
}
