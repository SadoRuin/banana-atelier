import axios from "axios";

// Access Token이 만료되었거나 만료 시간이 30초 미만이라면 Access Token의 갱신을 위한 함수
export const axiosReissue = () => {
  let expiration = +localStorage.getItem("expiration");
  let nowTime = new Date();
  let nowTime_sec = nowTime.getTime();
  let diffTime = (expiration - nowTime_sec) / 1000;
  if (diffTime < 30) {
    axios
      .post("https://i8a108.p.ssafy.io/api/auth/reissue", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then((response) => {
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("expiration", response.data.expiration);
      })
      .catch((error) => {
        console.log(error);
        localStorage.clear();
        return (window.location.pathname = "/login");
      });
  }
};

// axios 인스턴스 (header에 token이 있고 baseURL 존재)
export const axiosAuth = axios.create({
  baseURL: "https://i8a108.p.ssafy.io/api/",
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`,
  },
});
