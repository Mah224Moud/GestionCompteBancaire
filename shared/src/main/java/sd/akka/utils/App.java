package sd.akka.utils;

import java.util.List;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        List<History> histories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            histories.add(new History(i + 1, (i + 1) * 2, (i + 1) * 2.5, "Depot", String.valueOf(i + 1)));
        }

        Histories h = new Histories(histories);
        h.seeHistories();

    }
}
