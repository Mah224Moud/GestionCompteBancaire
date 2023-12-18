package sd.akka.utils;

import java.util.List;

public interface Action {
    public String withdraw(double amount, int accountNumber) throws Exception;

    public String deposit(double amount, int accountNumber) throws Exception;

    public void seeBalance(int accountNumber);

    public boolean addHistory(History history);

    public List<History> getHistories(int accountNumber);
}
