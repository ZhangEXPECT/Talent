package com.example.talent.controller;

import com.alipay.api.kms.aliyun.exceptions.ClientException;
import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.UserInfo;
import com.example.talent.service.SmsCodeService;
import com.example.talent.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/sms")
public class SmsCodeController {

    private final SmsCodeService smsCodeService;

    @Resource
    UserService userService;

    @Autowired
    public SmsCodeController(SmsCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }

    /**
     * 获取短信验证码接口
     *
     * @param phone 手机号码
     * @return ResultVO
     */
    @GetMapping("/sentSms")
    public ResultVO getSmsCode(@RequestParam String phone) throws ClientException, com.aliyuncs.exceptions.ClientException {

        // 生成验证码并保存到 Redis 中
        String code = smsCodeService.generateSmsCode();
        smsCodeService.save(phone, code);

        // TODO: 调用发送短信的接口，将验证码发送给用户
        smsCodeService.sendSms(phone, code);
        return new ResultVO(ResultEnum.SUCCESS);
    }

    /**
     * 验证短信验证码接口
     *
     * @param phone 手机号码
     * @param code  短信验证码
     * @return ResultVO
     */
    @PostMapping("/verifySms")
    public ResultVO verifySmsCode(@RequestParam String phone, @RequestParam String code) {
        // 从 Redis 中获取指定电话号码对应的验证码
        String savedCode = smsCodeService.getCode(phone);
        if (StringUtils.isEmpty(savedCode)) {
            return new ResultVO(ResultEnum.FAIL,"验证码未发送或已过期");
        }
        if (!code.equals(savedCode)) {
            return new ResultVO(ResultEnum.FAIL,"验证码错误");
        }
        User user = userService.smsLogin(phone);
        String token = userService.getToken(user);
        UserInfo userInfo = new UserInfo(user,token);
        // 验证通过，删除Redis中的验证码
        smsCodeService.remove(phone);
        return
        new ResultVO(ResultEnum.SUCCESS,userInfo);
    }
}
