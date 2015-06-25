package com.comcast.cable.hibernate.examples.versioning.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Friend {
    @Column(name = "name", nullable = false, unique=true, length=50)
    private String name;

    @Column(name = "trusted", nullable = false, unique=false)
    private boolean isTrusted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTrusted() {
        return isTrusted;
    }

    public void setIsTrusted(boolean isTrusted) {
        this.isTrusted = isTrusted;
    }
}
