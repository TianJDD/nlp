package com.ultra.nlp.manage.model;

public class JsonModel {

    //成功或者失败
    private boolean flag;
    //返回信息
    private String message;
    //返回代码
    private String code;
    //数据
    private Object  data;

    private Integer pageSize;

    private Integer total;

    private Integer pageCount;

    public JsonModel(boolean flag, String message, String code, Object data) {
        this.flag = flag;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public JsonModel(boolean flag, String message, String code) {
        this.flag = flag;
        this.message = message;
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageCount() {
        return pageCount;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "JsonModel{" +
                "result='" + flag + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

}
