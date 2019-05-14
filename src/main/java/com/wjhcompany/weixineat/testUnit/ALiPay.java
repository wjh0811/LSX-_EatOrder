package com.wjhcompany.weixineat.testUnit;

/**
 * @author JH Wei
 * @date 2019/4/11-21:11
 */
public class ALiPay implements MeiTuan {
    public ALiPay() {
        System.out.println("支付宝的构造器");

    }

    @Override
    public void payOnLine() {
        System.out.println("支付宝支付了");
    }
}
