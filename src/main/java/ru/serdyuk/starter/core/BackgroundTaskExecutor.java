package ru.serdyuk.starter.core;

public interface BackgroundTaskExecutor {

    void schedule(String taskId, Runnable task);
}
