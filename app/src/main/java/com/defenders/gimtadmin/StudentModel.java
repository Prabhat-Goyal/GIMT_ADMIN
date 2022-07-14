package com.defenders.gimtadmin;

public class StudentModel {
    String Semester,adate,bgroup,branch,caddress,course,dob,email,fname,gname,mname,mnumber,name,paddress,pmnumber,rollno;

    public StudentModel(String semester, String adate, String bgroup, String branch, String caddress, String course, String dob, String email, String fname, String gname, String mname, String mnumber, String name, String paddress, String pmnumber, String rollno) {
        this.Semester = semester;
        this.adate = adate;
        this.bgroup = bgroup;
        this.branch = branch;
        this.caddress = caddress;
        this.course = course;
        this.dob = dob;
        this.email = email;
        this.fname = fname;
        this.gname = gname;
        this.mname = mname;
        this.mnumber = mnumber;
        this.name = name;
        this.paddress = paddress;
        this.pmnumber = pmnumber;
        this.rollno = rollno;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }

    public String getBgroup() {
        return bgroup;
    }

    public void setBgroup(String bgroup) {
        this.bgroup = bgroup;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMnumber() {
        return mnumber;
    }

    public void setMnumber(String mnumber) {
        this.mnumber = mnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getPmnumber() {
        return pmnumber;
    }

    public void setPmnumber(String pmnumber) {
        this.pmnumber = pmnumber;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }
}
