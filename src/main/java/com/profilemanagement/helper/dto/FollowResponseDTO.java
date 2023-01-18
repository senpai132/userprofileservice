package com.profilemanagement.helper.dto;

public class FollowResponseDTO {
    public String initiator;
    public String target;
    public int response;
    public int notifyOnMessage;
    public int notifyOnPost;

    public FollowResponseDTO(String initiator, String target, int response, int notifyOnMessage, int notifyOnPost) {
        this.initiator = initiator;
        this.target = target;
        this.response = response;
        this.notifyOnMessage = notifyOnMessage;
        this.notifyOnPost = notifyOnPost;
    }

    public FollowResponseDTO() {

    }

    public int getNotifyOnMessage() {
        return notifyOnMessage;
    }

    public void setNotifyOnMessage(int notifyOnMessage) {
        this.notifyOnMessage = notifyOnMessage;
    }

    public int getNotifyOnPost() {
        return notifyOnPost;
    }

    public void setNotifyOnPost(int notifyOnPost) {
        this.notifyOnPost = notifyOnPost;
    }
    
    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String intiator) {
        this.initiator = intiator;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

}
