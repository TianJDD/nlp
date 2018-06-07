package com.ultra.nlp.manage.model;

public class NlpDict {
    private String dictCode;
    private String dictName;
    private String dictValue;
    private String parentCode;
    private String parentName;
    private String dictIcon;
    private int dictState;
    private String createTime;
    private String updateTime;
    private String createUser;
    private String updateUser;
    private String dictDesc;

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDictIcon() {
        return dictIcon;
    }

    public void setDictIcon(String dictIcon) {
        this.dictIcon = dictIcon;
    }

    public int getDictState() {
        return dictState;
    }

    public void setDictState(int dictState) {
        this.dictState = dictState;
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

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    @Override
    public String toString() {
        return "NlpDict{" +
                "dictCode='" + dictCode + '\'' +
                ", dictName='" + dictName + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", parentName='" + parentName + '\'' +
                ", dictState=" + dictState +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
