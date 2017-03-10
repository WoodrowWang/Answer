package com.example.wl.answer.model;

import java.net.URL;

/**
 * Created by wanglin on 17-3-10.
 */

public class MessageInfo {
    private String name;
    private String time;
    private String content;
    private URL headIcon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public URL getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(URL headIcon) {
        this.headIcon = headIcon;
    }
}
