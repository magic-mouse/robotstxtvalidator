package com.kvedro.lib.robots_txt_validator.groups;

import java.util.ArrayList;
import java.util.List;

public class RobotsTxt {

    private String path;
    private List<Groups> groupsList;

    public RobotsTxt() {
        groupsList = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void appendGroup(Groups groups){
        this.groupsList.add(groups);
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }


}
