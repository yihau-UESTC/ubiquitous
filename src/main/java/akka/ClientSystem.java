package akka;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;

public class ClientSystem {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("clientSystem", ConfigFactory.load().getConfig("ClientSys"));

    }
}
