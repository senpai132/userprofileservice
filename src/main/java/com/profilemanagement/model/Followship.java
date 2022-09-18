package com.profilemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "followship")
public class Followship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
  
    @Column(name = "asker")
    public String initiator;

    @Column(name = "target")
    public String target;

    public int approved;
    
    @Column(name = "notifyonpost")
    public int notifyOnPost;

    @Column(name = "notifyonmessage")
    public int notifyOnMessage;

    public Followship() {    
        this.notifyOnMessage = 1;
        this.notifyOnPost = 1;
    }

    public int getNotifyOnPost() {
        return notifyOnPost;
    }

    public int getNotifyOnMessage() {
        return notifyOnMessage;
    }

    public void setNotifyOnPost(int notifyOnPost) {
        this.notifyOnPost = notifyOnPost;
    }

    public void setNotifyOnMessage(int notifyOnMessage) {
        this.notifyOnMessage = notifyOnMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String follow_asker) {
        this.initiator = follow_asker;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String follow_target) {
        this.target = follow_target;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }
}
