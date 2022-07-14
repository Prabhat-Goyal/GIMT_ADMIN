package com.defenders.gimtadmin;

public class NoticeModel {
    String date,details,heading;

    public NoticeModel(String date, String details, String heading) {
        this.date = date;
        this.details = details;
        this.heading = heading;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
