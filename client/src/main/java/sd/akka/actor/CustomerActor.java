package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import sd.akka.utils.Transaction;
import sd.akka.customer.Customer;
import sd.akka.utils.Login;
import sd.akka.utils.Message;
import sd.akka.utils.Logged;
import sd.akka.utils.HistMessage;
import sd.akka.utils.Histories;
public class CustomerActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorSelection bank;
    private Customer customer;

    private ActorSystem actorSystem;

    public CustomerActor(final ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.bank = actorSystem.actorSelection("akka://myActorSystem@127.0.0.1:8810/user/bankActor");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.class, message -> {
                    String s = message.getMessage();
                    switch (s) {
                        case "Voir le solde":
                            log.info("\nMessage recu par le client: {}", message.getMessage());
                            customer.seeBalance();
                            break;
                        case "Faire un depot":
                            log.info("\nMessage recu par le client: {}", message.getMessage());
                            bank.tell(
                                    new Transaction(message.getMessage(), message.getAmount(),
                                            customer.getAccountNumber()),
                                    ActorRef.noSender());

                            break;
                        case "Faire un retrait":
                            log.info("\nMessage recu par le client: {}", message.getMessage());
                            bank.tell(
                                    new Transaction(message.getMessage(), message.getAmount(),
                                            customer.getAccountNumber()),
                                    ActorRef.noSender());
                            break;
                        case "Consutler l'historique des transactions":
                            log.info("\nMessage recu par le client: {}", message.getMessage());
                            bank.tell(new HistMessage(message.getMessage(), customer.getAccountNumber()), ActorRef.noSender());
                        default:
                            log.info("\nMessage recu par le client: {}", message.getMessage());
                            return;
                    }
                })
                .match(Login.class, login -> {
                    customer = new Customer(login.getEmail(), login.getPassword());
                    System.out.println(customer);
                    Logged logged = new Logged(
                            customer.getEmail(),
                            customer.getName(),
                            customer.getFirstname(),
                            customer.getGender(),
                            customer.getAddress(),
                            customer.getPhone(),
                            "CLIENT");
                    bank.tell(logged, ActorRef.noSender());
                })
                .match(Histories.class, histories -> {
                    log.info("\nMessage recu par le client: L'historique des transactions...\n");
                    histories.seeHistories();
                })
                .match(String.class, s -> {
                    log.info("\n{}", s);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    public static Props props(ActorSystem actorSystem) {
        return Props.create(CustomerActor.class, () -> new CustomerActor(actorSystem));
    }
}
