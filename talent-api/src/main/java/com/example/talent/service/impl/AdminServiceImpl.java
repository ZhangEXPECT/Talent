package com.example.talent.service.impl;

import com.example.talent.dao.AdminDao;
import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Admin;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

   @Autowired
   AdminDao admindao;
   public void add(Admin admin) {
      // TODO: implement
      admindao.add(admin);

   }


   public ResultVO delete(Integer id) {
      // TODO: implement
      admindao.delete(id);
      return new ResultVO(ResultEnum.DELETE_SUCCESS);
   }


   public void update(Admin admin) {
      // TODO: implement
      admindao.update(admin);
   }


   public Admin queryId(Integer id) {
      // TODO: implement
     return admindao.queryId(id);
   }


   public List<Admin> queryCodition(Admin admin) {
      // TODO: implement
     return admindao.queryCondition(admin);
   }

   /**
    * 登录模块
    * @param admin
    * @return
    */
   public ResultVO login(Admin admin) {
      Admin a = new Admin();
      a.setAdminName(admin.getAdminName());

      List<Admin> list = admindao.queryCondition(a);
      System.out.println(list.get(0).getAdminPwd());
      if (list == null || list.isEmpty()) {
         return new ResultVO(ResultEnum.FAIL, "该账号不存在");
      } else if (list.get(0).getAdminPwd().equals(admin.getAdminPwd())) {
         return new ResultVO(list.get(0));
      } else {
         return new ResultVO(ResultEnum.FAIL, "密码错误");
      }
   }

}