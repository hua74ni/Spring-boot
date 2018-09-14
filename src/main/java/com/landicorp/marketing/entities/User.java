package com.landicorp.marketing.entities;


import com.landicorp.marketing.annotation.MongoField;

import java.util.Date;

/**
 * 用户实体
 * Created by jiangjt on 2017/9/25.
 */
public class User {
    /**
     * ID
     */
    @MongoField(field = "_id")
    private String id;
    /**
     * 登录名
     */
    @MongoField(field = "user_name")
    private String userName;
    /**
     * 用户电话
     */
    @MongoField(field = "user_tel")
    private String userTel;

    /**
     * 用户类型 ,对应roleId
     */
    @MongoField(field = "user_type")
    private String userType;

    /**
     * 密码
     */
    @MongoField(field = "password")
    private String password;
    /**
     * 回话id
     */
    @MongoField(field = "session_id")
    private String sessionId;
    /**
     * 是否有效 0-无效 1-有效
     */
    @MongoField(field = "available")
    private Integer available;
    /**
     * 最后一次登录时间
     */
    @MongoField(field = "last_login_time")
    private String lastLoginTime;
    /**
     * 最后一次登录ip
     */
    @MongoField(field = "last_login_ip")
    private String lastLoginIp;
    /**
     * 登录时间
     */
    @MongoField(field = "login_time")
    private String loginTime;
    /**
     * 登录ip
     */
    @MongoField(field = "login_ip")
    private String loginIp;
    /**
     * 创建时间
     */
    @MongoField(field = "create_time")
    private String createTime;
    /**
     * 创建人
     */
    @MongoField(field = "create_user")
    private String createUser;
    /**
     * 更新时间
     */
    @MongoField(field = "update_time")
    private String updateTime;
    /**
     * 更新人
     */
    @MongoField(field = "update_user")
    private String updateUser;

    /**
     * 角色类型
     */
    private Integer roleType;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
