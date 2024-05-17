package lab5.comands;

import lab5.CollectionManager;
import lab5.exceptions.ArgumentException;

/**
 * класс {@link RemoveLowerCommand} отвечает за выполнение команды, которая удаляет из коллекции все элменты, меньшие, чем заданный
 */

public class RemoveLowerCommand {
    public static void run(CollectionManager manager, String id) {
        int id_int = -1;
        try {
            id_int = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }
        manager.removeLower(id_int);
    }
}
