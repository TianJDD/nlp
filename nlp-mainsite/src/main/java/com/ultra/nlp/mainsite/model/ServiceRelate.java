package com.ultra.nlp.mainsite.model;

/**
 * Created by sgy05 on 2018/5/4.
 */
public class ServiceRelate {
    private String relateType;
    private String title;
    private String content;
    private String banner;
    private String dictName;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getRelateType() {
        return relateType;
    }

    public void setRelateType(String relateType) {
        this.relateType = relateType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ServiceRelate{" +
                "relateType='" + relateType + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", banner='" + banner + '\'' +
                ", dictName='" + dictName + '\'' +
                '}';
    }
}
