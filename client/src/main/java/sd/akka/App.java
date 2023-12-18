package sd.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.util.Scanner;
import sd.akka.actor.*;
import sd.akka.database.DatabaseManager;
import sd.akka.models.CommonModel;
import sd.akka.utils.Login;
import sd.akka.utils.Utils;
import sd.akka.utils.Message;

public class App {
    private CommonModel commonModel;
    private DatabaseManager databaseManager;

    public App() {
        databaseManager = new DatabaseManager();
        commonModel = new CommonModel(databaseManager);
    }

    public void start(ActorRef customerActor, ActorSystem actorSystem) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\n\nEntrez votre email : ");
            String email = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String password = scanner.nextLine();

            if (!commonModel.isLogged(email, password, "customer")) {
                System.out.println("Email ou mot de passe incorrect");
            } else {
                customerActor.tell(new Login(email, password), ActorRef.noSender());
                while (true) {
                    Utils.menuCustomer();

                    System.out.print("Entrez votre choix: ");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            System.out.println("Option choisie: Voir le solde");
                            customerActor.tell(new Message("Voir le solde", 0.0), ActorRef.noSender());
                            break;
                        case "2":
                            System.out.println("Option choisie: Faire un depot");
                            System.out.print("Entrez le montant à deposer : ");
                            double deposit = scanner.nextDouble();
                            customerActor.tell(new Message("Faire un depot", deposit), ActorRef.noSender());
                            break;
                        case "3":
                            System.out.println("Option choisie: Faire un retrait");
                            System.out.print("Entrez le montant à retirer : ");
                            double withdraw = scanner.nextDouble();
                            customerActor.tell(new Message("Faire un retrait", withdraw), ActorRef.noSender());
                            break;
                        case "4":
                            System.out.println("Option choisie: Consutler l'historique des transactions");
                            customerActor.tell(new Message("Consutler l'historique des transactions", 0.0), ActorRef.noSender());
                            break;
                        case "5":
                            System.out.println("Au revoir !");
                            endSystem(actorSystem);
                            return;
                        default:
                            System.out.println("Choix invalide");
                            break;
                    }
                }
            }
        }
    }

    public void endSystem(ActorSystem actorSystem) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            actorSystem.terminate();
            System.out.println("System terminated.");
        }));
    }
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Customer");
        ActorRef customer = actorSystem.actorOf(CustomerActor.props(actorSystem), "customer");

        App app = new App();
        app.start(customer, actorSystem);

        app.endSystem(actorSystem);
    }
}
