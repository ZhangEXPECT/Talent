package com.example.talent.service;

import com.alipay.api.kms.aliyun.exceptions.ClientException;


public interface SmsCodeService {

    // 生成短信验证码
    String generateSmsCode();

    // 保存验证码到 Redis 中，并设置过期时间
    void save(String phone, String code);

    // 根据电话号码从 Redis 中获取验证码
    String getCode(String phone);

    // 删除指定电话号码对应的验证码
    void remove(String phone);
    void sendSms(String phoneNumber, String code) throws ClientException, com.aliyuncs.exceptions.ClientException;
}
