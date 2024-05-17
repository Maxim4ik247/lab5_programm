package lab5.comands;

import lab5.CollectionManager;
import lab5.exceptions.ArgumentException;

/**
 * класс {@link RemoveCommand} отвечает за выполнение команды, которая удаляет элемент из коллекции, id которого равен заданному
 */

public class RemoveCommand {
    public static void run(CollectionManager manager, String id){
        int id_int = -1;
        try {
            id_int = Integer.parseInt(id);
        } catch (NumberFormatException e){
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }
        if(manager.getBands().get(id_int) == null)
            throw new ArgumentException("Проверьте правильность введенных аргументов!");

        manager.removeById(id_int);
    }
}
