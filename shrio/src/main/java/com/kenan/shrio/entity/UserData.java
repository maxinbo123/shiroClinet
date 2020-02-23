package com.kenan.shrio.entity;

import java.io.Serializable;

/**
 * Created by maxb on 2020/2/16.
 */
public class UserData implements Serializable{

    private static final long serialVersionUID = 2806660615198061277L;

    private String name;
    private String pass;
    private String type;


    public UserData(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public UserData() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
