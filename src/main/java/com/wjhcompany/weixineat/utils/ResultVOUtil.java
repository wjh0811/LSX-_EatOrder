package com.wjhcompany.weixineat.utils;

import com.wjhcompany.weixineat.VO.ResultVo;

/**
 * @author JH Wei
 * @date 2019/3/15-17:37
 */
public class ResultVOUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success(){
         return success(null);

    }

    public static ResultVo error(Integer code, String msg)
    {
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        resultVo.setData(null);
        return resultVo;



    }
}
