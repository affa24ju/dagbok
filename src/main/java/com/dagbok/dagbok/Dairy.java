package com.dagbok.dagbok;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Dairy{

    //GenerationType.AUTO ger fel meddelande att det blir duplicate ID, därför använder IDENTITY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    private String title;

    //För att specifera att date är bara Date type utan tid
    @Temporal(TemporalType.DATE)
    private Date date;

    private String text;

    //För att ha koll på att ta bort från websidan men inte från databasen 
    private boolean isDeleted = false;

    
    //Getters & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}
