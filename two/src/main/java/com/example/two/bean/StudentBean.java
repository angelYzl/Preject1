package com.example.two.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class StudentBean {
    @Id(autoincrement = true)
    private long id;
    private String title;
    private String text;
    private String url;
    @Generated(hash = 1179579920)
    public StudentBean(long id, String title, String text, String url) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.url = url;
    }
    @Generated(hash = 2097171990)
    public StudentBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
