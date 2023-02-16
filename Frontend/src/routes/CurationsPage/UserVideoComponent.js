import React, { Component } from 'react';
import OpenViduVideoComponent from './OvVideo';
import styled from 'styled-components';

export const Streamcomponent = styled.div`
    display: flex;
    border-radius: 10px;
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
                <div><p> 🍌{this.getNicknameTag()} 님🍌 </p></div>
            </div>
        );
    }
}
