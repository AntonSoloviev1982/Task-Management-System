package com.solovyev_anton.taskmanagementsystem.entities;

public enum Priority {

    HIGH ("высокий"),
    MEDIUM ("средний"),
    LOW ("низкий");

    private String title;

    Priority(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
