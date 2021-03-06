package com.core.model;


import java.util.Date;

public class SysUser  {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_ID
     *
     * @mbggenerated
     */
    private Integer userId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public void setPwdErrTime(Integer pwdErrTime) {
        this.pwdErrTime = pwdErrTime;
    }

    public SysUser() {
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_NAME
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_LOGIN_NAME
     *
     * @mbggenerated
     */
    private String userLoginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.EMAIL
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.MOBILE_PHONE
     *
     * @mbggenerated
     */
    private String mobilePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_STATUS
     *
     * @mbggenerated
     */
    private String userStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_TYPE
     *
     * @mbggenerated
     */
    private String userType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CREATER
     *
     * @mbggenerated
     */
    private Integer creater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CREATE_TIME
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.UPDATER
     *
     * @mbggenerated
     */
    private Integer updater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.UPDATE_TIME
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.LOGIN_PWD
     *
     * @mbggenerated
     */
    private String loginPwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CHANGE_TIME
     *
     * @mbggenerated
     */
    private Date changeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.PWD_ERR_TIME
     *
     * @mbggenerated
     */
    private Integer pwdErrTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    public SysUser(Integer userId, String userName, String userLoginName, String email, String mobilePhone, String userStatus, String userType, Integer creater, Date createTime, Integer updater, Date updateTime, String loginPwd, Date changeTime, Integer pwdErrTime) {
        this.userId = userId;
        this.userName = userName;
        this.userLoginName = userLoginName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.userStatus = userStatus;
        this.userType = userType;
        this.creater = creater;
        this.createTime = createTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.loginPwd = loginPwd;
        this.changeTime = changeTime;
        this.pwdErrTime = pwdErrTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_ID
     *
     * @return the value of sys_user.USER_ID
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_NAME
     *
     * @return the value of sys_user.USER_NAME
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_LOGIN_NAME
     *
     * @return the value of sys_user.USER_LOGIN_NAME
     *
     * @mbggenerated
     */
    public String getUserLoginName() {
        return userLoginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.EMAIL
     *
     * @return the value of sys_user.EMAIL
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.MOBILE_PHONE
     *
     * @return the value of sys_user.MOBILE_PHONE
     *
     * @mbggenerated
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_STATUS
     *
     * @return the value of sys_user.USER_STATUS
     *
     * @mbggenerated
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_TYPE
     *
     * @return the value of sys_user.USER_TYPE
     *
     * @mbggenerated
     */
    public String getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CREATER
     *
     * @return the value of sys_user.CREATER
     *
     * @mbggenerated
     */
    public Integer getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CREATE_TIME
     *
     * @return the value of sys_user.CREATE_TIME
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.UPDATER
     *
     * @return the value of sys_user.UPDATER
     *
     * @mbggenerated
     */
    public Integer getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.UPDATE_TIME
     *
     * @return the value of sys_user.UPDATE_TIME
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.LOGIN_PWD
     *
     * @return the value of sys_user.LOGIN_PWD
     *
     * @mbggenerated
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CHANGE_TIME
     *
     * @return the value of sys_user.CHANGE_TIME
     *
     * @mbggenerated
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.PWD_ERR_TIME
     *
     * @return the value of sys_user.PWD_ERR_TIME
     *
     * @mbggenerated
     */
    public Integer getPwdErrTime() {
        return pwdErrTime;
    }
}