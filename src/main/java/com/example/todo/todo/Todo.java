package com.example.todo.todo;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class Todo {
    private @NonNull long id;
    private static long last_id = 0;
    private @NonNull String description;
    private @NonNull String deadlineDate;

    public Todo(String description, String deadlineDate) {
        last_id++;
        this.id = last_id;
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @NonNull
    public long getId() {
        return id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Nullable
    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(@NonNull String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}
