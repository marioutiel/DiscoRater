package com.example.discoraterjorge;

public class Post {

    private String name, location;
    private long timestamp;

    public Post(String name, String location, long timestamp) {
        this.name = name;
        this.location = location;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
