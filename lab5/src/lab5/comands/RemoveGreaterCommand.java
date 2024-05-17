package lab5.comands;

import lab5.CollectionManager;
import lab5.exceptions.ArgumentException;

/**
 * класс {@link RemoveGreaterCommand} отвечает за выполнение команды, которая уадлляет все элементы, превышающие заданный
 */

public class RemoveGreaterCommand {
    public static void run(CollectionManager manager, String id){
        int id_int = -1;
        try {
            id_int = Integer.parseInt(id);
        } catch (NumberFormatException e){
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }
        manager.removeGreater(id_int);
    }
}
