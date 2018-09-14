package com.landicorp.marketing.constant;

/**
 * Created by jiangjt on 2017/9/22.
 */
public class ResponseCode {
    /**
     * 成功1
     */
    public static final int SUCCESS=1;
    /**
     * 系统异常
     */
    public static final int EXCEPTION=2;

    /**
     * 参数错误
     */
    public static final int PARAM_ERROR=3;
    /**
     * 数据库操作异常
     */
    public static final int DATE_ERROR=4;
    /**
     * 登陆用户名密码不对
     */
    public static final int LOGIN_ERROR=5;
    /**
     * 未登陆
     */
    public static final int SESSION_ERROR=6;
    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR=7;
}
