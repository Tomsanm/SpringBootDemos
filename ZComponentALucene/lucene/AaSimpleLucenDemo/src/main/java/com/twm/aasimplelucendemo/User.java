package com.twm.aasimplelucendemo;

/**
 * 数据对象
 */
public class User {
    private String id;
    private String name;
    private String info;

    public User(String id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
