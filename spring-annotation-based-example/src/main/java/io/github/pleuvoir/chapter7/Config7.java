package io.github.pleuvoir.chapter7;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.github.pleuvoir.chapter7.conf.EnvironmentWrapper;

@Configuration
@Import({ EnvironmentWrapper.class })
@ComponentScan("io.github.pleuvoir.chapter7")
public class Config7 {

}
