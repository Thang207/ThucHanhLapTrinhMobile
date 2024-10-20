package com.example.bth2_4;

public class Employee {
    private String id;
    private String fullName;
    private boolean isManager;

    public Employee (String id, String fullName, boolean isManager) {
        this.id = id;
        this.fullName = fullName;
        this.isManager = isManager;
    }

    public String getId(){
        return id;
    }

    public String getFullName(){
        return fullName;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public boolean isManager(){
        return isManager;
    }
    public void setManager(boolean isManager){
        this.isManager = isManager;
    }
}
