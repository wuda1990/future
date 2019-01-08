package com.quantumn.future;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleRulesTest {
    @Test
    public void testSimpleRule() {
        KieSession ksession = KieServices.get().newKieClasspathContainer().newKieSession("simple-rules");
        ksession.insert("1");
        ksession.insert(1);
        ksession.fireAllRules();

    }

    @Test
    public void testCollect() {
        KieSession ksession = KieServices.get().newKieClasspathContainer().newKieSession("simple-rules");
        String[] arr =  {"黄渤","张艺兴","孙红雷","","小猪","牙哥","黄磊"};
        List<String> answers = new ArrayList<String>(Arrays.asList(arr));
        DroolsPojo pojo = new DroolsPojo();
        pojo.setAnswers(answers);
        ksession.insert(pojo);
        ksession.fireAllRules();
    }
}
