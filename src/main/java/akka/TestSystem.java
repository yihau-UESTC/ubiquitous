package akka;

import SEBasic.Test;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;

import java.util.concurrent.CompletionStage;

import static scala.compat.java8.FutureConverters.toJava;


public class TestSystem {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("TestSystem", ConfigFactory.load().getConfig("LocalSys"));
        Props props = Props.create(TestActor2.class);
        ActorRef actorRef = system.actorOf(props);
        byte[] bytes = new byte[4 * 1024];
        actorRef.tell(bytes, ActorRef.noSender());

    }
}
