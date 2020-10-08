package com.project.service;

import java.util.Date;

public class Holiday {

    private String title;
    private String start;
    private String end;
    private boolean allDay;
    private String color;
    private String id;

    public Holiday(String title, String start, String end, boolean allDay, String color, String id) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.allDay = allDay;
        this.color = color;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }
}
