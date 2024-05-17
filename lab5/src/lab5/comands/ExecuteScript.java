package lab5.comands;

/**
 * класс {@link lab5.comands.ExecuteScript} отвечает за выполнение команды, которая считывает и исполняет скрипт
 */

import lab5.CollectionManager;
import lab5.ComandExecuter;
import lab5.exceptions.ArgumentException;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExecuteScript {
    public static void run(InputStream in, CollectionManager manager) {
        Scanner scanner = new Scanner(in);
        ComandExecuter comandExecuter = new ComandExecuter(manager);
        while (true){
            String command;
            try {
                command = scanner.nextLine().trim();
            } catch (NoSuchElementException e){
                break;
            }
            try {
                comandExecuter.execute(command, in);
            } catch (ArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
