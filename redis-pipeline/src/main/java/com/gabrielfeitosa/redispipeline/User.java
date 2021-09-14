package com.gabrielfeitosa.redispipeline;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    private UUID id;
    private String name;

    public User(){
    }

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int index) {
        this.id = UUID.randomUUID();
        this.name = "Name "+ index;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
