package UserInterface;

import BussinessLogic.ConsoleLogic;
import BussinessLogic.Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI implements UI {
    Logic logic;

    public ConsoleUI(ConsoleLogic logic) {
        this.logic = logic;
    }

    public String getComand() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            return s;
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода");
        }
        return null;
    }



    public void createCommand( ) {
        logic.startQuery(getComand());
    }

    void prinfMenu() {
        System.out.println("Консольное приложение для работы с БД, для помощи наберите help");
    }

    public void run() {
        prinfMenu();
        while (true) {
            System.out.println();
            createCommand();
        }
    }
}
