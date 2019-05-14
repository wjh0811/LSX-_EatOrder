package com.wjhcompany.weixineat.testUnit;

/**
 * @author JH Wei
 * @date 2019/4/11-21:10
 */
public class WeChat implements MeiTuan {
    public WeChat() {
        System.out.println("微信的构造器");
    }

    @Override
    public void payOnLine() {
        System.out.println("微信支付了");
    }
}
