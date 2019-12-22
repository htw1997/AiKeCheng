package com.muker.Personal.controller;


import com.muker.Personal.entity.UserInfos;
import com.muker.Personal.service.PersonalService;
import com.muker.Personal.dto.PhoneCodeDto;
import com.muker.sms.service.SmsService;
import com.muker.user.entity.User;
import com.muker.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//标记返回的是字符串 json/xml格式 代替：@Controller + @ResponseBody
@Api(value = "关于个人中心的相关接口", tags = "实现用户个人中心的相关功能")
public class PersonalController {
    @Autowired
    PersonalService personalService;
    @Autowired
    private SmsService smsService;

    @PostMapping("/api/personal/showPersonal")
    @ApiOperation(value = "展示用户个人信息", notes = "展示用户个人信息")


    public R showUserInfo(String phone) {
        return personalService.showUserInfo(phone);
    }

    //--------------------------用户绑定模块-----------------------------------------------

    /**
     * @param email 从前端页面获取输入的邮箱
     * @return 返回ok则表示可用 返回fail表示不能用
     */
    @PostMapping("/api/personal/verifyEmail")
    @ApiOperation(value = "该方法用来验证邮箱是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
    })
    public R verifyEmail(String email) {
        UserInfos count = personalService.selectEmail(email);
        if (count != null) {
            return R.fail("该邮箱已经存在");
        }
        return R.Ok("200");
    }

    @PostMapping("/api/personal/sendEmail")
    @ApiOperation(value = "发送邮箱验证码", notes = "发送邮箱验证码")

    //一个问题   验证码目前存到了数据库中  未存到redis中。
    //解决的方案是  将 验证码存储到redis中进行保存。
    //周六完成
    public R sendEmail(String email) {
        //public R sendEmailCode(@RequestBody UserInfos userInfos) {

       // int count = personalService.sendEmailCode(email);
        System.out.println(email);
       // System.out.println(count);

       // if (count > 0) {
       //     return R.setR(200, "已发送验证码", null);
       // }
        return R.fail("验证码发送失败");
    }

    @PostMapping("/api/personal/bindEmail")
    @ApiOperation(value = "该方法用于用户使用邮箱绑定，输入邮箱，验证码")
    @RequestMapping(value = "/bindEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "validateNum", value = "验证码", required = true, dataType = "String")
    })
    public R bindEmail( String email) {


        int count = personalService.bindEmail(email);
        if (count > 0) {

            return R.setR(400, "该邮箱已经存在", null);
        }
        return R.Ok("200");
    }

    //------------------------发送手机验证码-------------------------------------------------






    //绑定手机号
    @ApiOperation(value = "绑定手机号", notes = "绑定手机号")
    @PostMapping("/api/sms/bindPhone.do")
    public R bindPhone(@RequestBody PhoneCodeDto phoneCodeDto){

        return personalService.bindPhone(phoneCodeDto);
    }

    //取消绑定手机号
    @ApiOperation(value = "取消绑定手机号", notes = "取消绑定手机号")
    @PostMapping("/api/sms/cancelBind.do")
    public R cancelBindPhone(@RequestBody UserInfos userInfos) {
        System.out.println(userInfos.getRusername());
        return personalService.cancelBindPhone(userInfos);
    }

}
