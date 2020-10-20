package com.project.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

public class Holiday {

    private String title;
    private String start;
    private String end;
    private boolean allDay;
    private String color;
    @Id
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

    @Override
    public String toString() {
        return "Holiday{" +
                "title='" + title + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", allDay=" + allDay +
                ", color='" + color + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
