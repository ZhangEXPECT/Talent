package com.example.talent.service;

import com.example.talent.module.entity.Admin;
import com.example.talent.module.vo.ResultVO;

import java.util.*;


public interface AdminService {

   void add(Admin admin);

   ResultVO delete(Integer id);

   void update(Admin admin);

   Admin queryId(Integer id);

   List<Admin> queryCodition(Admin admin);

   ResultVO login(Admin admin);

}