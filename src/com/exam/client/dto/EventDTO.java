package com.exam.client.dto;

import java.io.Serializable;
import java.util.Date;

public class EventDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Date date;

    public EventDTO() {
    }

    public EventDTO(String title, Date date) {
        this.title = title;
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
