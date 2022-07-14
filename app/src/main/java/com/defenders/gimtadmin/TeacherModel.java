package com.defenders.gimtadmin;

public class TeacherModel {
    String bgroup,branch,caddress,dob,email,gname,id,jdate,mnumber,name,onumber,paddress,qualification;

    public TeacherModel(String bgroup, String branch, String caddress, String dob, String email, String gname, String id, String jdate, String mnumber, String name, String onumber, String paddress, String qualification) {
        this.bgroup = bgroup;
        this.branch = branch;
        this.caddress = caddress;
        this.dob = dob;
        this.email = email;
        this.gname = gname;
        this.id = id;
        this.jdate = jdate;
        this.mnumber = mnumber;
        this.name = name;
        this.onumber = onumber;
        this.paddress = paddress;
        this.qualification = qualification;
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

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
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

    public String getOnumber() {
        return onumber;
    }

    public void setOnumber(String onumber) {
        this.onumber = onumber;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
