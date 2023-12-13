package sd.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.util.Scanner;
import sd.akka.actor.*;
import sd.akka.database.DatabaseManager;
import sd.akka.models.CommonModel;
import sd.akka.utils.Login;

public class App {
    private CommonModel commonModel;
    private DatabaseManager databaseManager;

    public App() {
        databaseManager = new DatabaseManager();
        commonModel = new CommonModel(databaseManager);
    }

    public void start(ActorRef bankerActor) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\n\nEntrez votre email : ");
            String email = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String password = scanner.nextLine();

            if (!commonModel.isLogged(email, password, "banker")) {
                System.out.println("Email ou mot de passe incorrect");
            } else {
                bankerActor.tell(new Login(email, password), ActorRef.noSender());
            }
        }
    }

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Banker");
        ActorRef banker = actorSystem.actorOf(BankerActor.props(actorSystem), "banker");

        new App().start(banker);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            actorSystem.terminate();
            System.out.println("System terminated.");
        }));
    }
}
