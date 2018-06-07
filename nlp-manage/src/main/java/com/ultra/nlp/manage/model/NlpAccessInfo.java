package com.ultra.nlp.manage.model;

public class NlpAccessInfo {

    private String id;

    private String serverId;

    private String accessId;

    private String accessType;

    private String accessModel;

    private String deployPath;

    private String visitURL;

    private String academyId;

    private String professorId;

    private String accessWay;

    private String accessState;

    private String contactPerson;

    private String contactTel;

    private String accessPerson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getAccessModel() {
        return accessModel;
    }

    public void setAccessModel(String accessModel) {
        this.accessModel = accessModel;
    }

    public String getDeployPath() {
        return deployPath;
    }

    public void setDeployPath(String deployPath) {
        this.deployPath = deployPath;
    }

    public String getVisitURL() {
        return visitURL;
    }

    public void setVisitURL(String visitURL) {
        this.visitURL = visitURL;
    }

    public String getAcademyId() {
        return academyId;
    }

    public void setAcademyId(String academyId) {
        this.academyId = academyId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getAccessWay() {
        return accessWay;
    }

    public void setAccessWay(String accessWay) {
        this.accessWay = accessWay;
    }

    public String getAccessState() {
        return accessState;
    }

    public void setAccessState(String accessState) {
        this.accessState = accessState;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getAccessPerson() {
        return accessPerson;
    }

    public void setAccessPerson(String accessPerson) {
        this.accessPerson = accessPerson;
    }

    @Override
    public String toString() {
        return "NlpAccessInfo{" +
                "id=" + id +
                ", serverId=" + serverId +
                ", accessId='" + accessId + '\'' +
                ", accessType='" + accessType + '\'' +
                ", accessModel='" + accessModel + '\'' +
                ", deployPath='" + deployPath + '\'' +
                ", visitURL='" + visitURL + '\'' +
                ", academyId=" + academyId +
                ", professorId=" + professorId +
                ", accessWay='" + accessWay + '\'' +
                ", accessState='" + accessState + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", accessPerson='" + accessPerson + '\'' +
                '}';
    }
}
