package com.example.wl.answer.model;

/**
 * Created by wanglin on 17-3-13.
 */

public class ChatText {
    public static final int TYPE_OWN = 1;
    public static final int TYPE_OTHER = 2;
    private String text;
    private int type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
