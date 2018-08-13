package akka;

import akka.actor.UntypedActor;

import java.util.Date;

public class TestActor extends UntypedActor {


    public TestActor() {
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("testActor has constructed");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof byte[]) {
            System.out.println("receive data time = " + new Date().toString());
        }
    }
}
