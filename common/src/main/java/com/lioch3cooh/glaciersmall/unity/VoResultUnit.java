package com.lioch3cooh.glaciersmall.unity;

import com.lioch3cooh.glaciersmall.vo.VoResult;

import java.util.Map;

public class VoResultUnit {
    public static  VoResult getDefaultVoRes() {
        return new VoResult(0, "查询失败", null);
    }

    public static VoResult getSuccessVoRes(VoResult defaultVoRes, Map<String, Object> result) {
        defaultVoRes.setResult(result);
        defaultVoRes.setMsg("查询成功");
        defaultVoRes.setCode(1);
        return defaultVoRes;
    }
}
