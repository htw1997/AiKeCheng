package com.muker.vo;

import com.muker.result.ResultCode;
import lombok.Data;


@Data
public class R {
    private int code;   //结果码
    private String msg;//字符串
    private Object data;;//携带数据

    public static R setR(int code,String msg,Object data){
        R r=new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R Ok(Object data) {
        return setR(ResultCode.OK.getVal(),"OK",data);
    }

    public static R Ok(){
        return setR(ResultCode.OK.getVal(),"OK",null);
    }

    public static R fail(){
        return setR(ResultCode.ERROR.getVal(),"Error",null);
    }

    public static R fail(String msg) {
        return setR(ResultCode.ERROR.getVal(),msg,null);
    }

    public static R noau(String msg) {
        return setR(ResultCode.NOAUTH.getVal(),msg,null);
    }
}
