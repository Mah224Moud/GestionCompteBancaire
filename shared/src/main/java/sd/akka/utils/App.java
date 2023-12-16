package sd.akka.utils;

import java.util.List;

import sd.akka.database.DatabaseManager;
import sd.akka.models.BankerModel;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        BankerModel bankerModel = new BankerModel(databaseManager);

        List<History> histories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            histories.add(new History(i + 1, (i + 1) * 2, (i + 1) * 2.5, "Depot", String.valueOf(i + 1)));
        }

        List<History> h = new Histories(histories).getHistories();

        for (History history : histories) {
            bankerModel.addHistory(history);
            System.out.println(history);
        }

    }
}
