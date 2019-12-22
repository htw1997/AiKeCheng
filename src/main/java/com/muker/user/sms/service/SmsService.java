package com.muker.user.sms.service;

import com.muker.user.sms.dto.PhoneCodeDto;
import com.muker.vo.R;

public interface SmsService {

    R sendSmsCode(String phone);
    R checkSmsCode(PhoneCodeDto phoneCodeDto);

}
