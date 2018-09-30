package com.muppet;

import java.io.Serializable;

public class Email implements Serializable {

    public Email(String message) {
        this.message = message;
    }

    public String message;
}
