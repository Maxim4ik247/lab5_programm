package lab5.comands;

/**
 * класс {@link lab5.comands.ClearCommand} отвечает за выполнение команды, которая очищает коллекцию
 */

import lab5.CollectionManager;

public class ClearCommand implements Runable {
    public static void run(CollectionManager manager) {
        manager.clear();
    }
}