package com.ohua_blog.types;

import java.util.Date;

/**
 * Created by justusadam on 13/01/16.
 */
public class PostInfo {
    private int id;
    private String topic;
    private String author;
    private Date date;

    public PostInfo(int id, String topic, String author, Date date) {
      this.id = id;
      this.topic = topic;
      this.author = author;
      this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
