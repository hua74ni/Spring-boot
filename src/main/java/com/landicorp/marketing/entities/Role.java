package com.landicorp.marketing.entities;


import com.landicorp.marketing.annotation.MongoField;

/**
 * Created by jiangjt on 2017/9/25.
 */
public class Role {
    /**
     * ID
     */
    @MongoField(field = "_id")
    private String id;
    /**
     * 角色名
     */
    @MongoField(field = "role_name")
    private String roleName;
    /**
     * 角色类型 1-系统管理员 2-应用提交人员 3-应用审核发布人员 4-管理人员
     */
    @MongoField(field = "role_type")
    private Integer roleType;
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
     * 备注
     */
    @MongoField(field = "memo")
    private String memo;
    /**
     * 操作状态
     */
    @MongoField(field = "status")
    private Integer status;

    public Role() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
