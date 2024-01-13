package ru.serdyuk.starter.configuration;

import ru.serdyuk.starter.configuration.properties.BackgroundTaskProperties;
import ru.serdyuk.starter.core.impl.TaskStopper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Configuration
@ConditionalOnProperty("background-executor.enabled")
@EnableConfigurationProperties(BackgroundTaskProperties.class)
@Slf4j
public class BackgroundConfiguration {

    /**
     * Позволяет пользователю запускать задачи в многопоточной среде
     * по крону и времени
     * **/
    @Bean
    public ConcurrentTaskScheduler concurrentTaskScheduler() {
        //CRON "0 0 * * * *" -> run в полночь
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public TaskStopper taskStopper(BackgroundTaskProperties properties) {
        return new TaskStopper(new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, ScheduledFuture<?>> eldest) {
                if (size() > properties.getTaskSize()) {
                    eldest.getValue().cancel(true);
                    log.debug("Eldest task preparing for delete. Id is {}", eldest.getKey());
                    return true;
                }
                return false;
            }
        });
    }

}
