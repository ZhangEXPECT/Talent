import axios from 'axios';
import { loginreq, req } from './axiosFun';
/**
 * 入住管理 
 **/
export const addNews = (params) => { return axios.post("/news/add", params) };
export const deleteNews = (params) => { return axios.get("/news/delete", params) };
export const getNews = (params) => { return axios.get("/news/queryById", params) };
export const getAllNews = (params) => { return axios.post("/news/getNews", params) };

/**
 * 补贴申请管理 
 **/
export const addSubsidy = (params) => { return req("post","/subsidy/add", params) };
export const deleteSubsidy = (params) => { return axios.get("/subsidy/delete/"+ params) };
export const getSubsidyById = (params) => { return axios.get("/subsidy/queryById/" +params) };
export const getSubsidyByUserId = (params) => { return axios.get("/subsidy/queryByUserId/"+params) };
export const getAllSubsidy = (params) => { return axios.post("/subsidy/getSubsidy", params) };
export const updateSubsidy = (params) => { return axios.post("/subsidy/update", params) };
export const querySubsidyByUser = (params) => { return axios.post("/subsidy/queryByUser", params) };

/**
 * 资格审核
 **/
export const addQualication = (params) => { return req("post","/qualification/add", params) };
export const deleteQualication = (params) => { return axios.get("/qualification/delete/"+ params) };
export const getQualicationById = (params) => { return axios.get("/qualification/queryById/" +params) };
export const getQualicationByUserId = (params) => { return axios.get("/qualification/queryByUserId/"+params) };
export const getAllQualication = (params) => { return axios.post("/qualification/getQualification", params) };
export const updateQualication = (params) => { return axios.post("/qualification/update", params) };
export const queryQualicationByUser = (params) => { return axios.post("/qualification/queryByUser", params) };


export const addAppoint = (params) => { return axios.post("/appoint/add", params) };
export const updateAppoint = (params) => { return axios.post("/appoint/update",params) };
export const deleteAppoint = (params) => { return axios.get("/appoint/delete/"+params) };
export const getAppoint = (params) => { return axios.get("/appoint/queryById/"+params) };
export const getAllAppoint = (params) => { return axios.post("/appoint/getAppointByStatus", params) };


export const addCheck = (params) => { return axios.post("/check/add", params) };
export const updateCheck = (params) => { return axios.post("/check/update",params) };
export const deleteCheck = (params) => { return axios.get("/check/delete/"+params) };
export const getCheck = (params) => { return axios.get("/check/queryById/"+params) };
export const getAllCheck = (params) => { return axios.post("/check/getCheckByStatus",params) };