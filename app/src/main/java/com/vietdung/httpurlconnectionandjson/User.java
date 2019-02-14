package com.vietdung.httpurlconnectionandjson;

public class User {
    private String mName;
    private String mFullName;

    public User(String name, String fullName) {
        mName = name;
        mFullName = fullName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }
}
