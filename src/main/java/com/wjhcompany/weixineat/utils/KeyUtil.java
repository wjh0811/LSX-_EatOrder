package com.wjhcompany.weixineat.utils;

import java.util.Random;

/**
 * @author JH Wei
 * @date 2019/3/18-11:32
 */
public class KeyUtil {

    /**
        * @Description: ${生成订单唯一的主键，格式：时间+随机数}
        * @Param: ${tags}
        * @Return: ${return_type}
        * @Author: JH WEI
        * @Date: 2019/3/18 11:32
        * @Updated In:
        * @Modified By:
    */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis()+ String.valueOf(number);


    }
}
