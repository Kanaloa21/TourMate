import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8088/tourmate",
  // baseURL: "http://192.168.214.75:8088/tourmate",
  headers: {
    // 파일 업로드가 있을 경우 이거 안해놔야함, 형식 지정하면 안된다.
    "Content-Type": "application/json;charset=utf-8", // front -> back으로 보내는 데이터 형식
  },
}); //axios.create()해서  axios객체를 하나 내보낼거야
// 헤더에 자동으로 넣어주도록
