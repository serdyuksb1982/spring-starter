package ru.serdyuk.starter.configuration.properties;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CronExecutorProperties {

    private String expression;
}
