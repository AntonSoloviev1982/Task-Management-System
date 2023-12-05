package com.solovyev_anton.taskmanagementsystem.entities;

public enum Status {

    IN_WAITING ("в ожидании"),
    IN_PROGRESS ("в процессе"),
    COMPLETED ("завершено");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Status{" +
                "title='" + title + '\'' +
                '}';
    }
}
