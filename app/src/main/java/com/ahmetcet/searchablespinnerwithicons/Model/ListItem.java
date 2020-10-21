package com.ahmetcet.searchablespinnerwithicons.Model;

public class ListItem {
    String id;
    String value;

    public ListItem(String id, String val){
        this.id = id;
        this.value = val;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
