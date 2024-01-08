package ru.serdyuk.starter.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "background-executor")
@Getter@Setter
public class BackgroundTaskProperties {

    private Boolean enabled;

    //time; cron выбор
    private String defaultExecutor;

    //количество задач
    private int taskSize;

    @NestedConfigurationProperty
    //TODO данная конфигурация указывает на то, что в свойствах приложения есть вложенные свойства
    private CronExecutorProperties cron;

    @NestedConfigurationProperty
    //TODO данная конфигурация указывает на то, что в свойствах приложения есть вложенные свойства
    private TimeExecutorProperties time;

}
