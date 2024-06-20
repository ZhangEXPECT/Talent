package com.example.talent.service.impl;

import com.example.talent.dao.UserDao;
import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.UserVO;
import com.example.talent.service.UserService;
//import com.example.talent.utils.JwtUtils;
import com.example.talent.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public void add(User user) {
        userDao.add(user);
    }


    public ResultVO delete(Integer userId) {
        User u = new User();
        u.setUserId(userId);
        if (userDao.queryCondition(u).isEmpty()) {
            return new ResultVO(ResultEnum.DELETE_FAIL, "用户不存在");
        } else {
            userDao.delete(userId);
            return new ResultVO(ResultEnum.DELETE_SUCCESS);
        }
    }


    public ResultVO update(User user) {
        User u = new User();
        u.setUserId(user.getUserId());
        if (userDao.queryCondition(u).isEmpty()) {
            return new ResultVO(ResultEnum.UPDATE_FAIL, "用户不存在");
        } else {
            userDao.update(user);
            return new ResultVO(ResultEnum.UPDATE_SUCCESS);
        }
    }


    public ResultVO queryId(Integer userId) {
        return new ResultVO(userDao.queryId(userId));
    }


    public ResultVO register(User user) {
        User u = new User();
        String nick = "User " + new Date();
        u.setNick(nick);
        u.setUserName(user.getUserName());

        List<User> list = userDao.queryCondition(u);
        if (list.isEmpty()) {
            u.setPassword(user.getPassword());
            userDao.add(u);
            return new ResultVO(ResultEnum.SUCCESS, "注册成功");
        } else {
            return new ResultVO(ResultEnum.FAIL, "该账号已存在");
        }
    }


    public ResultVO login(User user) {
        User u = new User();
        u.setUserName(user.getUserName());

        List<User> list = userDao.queryCondition(u);
        if (list == null || list.isEmpty()) {
            return new ResultVO(ResultEnum.FAIL, "该账号不存在");
        } else if (list.get(0).getPassword().equals(user.getPassword())) {
            return new ResultVO(list.get(0));
        } else {
            return new ResultVO(ResultEnum.FAIL, "密码错误");
        }
    }

    @Override
    public User smsLogin(String phone) {
        User user = new User();
        user.setPhone(phone);
        List<User> list = userDao.queryCondition(user);
        if (list.size() > 0)
            return list.get(0);
        LocalDateTime currentDate = LocalDateTime.now();
        int year = currentDate.getYear(); //获取当前年份
        int month = currentDate.getMonthValue(); //获取当前月份
        int day = currentDate.getDayOfMonth(); //获取当前日期
        long currentTimestamp = Instant.now().toEpochMilli();
        user.setNick("用户" + year + month + day + currentTimestamp);
        //随机生成密码
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        user.setPassword(sb.toString());
        user.setUserName(phone);
        user.setGender("男");
        userDao.add(user);
        User user1 = userDao.queryCondition(user).get(0);
        return user1;
    }

    @Override
    public String getToken(User user) {
        System.out.println(user.getUserName());
        return JwtUtils.getJwtToken(user.getUserId(), user.getUserName());
    }

    @Override
    public ResultVO queryCondition(User user) {
        return new ResultVO(userDao.queryCondition(user));
    }


    @Override
    public ResultVO updatePassword(String oldPassword, String newPassword, Integer userId) {
        User u = userDao.queryId(userId);
        if (u == null) {
            return new ResultVO(ResultEnum.UPDATE_FAIL, "该用户不存在");
        } else if (u.getPassword().equals(oldPassword)) {
            u.setPassword(newPassword);
            userDao.update(u);
            return new ResultVO(ResultEnum.UPDATE_SUCCESS);
        } else {
            return new ResultVO(ResultEnum.UPDATE_FAIL, "密码错误");
        }
    }


    public void updateAvatar() {

    }

    @Override
    public ResultVO getCollect(Integer userId) {
        return null;
    }


    /**
     * 分页查询
     *
     * @param userVO
     * @return
     */
    @Override
    public ResultVO getUser(UserVO userVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(userVO.getCurrentPage());
        pageBeans.setPageSize(userVO.getPageSize());
        pageBeans.setCount(userDao.countBySelectActive(userVO));
        pageBeans.setData(userDao.queryBySelectActive(userVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

}