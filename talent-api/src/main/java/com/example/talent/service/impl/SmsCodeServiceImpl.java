package com.example.talent.service.impl;

import com.alipay.api.kms.aliyun.exceptions.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.example.talent.service.SmsCodeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class SmsCodeServiceImpl implements SmsCodeService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public SmsCodeServiceImpl(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String generateSmsCode() {
        // 生成6位随机数字验证码
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    @Override
    public void save(String phone, String code) {
        // 设置键值对，并设置过期时间为5分钟
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public String getCode(String phone) {
        // 根据电话号码从Redis中获取验证码
        return redisTemplate.opsForValue().get(phone);
    }

    @Override
    public void remove(String phone) {
        // 根据电话号码删除对应的验证码
        redisTemplate.delete(phone);
    }

    public void sendSms(String phoneNumber, String code) throws ClientException, com.aliyuncs.exceptions.ClientException {
        String accessKeyId = "LTAI5t6fBoiuAh6saCKL4ssr";
        String accessKeySecret="ZZkj4a27cuspux7vzjRIjfoby1Blx3";
        String signName = "阿里云短信测试";
        String templateCode = "SMS_154950909";
        // 设置短信服务参数
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber); // 接收短信的手机号码
        request.setSignName(signName); // 短信签名名称
        request.setTemplateCode(templateCode); // 短信模板 ID

        // 设置短信模板中的参数，在此示例中，我们将验证码作为短信模板参数
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        Gson gson = new Gson();
        request.setTemplateParam(gson.toJson(params));

        // 发送短信请求
        SendSmsResponse response = client.getAcsResponse(request);
        if (!"OK".equals(response.getCode())) {
            throw new RuntimeException(response.getMessage());
        }
    }
}
