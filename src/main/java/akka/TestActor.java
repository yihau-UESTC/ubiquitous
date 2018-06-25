package akka;

import akka.actor.UntypedActor;

public class TestActor extends UntypedActor {


    public TestActor() {
    }


    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            System.out.println((String) message);
        }
    }
}
