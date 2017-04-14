package com.example.wl.answer.model;

/**
 * Created by wanglin on 17-4-6.
 */

public class PushMessage {
    private String mTitle;
    private String mDetails;

    public PushMessage() {
        mTitle = "";
        mDetails = "";
    }

    public PushMessage(String title, String details) {
        mTitle = title;
        mDetails = details;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }
}
