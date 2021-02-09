package com.task.experiment.model;

import com.google.gson.JsonObject;

public class PostData {
    JsonObject[] items;
    JsonObject[] item;

    String name, owner, desc;
    int position;

    public PostData(JsonObject[] item) { this.item = item; }

    public PostData(String name, String owner, String desc, int position) {
        this.name = name;
        this.owner = owner;
        this.desc = desc;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDesc() {
        return desc;
    }

    public int getPosition() {
        return position;
    }

    public JsonObject[] getItems() { return items; }
}
