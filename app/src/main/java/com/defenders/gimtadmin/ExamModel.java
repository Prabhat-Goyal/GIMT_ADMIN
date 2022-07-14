package com.defenders.gimtadmin;

public class ExamModel {

    String course, branch, sem, examheading,subject,stime, etime, room, date;

    public ExamModel(String course, String branch, String sem, String examheading, String subject, String stime, String etime, String room, String date) {
        this.course = course;
        this.branch = branch;
        this.sem = sem;
        this.examheading = examheading;
        this.subject = subject;
        this.stime = stime;
        this.etime = etime;
        this.room = room;
        this.date = date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getExamheading() {
        return examheading;
    }

    public void setExamheading(String examheading) {
        this.examheading = examheading;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
