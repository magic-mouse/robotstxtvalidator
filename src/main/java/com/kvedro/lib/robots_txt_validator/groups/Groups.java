package com.kvedro.lib.robots_txt_validator.groups;

import java.util.List;

public class Groups {

    String userAgent;
    List<String> allowed;
    List<String> disallowed;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public List<String> getAllowed() {
        return allowed;
    }

    public void setAllowed(List<String> allowed) {
        this.allowed = allowed;
    }

    public List<String> getDisallowed() {
        return disallowed;
    }

    public void setDisallowed(List<String> disallowed) {
        this.disallowed = disallowed;
    }
}
