package com.muker.user.sms.controller;


import com.muker.user.sms.dto.PhoneCodeDto;
import com.muker.user.sms.service.SmsService;
import com.muker.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SmsController
 * @Description
 * @Author JavaQ
 * @Date 2019/12/11 19:16
 **/
@Api(value = "短信验证码",tags = "短信验证码")
@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @ApiOperation(value = "发送短信验证码",notes = "发送验证码")
    @GetMapping("/sms/sendcode.do")
    public R sendCode(String phone) {
        return smsService.sendSmsCode(phone);
    }
    @ApiOperation(value = "校验短信验证码",notes = "校验短信验证码")
    @PostMapping("/sms/checkcode.do")
    public R checkCode(@RequestBody PhoneCodeDto phoneCodeDto) {
        return smsService.checkSmsCode(phoneCodeDto);
    }

}
