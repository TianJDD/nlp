package com.ultra.nlp.mainsite.model;

public enum ReturnCode {
    SUCESS_CODE_0000("0000","操作成功"),
    ERROR_CODE_0001("0001","用户不存在"),
    ERROR_CODE_0002("0002","密码错误"),
    ERROR_CODE_11001("1111","操作失败");
    private ReturnCode(String key,String value){
        this.key = key;
        this.value = value;
    }
    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
