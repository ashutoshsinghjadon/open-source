package com.exam.server;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.exam.client.dto.EventDTO;

@Entity
@Table( name = "EVENTS" )
public class Event {
    private int id;
    private String title;
    private Date date;
    public Event() {
        // this form used by Hibernate
    }
    public Event(String title, Date date) {
        // for application use, to create new events
        this.title = title;
        this.date = date;
    }

    public Event(EventDTO user) {
        this.title = user.getTitle();
        this.date = user.getDate();
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CUSTOMER_ID", precision=0)
    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EVENT_DATE")
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
