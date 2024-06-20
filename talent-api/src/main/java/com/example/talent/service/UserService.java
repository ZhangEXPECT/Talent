package com.example.talent.service;

import com.example.talent.module.entity.User;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.UserVO;


public interface UserService {

   void add(User user);

   ResultVO delete(Integer userId);

   ResultVO update(User user);

   ResultVO queryId(Integer userId);

   ResultVO register(User user);

   ResultVO login(User user);

   User smsLogin(String phone);
   String getToken(User user);

   ResultVO queryCondition(User user);

   ResultVO updatePassword(String oldPassword, String newPassword,Integer userId);

   void updateAvatar();//更新头像

   ResultVO getCollect(Integer userId);

   ResultVO getUser(UserVO userVO);

}