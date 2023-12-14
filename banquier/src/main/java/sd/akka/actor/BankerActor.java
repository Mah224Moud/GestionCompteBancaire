package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import sd.akka.utils.Transaction;
import sd.akka.banker.Banker;
import sd.akka.utils.Login;
import sd.akka.utils.Message;
import sd.akka.utils.Logged;
import sd.akka.utils.CreateCustomer;
import sd.akka.customer.Customer;
import sd.akka.utils.Balance;

public class BankerActor extends AbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorSelection bank;
    private Banker banker;

    private ActorSystem actorSystem;

    public BankerActor(final ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.bank = actorSystem.actorSelection("akka://myActorSystem@127.0.0.1:8810/user/bankActor");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Transaction.class, transaction -> {
                    String message = transaction.getMessage();
                    switch (message) {
                        case "Faire un depot":
                            log.info("Message recu par la banque: {}\n", transaction.getMessage());
                            String depositMsg = banker.deposit(transaction.getAmount(), transaction.getAccountNumber());
                            log.info("\n" + depositMsg);
                            bank.tell(new Message(depositMsg, 0), ActorRef.noSender());
                            break;
                        case "Faire un retrait":
                            log.info("Message recu par la banque: {}\n", transaction.getMessage());
                            String withdrawalMsg = banker.withdraw(transaction.getAmount(),
                                    transaction.getAccountNumber());
                            log.info("\n" + withdrawalMsg);
                            bank.tell(new Message(withdrawalMsg, 0), ActorRef.noSender());
                            break;
                        default:
                            log.info("Message non traité par le client");
                            return;
                    }
                })
                .match(Login.class, login -> {
                    banker = new Banker(login.getEmail(), login.getPassword());
                    System.out.println(banker.toString());
                    Logged logged = new Logged(
                            banker.getEmail(),
                            banker.getName(),
                            banker.getFirstname(),
                            banker.getGender(),
                            banker.getAddress(),
                            banker.getPhone(),
                            "BANQUIER");
                    bank.tell(logged, ActorRef.noSender());

                })
                .match(String.class, s -> {
                    log.info("Received String message: {}", s);
                })
                .match(CreateCustomer.class, createCustomer -> {
                    Customer customer = createCustomer.getCustomer();
                    customer.setBankerId(banker.getId());
                    banker.addCustomer(customer);
                })
                .match(Balance.class, message -> {
                    log.info("\nMessage recu: {}", message.getMessage());
                    banker.seeBalance(message.getAccountNumber());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    public static Props props(ActorSystem actorSystem) {
        return Props.create(BankerActor.class, () -> new BankerActor(actorSystem));
    }
}
