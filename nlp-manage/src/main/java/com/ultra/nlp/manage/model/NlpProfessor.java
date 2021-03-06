package com.ultra.nlp.manage.model;

public class NlpProfessor {
    private String id;
    private String academyId;
    private String academyName;
    private String groupId;
    private String groupName;
    private String professorName;
    private String professorDesc;
    private String professorPhone;
    private String professorImg;
    private String professorTitle;
    private String professorSex;
    private String professorState;
    private String professorUpdateTime;
    private String professorCreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcademyId() {
        return academyId;
    }

    public void setAcademyId(String academyId) {
        this.academyId = academyId;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorDesc() {
        return professorDesc;
    }

    public void setProfessorDesc(String professorDesc) {
        this.professorDesc = professorDesc;
    }

    public String getProfessorPhone() {
        return professorPhone;
    }

    public void setProfessorPhone(String professorPhone) {
        this.professorPhone = professorPhone;
    }

    public String getProfessorImg() {
        return professorImg;
    }

    public void setProfessorImg(String professorImg) {
        this.professorImg = professorImg;
    }

    public String getProfessorTitle() {
        return professorTitle;
    }

    public void setProfessorTitle(String professorTitle) {
        this.professorTitle = professorTitle;
    }

    public String getProfessorSex() {
        return professorSex;
    }

    public void setProfessorSex(String professorSex) {
        this.professorSex = professorSex;
    }

    public String getProfessorState() {
        return professorState;
    }

    public void setProfessorState(String professorState) {
        this.professorState = professorState;
    }

    public String getProfessorUpdateTime() {
        return professorUpdateTime;
    }

    public void setProfessorUpdateTime(String professorUpdateTime) {
        this.professorUpdateTime = professorUpdateTime;
    }

    public String getProfessorCreateTime() {
        return professorCreateTime;
    }

    public void setProfessorCreateTime(String professorCreateTime) {
        this.professorCreateTime = professorCreateTime;
    }

    @Override
    public String toString() {
        return "NlpProfessor{" +
                "id=" + id +
                ", academyId=" + academyId +
                ", academyName='" + academyName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", professorName='" + professorName + '\'' +
                ", professorDesc='" + professorDesc + '\'' +
                ", professorPhone='" + professorPhone + '\'' +
                ", professorImg='" + professorImg + '\'' +
                ", professorTitle='" + professorTitle + '\'' +
                ", professorSex='" + professorSex + '\'' +
                ", professorState='" + professorState + '\'' +
                ", professorUpdateTime='" + professorUpdateTime + '\'' +
                ", professorCreateTime='" + professorCreateTime + '\'' +
                '}';
    }
}
