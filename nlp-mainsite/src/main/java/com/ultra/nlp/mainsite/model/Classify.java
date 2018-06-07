package com.ultra.nlp.mainsite.model;

import java.math.BigDecimal;

public class Classify {
    private  int id;
    private String name;
    private String descr;
    private String icon;
    private BigDecimal rec_num;
    private int leaf;
    private int p_id;
    private int ser_type;
    private String img;
    private String ext1;
    private String ext2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public BigDecimal getRec_num() {
        return rec_num;
    }

    public void setRec_num(BigDecimal rec_num) {
        this.rec_num = rec_num;
    }

    public int getLeaf() {
        return leaf;
    }

    public void setLeaf(int leaf) {
        this.leaf = leaf;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getSer_type() {
        return ser_type;
    }

    public void setSer_type(int ser_type) {
        this.ser_type = ser_type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", icon='" + icon + '\'' +
                ", rec_num=" + rec_num +
                ", leaf=" + leaf +
                ", p_id=" + p_id +
                ", ser_type=" + ser_type +
                ", img='" + img + '\'' +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                '}';
    }
}
