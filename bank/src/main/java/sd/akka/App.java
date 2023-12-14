package sd.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import sd.akka.actor.*;

public class App {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("myActorSystem");
        ActorRef bankActor = actorSystem.actorOf(BankActor.props(actorSystem), "bankActor");
        bankActor.tell(
                "\nBONJOUR ET BIENVENUE !!!\nLe système est prêt.\nPensez à lancer le banquier puis le client.\n",
                ActorRef.noSender());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            actorSystem.terminate();
            System.out.println("System terminated.");
        }));
    }
}
