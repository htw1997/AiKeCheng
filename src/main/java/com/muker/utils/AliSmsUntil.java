package com.muker.utils;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


public class AliSmsUntil {


    /**
     * @ClassName AliSmsUtil
     * @Description: TODO
     * @Author 小米
     * @Date 2019/12/11
     * @Version V1.0
     **/

    public static class AliSmsUtil {

        /**
         * 发送短信验证码 有效期10分钟
         *
         * @param code 验证码 4位 电话
         */
        public static void sendSmsCode(String rusername, int code) {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "", "");
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", rusername);
            request.putQueryParameter("SignName", "来自邢朋辉的短信");
            request.putQueryParameter("TemplateCode", "SMS_115250125");
            request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
