import axios from "axios";

// axios 인스턴스 (baseURL 존재)
const axiosCustom = axios.create({
  baseURL: "https://i8a108.p.ssafy.io/api/",
});

export default axiosCustom;
