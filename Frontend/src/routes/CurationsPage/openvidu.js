import React, { Component } from "react";
import { OpenVidu } from "openvidu-browser";
import { useParams, useLoaderData } from "react-router-dom";
import axios from "axios";

// import './App.css';
import { axiosAuth } from "../../_actions/axiosAuth";
import UserVideoComponent from "./UserVideoComponent";
import UserVideoComponent2 from "./UserVideoComponent2";
import CurationInfo from "../../components/Curations/curationInfo";
// import OpenViduVideoComponent from './OvVideo';
import styled from 'styled-components';
import './openvidu.css'
import { RedBtn } from "../../components/commons/buttons";


// 어플리케이션 서버의 url
// const APPLICATION_SERVER_URL = "http://localhost:4443/";
const APPLICATION_SERVER_URL = "https://i8a108.p.ssafy.io:8447";
const OPENVIDU_SERVER_SECRET = "dnjftlr";

const Frame = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 25px;
`
const LeftSide = styled.div`
  grid-column: 1/span 1;
`
const RightSide = styled.div`
  grid-column: 2/span 2;
`

export async function loader ({params}) {
  const curationSeq = params.curation_seq;
  const curationArtsList = await axiosAuth(`curation-art/list/${curationSeq}`)
    .then(response => response.data);
  const artistSeq = await curationArtsList[0].artistSeq

  return [curationArtsList, artistSeq]
}

// 클래스형 컴포넌트에서 params 가져오기
function withParams(Component) {
  return props => <Component {...props} params={useParams()} loader={useLoaderData()} />;
}

class Openvidu extends Component {
  constructor(props) {
    super(props);
    let userSeq = localStorage.getItem("userSeq")
    // 세션 ID, 유저 이름, 메인 스트리밍 화면, publisher(방장), subscribers(시청자) 세팅
    // These properties are in the state's component in order to re-render the HTML whenever their values change
    let curationSeq = props.params.curation_seq;
    let [curationArtsList, artistSeq] = props.loader;
    this.state = {
      curationSeq: curationSeq,
      curationArtList: curationArtsList,
      artistSeq: artistSeq,
      mySessionId: userSeq,
      myUserName: localStorage.getItem("nickname"),
      // myUserName: "Participant" + Math.floor(Math.random() * 100),
      session: undefined,
      mainStreamManager: undefined, // Main video of the page. Will be the 'publisher' or one of the 'subscribers'
      publisher: undefined,
      subscribers: [],
    };

    this.joinSession = this.joinSession.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    // this.switchCamera = this.switchCamera.bind(this);
    this.handleChangeSessionId = this.handleChangeSessionId.bind(this);
    this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.handleMainVideoStream = this.handleMainVideoStream.bind(this);
    this.onbeforeunload = this.onbeforeunload.bind(this);
  }

  componentDidMount() {
    window.addEventListener("beforeunload", this.onbeforeunload);
  }

  componentWillUnmount() {
    window.removeEventListener("beforeunload", this.onbeforeunload);
  }

  onbeforeunload(event) {
    this.leaveSession();
  }

  handleChangeSessionId(e) {
    this.setState({
      mySessionId: e.target.value,
    });
  }

  handleChangeUserName(e) {
    this.setState({
      myUserName: e.target.value,
    });
  }

  handleMainVideoStream(stream) {
    if (this.state.mainStreamManager !== stream) {
      this.setState({
        mainStreamManager: stream,
      });
    }
  }

  deleteSubscriber(streamManager) {
    let subscribers = this.state.subscribers;
    let index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      subscribers.splice(index, 1);
      this.setState({
        subscribers: subscribers,
      });
    }
  }

  joinSession() {
    // --- 1) Get an OpenVidu object ---
    this.OV = new OpenVidu();

    // --- 2) Init a session ---
    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        var mySession = this.state.session;

        // --- 3) Specify the actions when events take place in the session ---

        // On every new Stream received...
        mySession.on("streamCreated", (event) => {
          // Subscribe to the Stream to receive it. Second parameter is undefined
          // so OpenVidu doesn't create an HTML video by its own
          var subscriber = mySession.subscribe(event.stream, undefined);
          var subscribers = this.state.subscribers;
          subscribers.push(subscriber);

          // Update the state with the new subscribers
          this.setState({
            subscribers: subscribers,
          });
        });

        // On every Stream destroyed...
        mySession.on("streamDestroyed", (event) => {
          // Remove the stream from 'subscribers' array
          this.deleteSubscriber(event.stream.streamManager);
        });

        // On every asynchronous exception...
        mySession.on("exception", (exception) => {
          console.warn(exception);
        });

        // --- 4) Connect to the session with a valid user token ---

        // Get a token from the OpenVidu deployment
        this.getToken().then((token) => {
          // First param is the token got from the OpenVidu deployment. Second param can be retrieved by every user on event
          // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
          mySession
            .connect(token, { clientData: this.state.myUserName })
            .then(async () => {
              // --- 5) Get your own camera stream ---

              // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
              // element: we will manage it on our own) and with the desired properties
              let publisher = await this.OV.initPublisherAsync(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                publishVideo: true, // Whether you want to start publishing with your video enabled or not
                resolution: "640x480", // The resolution of your video
                frameRate: 30, // The frame rate of your video
                insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                mirror: false, // Whether to mirror your local video or not
              });

              // --- 6) Publish your stream ---

              mySession.publish(publisher);

              // Obtain the current video device in use
              var devices = await this.OV.getDevices();
              var videoDevices = devices.filter((device) => device.kind === "videoinput");
              var currentVideoDeviceId = publisher.stream
                .getMediaStream()
                .getVideoTracks()[0]
                .getSettings().deviceId;
              var currentVideoDevice = videoDevices.find(
                (device) => device.deviceId === currentVideoDeviceId
              );

              // Set the main video in the page to display our webcam and store our Publisher
              this.setState({
                currentVideoDevice: currentVideoDevice,
                mainStreamManager: publisher,
                publisher: publisher,
              });
            })
            .catch((error) => {
              console.log(
                "There was an error connecting to the session:",
                error.code,
                error.message
              );
            });
        });
      }
    );
  }

  leaveSession() {
    // --- 7) Leave the session by calling 'disconnect' method over the Session object ---

    const mySession = this.state.session;

    if (mySession) {
      mySession.disconnect();
    }

    // Empty all properties...
    this.OV = null;
    this.setState({
      session: undefined,
      subscribers: [],
      mySessionId: this.state.userSeq,
      myUserName: localStorage.getItem("nickname"),
      // myUserName: "Participant" + Math.floor(Math.random() * 100),
      mainStreamManager: undefined,
      publisher: undefined,
    });
  }

  async getToken() {
    const sessionId = await this.createSession(this.state.mySessionId);
    return await this.createToken(sessionId);
  }

  render() {
    const mySessionId = this.state.mySessionId;
    const nickname = localStorage.getItem("nickname")
    console.log(this.state.curationSeq);
    console.log(this.state.curationArtList);
    console.log(this.state.artistSeq)
    // const myUserName = this.state.myUserName;
    return (
      <div>
        {/* 처음에 생성할 때 html */}
        {this.state.session === undefined ? (
          <div id="join">
            <div id="img-div">
              {/* <img src="resources/images/openvidu_grey_bg_transp_cropped.png" alt="OpenVidu logo" /> */}
            </div>
            <div id="join-dialog" className="jumbotron vertical-center">
              <h1> 큐레이션 시작하기 </h1>
              <form className="form-group" onSubmit={this.joinSession}>
                <p>
                  <label> Session : </label>
                    <input
                      className="form-control"
                      type="text"
                      id="sessionId"
                      value={mySessionId}
                      onChange={this.handleChangeSessionId}
                      required
                      style={{width: "30px"}}
                    />
                </p>
                <p className="text-center">
                  <input
                    className="btn btn-lg btn-success"
                    name="commit"
                    type="submit"
                    value="시작"
                  />
                </p>
              </form>
            </div>
          </div>
        ) : null}

        {/* 큐레이션 화면 */}
        {this.state.session !== undefined ? (
          <div id="session">
            <div id="session-header">
              <h1 id="session-title">🍌{nickname}🍌 작가님의 큐레이션</h1>
              {/*<RedBtn*/}
              {/*  type="button"*/}
              {/*  id="buttonLeaveSession"*/}
              {/*  onClick={this.leaveSession}*/}
              {/*  value="나가기"*/}
              {/*>*/}
              {/*  나가기*/}
              {/*</RedBtn>*/}
            </div>

            
            <Frame>

              <LeftSide>
                {this.state.mainStreamManager !== undefined ? (
                  <div id="main-video" >
                    <UserVideoComponent streamManager={this.state.mainStreamManager} />
                  </div>
                ) : null}
              </LeftSide>

              <RightSide>

                <CurationInfo
                  curationArtsList={this.state.curationArtList}
                  outBtn={<RedBtn
                    type="button"
                    id="buttonLeaveSession"
                    onClick={this.leaveSession}
                    value="나가기"
                    >
                      나가기
                    </RedBtn>} />

                <div id="video-container" >
                  {/* {this.state.publisher !== undefined ? (
                    <div className="stream-container col-md-6 col-xs-6" onClick={() => this.handleMainVideoStream(this.state.publisher)}>
                      <UserVideoComponent
                      streamManager={this.state.publisher} />
                    </div>
                  ) : null} */}
                  {this.state.subscribers.map((sub, i) => (
                    <div key={i}  onClick={() => this.handleMainVideoStream(sub)}>
                      <UserVideoComponent2 streamManager={sub} />
                    </div>
                  ))}

                  {/* {this.state.publisher !== undefined ? (
                    <div> {this.state.publisher} </div>
                    ) : null}
                  {this.state.subscribers !== undefined ? (this.state.subscribers) : null} */}       
                </div>
              
              </RightSide>
            </Frame>
          </div>
        ) : null}
      </div>
    );
  }
  
  /**
   * --------------------------------------------
   * GETTING A TOKEN FROM YOUR APPLICATION SERVER
   * --------------------------------------------
   * The methods below request the creation of a Session and a Token to
   * your application server. This keeps your OpenVidu deployment secure.
  *
  * In this sample code, there is no user control at all. Anybody could
  * access your application server endpoints! In a real production
  * environment, your application server must identify the user to allow
  * access to the endpoints.
  *
  * Visit https://docs.openvidu.io/en/stable/application-server to learn
   * more about the integration of OpenVidu in your application server.
  */
 
 createSession(sessionId) {
   return new Promise((resolve, reject) => {
     var data = JSON.stringify({ customSessionId: sessionId });
     axios
     .post(APPLICATION_SERVER_URL + "/openvidu/api/sessions", data, {
       headers: {
            Authorization: "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          console.log("CREATE SESION", response);
          resolve(response.data.id);
        })
        .catch((response) => {
          var error = Object.assign({}, response);
          if (error.response && error.response.status === 409) {
            resolve(sessionId);
          } else {
            console.log(error);
            console.warn(
              "No connection to OpenVidu Server. This may be a certificate error at " +
                APPLICATION_SERVER_URL
            );
            if (
              window.confirm(
                'No connection to OpenVidu Server. This may be a certificate error at "' +
                  APPLICATION_SERVER_URL +
                  '"\n\nClick OK to navigate and accept it. ' +
                  'If no certificate warning is shown, then check that your OpenVidu Server is up and running at "' +
                  APPLICATION_SERVER_URL +
                  '"'
              )
            ) {
              window.location.assign(APPLICATION_SERVER_URL + "/accept-certificate");
            }
          }
        });
    });
  }

  createToken(sessionId) {
    return new Promise((resolve, reject) => {
      var data = JSON.stringify({});
      axios
        .post(
          APPLICATION_SERVER_URL + "/openvidu/api/sessions/" + sessionId + "/connection",
          data,
          {
            headers: {
              Authorization: "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          console.log("TOKEN", response);
          resolve(response.data.token);
        })
        .catch((error) => reject(error));
    });
  }
}

export default withParams(Openvidu);
