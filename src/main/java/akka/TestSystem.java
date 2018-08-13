package akka;

import SEBasic.Test;
import akka.actor.*;
import akka.pattern.Patterns;
import akka.remote.RemoteScope;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;

import java.util.concurrent.CompletionStage;

import static scala.compat.java8.FutureConverters.toJava;


public class TestSystem {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("TestSystem", ConfigFactory.load().getConfig("LocalSys"));
        Props props = Props.create(TestActor.class);
        ActorRef local = system.actorOf(props);
        Address address = new Address("akka.tcp", "clientSystem", "192.168.1.117", 8888);
        ActorRef remote = system.actorOf(Props.create(TestActor.class).withDeploy(new Deploy(new RemoteScope(address))));

        byte[] bytes = new byte[4 * 1024];
        local.tell(bytes, ActorRef.noSender());
        remote.tell(bytes, ActorRef.noSender());

    }
}
