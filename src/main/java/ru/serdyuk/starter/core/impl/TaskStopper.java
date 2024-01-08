package ru.serdyuk.starter.core.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
@Slf4j
public class TaskStopper {

    //хранилище задач - map
    private final Map<String, ScheduledFuture<?>> tasks;

    /**
     * Данный метод отвечает за отключение и удаление задач из хранилища
     * **/
    public void stop(String taskKey) {
        if (tasks.containsKey(taskKey)) {
            var currentTask = tasks.get(taskKey);
            currentTask.cancel(true);
            tasks.remove(taskKey);

            log.debug("Task with taskKey {} stopped", taskKey);
        }
    }

    /**
     * Отвечает за хранение задач в хранилище и доступу к ним
     * **/
    void registryTask(String taskId, ScheduledFuture<?> future) {
        tasks.put(taskId, future);
    }

}
