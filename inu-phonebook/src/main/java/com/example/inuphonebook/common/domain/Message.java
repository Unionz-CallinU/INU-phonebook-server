package com.example.inuphonebook.common.domain;

import lombok.Getter;

@Getter
public class Message {
    private String message;
    public Message(String message) {
        this.message = message;
    }
}
