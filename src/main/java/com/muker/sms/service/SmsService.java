package com.muker.sms.service;

import com.muker.sms.dto.PhoneCodeDto;
import com.muker.vo.R;

public interface SmsService {

    R sendSmsCode(String phone);

    R checkSmsCode(PhoneCodeDto phoneCodeDto);

}
