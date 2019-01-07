package com.quantumn.future;

import com.quantumn.future.model.Trade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessionTest {
    @Test
    public void testActiveMode() throws InterruptedException {
        KieSession kieSession = KieServices.get().newKieClasspathContainer(SessionTest.class.getClassLoader()).newKieSession("test-trade-rules");
        Trade trade1 = new Trade(1, "123", 1000L, new Date(), "F");
        Trade trade2 = new Trade(2, "123", 2000L, new Date(), "S");
        Trade trade3 = new Trade(3, "123", 2000L, new Date(), "F");
        Trade trade4 = new Trade(4, "123", 2000L, new Date(), "F");
        new Thread(() -> {
            kieSession.fireUntilHalt();
        }).start();
//        kieSession.insert(trade1);
//        kieSession.insert(trade2);

        kieSession.submit((session)->{
            session.insert(trade1);
            session.insert(trade2);
            session.insert(trade3);
            session.insert(trade4);
        });

        Thread.sleep( 1000L );
        kieSession.halt();
        kieSession.dispose();

    }

    @Test
    public void testPassiveMode() {
        KieSession kieSession = KieServices.get().newKieClasspathContainer().newKieSession("test-pseudo-trade-rules");
        SessionPseudoClock sessionClock = kieSession.getSessionClock();
        Trade trade1 = new Trade(1, "123", 1000L, new Date(), "S");
        Trade trade2 = new Trade(2, "123", 2000L, new Date(), "S");
        Trade trade3 = new Trade(3, "123", 2000L, new Date(), "F");
        kieSession.insert(trade1);
        kieSession.fireAllRules();
        sessionClock.advanceTime(8, TimeUnit.SECONDS);
        kieSession.insert(trade2);
        kieSession.fireAllRules();
    }

    @Test
    public void getPst10mFailTradeCount() throws InterruptedException {
        KieSession kieSession = KieServices.get().newKieClasspathContainer().newKieSession("test-pseudo-trade-rules");
        SessionPseudoClock sessionClock = kieSession.getSessionClock();
        Trade trade1 = new Trade(1, "123", 10L, new Date(), "F");
        Trade trade2 = new Trade(2, "125", 100L, new Date(), "F");
        Trade trade3 = new Trade(3, "125", 300L, new Date(), "F");
        Trade trade4 = new Trade(4, "125", 300L, new Date(), "F");
        new Thread(() -> {
            kieSession.fireUntilHalt();
        }).start();
        kieSession.insert(trade1);
        Thread.sleep(1000);
        sessionClock.advanceTime(5, TimeUnit.MINUTES);
        kieSession.insert(trade2);
        Thread.sleep(1000);
        sessionClock.advanceTime(4, TimeUnit.MINUTES);
        kieSession.insert(trade3);
        Thread.sleep(1000);
        sessionClock.advanceTime(2, TimeUnit.MINUTES);
        kieSession.insert(trade4);
        Thread.sleep(1000);

        kieSession.halt();
        kieSession.dispose();
    }
}
