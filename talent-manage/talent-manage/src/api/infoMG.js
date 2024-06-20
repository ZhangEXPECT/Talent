import axios from 'axios';
import { loginreq, req } from './axiosFun';

/**
 * 资讯管理 
 **/
export const addNews = (params) => { return axios.post("/news/add", params) };
export const deleteNews = (params) => { return axios.get("/news/delete", params) };
export const getNews = (params) => { return axios.get("/news/queryById", params) };
export const getAllNews = (params) => { return axios.post("/news/getNews", params) };

export const addPolicy = (params) => { return axios.post("/policy/add", params) };
export const deletePolicy = (params) => { return axios.get("/policy/delete/"+params) };
export const getPolicy = (params) => { return axios.post("/policy/queryById/"+params) };
export const getAllPolicy = (params) => { return req("post","/policy/getPolicy", params) };

export const addVR = (params) => { return axios.post("/vr/add", params) };
export const updateVR = (params) => { return axios.post("/vr/update", params) };
export const deleteVR = (params) => { return axios.get("/vr/delete/"+params) };
export const getVR = (params) => { return axios.get("/vr/queryById/"+params) };
export const getAllVR = (params) => { return req("post","/vr/getVr", params) };



// 获取用户菜单
export const menu = (params) => { return axios.get("/api/menu?&token=" + localStorage.getItem('logintoken')).then(res => res.data) };
// 退出接口
export const loginout = () => { return axios.delete("/api/login?&token=" + localStorage.getItem('logintoken')).then(res => res.data) };
