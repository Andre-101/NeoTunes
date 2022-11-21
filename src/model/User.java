package model;

import java.util.Date;

public abstract class User {

    //Attributes
    private String nickname;
    private String id;
    private Date joinDate;

    //Builder
    public User(String nickname, String id) {
        this.nickname = nickname;
        this.id = id;
        this.joinDate = new Date();
    }

    //Getters and Setters

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinData) {
        this.joinDate = joinData;
    }
}
