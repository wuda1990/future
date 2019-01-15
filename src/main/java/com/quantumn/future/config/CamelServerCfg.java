package com.quantumn.future.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:camel/camel-server.xml")
public class CamelServerCfg {
}
