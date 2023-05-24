import http from "./index.js";

const api = http;

async function login(user, success, fail) {
  await api.post(`/user/login`, user).then(success).catch(fail);
}

async function checkAuth(token, success, fail) {
  api.defaults.headers["access-token"] = token;
  await api.get(`/user/checkAuth`).then(success).catch(fail);
}

async function tokenRegeneration(user, token, success, fail) {
  api.defaults.headers["refresh-token"] = token;
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userId, success, fail) {
  await api.get(`/user/logout/${userId}`).then(success).catch(fail);
}

async function getInfo(userId, success, fail) {
  await api.get(`/user/${userId}`).then(success).catch(fail);
}

export { login, checkAuth, tokenRegeneration, logout, getInfo };
