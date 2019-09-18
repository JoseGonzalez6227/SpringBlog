package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ad ads;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Ad getAds() {
        return ads;
    }

    public void setAds(Ad ads) {
        this.ads = ads;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

}