package com.quantumn.future.auditor;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;

public class KieContainerFactory {

    private static  KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        if (kieContainer == null) {
            kieContainer = KieServices.get().newKieClasspathContainer();
        }
        return kieContainer;
    }
}
