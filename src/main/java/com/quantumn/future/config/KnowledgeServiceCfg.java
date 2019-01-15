package com.quantumn.future.config;

import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:camel/knowledge-services.xml")
public class KnowledgeServiceCfg {
    @Bean
    public KModuleBeanFactoryPostProcessor getProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }
}
