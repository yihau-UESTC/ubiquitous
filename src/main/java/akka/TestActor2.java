package akka;

import akka.actor.*;
import akka.remote.RemoteScope;

public class TestActor2 extends UntypedActor {

    private ActorRef actorRef;

    public TestActor2() {
        Address address = new Address("akka.tcp", "clientSystem", "192.168.1.117", 8888);
        this.actorRef = getContext().actorOf(Props.create(TestActor.class).withDeploy(new Deploy(new RemoteScope(address))));
        System.out.println(actorRef.path().toStringWithoutAddress());
    }


    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof byte[]) {
            byte[] b = (byte[]) message;
            System.out.println("receive a byte[], size = " + b.length);
            actorRef.tell("hello", ActorRef.noSender());
        } else if (message instanceof String) {

        }
    }
}
