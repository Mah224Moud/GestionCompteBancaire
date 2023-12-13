package sd.akka;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import sd.akka.actor.*;

public class App {
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("myActorSystem");
		ActorRef bankActor = actorSystem.actorOf(BankActor.props(actorSystem), "bankActor");
        bankActor.tell("\nBONJOUR ET BIENVENUE !!!\nLe système est prêt pensez à lancer le banquier puis le client.\n", ActorRef.noSender());
  
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            actorSystem.terminate();
            System.out.println("System terminated.");
        }));
	}
}
